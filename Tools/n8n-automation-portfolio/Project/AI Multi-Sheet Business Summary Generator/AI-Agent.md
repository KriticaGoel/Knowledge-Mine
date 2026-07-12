# 🤖 AI Agent

> "An AI Agent is an LLM that can reason, use tools, maintain context, and perform tasks autonomously to achieve a goal."

---

# 📌 Quick Revision (2 Minutes)

**Time Required:** 10 Minutes

## Core Concepts

- LLM
- Prompt Engineering
- Tool Calling
- Memory
- Reasoning
- Structured Output
- Context Window
- Token Flow

---

## Mental Model

```
            Goal

             │

             ▼

        AI Agent (Brain)

             │

 ┌───────────┼────────────┐

 ▼           ▼            ▼

Prompt     Memory      Tools

             │

             ▼

         Reasoning

             │

             ▼

      Structured Output

             │

             ▼

        Final Response
```

Remember this equation:

```
AI Agent

=

LLM

+

Prompt

+

Memory

+

Reasoning

+

Tools

+

Output Parser
```

---

# 📖 What is an AI Agent?

A Large Language Model (LLM) can only **generate text**.

An AI Agent extends the LLM by giving it the ability to:

- Think
- Plan
- Decide
- Call external tools
- Observe results
- Continue reasoning
- Achieve a goal

Unlike a chatbot, an AI Agent is **action-oriented**.

---

## LLM vs AI Agent

| LLM | AI Agent |
|------|----------|
| Generates text | Solves problems |
| No external actions | Uses tools |
| Single response | Multi-step reasoning |
| No planning | Plans next action |
| Doesn't access live data | Reads APIs, databases, files |

Example

```
User

↓

What's today's Apple stock price?
```

LLM

```
I don't know today's price.
```

AI Agent

```
Think

↓

Use MarketStack Tool

↓

Read latest stock

↓

Return answer
```

---

# 🧠 AI Agent Components

```
               AI Agent

                   │

      ┌────────────┼────────────┐

      ▼            ▼            ▼

   Prompt       Memory       Tools

                   │

                   ▼

             Reasoning

                   │

                   ▼

          Structured Output
```

Each component has a specific responsibility.

---

# 🔧 Tool Calling

## What is Tool Calling?

Tool Calling allows an AI Agent to interact with external systems.

Examples

- Google Sheets
- SQL Database
- HTTP APIs
- Slack
- Telegram
- Calculator
- File System

Instead of generating fake information, the AI retrieves real data.

---

## Example

```
User

↓

Generate today's report

↓

AI Agent

↓

Read Sales Sheet

↓

Read Inventory Sheet

↓

Read Support Sheet

↓

Combine Results

↓

Generate Report
```

---

## Tool Selection Process

```
User

↓

AI Agent

↓

Do I need external information?

↓

Yes

↓

Select Tool

↓

Execute Tool

↓

Observe Result

↓

Continue Thinking
```

---

## Multiple Tool Execution

An AI Agent can call several tools in sequence.

Example

```
Generate Business Report

↓

Read Sales

↓

Read Inventory

↓

Read Feedback

↓

Read Support

↓

Generate Summary
```

The LLM decides:

- Which tool?
- When?
- How many times?
- What data to pass?

---

## Tool Description Matters

Bad

```
Google Sheet
```

Good

```
Reads daily sales information including revenue,
orders, returns and growth.
```

The better the description, the better the AI selects the tool.

---

# 🧠 Reasoning

Reasoning is the decision-making process inside the AI Agent.

Example

```
User

↓

Generate today's report

↓

Need Sales?

YES

↓

Need Inventory?

YES

↓

Need Feedback?

YES

↓

Combine Information

↓

Generate Report
```

Reasoning is invisible but extremely important.

---

## ReAct Pattern

Modern AI Agents commonly follow the **ReAct (Reason + Act)** pattern:

```
Thought

↓

Action

↓

Observation

↓

Thought

↓

Action

↓

Observation

↓

Answer
```

Example

```
Thought

Need sales data.

↓

Action

Read Sales Sheet

↓

Observation

Revenue increased.

↓

Thought

Need inventory.

↓

Action

Read Inventory Sheet

↓

Observation

Stock healthy.

↓

Answer

Business is healthy.
```

---

# 📝 Prompt Engineering

Prompt = Instructions for the AI.

A good prompt contains:

- Role
- Goal
- Constraints
- Output Format
- Examples

Example

```
You are an Operations Intelligence Assistant.

Your task is to analyse business data.

Generate:

1. Director Report

2. Technical Report

Always return JSON.
```

---

## Prompt Template

```
Role

↓

Objective

↓

Rules

↓

Available Tools

↓

Output Format

↓

Examples
```

---

# 🔢 Token Flow

Understanding token flow is essential for cost and performance.

```
User Input

↓

System Prompt

↓

Memory

↓

Tool Results

↓

LLM

↓

Output
```

All of these consume tokens.

```
Prompt

300 Tokens

+

Memory

200 Tokens

+

Sales Data

500 Tokens

+

Inventory

300 Tokens

+

Support

400 Tokens

+

Output

250 Tokens

=

1950 Tokens
```

---

## How to Reduce Tokens

✔ Short prompts

✔ Read only required data

✔ Avoid duplicate tool calls

✔ Summarize large datasets

✔ Use Structured Output

---

# 🔄 Multi Tool Execution

Example

```
User

↓

AI Agent

↓

Sales Tool

↓

Inventory Tool

↓

Support Tool

↓

Feedback Tool

↓

Combine Results

↓

Director Report

↓

Technical Report
```

The AI decides which tool to call based on the user's request.

---

## Tool Orchestration

```
Need Sales?

↓

Call Sales Tool

↓

Need Inventory?

↓

Call Inventory Tool

↓

Need Feedback?

↓

Call Feedback Tool

↓

Generate Final Report
```

---

# ⚠ AI Limitations

## Hallucination

AI may generate incorrect information if it cannot verify facts.

Solution

Use tools instead of relying on model memory.

---

## Context Window

Every model has a maximum number of tokens.

Large datasets may exceed the limit.

Solution

Summarize or retrieve only relevant data.

---

## Tool Selection Errors

The AI may choose the wrong tool if descriptions are unclear.

Solution

Write detailed tool descriptions.

---

## Invalid JSON

LLMs sometimes produce malformed JSON.

Solution

Use Structured Output Parser.

---

## Cost

Every token costs money.

Reduce unnecessary prompts and duplicate data.

---

# ✅ Best Practices

## Prompt

- Keep prompts focused.
- Clearly define the role.
- Provide examples.
- Specify output format.

---

## Tools

- One responsibility per tool.
- Descriptive names.
- Clear descriptions.
- Return only required data.

---

## Structured Output

- Always use JSON schemas.
- Validate responses.
- Avoid parsing free text.

---

## Memory

- Keep only useful context.
- Remove irrelevant history.
- Avoid long conversations when unnecessary.

---

## Performance

- Minimize tool calls.
- Reduce token usage.
- Cache repeated data.
- Batch requests where possible.

---

# 🏢 Production Architecture

```
                     User

                       │

                       ▼

                  AI Agent

       ┌─────────────┼─────────────┐

       ▼             ▼             ▼

  Google Sheets   SQL DB      REST APIs

       ▼             ▼             ▼

        Structured Output Parser

                     │

                     ▼

                Business Logic

                     │

                     ▼

              Final Response
```

---

# 🧩 Connected Concepts

This topic is closely related to:

- Prompt Engineering
- Tool Calling
- Memory
- Structured Output Parser
- OpenRouter
- LLM
- RAG
- MCP
- Multi-Agent Systems

---

# 🚀 Next Topics to Learn

1. Prompt Engineering
2. Structured Output Parser
3. Tool Calling
4. Memory
5. RAG
6. MCP (Model Context Protocol)
7. Multi-Agent Systems

---

# 📝 Revision Summary

Remember this equation:

```
AI Agent

=

LLM

+

Prompt

+

Reasoning

+

Memory

+

Tools

+

Structured Output
```

Workflow:

```
User

↓

AI Agent

↓

Reason

↓

Use Tool

↓

Observe

↓

Reason Again

↓

Return Structured Output
```

If you remember this flow, you can rebuild almost any AI Agent in n8n or another orchestration platform.