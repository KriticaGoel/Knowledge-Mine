Everything about

JSON Schema
Validation
Why parser
Benefits
Without parser
Common errors

# Structured Output Parser

> Convert AI responses into predictable, machine-readable JSON so that n8n workflow nodes (IF, Switch, Set, Google Sheets, etc.) can reliably use the data.

---

# 📌 Quick Revision

**Time Required:** 5 Minutes

## Purpose

Convert AI output into structured JSON that can be used by downstream workflow nodes.

---

## Mental Model

```
User

↓

AI Agent

↓

JSON

↓

Code Node (if required)

↓

IF Node

↓

Workflow
```

---

## Connected Concepts

- AI Agent
- Prompt Engineering
- IF Node
- Code Node
- Tool Calling

---

# Business Problem

The AI Agent was instructed to return JSON.

Prompt

```text
Always return JSON.

{
  "status":"success",
  "assetType":"stock",
  "ticker":"AAPL",
  "message":"..."
}

If unsupported:

{
  "status":"unsupported",
  "assetType":"commodity",
  "message":"Gold isn't supported"
}
```

However, the AI Agent returned

````text
```json
{
  "status":"success",
  "assetType":"stock",
  "ticker":"INFY",
  "message":"Last closing price: 10.88 on 2026-07-06"
}
```