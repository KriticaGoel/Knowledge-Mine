# 30 Day Practice Plan

## Week 1 — Pattern Vision Training

Goal: learn to see problems.

### Day 1 — Notification System

- **Problem**
  - Send Email, SMS, Push notifications
  - New types may be added
- **Find**
  - What changes?
  - What smells exist with if-else?
- **UML**

```text
Client
|
NotificationService
|
NotificationStrategy
/    |     \
Email SMS Push
```

- **Pattern spotting**
  - Spring's JavaMailSender
  - Why interface instead of concrete class?
- **Target pattern**: Strategy

### Day 2 — Payment Processing

- Card
- UPI
- Wallet
- Net Banking
- **Add**
  - Cashback logic changes often
- **Draw UML**
- **Spot**
  - Strategy
  - Open/Closed Principle
- **Spring exercise**
  - Inject payment implementations.

### Day 3 — Document Generator

- PDF
- CSV
- Excel
- **Creation becoming messy**
  - `if(type.equals("PDF"))`
- **UML**

```text
Client
|
DocumentFactory
/   |    \
PDF CSV Excel
```

- **Pattern**: Factory
- **Spot in Spring**: BeanFactory

### Day 4 — User Registration

- User has 15 fields
- Some optional
- **Code smell**
  - `new User(...)`
- **UML**
  - `UserBuilder → User`
- **Pattern**: Builder
- **Spring spotting**
  - Why RestTemplateBuilder exists?

### Day 5 — Coffee Shop

- Coffee
- +Milk
- +Cream
- +Chocolate
- **Question**
  - Avoid subclass explosion.
- **UML**

```text
Coffee
|
Decorator
/   |   \
Milk Cream Chocolate
```

- **Pattern**: Decorator
- **Spot**: Spring Security filter chain

### Day 6 — Logging Utility

- **Requirement**
  - Only one logger instance.
- **UML**
  - `Logger`
  - `getInstance()`
- **Pattern**: Singleton
- **Spring spotting**
  - Default bean scope?

### Day 7 — Reflection Day

- Take a Spring project.
- **Find**
  - Factory
  - Singleton
  - Proxy
- Write notes only.
- No coding.

## Week 2 — UML Muscle Building

Goal: think in relationships.

### Day 8 — Food Delivery App

- **Objects**
  - Restaurant
  - Order
  - Menu
  - DeliveryAgent
- **Questions**
  - What changes frequently?
  - Delivery strategy?
- Draw UML.

### Day 9 — Inventory Management

- Close to your experience.
- **Objects**
  - Warehouse
  - Inventory
  - Product
  - StockValidator
- **Changing**
  - validation rules
- **Pattern**: Strategy
- **Spring challenge**
  - Use interface-based validators.

### Day 10 — Email Template Utility

- **Objects**
  - Template
  - EmailSender
  - TemplateLoader
- **Changing**
  - email formats
- **Pattern candidates**
  - Prototype
  - Factory

### Day 11 — OMS Integration

- **Scenario**
  - Multiple vendors:
	- Oracle OMS
	- SAP
	- Custom OMS
  - Different request formats.
- **UML**

```text
OMSAdapter
/      \
Oracle  SAP
```

- **Pattern**: Adapter
- Real-life relevance: your work

### Day 12 — Bluetooth Printer

- **Problem**
  - Printer APIs differ.
- **Pattern**: Adapter
- **Challenge**
  - Convert printer formats.

### Day 13 — Report Generation

- **Steps fixed**
  - fetch
  - transform
  - export
- Some variations.
- **Pattern**: Template Method
- **Spot in Spring**: JdbcTemplate

### Day 14 — Reflection Day

- Take your current project.
- **Identify**
  - Builder
  - Strategy
  - Factory

### Elevator System

### CarRental System (ZoomCar)

### Logging System

### Snake and Ladder

### Vending Machine

### ATM

### ChessGame

### cricbuzz

### Inventory Management

## Week 3 — Spring Pattern Detective

Goal: learn from framework internals.

### Day 15

- **Investigate**
  - Why ApplicationContext exists?
- **Pattern**: Factory

### Day 16

- **Investigate**
  - Autowired by interface.
- **Question**
  - Why not concrete classes?
- **Pattern**: Dependency inversion + Strategy

### Day 17

- **Investigate**
  - AOP
- **UML**

```text
Client
|
Proxy
|
Service
```

- **Pattern**: Proxy

### Day 18

- **Investigate**
  - Spring events
- **Pattern**: Observer
- **Create**: `OrderPlacedEvent`

### Day 19

- **Investigate**
  - JdbcTemplate
- **Pattern**: Template Method

### Day 20

- **Investigate**
  - Feign clients
- **Pattern**: Proxy

### Day 21 — Reflection Day

- Write: `"Patterns hidden inside Spring"`

## Week 4 — Real System Design Gym

Goal: automatic thinking.

### Day 22 — Parking Lot

- **Objects**
  - ParkingSpot
  - Vehicle
  - Ticket
- **Changing**
  - pricing logic
- **Pattern**: Strategy

### Day 23 — ATM System

- **Objects**
  - Account
  - Card
  - Transaction
- **Changing**
  - withdraw flow
- **Pattern**: State

### Day 24 — Chess

- **Changing**
  - piece movement rules
- **Pattern**: Strategy

### Day 25 — Ride Booking

- **Changing**
  - fare calculation
- **Pattern**: Strategy

### Day 26 — File Upload Service

- **Storage**
  - S3
  - Local
  - Azure
- **Pattern**
  - Strategy + Factory

### Day 27 — Transaction Correction Utility

- Very close to your experience.
- **Actions**
  - rollback
  - retry
  - repair
- **Pattern**: Command

### Day 28 — Inventory Reconciliation

- **Changing**
  - validation algorithms
- **Pattern**: Strategy

### Day 29 — Design From Scratch

- **Build**
  - Movie Booking System
- **Steps**
  - entities
  - relationships
  - changing behavior
  - UML
  - patterns

### Day 30 — Final Boss

- **Design**
  - Mini E-commerce
- **Features**
  - payments
  - discounts
  - notifications
  - inventory
  - shipping
- **Find**
  - Builder
  - Strategy
  - Factory
  - Observer
  - Adapter

No internet.

Only brain.