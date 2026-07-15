### Agenda

* [Intro](#intro) 
* [Setup Workspace](#setup-workspace)
* [Tasklet Implementation](#tasklet-step-implementation)
* [Chunk data Processing](#chunk-data-processing)
  * [Table Sequence](#table-sequence)
  * [Steps for chunk data processing](#steps-for-chunk-data-processing)
  * [properties](#appplicationproperties)
  * [Entity Class](#entity-class)
  * [Repository interface](#repository-interface)
  * [ItemReader](#itemreader)
  * [ItemProcessor](#itemprocessor)
  * [ItemWriter](#itemwriter)
  * [Step](#step)
  * [Job](#job)
  * [Job launcher](#job-launcher)

#### Intro

Job is a task having steps
**Types of steps**:

1. Tasklet step - Doing steps in sequential manner
2. Chunk step-consuming data in chunk(batches) having the following components:
   **Item Reader**—Reading from the source
   **Item Processor**-Processing or modifying data
   **Item Writer**—Writing to the destination
#### Dependency

1. SpringBatch
2. spring data jdbc /Spring data JPA
3. mysql driver

#### Setup workspace

1. We need spring batch and any db as dependencies
2. Add annotation @EnableBatchProcessing on the main class

Mysql Connectivity
Add mysql dependency in pom.xml

```
<dependency>
			<groupId>mysql</groupId>
			<artifactId>mysql-connector-java</artifactId>
			<scope>runtime</scope>
		</dependency>
```

Add credentials in application.properties

```java
spring.datasource.url=jdbc:mysql://localhost:3306/springbatch
spring.datasource.username=root
spring.datasource.password=root
##use .sql file . it will not create table everytime. its liik for table if present its skip
spring.batch.jdbc.initialize-schema=always
```
#### Tasklet step Implementation

1. create job example a Sample job and add annotation @Configuration
2. spring batch provides one class called **JobBuilderFactory** help in creating job. (Autowired)
3. Use a class Job to create a job name provided by spring batch.
4. Spring batch provides one class called **StepBuilderFactory** to create a step
5. after then create a task using Tasklet()

```java
@Configuration
public class SampleJob {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Bean
	public Job firstJob() {
		return jobBuilderFactory.get("First Job")
				.start(firstStep())
				.next(secondStep())
				.build();
	}

	private Step firstStep() {
		return stepBuilderFactory.get("First Step")
				.tasklet(firstTask())
				.build();
	}

	private Tasklet firstTask() {
		return new Tasklet() {

			@Override
			public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
				System.out.println("This is first tasklet step");
				return RepeatStatus.FINISHED;
			}
		};
	}

	private Step secondStep() {
		return stepBuilderFactory.get("Second Step")
				.tasklet(secondTask())
				.build();
	}

	private Tasklet secondTask() {
		return new Tasklet() {

			@Override
			public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
				System.out.println("This is second tasklet step");
				return RepeatStatus.FINISHED;
			}
		};
	}

}
```

Customized Tasklet

```java

@Service
public class SeconTasklet implements Tasklet {
    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        System.out.println("This is second tasklet step");
        return RepeatStatus.FINISHED;
    }
}
```

```java

@Autowired
private SeconTasklet seconTasklet;

private Step secondStep() {
		return stepBuilderFactory.get("Second Step")
				.tasklet(seconTasklet)
				.build();
	}

```

### Chunk data Processing
![SpringBatchOverview.png](../../resources/SpringBatchOverview.png)
#### Table Sequence

1. batch_job_instance - contains number of jobs configured
2. batch_job_execution—Spring batch store information regarding job execution like when started when ended and status
3. batch_job_execution_context - it's a map.
4. batch_step_execution - when step started ended and status
5. batch_step_execution_context-Its a map
6. batch_step_execution_seq-

#### Steps for chunk data processing
1. Create spring starter project using dependencies
   * web-started
   * batch
   * data-jpa
   * mysql-driver
   * lombok (optional)
2. Configure datasource properties in application.properties
3. Keep csv file in resources
4. Create Entity class and Repository interface
5. Create Batch configuration class
   1. Create Item Reader
   2. Create Item Processor
   3. Create Item Writer
   4. Create Step
   5. Create Job

#### Appplication.properties
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/spring_batch
spring.datasource.username=root
spring.datasource.password=root
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

#----------ORM Details-------------------
#To display SQL At console
spring.jpa.show-sql=true
#To Create tables
spring.jpa.hibernate.ddl-auto=create
#To Generate SQL queries
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect

#----------Spring Batch Properties----------
# By default it's true which means all the Spring batches will start executing automatically
spring.batch.job.enabled=true
# Tables for metadata created by Spring Boot (Always, Embedded, Never)
spring.batch.jdbc.initialize-schema=ALWAYS

```

#### Entity Class

we create a class called Product

```java
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    private String productId;
    private String title;
    private String description;
    private Double price;
    private Double discount;
    private Double finalAmount;
}
```

#### Repository interface

```java
public interface ProductRepository extends JpaRepository<Product, Long> {
}
```

### Create Batch configuration class and add the below configurations

#### ItemReader

Read data from source. As needed, multiple Item reader is present in JPA

We are reading csv file
```java
@Bean
public FlatFileItemReader<Product> reader() {
    return new FlatFileItemReaderBuilder<Product>()
            .name("ReadProductData")
            .resource(new ClassPathResource("data.csv"))
            .linesToSkip(1) // skip first line since its header
            .delimited()// line mapper read each line from csv and convert to java object
            .names("productId","title","description","price","discount")
            .strict(false)//Data not available consider as a null value
            .targetType(Product.class)
            // .recordSeparatorPolicy(new BlankLineRecordSeparatorPolicy())
            .build();

}
```
OR
```java
@Bean
    public FlatFileItemReader<Product> reader() {

        FlatFileItemReader<Product> reader= new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource("/data.csv"));
        reader.setLinesToSkip(1);

        reader.setLineMapper(new DefaultLineMapper<>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setDelimiter(DELIMITER_COMMA);
                setNames("productId","title","description","price","discount");
            }});

            setFieldSetMapper(new BeanWrapperFieldSetMapper<>() {{
                setTargetType(Product.class);
            }});
        }});

        reader.setRecordSeparatorPolicy(new BlankLineRecordSeparatorPolicy());

        return reader;
    }
```

#### ItemProcessor

This is used to massage the data before insert

```java
@Bean
public ItemProcessor<Product, Product> processor(){
    return new CustomItemProcessor();
}
```

```java
@Component
public class CustomItemProcessor implements ItemProcessor<Product, Product> {
    @Override
    public Product process(Product item)  {
        Double discount = item.getPrice()*(item.getDiscount()/100.0);
        Double finalAmount= item.getPrice()-discount;
        item.setFinalAmount(finalAmount);

        return item;
    }
}
```

#### ItemWriter

```java
@Autowired
private ProductRepository repository;

@Bean
public ItemWriter<Product> writer(){
    return new RepositoryItemWriterBuilder<Product>()
            .repository(repository)
            .methodName("save")
            .build();


}
```
OR
```java
@Autowired
private ProductRepository repository;

@Bean
    public ItemWriter<Product> writer(){
        return product -> {
            System.out.println("Saving Product Records: " +product);
            repository.saveAll(product);
        };
    }
```

#### Step - if data is not saving in database but read from CSV file then check transactionManager. It should be PlatformTransactionManager
```java
@Bean
public Step steps(
        JobRepository jobRepository,
        PlatformTransactionManager  transactionManager
){
    return new StepBuilder("Step1",jobRepository)
            .<Product,Product>chunk(2,transactionManager)
            .reader(reader())
            .processor(processor())
            .writer(writer())
            .build();

}
```
Or Inject
```java
@Bean
    public Step steps(
            JobRepository jobRepository,
            PlatformTransactionManager transactionManager,
            ItemReader<Product> reader,
            ItemProcessor<Product,Product> processor,
            ItemWriter<Product> writer
    ){
        return new StepBuilder("Step1",jobRepository)
                .<Product,Product>chunk(10,transactionManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();

    }
```
#### JobExecutionListener
```java
@Component
public class JobCompletionNotificationImpl implements JobExecutionListener {
   private Logger logger = LoggerFactory.getLogger(JobCompletionNotificationImpl.class);

    @Override
    public void beforeJob(JobExecution jobExecution) {
        logger.info("My Job execution started");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if(jobExecution.getStatus() == BatchStatus.COMPLETED)
            logger.info("My Job execution ended");
    }
}
```

#### Job

```java
@Bean
public Job jobBean(JobRepository jobRepository,
                   JobCompletionNotificationImpl listener,
                   Step steps){
    return new JobBuilder("FirstJob",jobRepository)
            .listener(listener)
            .start(steps)
            .build();
}
```

#### Job launcher

Two types: 
1. Manual trigger of job - Rest API
2. Scheduling of a job—Using Spring Scheduler

For this first thing is change the property in application.properties
```properties
# By default it's true which means all the Spring batches will start executing automatically
spring.batch.job.enabled=false
```

1. Rest Controller
```java
@RestController
public class ProductEndPoint {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job jobBean;

    @GetMapping("/loadProduct")
    public void loadProduct() throws Exception{
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("start-at", System.currentTimeMillis()).toJobParameters();

        jobLauncher.run(jobBean,jobParameters);

    }

}
```
2. CommandLineRunner
```java
@Component
public class JobRunner implements CommandLineRunner {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired   
    private Job jobA;

    @Override    
    public void run(String... args) throws Exception {

    JobParameters jobParameters =
          new JobParametersBuilder()
            .addLong("time", System.currentTimeMillis())
            .toJobParameters();

    jobLauncher.run(jobA, jobParameters);
    System.out.println("JOB Execution completed!");
    }
}
```
