# Week 8: CodInClub Quiz & Concept Solutions

## Quiz Questions & Answers

### Question 1
**Question:** A system processes various types of financial transactions (deposits, withdrawals, transfers). Common interface for initiation and status tracking. Which design principle and OOP concept combination primarily enables this flexibility and extensibility?
**Answer: C - Abstraction and Runtime Polymorphism**
*Explanation:* Defining a common transaction interface (Abstraction) and allowing concrete transaction implementations to be executed dynamically via polymorphic dispatch (Runtime Polymorphism) allows new transactions to be added without modifying the core processing engine (Open/Closed Principle).

---

### Question 2
**Question:** A function `print_vehicle_details` accepts a `Vehicle` object (Car or Motorcycle) and calls `calculate_fuel_efficiency` without knowing the specific type at compile time. Which OOP concepts are directly demonstrated?
**Answer: B - Runtime Polymorphism (also dynamic dispatch and inheritance)**
*Explanation:* The method call is resolved at runtime based on the actual object type rather than the compile-time reference type.

---

### Question 3
**Question:** A Book object in a library tracks availability ('Available' -> 'Borrowed' -> 'Available'). Which UML behavioral model is best suited to represent these distinct states and transitions?
**Answer: D - State Diagram**
*Explanation:* State machine / state diagrams are designed to model lifecycle states of an individual object and the transitions triggered by events/guards.

---

### Question 4
**Question:** A Customer can place multiple Orders, but an Order is always placed by exactly one Customer. If a Customer is removed, all their Orders should also be removed. Which UML relationship types are most appropriate?
**Answer: C - Composition**
*Explanation:* Composition represents a strong "has-a" relationship with lifecycle dependency where child objects (Orders) cannot exist without the parent (Customer) and are cascade deleted.

---

### Question 5
**Question:** A FoodOrder object encapsulates its LineItems. LineItems are created and managed exclusively by FoodOrder and cease to exist if FoodOrder is cancelled or completed. Which relationship describes this?
**Answer: D - Composition**
*Explanation:* The lifecycle of LineItem is strictly owned and bound to the lifetime of FoodOrder.

---

### Question 6
**Question:** PaymentProcessor interacts with payment methods via IPaymentMethod interface. When a new method is introduced, PaymentProcessor requires no changes. Which OOP principle is most directly applied?
**Answer: C - Abstraction**
*Explanation:* Coding to an interface (Dependency Inversion / Abstraction) decouples the processor from concrete payment implementations.

---

### Question 7
**Question:** During an online exam, flow of actions (start, answer, submit, evaluate) and sequence of interactions between Student, Examination, and Submission objects are shown. Which UML diagram type is best suited?
**Answer: C - Sequence Diagram**
*Explanation:* Sequence diagrams visualize message flow and time-ordered interactions between cooperating lifelines.

---

### Question 8
**Question:** Employee `annual_salary` cannot be negative. A `set_salary` method validates before updating internal state. Which OOP concepts are effectively demonstrated?
**Answer: B, D - Encapsulation and Maintaining Valid Object State (Data Hiding)**
*Explanation:* Hiding the field behind a setter that enforces invariants prevents corrupt/invalid state.

---

### Question 9
**Question:** FileLogger implements Logger interface. What is the UML relationship between FileLogger class and Logger interface?
**Answer: D - Realization**
*Explanation:* A class implementing an interface realizes the contract in UML (dashed line with an open hollow triangle).

---

### Question 10
**Question:** A Course can have a maximum of 50 Students. A Student can enroll in multiple Courses. Correct multiplicity representation:
**Answer: C - Course 1 --- 0..50 Student, Student 1 --- 0..* Course**
*Explanation:* From Course perspective, it holds between 0 and 50 students; from Student perspective, an individual can enroll in 0 or more courses.

---

## Concept Questions & Answers

### Question 1: Invariants & Encapsulation in ShoppingCart
**Answer:**
An **invariant** is a condition or business rule that must always hold true for an object to remain in a valid, trustworthy state throughout its lifetime. In a `ShoppingCart`, the invariant is:
$$\text{total\_price} = \sum_{i} \text{price}(\text{LineItem}_i)$$
If `total_price` were public or exposed through an unconstrained setter, external code could set an arbitrary value (e.g., negative or mismatched with the cart items), violating the invariant and creating an inconsistent state.
**Encapsulation enforces invariants by:**
1. Making internal state (`lineItems`, `totalPrice`) private (data hiding).
2. Allowing state changes *only* through controlled mutator methods (e.g., `addItem(Item, qty)`, `removeItem(Item)`).
3. Recalculating or synchronizing `totalPrice` inside these mutator methods so the invariant is guaranteed to hold before and after every operation.

---

### Question 2: Composition vs. Inheritance for SmartDevice Capabilities
**Answer:**
If optional capabilities like Camera, GPS, and Bluetooth were modeled via inheritance, every combination of features would require its own class (`SmartDeviceWithCamera`, `SmartDeviceWithCameraAndGPS`, `SmartDeviceWithBluetoothAndGPS`, etc.). With $N$ optional features, this results in a combinatorial explosion of $2^N$ subclasses. Furthermore, inheritance is static—a device's capabilities cannot change at runtime.

**Composition solves this by:**
1. Modeling capabilities as independent components conforming to a common interface (`Capability` or `HasCamera`, `HasGPS`).
2. Allowing a `SmartDevice` to hold a collection/map of capabilities (`List<Capability>`).
3. Providing dynamic addition/removal of capabilities at runtime via `device.addCapability(new GPSCapability())` without altering class definitions or creating rigid hierarchies.

---

### Question 3: UML Sequence Diagram for Checkout Flow
**Answer:**
A UML Sequence Diagram models dynamic collaboration along a horizontal time-ordered sequence:
1. **Lifelines:** Represent participants vertically with dashed lines: `CheckoutManager`, `PaymentGateway`, and `BankService`.
2. **Message Ordering (Top to Bottom):**
   - Synchronous call `processPayment(amount)` sent from `CheckoutManager` to `PaymentGateway` (solid arrow with filled head).
   - An activation box opens on `PaymentGateway`.
   - `PaymentGateway` sends synchronous message `authorizeTransaction(cardDetails, amount)` to `BankService`.
   - An activation box opens on `BankService`.
   - `BankService` processes and sends a reply message (dashed arrow with open head) `authorizationResult(SUCCESS)` back to `PaymentGateway`.
   - `PaymentGateway` sends a return message `paymentStatus(PAID)` back to `CheckoutManager`.
This diagram clearly depicts synchronous waiting, call nesting, message direction, and lifecycle boundaries.

---

### Question 4: Abstraction & Extensibility in NotificationService
**Answer:**
`NotificationService` achieves loose coupling by depending on an abstraction (e.g., `NotificationChannel` interface with `send(message)`) rather than concrete classes like `EmailSender` or `SmsSender`.
- The client application interacts solely through `NotificationService` and the `NotificationChannel` contract.
- To introduce a new communication channel (e.g., `WhatsAppNotificationChannel` or `SlackNotificationChannel`), developers only create a new class implementing `NotificationChannel`.
- The client code and existing service remain completely untouched and uncompiled, strictly adhering to the **Open/Closed Principle** (open for extension, closed for modification).

---

### Question 5: Aggregation vs. Composition in University, Department, and Professor
**Answer:**
Both Aggregation and Composition represent "whole-part" relationships, but they differ fundamentally in **ownership** and **lifecycle dependency**:

1. **University and Department — Composition (Strong Whole-Part):**
   - *Lifecycle Dependency:* A `Department` cannot exist without the `University`. If the `University` is dissolved, all its `Department` objects are destroyed with it.
   - *UML Notation:* Solid black diamond on the `University` side.

2. **Department and Professor — Aggregation (Weak Whole-Part):**
   - *Lifecycle Dependency:* A `Professor` is part of a `Department`, but has an independent existence. If the `Department` or `University` ceases to exist, the `Professor` continues to exist and can join another institution.
   - *UML Notation:* Hollow white diamond on the `Department` side.
