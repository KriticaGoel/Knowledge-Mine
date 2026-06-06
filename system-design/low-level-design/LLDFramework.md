
### LLD Thinking Framework

Step 1: Identify Actors

**Ask**: Who is using the system?

| System      | Who is using      |
|-------------|-------------------|
| Pen         | Writer            |
|             | Manufacturer      |
| Parking Lot | Driver            |
|             | Parking Attendant |
|             | Admin             |
| BookMyShow  | User              |
|             | Theatre           |
|             | Admin             |

Step 2: Write Use Cases (Important)

Think about actions.

| System      | Use Case        |
|-------------|-----------------|
| Pen         | Write           |
|             | Refill ink      |
|             | Check ink level |
| Parking Lot | Park vehicle    |
|             | Remove vehicle  |
|             | Generate ticket |
|             | Calculate fee   |

Step 3: Extract Nouns

Every noun is a candidate class.

Driver parks vehicle in parking slot and receives ticket

Nouns: (These become candidate classes.)

Driver

Vehicle

ParkingSlot

Ticket

ParkingLot

Step 4: Extract Verbs

Verbs become methods.

| Verb                  | Method                           |
|-----------------------|----------------------------------|
| Driver parks vehicle. | parkingLot.parkVehicle(vehicle); |
| Generates ticket.     | ticketService.generateTicket();  |
| Calculates fee        | feeStrategy.calculateFee();      |

Step 5: Find Relationships

Ask:Has-A ?

ParkingLot has many ParkingSlots

Is-A ?

Car is a Vehicle

Bike is a Vehicle

Step 6: Identify Variations

This is where Design Patterns appear.

Ask: What may change in future?

Pattern should be outcome of problem

Object creation varies. Therefore Factory fits.

Summary:

1. Clarify requirements
2. List use cases
3. Build MVP
4. Identify entities
5. Define relationships
6. Add services
7. Find variations
8. Apply patterns
9. Draw class diagram


Pattern Detection Sheet

| Situation                | Pattern   |
| ------------------------ | --------- |
| Multiple algorithms      | Strategy  |
| Object creation varies   | Factory   |
| One object many states   | State     |
| One event many listeners | Observer  |
| Tree hierarchy           | Composite |
| Wrapper behaviour        | Decorator |
| Single shared instance   | Singleton |
| Sequential commands      | Command   |

MVP Rule

Whenever stuck, ask:

What is the smallest version that works?

Parking Lot MVP:

Need only:

Vehicle

ParkingSlot

Ticket

ParkingLot

Don't think about:

Payment

Multiple floors


EV charging

Reservations

First make MVP work.



