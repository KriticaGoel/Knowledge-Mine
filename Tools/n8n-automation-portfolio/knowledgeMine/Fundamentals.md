n8n Knowledge Base

> A personal developer handbook for building, revising, and extending n8n workflows and AI integrations.

Table of Contents
3. AI Concepts
4. Second Workflow (Memory)
5. Languages Supported
6. Parameters in n8n
7. Expression Cheat Sheet
8. Authentication Patterns
9. Webhooks
10. Integrations Learned
11. Telegram AI Bot Integration
12. Quick Revision Cheat Sheet
13. Reusable Integration Template

### LLM (Large Language Model)
Definition

A Large Language Model (LLM) is an AI model trained on massive amounts of text data.

It predicts the most probable next words based on the input.
```
  Input
    │
    ▼
Pattern Matching
    │
    ▼
Probability Prediction
    │
    ▼
Generated Response
```
Examples
* GPT
* Claude
* Gemini
* Llama


### GPT vs ChatGPT
GPT	ChatGPT
Language Model	End-user application
Generates text	Uses GPT to answer users
Accessible via API	Interactive chat interface

### AI Agent
**Definition**

An AI Agent is an LLM capable of:

* Thinking
* Using tools
* Remembering context
* Making decisions
* Executing tasks until a goal is achieved


**Mental Model**
```
    LLM
    │
    ▼
    LLM + Tools
    │
    ▼
    LLM + Tools + Memory
    │
    ▼
    AI Agent
    Agent Execution Loop
    Question
    │
    Think
    │
    Use Tool
    │
    Read Result
    │
    Think Again
    │
    Repeat Until Goal
    │
    Final Answer
```

### Languages Supported
JavaScript

Python

### Parameters in n8n
**Fixed Parameters** - Static values.

Example :

Hello World

**Expression Parameters** - Dynamic values enclosed in {{ }}.

Example:

{{$json.name}}

|Purpose   |Expression   |
|---|---|
|Entire JSON   |{{$json}}   |
|Field   |{{$json.name}}   |
|Nested Object   |{{$json.address.city}}   |
|Previous Node   |{{$node["Node Name"].json}}   |
|JSON String   |{{JSON.stringify($json)}}   |

### Authentication Patterns

1. API Key
```
   Application
   │
   API Key
   │
   Service
```
Examples

* OpenRouter
* MarketStack
* Weather APIs

2. Preconfigured OAuth2

n8n already handles authentication.

Examples

* Gmail
* Google Sheets
* Google Drive

3. Custom OAuth2

Configure manually:

* Client ID
* Client Secret
* Authorization URL
* Token URL
* Redirect URL

Used for services not supported directly by n8n.

### Webhooks

> A webhook is a mechanism where one application automatically notifies another application when an event occurs.

Request–Response Pattern
```
    Client
    │
    HTTP Request
    │
    Server
    │
    HTTP Response
    │
    Client
```

Examples

* Login
* Search APIs
* Payment Verification

Event-Based Webhook
```
Event Occurs
│
Source System
│
Webhook URL
│
Target Workflow
│
Automation Starts
```
Examples

* GitHub Push
* Stripe Payment
* Google Form Submission
* Telegram Message
