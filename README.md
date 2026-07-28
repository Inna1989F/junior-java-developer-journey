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
