1. download Active mq
2. extract
3. go to bin folder and copen cmd
4. run command activemq start

create producer who writes a message on active mq

Controller class responsible to receive a message from user and send to the service layer

```java

@RestController
public class SendController {
    @Autowired
    private SendService sendService;

    @GetMapping("/send/{name}")
    public String sendName(@PathVariable("name") String name) {
        sendService.sendName(name);
        return "Hello " + name;
    }
}
```

Servcie layer responsible to receive message controller and send to activemq using jmstemplate and destination name

```java

@Component
public class SendService {

    private JmsTemplate jmsTemplate;

    @Autowired
    public SendService(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void sendName(String name) {
        jmsTemplate.convertAndSend("myQueue", name);
    }
}
```

Consumer class which is another project who read the message from active mq

```java

@Component
public class MeesageConsumerService {

    @JmsListener(destination = "myQueue")
    public void listenMessge(String name) {
        System.out.println("Listening Messge " + name);
    }

}
```

properties in application.properties

```
spring.activemq.broker-url=tcp://localhost:61616
spring.activemq.user=admin
spring.activemq.password=admin
```