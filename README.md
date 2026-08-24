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
