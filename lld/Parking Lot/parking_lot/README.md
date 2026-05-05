# 🚗 Parking Lot System — Low Level Design (LLD)

---

# 📌 Overview

This project demonstrates a **scalable and extensible Parking Lot System design** using:

* **Object-Oriented Design (OOP)**
* **Design Patterns (Strategy, Facade/Orchestrator)**
* **Clean Architecture principles**
* **Thread-safe operations**

The goal is to build a system that is:

* Easy to extend
* Easy to maintain
* Close to real-world systems

---

# 🎯 Features

* Multiple vehicle types (Bike, Car, Truck)
* Multiple parking spot types
* Efficient spot allocation
* Multiple pricing strategies:

    * Hourly
    * Flat
* Multiple payment modes:

    * UPI
    * Card
    * Cash
* Entry and Exit gates
* Ticket generation
* User interaction flow (CLI / API ready)

---

# 🧠 Design Principles

| Principle                        | Description                                               |
| -------------------------------- | --------------------------------------------------------- |
| **OOP (Polymorphism)**           | Avoids `if/switch` logic by pushing behavior into classes |
| **SRP (Single Responsibility)**  | Each class has one responsibility                         |
| **OCP (Open/Closed Principle)**  | Add new features without modifying existing code          |
| **Separation of Concerns**       | Clear separation between pricing, payment, and allocation |
| **Composition over Inheritance** | Behavior is injected via strategies                       |

---

# 🧾 Unified UML Diagram — Parking Lot System (with Fields & Methods)

```id="uml-full-detailed"
                                 +-----------------------------+
                                 | ParkingLot                  |
                                 |-----------------------------|
                                 | - List<ParkingFloor> floors |
                                 |-----------------------------|
                                 | + getFloors()               |
                                 +-------------+---------------+
                                               |
                                               | has-a
                                               v
                                 +-----------------------------+
                                 | ParkingFloor                |
                                 |-----------------------------|
                                 | - Map<Class, Queue> spots   |
                                 |-----------------------------|
                                 | + getSpots(type)            |
                                 +-------------+---------------+
                                               |
                                               | has-a
                                               v
                                 +-----------------------------+
                                 | ParkingSpot                 |
                                 |-----------------------------|
                                 | - String id                 |
                                 | - boolean occupied          |
                                 | - Vehicle vehicle           |
                                 |-----------------------------|
                                 | + canFit(Vehicle)           |
                                 | + occupySpot(Vehicle)       |
                                 | + releaseSpot()             |
                                 +-------------+---------------+
                                               ^
        ---------------------------------------|---------------------------------------
        |                                      |                                      |
        | is-a                                 | is-a                                 | is-a
        v                                      v                                      v
+----------------+                 +----------------+                 +----------------+
| SmallSpot      |                 | MediumSpot     |                 | LargeSpot      |
|----------------|                 |----------------|                 |----------------|
|                |                 |                |                 |                |
+----------------+                 +----------------+                 +----------------+


                                 +-----------------------------+
                                 | Vehicle                    |
                                 |-----------------------------|
                                 | - String vehicleNumber      |
                                 |-----------------------------|
                                 | + getNumber()               |
                                 | + getAllowedSpots()         |
                                 +-------------+---------------+
                                               ^
        ---------------------------------------|---------------------------------------
        |                                      |                                      |
        | is-a                                 | is-a                                 | is-a
        v                                      v                                      v
+----------------+                 +----------------+                 +----------------+
| Bike           |                 | Car            |                 | Truck          |
|----------------|                 |----------------|                 |----------------|
|                |                 |                |                 |                |
+----------------+                 +----------------+                 +----------------+


                                 +-----------------------------+
                                 | Ticket                     |
                                 |-----------------------------|
                                 | - String id                |
                                 | - Vehicle vehicle          |
                                 | - ParkingSpot spot         |
                                 | - entryTime                |
                                 |-----------------------------|
                                 | + getVehicle()             |
                                 | + getSpot()                |
                                 | + getEntryTime()           |
                                 +-------------+---------------+
                                               |
                    ---------------------------+---------------------------
                    |                                                      |
                    | has-a                                                | has-a
                    v                                                      v
          +------------------+                                  +------------------+
          | Vehicle          |                                  | ParkingSpot      |
          +------------------+                                  +------------------+


                                 +-----------------------------+
                                 | EntryGate                  |
                                 |-----------------------------|
                                 | - SpotManager manager      |
                                 | - ParkingLot lot           |
                                 |-----------------------------|
                                 | + park(Vehicle)            |
                                 +-------------+---------------+
                                               |
                                               | has-a
                                               v
                                 +-----------------------------+
                                 | SpotManager                |
                                 |-----------------------------|
                                 | - SpotAllocationStrategy   |
                                 |-----------------------------|
                                 | + allocate(Vehicle)        |
                                 +-------------+---------------+
                                               |
                                               | uses
                                               v
                       +--------------------------------------+
                       | SpotAllocationStrategy               |
                       |--------------------------------------|
                       | + findSpot(Vehicle, Floors)          |
                       +-------------+------------------------+
                                     ^
                                     |
                                     | is-a
                                     |
                       +--------------------------------------+
                       | NearestSpotStrategy                  |
                       |--------------------------------------|
                       | + findSpot(...)                      |
                       +--------------------------------------+


                                 +-----------------------------+
                                 | ExitGate                    |
                                 |-----------------------------|
                                 | - PricingService            |
                                 |-----------------------------|
                                 | + getAmount(Ticket)         |
                                 | + makePayment(Ticket)       |
                                 +-------------+---------------+
                                               |
                -------------------------------+-------------------------------
                |                                                               |
                | has-a                                                         | has-a
                v                                                               v
   +-----------------------------+                               +-----------------------------+
   | PricingService              |                               | PaymentService              |
   |-----------------------------|                               |-----------------------------|
   | - PricingStrategy           |                               | - PaymentStrategy           |
   |-----------------------------|                               |-----------------------------|
   | + calculate(Ticket)         |                               | + process(amount)           |
   +-------------+---------------+                               +-------------+---------------+
                 |                                                             |
                 | uses                                                        | uses
                 v                                                             v
   +-----------------------------+                               +-----------------------------+
   | PricingStrategy             |                               | PaymentStrategy             |
   |-----------------------------|                               |-----------------------------|
   | + calculateAmount(Ticket)   |                               | + pay(amount)               |
   +-------------+---------------+                               +-------------+---------------+
                 ^                                                             ^
                 |                                                             |
       -------------------------                                -------------------------------
       |                       |                                |        |        |
       | is-a                  | is-a                           | is-a   | is-a   | is-a
       v                       v                                v        v        v
+----------------+   +----------------+                +--------+  +--------+  +--------+
| HourlyPricing  |   | FlatPricing    |                |  UPI   |  | Card   |  | Cash   |
+----------------+   +----------------+                +--------+  +--------+  +--------+


                                 +-----------------------------+
                                 | ParkingLotOrchestrator     |
                                 |-----------------------------|
                                 | - EntryGate                |
                                 | - ExitGate                 |
                                 |-----------------------------|
                                 | + start()                  |
                                 | + parkVehicle()            |
                                 | + exitVehicle()            |
                                 +-------------+---------------+
                                               |
                 -------------------------------+-------------------------------
                 |                                                               |
                 | has-a                                                         | has-a
                 v                                                               v
          +-------------+                                                 +-------------+
          | EntryGate   |                                                 | ExitGate    |
          +-------------+                                                 +-------------+
```

---


# 🏗️ High-Level Architecture

```
User (CLI / UI / API)
        ↓
Orchestrator (Flow Control)
        ↓
EntryGate / ExitGate
        ↓
------------------------------------
| Domain + Strategy Layer          |
|----------------------------------|
| SpotManager                      |
| AllocationStrategy               |
| PricingStrategy                  |
| PaymentStrategy                  |
------------------------------------
        ↓
ParkingLot → Floors → Spots
```

---

# 🧩 Core Components

## 🚗 Vehicle (Polymorphic Design)

* Represents different vehicle types
* Each vehicle defines:

    * Which parking spots it can use

### Why this design?

* Eliminates hardcoded rules
* Adding a new vehicle requires **no modification** in existing code

---

## 🅿️ ParkingSpot (Polymorphic Design)

* Represents parking slots
* Types:

    * Small
    * Medium
    * Large

### Responsibilities:

* Check if a vehicle can fit
* Assign/remove vehicles
* Handle concurrency

---

## 🏢 ParkingFloor

* Maintains available spots
* Uses efficient data structures for fast lookup

### Key Idea:

* Spots are grouped by type
* Enables **O(1) allocation** instead of scanning all spots

---

## 🎟 Ticket

* Represents a parking session
* Contains:

    * Vehicle details
    * Assigned spot
    * Entry time

---

## 🎯 Spot Allocation Strategy

### Pattern: **Strategy Pattern**

### Responsibility:

* Decide **which spot to allocate**

### Current Implementation:

* Nearest Spot Strategy

### Why?

* Easily replaceable in future:

    * Distance-based allocation
    * Priority-based allocation

---

## 💰 Pricing Strategy

### Pattern: **Strategy Pattern**

### Responsibility:

* Calculate parking cost

### Implementations:

* Hourly pricing
* Flat pricing

### Key Benefit:

* Add new pricing rules without changing existing code

---

## 💳 Payment Strategy

### Pattern: **Strategy Pattern**

### Responsibility:

* Handle different payment modes

### Implementations:

* UPI
* Card
* Cash

---

## 🧠 SpotManager

* Delegates spot allocation to strategy
* Acts as abstraction over allocation logic

---

## 🚪 EntryGate

* Allocates parking spot
* Generates ticket

---

## 🚪 ExitGate

Handles exit flow:

1. Calculate amount
2. Show user
3. Accept payment
4. Free parking spot

---

## 🧾 PricingService

* Uses pricing strategy to compute cost

---

## 💳 PaymentService

* Uses payment strategy to process payment

---

## 🎮 Orchestrator (Most Important Layer)

### Pattern: **Facade / Orchestrator**

### Responsibility:

* Controls full user flow
* Coordinates between components

### Why needed?

* Keeps business logic clean
* Decouples UI from system logic
* Makes system API-ready

---

# 🔄 System Flow

## 🚗 Parking Flow

```
User → Orchestrator → EntryGate → SpotManager → AllocationStrategy → Spot Assigned → Ticket Generated
```

---

## 🚪 Exit Flow

```
User → Orchestrator → ExitGate
      → PricingService → Show Amount
      → User selects payment mode
      → PaymentService → Process payment
      → Spot Freed
```

---

# 🔥 Design Patterns Used

| Pattern                   | Where Used                   | Benefit                       |
| ------------------------- | ---------------------------- | ----------------------------- |
| **Strategy Pattern**      | Allocation, Pricing, Payment | Flexibility and extensibility |
| **Facade / Orchestrator** | Orchestrator Layer           | Simplifies interaction        |
| **Polymorphism**          | Vehicle, ParkingSpot         | Removes conditional logic     |
| **SRP (SOLID)**           | All classes                  | Better maintainability        |
| **OCP (SOLID)**           | Strategies                   | Easy feature addition         |

---

# ⚡ Key Design Decisions

## ❌ No Enums for Behavior

* Avoid rigid logic
* Use polymorphism instead

---

## ✅ Queue-Based Spot Management

* Fast allocation (O(1))
* No full iteration

---

## ✅ Separate Pricing and Payment

* Pricing = calculate cost
* Payment = collect money

---

## ✅ Orchestrator Layer

* Centralized flow control
* Clean and modular architecture

---

# 🔐 Concurrency Handling

* Each parking spot is **thread-safe**
* Prevents double allocation

---

# 🚀 Extensibility

You can easily add:

### New Vehicle

* Electric Vehicle
* VIP Vehicle

---

### New Spot Type

* EV Charging Spot

---

### New Pricing Strategy

* Surge pricing
* Weekend pricing

---

### New Payment Mode

* Wallet
* Net Banking

---

# 🏁 Conclusion

This design:

* ✔ Follows **clean OOP principles**
* ✔ Uses **industry-standard design patterns**
* ✔ Is **highly extensible**
* ✔ Avoids **hardcoding and conditionals**
* ✔ Is **interview-ready and production-ready**

---
# 🖥️ Console Walkthrough (Sample Run)

Below is a sample interaction showing how the system behaves when the program runs.


```text

-----------------------------------------------------------------
Welcome to Parking Lot!!!!
-----------------------------------------------------------------

Select vehicle type - BIKE | CAR | TRUCK: 
rikshau
Invalid vehicle type...Please try again
Select vehicle type - BIKE | CAR | TRUCK: 
car
Enter Vehicle Number: 
mh15ga5106

------------------------------------
----- TICKET -----
Ticket ID: 7422060e-37ba-4c73-a7f1-dd97ccd19d07
Vehicle: MH15GA5106
Spot: F0-M-010
Entry Time: 2026-05-06T00:44:56.042641100
-----------------------------------------

Do you want to Exit? YES | NO
yes
Amount to be Paid: 20.0
Select Payment Mode - UPI | CASH | CARD: 
ATM
Invalid Payment Mode...Please try again
Select Payment Mode - UPI | CASH | CARD: 
upi
UPI Payment done for amount: 20.0
Spot F0-M-010 has been released.
Vehicle Exited Successfully!!
```
---
