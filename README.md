# Junior Java Developer Journey

This project documents my progress from learning Java fundamentals
to building backend applications.

## Goal

Learn to think like a developer, not only memorize syntax.

## Current stage

Java Core and problem-solving practice.

## Learning principle

First understand the problem.
Then choose the tool.
Only then write the code.


## Progress

### Day 5

Implemented the first storage layer for the application.

Features:
- Created `ProductRepository`
- Added internal product storage using `List<Product>`
- Implemented `add(Product product)`
- Implemented `getAll()`
- Tested repository from `Main`

### What I learned

- Difference between `List` and `ArrayList`
- Why repositories own collections
### Reflection

Today I stopped memorizing Java and started thinking about software design.
- How responsibility affects where methods belong
- Thinking about business logic before writing Java code
## Day 6

### Business task
Prevent adding duplicate products to the repository.

### Features
- Added product existence validation
- Implemented `containsProduct(String name)`
- Prevented duplicate products by throwing `IllegalArgumentException`

### What I learned
- Every method should have one responsibility.
- Business rules belong close to the code that manages the data.
- A validation algorithm should be designed before writing Java code.
- Java syntax is easier to understand after the business logic is clear.

### Reflection
Today I realized that my biggest challenge is not programming logic but Java syntax. 
Building the algorithm first and then translating it into Java makes learning much easier.

Day 7 – Object Representation (toString())
Business Task

QA reported that products were displayed as technical object references (Product@6d06d69c) instead of readable information. The task was to make product output understandable for warehouse employees.

Features Implemented
Implemented toString() in the Product class.
Learned how System.out.println() automatically calls toString().
Displayed products in a human-readable format.
Verified that products must be added to the repository before they appear in the product list.
What I Learned
Every Java object inherits toString() from the Object class.
If toString() is not overridden, Java prints the class name and hash code.
The Product class is responsible for representing itself as text.
Creating an object and storing it in a repository are two separate operations.
println(object) automatically invokes object.toString().
Reflection

Today I learned that objects should be responsible not only for storing their own data but also for presenting themselves. I also understood that simply creating an object does not automatically add it to a collection.
Data must be explicitly stored in the repository before it can be retrieved.

## Day 8

### Business Task
Implement product search by name for warehouse employees and store managers.

### Features
- Added `findByName(String name)` method.
- Implemented sequential search through the product repository.
- Returned `Optional<Product>` instead of `null`.
- Tested both successful and unsuccessful search scenarios.

### What I Learned
- A search method should return the object itself, not just a boolean.
- `Optional` is a container that may or may not contain a value.
- `Optional.of()` represents a found object.
- `Optional.empty()` represents the absence of a result.
- `get()` should only be called after checking `isPresent()`.

### Reflection
Today I finally understood why `Optional` exists. It is not just another Java class but a safe way to express that a value may be missing.
I also realized that business requirements determine whether a missing result should be handled with `Optional` or with an exception.

## Day 9 - Product Filtering

Today I added price-based filtering to `ProductRepository`.

Implemented:
- `findCheaperThan(BigDecimal maxPrice)`
- `findMoreExpensiveThan(BigDecimal minPrice)`
- filtering products using `BigDecimal.compareTo()`
- testing repository methods in `Main`
- checking results in the console

Example:

```java
repository.findCheaperThan(new BigDecimal("100"));
repository.findMoreExpensiveThan(new BigDecimal("100"));

What I practiced:

working with List<Product>
for-each loops
BigDecimal
compareTo()
returning a new filtered list without changing the original list
testing repository behavior manually through Main


Day 10 - Service Layer and Separation of Responsibilities

Today I introduced a ProductService layer and separated business logic from data storage.

Implemented:

created ProductService
injected ProductRepository through the constructor
made the repository dependency final
moved price filtering logic from ProductRepository to ProductService
used repository.getAll() to access products from the service
separated repository responsibilities from business logic

Current responsibilities:

ProductRepository

stores products
adds products
returns all products
finds a product by name

ProductService

filters products by price
contains business logic related to products

What I practiced:

Single Responsibility Principle (SRP)
constructor dependency injection
separation between Repository and Service layers
working with object references
understanding final with reference types

Important:
final prevents a reference from being reassigned to another object,
 but it does not make the referenced object immutable.
        
        
## Day 11 - Repository Abstraction and Dependency Inversion

Today I refactored the repository layer to depend on an abstraction instead of a concrete implementation.

Implemented:

* converted `ProductRepository` from a class to an interface
* kept repository method contracts inside `ProductRepository`
* created `InMemoryProductRepository`
* implemented `ProductRepository` in `InMemoryProductRepository`
* moved the in-memory product storage and method implementations to `InMemoryProductRepository`
* updated `Main` to use the repository interface
* kept `ProductService` dependent on the `ProductRepository` abstraction

Current structure:

```text
ProductService
      ↓
ProductRepository
    (interface)
      ↑
      │ implements
InMemoryProductRepository
      ↓
List<Product>
```

What I practiced:

* Java interfaces
* `implements`
* programming to an interface
* abstraction vs implementation
* Dependency Inversion Principle
* constructor dependency injection
* separating business logic from data access implementation

Example:

```java
ProductRepository repository =
        new InMemoryProductRepository();

ProductService service =
        new ProductService(repository);
```

This allows the repository implementation to be replaced later without changing the business logic in `ProductService`.
Day 12 - Introduction to Unit Testing with JUnit 5

Today I started adding unit tests to the project using JUnit 5.

Implemented:

added JUnit 5 dependency to Maven
created InMemoryProductRepositoryTest
wrote a test for adding a valid product
wrote a test for adding a duplicate product
tested expected exceptions with assertThrows()
intentionally broke the add() method to verify that the test detects incorrect behavior

Example test scenarios:

GIVEN → an empty repository
WHEN  → a valid product is added
THEN  → the repository contains exactly this product
GIVEN → a repository already containing "Mouse"
WHEN  → another product named "Mouse" is added
THEN  → IllegalArgumentException is thrown

What I practiced:

JUnit 5
@Test
assertEquals(expected, actual)
assertThrows()
Arrange → Act → Assert
Given → When → Then
positive and negative test scenarios
test naming convention: <methodUnderTest>_<state>_<expectedBehavior>
understanding that one test represents one behavior scenario, but can contain multiple related assertions

Important lesson:

A passing test does not prove that the whole method works correctly. It only proves that the specific scenario covered by that test currently behaves as expected.

By intentionally removing duplicate validation from add(), I saw that the valid-product test still passed while the duplicate-product test failed.
This demonstrated why different behavior scenarios need separate tests.

## Day 14 - Testing Repository Search with Optional

Today I continued practicing unit testing with JUnit 5.

Implemented:
- added tests for `findByName()`
- tested successful product search
- tested search for a non-existing product
- practiced testing methods that return `Optional`
- learned not to test private helper methods directly

Test scenarios:

### Existing product

GIVEN:
- repository contains `Mouse`

WHEN:
- `findByName("Mouse")` is called

THEN:
- the returned `Optional` contains a value
- the returned product is `Mouse`

### Non-existing product

GIVEN:
- repository does not contain `iPhone`

WHEN:
- `findByName("iPhone")` is called

THEN:
- `Optional.empty()` is returned

What I practiced:
- `assertTrue()`
- `Optional.isPresent()`
- `Optional.isEmpty()`
- `Optional.get()`
- combining related assertions in one test
- testing public behavior instead of private implementation details

Example:

```java
Optional<Product> result = repository.findByName("Mouse");

assertTrue(result.isPresent());
assertEquals(mouse, result.get());

## Day 15 - Boundary Testing for Product Filtering

Today I practiced testing boundary conditions for price filtering in `ProductService`.

Implemented:

* created tests for `findCheaperThan()`
* tested a normal filtering scenario with multiple matching products
* tested the exact price boundary
* separated service tests from repository tests
* verified that `ProductService` is tested independently from `InMemoryProductRepository`

Test scenarios:

### Normal filtering

GIVEN:

* `Mouse` costs `29.99`
* `Keyboard` costs `59.99`
* `Monitor` costs `199.99`

WHEN:

* `findCheaperThan(60.00)` is called

THEN:

* two products are returned
* the result contains `Mouse`
* the result contains `Keyboard`

### Boundary case

GIVEN:

* `Mouse` costs `29.99`
* `Keyboard` costs exactly `30.00`
* `Monitor` costs `199.99`

WHEN:

* `findCheaperThan(30.00)` is called

THEN:

* only `Mouse` is returned
* a product with the exact boundary price is not included

What I practiced:

* testing `List<Product>`
* checking collection size
* checking collection contents
* boundary testing
* verifying the difference between `<` and `<=`
* separating `ProductServiceTest` from `InMemoryProductRepositoryTest`
* testing one method with multiple behavior scenarios

Example:

```java
List<Product> result =
        service.findCheaperThan(new BigDecimal("30.00"));

assertEquals(1, result.size());
assertEquals(mouse, result.get(0));
```

Important lesson:

One method can require several different tests.

A normal scenario checks that filtering works in general.

A boundary scenario checks that the exact condition is implemented correctly. For `findCheaperThan()`, a product with the same price as the limit must not be included.

Service logic should be tested in `ProductServiceTest`, while repository behavior should be tested in `InMemoryProductRepositoryTest`.

## Day 17 — Logging with Log4j2

Today I learned how logging works in a Java application using Log4j2.

### What I learned

- Added Log4j2 dependencies to Maven:
    - `log4j-api`
    - `log4j-core`
- Created `log4j2.xml` in `src/main/resources`.
- Learned that a `Logger` is used to record application events.
- Created a logger using:

```java
private static final Logger logger =
        LogManager.getLogger(Main.class);
Learned about logging levels:
INFO — normal application events
WARN — something unexpected happened, but the application can continue
ERROR — an operation failed
Learned that the Root level controls which log messages are recorded.
Learned that an Appender defines where logs are sent.
Configured a File appender instead of a console appender.
Successfully wrote logs to logs/app.log.
Log4j2 configuration
<File name="File" fileName="logs/app.log">
    <PatternLayout
        pattern="%d{HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n"/>
</File>
<Root level="error">
    <AppenderRef ref="File"/>
</Root>

With level="error", only ERROR messages are written to the log file.

Example
logger.info("Application started");
logger.warn("This is WARN");
logger.error("This is ERROR");

With the current ERROR level, only:

This is ERROR

is written to:

logs/app.log
Key takeaway

Logging flow:

Logger → Log level → Appender → logs/app.log

Log4j2 allows application events and errors
 to be recorded without using System.out.println().
