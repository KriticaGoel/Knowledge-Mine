# 📊 AI Business Intelligence Report Generator

> **Generate Director and Technical Business Reports automatically using AI by analyzing multiple Google Sheets through n8n AI Agents.**

---

# 🎯 Project Overview

Organizations often store operational data across multiple spreadsheets such as Sales, Inventory, Support, and Customer Feedback.

Manually reviewing all these reports every day is:

- Time consuming
- Error prone
- Difficult for management to understand quickly

This project uses **n8n AI Agent**, **OpenRouter/OpenAI**, and **Google Sheets Tools** to automatically:

- Read multiple Google Sheets
- Analyze business health
- Generate executive summaries
- Generate technical reports
- Store reports back into Google Sheets
- Notify stakeholders

---

# 💼 Business Problem

### Existing Process

Business users manually:

- Open multiple Google Sheets
- Compare KPIs
- Identify trends
- Detect issues
- Create reports
- Send updates

This process takes **30–60 minutes every day**.

---

### Proposed Solution

An AI Agent automatically:

1. Reads all required Google Sheets
2. Understands business data
3. Finds trends
4. Detects issues
5. Generates reports
6. Saves reports
7. Sends notifications

Entire workflow executes in a few minutes.

---

# ✨ Features

## AI Powered Analysis

- Multi-sheet data analysis
- Business trend detection
- Executive summary generation
- Technical report generation

---

## Google Sheets Integration

Reads data from

- Sales
- Inventory
- Support
- Customer Feedback

Writes results to

- Director Report Sheet
- Technical Report Sheet

---

## AI Agent

- Tool Calling
- Reasoning
- Prompt Engineering
- Structured Output
- Memory Support

---

## Notifications

Automatically sends notification once reports are generated.

---

## Extendable

Can easily integrate

- Slack
- Microsoft Teams
- Email
- Telegram
- Power BI
- SQL Database
- REST APIs

---

# 🛠 Tech Stack

| Technology | Purpose |
|------------|----------|
| n8n | Workflow Automation |
| OpenRouter | LLM Provider |
| OpenAI / GPT | AI Reasoning |
| Google Sheets | Business Data Source |
| Google Sheets Tool | AI Tool Calling |
| Structured Output Parser | JSON Validation |
| Pushover | Notifications |

---

# 🏗 Architecture

```text
                        User

                          │

                          ▼

                    Chat Trigger

                          │

                          ▼

                     AI Agent

       ┌──────────────┼──────────────┐

       ▼              ▼              ▼

 Sales Sheet    Inventory Sheet   Support Sheet

                     ▼

             Feedback Sheet

                     │

                     ▼

            Tool Calling Layer

                     │

                     ▼

          Structured Output Parser

                     │

      ┌──────────────┴───────────────┐

      ▼                              ▼

 Director Report             Technical Report

      │                              │

      ▼                              ▼

 Google Sheet                 Google Sheet

               │

               ▼

      Push Notification
```

---

# 📸 Screenshots

> Add screenshots inside `/Resources`

Example

```
Resources/

workflow.png

architecture.png

director-report.png

technical-report.png

google-sheet.png
```

Recommended screenshots

- Complete Workflow
- AI Agent Configuration
- Google Sheets
- Generated Director Report
- Generated Technical Report
- Notification

---

# 🎥 Demo

## Example Input

```
Generate today's business report.
```

---

## AI Agent

↓

Reads

- Sales Sheet
- Inventory Sheet
- Support Sheet
- Feedback Sheet

↓

Analyzes

↓

Creates

Director Report

Technical Report

↓

Stores Reports

↓

Sends Notification

---

## Example Director Report

```
Business Health : Healthy

Revenue Trend : Increasing

Customer Satisfaction : Excellent

Inventory : Stable

Critical Issues : None
```

---

## Example Technical Report

```
Sales

Revenue increased 8%

Support

Average resolution time improved

Inventory

No low stock items

Feedback

Positive sentiment increased
```

---

# 📂 Folder Structure

```
AI-Business-Intelligence/

│

├── README.md

├── Workflow.md

├── AI-Agent.md

├── Prompt.md

├── StructuredOutput.md

├── GoogleSheets.md

├── Setup.md

├── Troubleshooting.md

├── Improvements.md

├── workflow.json

│

├── Resources/

│     ├── workflow.png

│     ├── architecture.png

│     ├── reports.png

│     └── screenshots

│

└── SampleData/

      ├── Sales.xlsx

      ├── Inventory.xlsx

      ├── Support.xlsx

      └── Feedback.xlsx
```

---

# ⚙ Installation

## Prerequisites

- n8n
- OpenRouter API Key
- Google Account
- Google OAuth Credentials
- Pushover Account (Optional)

---

## Step 1

Clone repository

```bash
git clone https://github.com/<username>/AI-Business-Intelligence.git
```

---

## Step 2

Import

```
workflow.json
```

into n8n.

---

## Step 3

Configure Credentials

Create

- OpenRouter Credential
- Google OAuth Credential
- Pushover Credential

---

## Step 4

Update Google Sheet IDs

Replace your

- Sales Sheet
- Inventory Sheet
- Support Sheet
- Feedback Sheet

---

## Step 5

Run Workflow

Execute

```
Generate today's report
```

---

# 📖 Learning Outcomes

By completing this project you will understand:

## n8n

- AI Agent
- Tool Calling
- Google Sheets Tool
- Structured Output Parser
- Chat Trigger
- Notifications

---

## AI

- Prompt Engineering
- LLM Workflow
- Tool Selection
- Structured Output
- AI Reasoning
- Context Management

---

## Integration

- Google Sheets API
- AI + Workflow Automation
- Business Intelligence
- Report Generation

---

## Software Design

- Workflow Architecture
- Separation of Responsibility
- Scalable AI Workflows
- Error Handling
- Automation Best Practices

---

# 🚀 Future Roadmap

## Version 2

- Dashboard
- Charts
- Trend Analysis

---

## Version 3

- Slack Integration
- Microsoft Teams
- Email Reports
- Telegram Bot

---

## Version 4

- SQL Database Support
- Excel Support
- CSV Import
- REST API Integration

---

## Version 5

- Multi-Agent Architecture
- MCP Support
- RAG
- AI Memory
- Historical Trend Analysis
- Root Cause Analysis

---

# 🎯 Knowledge Mine Revision

## Business Problem

Generate business reports automatically from multiple Google Sheets.

---

## Core Concepts

- AI Agent
- Tool Calling
- Prompt Engineering
- Structured Output
- Google Sheets Tool
- Workflow Automation
- Business Intelligence

---

## Workflow

```
User

↓

AI Agent

↓

Read Multiple Sheets

↓

Analyse Data

↓

Generate Reports

↓

Save Reports

↓

Notify User
```

---

## Mental Model

```
AI Agent

=

LLM

+

Prompt

+

Tools

+

Reasoning

+

Structured Output
```

---

## Time Required to Revise

⏱ **10 Minutes**

---

# 🤝 Contributing

Feel free to improve the workflow by adding:

- More AI tools
- Better prompts
- New integrations
- Additional business reports
- Dashboard support

Pull Requests are always welcome.

---

# 📄 License

MIT License

---

# 👩‍💻 Author

**Kritica Goel**

Senior Java Backend Engineer | AI Automation Enthusiast

Building practical AI solutions using **Java**, **Spring Boot**, **n8n**, **LLMs**, and **Workflow Automation**.