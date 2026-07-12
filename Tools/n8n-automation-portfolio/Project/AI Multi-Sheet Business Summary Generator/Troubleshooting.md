Real-world issues

Invalid JSON
OAuth expired
Missing Sheets
Tool timeout
Rate limits
Prompt issues
Empty AI output

# 🔧 Troubleshooting Guide

> This document covers the most common issues encountered while running the **AI Business Intelligence Report Generator** workflow and provides practical solutions.

---

# 📌 Quick Revision

**Time Required:** 10 Minutes

## Common Issues

- Invalid JSON
- OAuth Expired
- Missing Google Sheets
- Tool Timeout
- Rate Limits
- Prompt Issues
- Empty AI Output

---

# 🧠 Troubleshooting Flow

```
Workflow Failed

      │

      ▼

Check Error Message

      │

      ├──────────────┬───────────────┬──────────────┐

      ▼              ▼               ▼              ▼

 JSON Error     OAuth Error    Tool Error    AI Response Error

      │              │               │              │

      ▼              ▼               ▼              ▼

 Fix Parser    Reconnect      Retry Tool     Verify Prompt
```

---

# 1. Invalid JSON

## Symptoms

- IF Node always goes to the **False** branch.
- `{{$json.status}}` returns `undefined`.
- JSON Parse Error in Code Node.
- Workflow stops after the AI Agent.

---

## Example

AI Agent returned:

````text
```json
{
  "