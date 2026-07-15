1. [Unit Testing](#unit-test-case)
   1. [Create Test File in STS](#create-a-test-file-in-sts)
   2. [Basic Annotation](#basic-annotation)
      1. ExtendWith
      2. DisplayName
      3. Test
      4. Disable
      5. BeforeEach and AfterEach
      6. BeforeAll and AfterAll
      7. Condition Annotations
         1. Operating system based annotation
            1. EnableOnOs or DisableOnOs
         2. JRE version based annotation
            1. EnableOnJre or DisableOnJre
            2. EnableOnJreRange or DisableOnJreRange
         3. System Property Conditions
            1. EnabledIfSystemProperty or DisabledIfSystemProperty
         4. Environment variable condition
            1. EnabledIfEnvironmentVariable or DisabledIfEnvironmentVariable
         5. Custom condition
            1. EnabledIf or DisabledIf
      8. Controlling the execution order of test
      9. Repeated Test
      10. Nested Test
   3. [Assertions](#assertions)
      1. Assertion and why needed
      2. AssertEquals
      3. AssertNotEquals
      4. WorkingWithArray
      5. AssertTrue
      6. AssertFalse
      7. WorkingWithGroupedAssertions
      8. Performance driven Testing 
      9. Timeout Preemptively
      10. Verify expected exception using Assertions
      11. Writing Assumptions
   4. [Parameterized Test](#parameterized-test)
      1. ValueSource 
      2. EnumSource 
      3. MethodSource 
      4. CsvSource 
      5. CsvFileSource
   5. Tagging and Filtering test cases


#### Unit test case

##### Create a test file in STS

1. Open STS and the Package Explorer (or Project Explorer).
2. Locate the Java class you want to test (e.g., src/main/java/com/example/demo/Calculator.java).
3. Right‑click the class file → New → JUnit Test Case.
4. If you don’t see “JUnit Test Case”: choose New → Other… → type “JUnit” → JUnit → JUnit Test Case.

##### Basic Annotation
1. **@ExtendWith**
2. **@DisplayName**
3. **@Test** - method is always public and void and no parameter
4. **@Disabled**

```java
@ExtendWith(MockitoExtension.class)
@DisplayName("Payment Controller Unit Test")
class PaymentControllerTest {
	
	
	@Test
	@DisplayName("Positive test case")
	void successes() {
		
	}
	
	@Test
	@DisplayName("Negative Test Case")
	void failed() {
		fail();
	}
	
	@Test
	@Disabled
	@DisplayName("Ignore Test Case")
	void ignored() {
		
	}

}
```

5. **@BeforeEach** - Execute before every test to be executed.
6. **@AfterEach** - Execute after every test to be executed.
```java
class CalculatorTest {

    @BeforeEach
    void setUp()  {
        System.out.println("Initialization done");
    }

    @Test
    @DisplayName("Addition Test")
    void testAdd() {
        System.out.println("Addition Test executed");

    }

    @Test
    @DisplayName("Subtraction Test")
    void testSubtract() {
        System.out.println("Subtraction Test executed");

    }

    @AfterEach
    void cleanUp()  {
        System.out.println("Clear the resources");
    }
}
```

```terminaloutput
Initialization done
Addition Test executed
Clear the resources
Initialization done
Subtraction Test executed
Clear the resources
```

7. **BeforeAll**  - Execute as a first method and only once and always be static

8. **After All** -  Execute as a last method and only once and always be static
```java
class CalculatorTest {
	
	@BeforeAll
	public static void openDatabase() {
		System.out.println("Database connection opened");
	}
	
	@BeforeEach
	void setUp() {
		System.out.println("Initialization done");
	}
	
	@Test
	@DisplayName("Addition Test")
	void testAdd() {
		System.out.println("Addition Test executed");
		
	}
	
	@Test
	@DisplayName("Subtraction Test")
	void testSubtract() {
		System.out.println("Subtraction Test executed");
		
	}
	
	@AfterEach
	void cleanUp() {
		System.out.println("Clear the resources");
	}
	
	@AfterAll
	public static void closeDatabase() {
		System.out.println("Database connection closed");
	}   
}
```

```terminaloutput
Database connection opened
Initialization done
Addition Test executed
Clear the resources
Initialization done
Subtraction Test executed
Clear the resources
Database connection closed
```

Question: why BeforeALl usually need static while others not?

Explanation :
* Default test lifecycle in JUnit Jupiter is PER_METHOD (a new test class instance is created for each test method). Because of that:
* There is no single shared test-instance available before any test methods run (JUnit creates instances per test). The test engine cannot reliably call an instance method "once for the whole class" if instances are created per test.
* Marking the method static gives JUnit a single, class-level method to call once before all tests run (and once after all tests).
* In short: static = no instance required, so it works with the default PER_METHOD lifecycle.

When you can make them non-static

* If you annotate the class with @TestInstance(Lifecycle.PER_CLASS), JUnit creates a single instance of the test class and re-uses it for all test methods. Because one instance exists for the whole class, @BeforeAll and @AfterAll may be instance methods (non-static).
* @TestInstance(Lifecycle.PER_CLASS) at the class level and then non-static @BeforeAll / @AfterAll.

**Best practices and cautions** - Prefer PER_METHOD (the default) when you want strong test isolation: each test gets its own instance and cannot accidentally share mutable state with others.

9. Condition Annotations:
   1. Operating system based annotation
      1.  **EnableOnOs(OS.WINDOWS)**
      2.  **DisableOnOs(OS.WINDOWS)**
   2. JRE version based annotation
      1.  **EnableOnJre(JRE.JAVA_14)**
      2.  **EnableOnJre({JRE.JAVA_10,JRE.JAVA_21})**  - Enable on multiple version specific in {}
      3.  **DisableOnJre(JRE.JAVA_14)** - Disable on jre 14
      4.  **DisableOnJre({JRE.JAVA_10,JRE.JAVA_21})** - Disable on multiple version specific in {}
      5.  **EnableOnJreRange(min = JRE.JAVA_10, max = JRE.JAVA-21)** - Enable between mentioned range. Only one parameter is also valid like min = JRE.JAVA-21 that means all JAVA version starting from 21 is valid.
      6.  **DisableOnJreRange**
   3. System Property Conditions  - System.getProperties gives all system properties of JVM. the we can enable the test case based on thosed proeprties
      1.  **EnabledIfSystemProperty(named="USER" , matches ="Kritica")**  - test case run of parameter and value matches with system properites.
      2.  **DisableIfSystemProperty**
   
```java
public class SystemPropertyConditions {

    @Test
    public void getSystemProperties() {
        System.out.println("System Properties: " + System.getProperties());
        System.out.println("Java Version: " + System.getProperty("java.version"));
    }

    @Test
    @EnabledIfSystemProperty(named = "os.name", matches = ".*Windows.*")
    public void runOnlyOnWindows() {
        System.out.println("This test runs only on Windows OS");
    }

}

```
```terminaloutput
This test runs only on Windows OS
```
      
   4. Environment variable condition
     1.  **EnabledIfEnvironmentVariable(named="USER" , matches ="Kritica")** - EnabledIfEnvironmentVariable named contain environment variable key and matches contains its value. If name and key matches then only test cases execute otherwise not. 
   5. Custom condition - Used to enable or disable the test method based on the boolean value that custom method returns (based on some calculations).
      1. EnabledIf 
      2. DisabledIf

```Java
public class CustomCondition {
	
	@Test
	@EnabledIf("isConditionMet")
	public void customCondition() {
		System.out.println("This is a custom condition example");
	}

	public boolean isConditionMet() {
		// Custom logic to determine if the condition is met
		int a = 10;
		int b = 20;
		if (a + b > b) {
			return true;
		} else {
			return true;
		}
	}

}

```
```terminaloutput
This is a custom condition example
```
10. Controlling the execution order of test - Helps in defining order in which test method executes

```java
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ControlOrderExecution {

	@Test
	@Order(1)
	public void test1() {
		System.out.println("Test 1 executed");
	} 
	
	@Test
	@Order(2)
	public void test2() {
		System.out.println("Test 2 executed");
	}
	@Test
	@Order(3)
	public void test3() {
		System.out.println("Test 3 executed");
	}
	@Test
	@Order(4)
	public void test4() {
		System.out.println("Test 4 executed");
	}
}
```

```terminaloutput
Test 1 executed
Test 2 executed
Test 3 executed
Test 4 executed
```
11. Repeated Test - Its helps in execute any test multiple times. Number of times we need to give as a argument.

```java
@RepeatedTest(5)
	public void repeatTest() {
		System.out.println("This test is repeated 5 times");
	}
```
```terminaloutput
This test is repeated 5 times
This test is repeated 5 times
This test is repeated 5 times
This test is repeated 5 times
This test is repeated 5 times
```

12.  Nested Test-


##### Assertions
Assertions help software programmers verify the output of a particular function or part of software.

We write assertion to test our assumptions around a particular piece of code.

When the assertion is executed it is assumed to be true, If the assumption is not correct then the test fails.

We cannot validate the output with an if and else statement. Since it make test case pass always; either code execute if statement or else statement

```java
@Test
public void assertwithIfElse() {
    Calculations calculatorTest = new Calculations();
    if(calculatorTest.add(4,5) ==10)
    {
        System.out.println("Test Passed");
    }
    else
    {
        System.out.println("Test Failed");
    }
}
```
Test case passed with output Test Failed

1. AssertEquals - assertEquals(expected, actual, message)

**Failed Test case**
```java
@Test
	public void asserts() {
		Calculations calculatorTest = new Calculations();
		Assertions.assertEquals(8, calculatorTest.add(4,5),"Addition of(4,5) is failed");		
		System.out.println("Addition Test Passed");
		
	}
```
Test failed - Addition of(4,5) is failed ===> excepted:<8> but was<9>

**Success Test case**
```java
@Test
	public void asserts() {
		Calculations calculatorTest = new Calculations();
		Assertions.assertEquals(9, calculatorTest.add(4,5),"Addition of(4,5) is failed");		
		System.out.println("Addition Test Passed");
		
	}
```
```terminaloutput
Addition Test Passed
```

Alternative way
```java
@Test
	public void assertsAlternative() {
		Calculations calculatorTest = new Calculations();
		assert(calculatorTest.add(4,5) ==19) : "Addition Test Failed";
		System.out.println("Addition Test Passed");
		
	}
```
Test failed - Addition Test Failed

```java
@Test
	public void assertsAlternative() {
		Calculations calculatorTest = new Calculations();
		assert(calculatorTest.add(4,5) ==9) : "Addition Test Failed";
		System.out.println("Addition Test Passed");
		
	}
```

```terminaloutput
Addition  Test Passed
```

2. AssertNotEquals  - work opposite of AssertEqual
```java
@Test
		public void assertsNotEqual() {
			Calculations calculatorTest = new Calculations();
			Assertions.assertNotEquals(9, calculatorTest.add(4,5),"Addition of(4,5) is failed");		
			System.out.println("Addition Test Passed");
			
		}
```

test case failed as actual and expected values are equal
3. Working with Arrays

**Success Test case**
```java
@Test
	public void assertsWithArraysDemo() {
		int[] expectedArray = {1,2,3,4,5};
		int[] actualArray = {1,2,3,4,5};
		//Assertions.assertEquals(expectedArray, actualArray,"Arrays are not equal");//compare adddress
		Assertions.assertArrayEquals(expectedArray, actualArray,"Arrays are not equal");
	}
```

**Failed Test Case**
```java
@Test
	public void assertsWithArraysDemo() {
		int[] expectedArray = {1,2,3,4,5};
		int[] actualArray = {2,1,3,4,5};
		//Assertions.assertEquals(expectedArray, actualArray,"Arrays are not equal");//compare adddress
		Assertions.assertArrayEquals(expectedArray, actualArray,"Arrays are not equal");
	}
```

Arrays are not equal ==> array contents differ at index [0], expected: <1> but was: <2>

4. AssertTrue  - used to check Boolean Values

```java
@Test
	public void assertsTrueDemo() {
		boolean condition = 5 < 3;
		Assertions.assertTrue(condition,"Condition is false");
	}
```
```output
 Condition is false ==> expected: <true> but was: <false>
	
```
```java
@Test
	public void assertsTrueDemo() {
		String str1 = "Hello";
		Assertions.assertTrue(str1.startsWith("H"),"Condition is false");
		System.out.println("Condition is true");
	}
```
```terminaloutput
Condition is true
```
5. AssertFalse -- opposite of AssertTrue
6. WorkingWithGroupedAssertions - help to run all asertions once in case of one or multi failure and provide output one end

```java
@Test
	public void assertsGroup() {
		Calculations calculatorTest = new Calculations();
		Assertions.assertAll("Addition Tests",
				() -> Assertions.assertEquals(9, calculatorTest.add(4,5),"Addition of(4,5) is failed"),
				() -> Assertions.assertEquals(1, calculatorTest.add(-4,4),"Addition of(-4,4) is failed"),
				() -> Assertions.assertEquals(8, calculatorTest.add(-4,-4),"Addition of(-4,-4) is failed")
				);
		
	}
```
7. Performance driven Testing - We can check the performance by TimeOut annotation. It can be used at class level and method level. if timeout annotation added at class level then its will be applied on all method within the class unless method aslo have timeout annotation to override class timeout.

```java
@Timeout(5)
public class PerformanceDriven {


    @Test
    public void performanceTest() throws InterruptedException {
        Thread.sleep(4000); // Pass as time out at class level is 5 milliseconds
    }

    @Test
    public void performanceTest1() throws InterruptedException {
        Thread.sleep(6000); // Failed as time out at class level is 5 milliseconds
    }


    @Test
    //@Timeout(8)
    @Timeout(value = 8000, unit = TimeUnit.MILLISECONDS)
    public void performanceTest2() throws InterruptedException {
        Thread.sleep(7000); // Pass as time out at class level is 5 milliseconds but method have 8 milliseconds
    }
    // Alternative way to chek timeout but this will not override class annotation. That's why below testcase failed
    @Test
    public void performanceTest3() throws InterruptedException {
        Assertions.assertTimeout(Duration.ofMillis(7000), () -> {
            Thread.sleep(6000); // Simulating a long-running task
        });
    }
}
```
8. Timeout Preemptively - same as above one. only difference is it not executed on main thread and one timeout arrived and task not completed its sent error.
```java
@Test
	public void performanceTest4() throws InterruptedException {
		Assertions.assertTimeoutPreemptively(Duration.ofMillis(7000), () -> {
			Thread.sleep(3000); // Simulating a long-running task
		});
	}
```
9. Verify expected exception using Assertions
```java
@Test
	public void validateExpectedException() {
		Assertions.assertThrows(ArithmeticException.class, () -> {
			int result = 10 / 0; // This will throw ArithmeticException
		});
	}
```
#### Summary

| Assertion                                    | Description              |
| -------------------------------------------- | ------------------------ |
| `assertEquals(expected, actual)`             | Checks equality          |
| `assertNotEquals(expected, actual)`          | Checks not equal         |
| `assertTrue(condition)`                      | Checks boolean condition |
| `assertFalse(condition)`                     | Checks boolean condition |
| `assertNull(object)`                         | Checks if null           |
| `assertNotNull(object)`                      | Checks if not null       |
| `assertThrows(Exception.class, () -> {...})` | Checks exception         |

10. Assumptions - 
1. Assumptions is like if condition when assumption is passed test case will execute if assumption is failed the test case will aborted and mark abort in reports. 
2. Assumption class provide method to create assumption.
3. In If and else test case never aborted. but by using assumption test case skipped or aborted.
4. Assertion True or False, failed the complete test case. However Assumption true or false skipped/ignore the test case in case of failure.
5. we have two method in Assumption class assumptionTrue and assumptionFalse
```java
@Test
public void assumptionTest() {
    Assumptions.assumeTrue("Kritica".startsWith("k"));
    System.out.println("This test runs only if the assumption is true");
}

@Test
public void assumptionTestFalse() {
    Assumptions.assumeFalse("Kritica".startsWith("k"));
    System.out.println("This test runs only if the assumption is false");
}

@Test
public void assertionTestFalse() {
    Assertions.assertFalse("Kritica".startsWith("K"));
    System.out.println("This test runs only if the assumption is false");
}
```
![assumption.png](../../resources/assumption.png)


#### Parameterized Test
Test the scenario on bases of parameters or different set of arguments
* @ValueSource
* @EmptySource
* @NullSource
* @NullAndEmptySource
* @EnumSource
* @MethodSource
* @CsvSource
* @CsvFileSource


1. Value Source Parameterized Test : Used to test a methods for different values
```java
@ParameterizedTest
	@ValueSource(strings = {"Apple", "Ball", "Avocado"})
	@EmptySource
	@NullSource
	@NullAndEmptySource
	public void parameterizedTestExample(String variable) {
		Assertions.assertTrue(variable.isEmpty(), "Variable starts with A");
		System.out.println("Parameterized test executed with value: " + variable);
	}
```
2. Enum Source Parameterized Test : Modes are Exclude, Include, Match all, match any
```java
@ParameterizedTest
	@EnumSource(Values.class)
	public void parameterizedTestWithEnum(Values day) {
		Assertions.assertTrue(StringUtils.isEmpty(day), "Enum value should not be null");
		System.out.println("Parameterized test executed with enum value: " + day);
	}
	
	
	enum Values{
		ONE, TWO, THREE, FOUR, FIVE
	}

@ParameterizedTest
@EnumSource(value=Values.class, mode = EnumSource.Mode.EXCLUDE, names={"TWO", "FOUR"})
public void parameterizedTestWithEnum1(Values day) {
    Assertions.assertTrue(StringUtils.hasText(day.toString()), "Enum value should not be null");
    System.out.println("Parameterized test executed with enum value: " + day);
}


enum Values{
    ONE, TWO, THREE, FOUR, FIVE
}

```
3. MethodSource : Parameterized method
```java
@ParameterizedTest
	@MethodSource("stringProvider")
	public void useMethodSourceString(String parameter) {

		Assertions.assertTrue(StringUtils.hasText(parameter), "Parameter should not be null or empty");
		System.out.println("Parameterized test executed with method source value: " + parameter);
	}

static Stream<String> stringProvider() {
		return Stream.of("apple", "banana");
	}
```
4. CsvSource :
```java
@ParameterizedTest
@CsvSource( {"apple,banana", "carrot,durian"})
public void testWithCsvSource(String first, String second) {
	Assertions.assertTrue(StringUtils.hasText(first), "First parameter should not be null or empty");
	Assertions.assertTrue(StringUtils.hasText(second), "Second parameter should not be null or empty");
	System.out.println("Parameterized test executed with CSV source values: " + first + ", " + second);
}
```
5. CsvFileSource :
```java
@ParameterizedTest
@CsvFileSource(resources = "/test-data.csv", numLinesToSkip = 1)
public void testWithCsvSource1(String first, String second) {
	Assertions.assertTrue(StringUtils.hasText(first), "First parameter should not be null or empty");
	Assertions.assertTrue(StringUtils.hasText(second), "Second parameter should not be null or empty");
	System.out.println("Parameterized test executed with CSV source values: " + first + ", " + second);
}
```
6. Tagging and Filtering test cases - we can do one method level and class level both. Multiple tags can be added if features can map to multiple tags.

##############################Implement in Project##################################
Dependencies needed

1. Junit  - found in spring-boot-starter-test
2. Mockito - found in spring-boot-starter-test
3. AssertJ - found in spring-boot-starter-test
4. H2 Database

we have to write test case of all layers separately and without creating dependencies.
 
Let start with Controller
