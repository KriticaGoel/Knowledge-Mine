# 📈 AI Stock Portfolio Manager (n8n)

An AI-powered stock portfolio assistant built with n8n.

The workflow allows users to interact with their stock portfolio using natural language. The AI Agent can read portfolio data from Google Sheets, fetch live market prices using the Marketstack API, and update the spreadsheet automatically.

---

## 🚀 Features

- 🤖 AI-powered chat interface
- 📊 Reads stock portfolio from Google Sheets
- 📈 Fetches latest End-of-Day stock prices
- ✍️ Updates stock prices automatically
- 🧠 Conversation memory
- 💬 Natural language interaction

---

## Architecture

```
                  Chat Trigger
                        │
                        ▼
                  AI Agent
        ┌────────┼──────────┬──────────────┐
        │        │          │              │
        ▼        ▼          ▼              ▼
 OpenAI Model  Memory   Google Sheets   Marketstack
                            │
                            ▼
                   Update Google Sheet
```

---

## Example Commands

```
Show my portfolio.

Update the latest price for AAPL.

Refresh today's stock prices.

What is the current price of Microsoft?

Update Google Sheet with latest stock prices.
```

---

## Technologies Used

- n8n
- OpenAI GPT-5 Mini
- Google Sheets API
- Marketstack API
- LangChain AI Agent
- AI Memory

---

## Workflow Explanation

### 1. Chat Trigger

Starts the workflow whenever a user sends a message.

---

### 2. AI Agent

Acts as the brain of the workflow.

It understands the user's request and decides which tools should be used.

---

### 3. OpenAI Chat Model

Provides reasoning capability to the AI Agent.

Examples:

- Understands "Update Tesla price."
- Understands "Show my stocks."

---

### 4. Simple Memory

Stores previous conversation context.

Example:

User:
Update Apple.

Later:

Update Microsoft too.

The AI remembers the previous conversation.

---

### 5. Google Sheets Tool (Read)

Reads the existing portfolio.

Example:

| Stock | Purchased | Price |
|--------|-----------|-------|
| AAPL | 180 | 210 |
| TSLA | 270 | 305 |

---

### 6. Marketstack Tool

Fetches the latest stock prices.

Example:

```
AAPL → 214.56

TSLA → 312.71
```

---

### 7. Google Sheets Tool (Update)

Updates matching stock rows with the latest prices.

Matching is done using the **Stock** column.

---

## Requirements

- n8n
- OpenAI API Key
- Google Sheets OAuth Credentials
- Marketstack API Key

---

## Installation

1. Clone this repository.

2. Import `workflow.json`.

3. Configure credentials.

4. Activate the workflow.

---

## Project Structure

```
GoogleSheetWorkflow.json
README.md
```

---

## Future Improvements

- Real-time stock prices
- Multiple portfolios
- Portfolio performance dashboard
- Profit/Loss calculation
- Daily email reports
- Telegram/Slack notifications
- Buy/Sell recommendations using AI
- Price alerts