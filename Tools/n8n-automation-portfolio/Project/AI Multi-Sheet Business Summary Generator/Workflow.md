# Nodes
* [Chat Trigger](#chat-trigger)
* [AI Agent](#ai-agent)
* [OpenAI](#openrouter-chat-model)
* [Google Sheet Tool](#google-sheets-tool)
* [Structured Output Parser](#structured-output-parser)
* [Append Row](#append-row)
* [Pushover](#pushover-notification)

# Chat Trigger

> Entry point of an AI chat workflow.

---

# Purpose

The Chat Trigger node starts the workflow whenever a user sends a message through the n8n chat interface.

Without this node, the AI Agent has no user input to process.

---

# Where it fits

```
User

↓

Chat Trigger

↓

AI Agent
```

---

# Responsibilities

- Receive user message
- Start workflow
- Pass message to AI Agent
- Maintain conversation context

---

# Configuration

Typical configuration:

- Enable Public Chat (Optional)
- Authentication (Optional)
- Chat Title
- Response Mode

Normally, no advanced configuration is required.

---

# Input

Example

```json
{
  "chatInput": "Generate today's business report"
}
```

---

# Output

```json
{
  "chatInput": "Generate today's business report"
}
```

---

# Why it is used

Instead of manually triggering a workflow, Chat Trigger allows users to interact naturally.

Example

```
User

↓

What is today's business health?

↓

Workflow starts automatically
```

---

# Real-world Example

Customer Support Bot

```
Customer

↓

Where is my order?

↓

AI Agent
```

---

Business Reporting

```
Generate today's report

↓

AI Agent
```

---

# Common Mistakes

❌ Forgetting to use `{{$json.chatInput}}`

❌ Using Manual Trigger instead of Chat Trigger

❌ Not testing with sample user messages

---

# Best Practices

✅ Keep prompts dynamic

```
{{$json.chatInput}}
```

✅ Validate empty input

✅ Keep Chat Trigger only as an entry point

---

# Mental Model

```
User speaks

↓

Chat Trigger listens

↓

Workflow starts
```

---

# Revision

Time: 2 minutes

Remember

```
Chat Trigger

=

Workflow Entry Point
```

---

# Next Concept

# AI Agent

> The brain of the workflow.

---

# Purpose

The AI Agent understands user intent, decides which tools to use, collects information, reasons over the data, and generates the final response.

---

# Responsibilities

✔ Understand user request

✔ Decide which tool to call

✔ Read Google Sheets

✔ Analyze data

✔ Generate reports

✔ Return structured output

---

# Architecture

```
User

↓

AI Agent

↓

Reasoning

↓

Tool Calling

↓

Response
```

---

# Components

AI Agent consists of

```
LLM

+

Prompt

+

Memory

+

Tools

+

Reasoning

+

Structured Output
```

---

# Configuration

Connect

- Chat Model
- Memory
- Google Sheets Tool
- Structured Output Parser

---

# Input

```json
{
    "chatInput":"Generate today's report"
}
```

---

# Output

```json
{
    "directorReport":{},
    "technicalReport":{}
}
```

---

# Why used

Traditional workflow

```
If

Else

Switch

HTTP

Google Sheet

Database
```

AI Agent

↓

Decides automatically.

---

# Common Mistakes

❌ Too many tools

❌ Weak prompts

❌ Asking AI to do business logic

❌ No structured output

---

# Best Practices

✔ One responsibility

✔ Good tool descriptions

✔ Small prompts

✔ Structured JSON

✔ Validation

---

# Mental Model

```
AI Agent

=

Brain
```

---

# Revision

Agent

=

LLM

+

Tools

+

Reasoning

+

Memory

---

# OpenRouter Chat Model

> The Large Language Model (LLM) powering the AI Agent.

---

# Purpose

Generates responses, reasons over user input, and decides how to use tools.

---

# Responsibilities

- Understand prompts
- Generate text
- Decide tool usage
- Follow instructions

---

# Configuration

- API Key
- Model (GPT OSS, Claude, DeepSeek, etc.)
- Temperature
- Max Tokens

---

# Input

Prompt from AI Agent.

---

# Output

Natural language or structured JSON.

---

# Why Used

OpenRouter allows switching between multiple LLM providers without changing your workflow.

---

# Common Mistakes

❌ High temperature for deterministic tasks.

❌ Very long prompts.

❌ Forgetting API limits.

---

# Best Practices

✔ Low temperature (0–0.3) for automation.

✔ Choose models based on task.

✔ Monitor token usage.

---

# Mental Model

```
Prompt

↓

LLM

↓

Response
```

---

# Revision

LLM = Text Generation Engine

# Google Sheets Tool

> Gives the AI Agent access to spreadsheet data.

---

# Purpose

Allows the AI Agent to read and write Google Sheets without manually building API requests.

---

# Responsibilities

- Read rows
- Append rows
- Update rows
- Search data

---

# Configuration

- Google OAuth Credential
- Spreadsheet ID
- Sheet Name
- Operation

---

# Input

Example:

```
Read Sales Sheet
```

---

# Output

Returns rows as JSON.

---

# Why Used

Acts as the AI Agent's knowledge source.

---

# Common Mistakes

❌ Wrong Sheet ID.

❌ Missing OAuth permission.

❌ Wrong operation selected.

---

# Best Practices

✔ Use descriptive tool names.

✔ Keep one tool per sheet.

✔ Limit returned rows where possible.

---

# Mental Model

```
Google Sheet

↓

JSON

↓

AI Agent
```

# Structured Output Parser

> Converts AI responses into validated JSON.

---

# Purpose

Ensures the AI always returns data in a predictable structure.

---

# Why Needed

Without parser:

```
"```json
{
...
}
```"
```

With parser:

```json
{
  "status":"success"
}
```

---

# Responsibilities

- Validate schema
- Prevent malformed output
- Reduce hallucinations

---

# Common Mistakes

❌ Missing required fields.

❌ Invalid JSON schema.

---

# Best Practices

✔ Define required properties.

✔ Keep schema simple.

✔ Validate all AI outputs.

---

# Mental Model

```
AI

↓

JSON Validation

↓

Workflow
```

# Append Row

> Writes AI-generated reports into Google Sheets.

---

# Purpose

Stores generated reports for future reference.

---

# Responsibilities

- Add new row
- Map fields
- Preserve history

---

# Input

Structured JSON.

---

# Output

New row added to Google Sheet.

---

# Common Mistakes

❌ Wrong column mapping.

❌ Incorrect data types.

❌ Duplicate entries.

---

# Best Practices

✔ Map fields explicitly.

✔ Add timestamp.

✔ Validate required columns.

---

# Mental Model

```
JSON

↓

Google Sheet

↓

Saved
```

# Pushover Notification

> Sends a notification after workflow completion.

---

# Purpose

Notify users that report generation has completed.

---

# Responsibilities

- Send push notification
- Deliver summary message
- Alert stakeholders

---

# Configuration

- User Key
- API Token
- Message
- Title

---

# Input

Example

```
Director report generated successfully.
```

---

# Output

Push notification on user's device.

---

# Common Mistakes

❌ Invalid API token.

❌ Missing user key.

❌ Empty message.

---

# Best Practices

✔ Keep messages concise.

✔ Include report status.

✔ Add links if needed.

---

# Mental Model

```
Workflow Finished

↓

Pushover

↓

Mobile Notification
```