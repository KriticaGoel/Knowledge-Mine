# Workflow Explanation

## Objective

Allow users to manage their stock portfolio using natural language.

Instead of manually opening Google Sheets and searching stock prices, users simply chat with the AI.

---

# Workflow Flow

```
User
 │
 ▼
Chat Trigger
 │
 ▼
AI Agent
 │
 ├── OpenAI Chat Model
 ├── Memory
 ├── Google Sheets (Read)
 ├── Marketstack
 └── Google Sheets (Update)
```

---

# Execution Steps

## Step 1

The user sends a message.

Example:

```
Update AAPL price.
```

---

## Step 2

The Chat Trigger receives the request.

---

## Step 3

The AI Agent analyzes the request.

It asks itself:

- Do I need portfolio data?
- Do I need live market data?
- Do I need to update Google Sheets?

---

## Step 4

The AI uses the OpenAI model to reason.

The model decides:

```
I need:

✓ Read Google Sheet
✓ Fetch latest stock price
✓ Update Google Sheet
```

---

## Step 5

The AI reads portfolio data from Google Sheets.

Example:

```
Stock    Price

AAPL     208
TSLA     305
```

---

## Step 6

The AI requests the latest stock price from Marketstack.

Response:

```
AAPL

Latest Price

214.80
```

---

## Step 7

The AI updates the matching row in Google Sheets.

Matching Column:

```
Stock
```

Updated:

```
AAPL → 214.80
```

---

## Step 8

The AI confirms completion.

Example:

```
Apple stock price has been updated to $214.80.
```

---

# Components

## Chat Trigger

Starts the workflow.

---

## OpenAI Model

Provides intelligence.

Without it, the workflow cannot understand natural language.

---

## Memory

Keeps conversation history.

---

## Google Sheets Tool

Reads and updates portfolio data.

---

## Marketstack Tool

Fetches current market prices.

---

# Real-world Use Cases

- Personal stock tracker
- Investment dashboard
- Portfolio automation
- Financial reporting
- Daily stock updates
- AI investment assistant

---

# Key Learning

Unlike traditional workflows, the AI Agent dynamically decides which tools to invoke based on the user's request. Google Sheets and Marketstack are connected as **AI tools**, meaning they are called only when required rather than executing in a fixed sequence.