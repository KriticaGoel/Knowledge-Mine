```
Problem Statement
↓
Questions
↓
Requirement Discovery (understanding)
↓
Use Cases
↓
Domain Model
↓
High-Level Design
↓
Low-Level Design
↓
Code
```
## Requirement Gathering
5 Layers of Questions to complete Requirement Gathering
### Layer 1: Understand the Business

**Ask**:

Why are we building this?
What business problem are we solving?
Who will use it?
What happens if we don't build it?
What is the expected outcome?
Example

**Problem**:

Build a Missing Transaction Recovery System.

**Questions**:

Why do transactions go missing?
How often does it happen?
What is the business impact?
Is recovery manual today?

Without understanding business, you may build something nobody needs.

### Layer 2: Understand Inputs and Outputs

**Ask:**

**Inputs**
What data comes in?
Where does it come from?
What format?
Is it real-time or batch?

**Outputs**
What should be produced?
Who consumes it?
Where is it stored?

**Example**

For your recovery project:

Input:

ORI
ORC
ORD
JT
JF reports

Output:

Missing transaction list
Recovery execution
Audit report

### Layer 3: Find Constraints

Great engineers think in constraints.

**Ask:**

**Volume**
100 records?
1 million?
100 million?
**Performance**
Response time?
Batch window?
**Reliability**
Can data be lost?
Retry requirements?
**Security**
Sensitive data?
Encryption needed?
**Example**

**Questions**:

Can the same transaction appear twice?
Can reports arrive late?
What if DB is down?
What if recovery fails midway?

These questions uncover hidden requirements.

### Layer 4: Find Edge Cases

This is where senior engineers separate themselves.

**Ask**:

**Missing Data**
What if report is empty?
**Duplicate Data**
What if same transaction appears twice?
**Invalid Data**
What if transaction id is null?
**Partial Failure**
What if only half records are processed?
**Timing Issues**
What if file arrives late?
**Example**

For your project:

ORI available but ORC missing
JT format changed
Transaction exists in DB but not report
Recovery already executed

These become interview gold.

### Layer 5: Discover Domain Concepts

**Ask**:

What are the entities?
What are the relationships?
What changes frequently?
What stays stable?

**Example**:

Your project:

Entities:

Report
Transaction
Classification Rule
Recovery Job
Execution Result

Now design patterns start appearing naturally.

## Use Cases


If you can clearly describe:

Actor
Trigger
Steps
Expected result

for every major flow, your use cases are reasonably complete.

## HLD

When is HLD Complete?

After requirements and use cases, you should be able to answer:

Components
Report Reader
↓
Classification Engine
↓
Missing Transaction Detector
↓
Recovery Engine
↓
Audit Module
Data Flow
Report
↓
Parse
↓
Classify
↓
Detect Missing
↓
Recover
↓
Generate Report
Database
What tables?
What relationships?
Integrations
DB
External APIs
File system
Scheduler

If you can draw these at a whiteboard level, your HLD is mostly complete.
