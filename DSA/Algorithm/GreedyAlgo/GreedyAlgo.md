>A Greedy Algorithm makes the best possible choice at the current moment, 
> hoping that these local optimal choices will lead to a globally optimal solution.


## How to Recognize Greedy Problems

When reading a problem, ask:

### Step 1 : Is it asking for a minimum or maximum?

### Step 2 : Can I sort the input?

### Step 3 : Can I repeatedly pick the "best" option?

### Step 4 : Will that choice remain valid forever?

### Step 5 : Can I prove that taking the local optimum leads to the global optimum?

If most answers are yes, consider a greedy approach.


## Example 1:

You have coins: 1, 2, 5, 10, 20, 50, 100, 200, 500

Make ₹143 using the minimum number of coins.

### Step 1: Is it asking for a minimum or maximum

Ask your self:
> What am I optimizing?

Answer
> minimum number of coins.

Whenever you see words like:

* Minimum
* Maximum
* Least
* Highest
* Largest

your brain should immediately think:

> "This is an optimization problem."

### Step 2: Can I sort the input?

You have coins: 1, 2, 5, 10, 20, 50, 100, 200, 500
For Greedy, we usually want: 500,200,100,50,20,10,5,2,1

Ask your self:
> Can I sort them?

Answer
> Yes

### Step 3: Can I repeatedly pick the "best" option?
```
Can I define a rule
such that
every time I follow it,
I never regret that decision later?
```
Algorithms must work for all valid inputs, not just the one in front of you.

Target is 143

Largest possible coin 100

Target 43

Largest possible coin 20

Target 23

Largest possible coin 20

Target 3

Largest possible coin 2

Target 1

Largest possible coin 1

Total coins 5

Best repeat option here is
>Take the largest possible coin.


### Step 4: Will that choice remain valid forever?

Suppose we picked ₹100 first.

Could we later regret choosing ₹100?

Let's test it.

Alternative:

Don't choose ₹100.

Instead : 50, 50, 20, 20, 2, 1

Total coins 6

Another option : 20, 20, 20, 20, 20, 20, 20, 2, 1

Total coins : 9 (Much worst)

So taking ₹100 didn't block us from reaching a better solution.

Try same for 20, 2, 1

> Each greedy choice remains valid and never needs to be undone.

### Step 5: Can I prove that local optimum gives global optimum?

Target is 143

Have a ₹100 coin available.

Can any combination of smaller coins replace one ₹100 coin using fewer coins?

To make ₹100 without a ₹100 coin:
```
50 + 50 = 2 coins

20 + 20 + 20 + 20 + 20 = 5 coins

10 × 10 = 10 coins
```
Every alternative uses more coins.

Therefore, whenever possible, taking ₹100 cannot make the solution worse.

The same logic holds for:

* ₹50
* ₹20
* ₹10
* ₹5
* ₹2

Because of this property, every greedy choice is safe.

This is why greedy works for the standard Indian coin system.


### Final Decision

Let's evaluate the checklist.

| Question                                    | Answer | Reason                                                         |
| ------------------------------------------- | ------ | -------------------------------------------------------------- |
| Is it asking for minimum/maximum?           | ✅ Yes  | Minimum number of coins                                        |
| Can I sort the input?                       | ✅ Yes  | Descending coin values                                         |
| Can I repeatedly pick the best option?      | ✅ Yes  | Largest valid coin each step                                   |
| Will that choice remain valid forever?      | ✅ Yes  | Larger denominations never need to be replaced by smaller ones |
| Can I prove local optimum = global optimum? | ✅ Yes  | Standard coin denominations have the greedy-choice property    |


All five answers are Yes.

So this is a strong signal that a greedy algorithm is appropriate.



## Example 2:

You have coins: 4,3,1

Make ₹6 using the minimum number of coins.

### Step 1 : Is it asking for a minimum or maximum?

This is an optimization problem.

### Step 2 : Can I sort the input?
Yes we can sort input in decreasing order

### Step 3 : Can I repeatedly pick the "best" option?

Target 6

Largest coin 4

Target 2

Largest coin 1

Target 1

Largest coin 1

Total coin 3

this is not best option as 3+3=6 total coin is 2

FAILED.

### Step 4 : Will that choice remain valid forever?

let's not take 4 instead take 3

remaining 3 take 3 again

total coin is 2

so Greedy fail here too

### Step 5 : Can I prove that taking the local optimum leads to the global optimum?
local optimum is 4+1+1 =6
Global Optimum is 3+3 =6

Greedy fail again

Final Decision - Greedy algo will not work here



## Time complexity

Sorting  -> O(n log n)

Traversal -> O(n)

Total -> O(n log n)

## Template

```java
sort(data);

for(each item){

    if(canTake(item)){
        take(item);
    }

}
```

## Patterns

### Pattern 1: Interval Problems

Examples

* Merge Intervals
* Non-overlapping Intervals
* Meeting Rooms
* Insert Interval

Usually sort by

Start Time or End Time

### Pattern 2: Activity Selection

* Choose maximum activities.

Sort by - Finish Time

### Pattern 3: Scheduling

Examples

* Job Scheduling
* CPU Scheduling
* Task Scheduler

Usually sort by

* Deadline
* Profit
* End Time

### Pattern 4: Minimum/Maximum

Examples

* Minimum Platforms
* Minimum Arrows
* Maximum Units

Usually
```
Sort
↓
Pick Best
↓
Repeat
```
### Pattern 5: Fractional Knapsack

Sort by

Value / Weight

Take highest ratio first.

### Pattern 6: Huffman Coding

Repeatedly combine the two smallest frequencies.

Uses

Priority Queue (Min Heap)
