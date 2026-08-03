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
