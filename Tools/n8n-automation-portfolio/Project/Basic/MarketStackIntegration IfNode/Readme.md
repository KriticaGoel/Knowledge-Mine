# 📈 AI Stock Price Assistant using n8n + OpenRouter + MarketStack

An AI-powered stock assistant built with **n8n**, **OpenRouter LLM**, and **MarketStack** that allows users to ask stock-related questions in natural language.

Example:

> 💬 "What is the latest price of Infosys?"

↓

🤖 AI understands the request, determines the ticker symbol (`INFY`), retrieves the latest stock price from MarketStack, and returns the response.

---

# Features

- Natural language stock queries
- AI-powered ticker identification
- MarketStack integration
- Conversation memory
- Support for unsupported assets
- Workflow branching using IF node
- Easily extendable for News, Crypto, Gold, etc.

---

# Tech Stack

| Technology | Purpose |
|------------|---------|
| n8n | Workflow Automation |
| OpenRouter | LLM Provider |
| GPT OSS 20B | AI Reasoning |
| MarketStack | Stock Market API |
| JavaScript | Response Transformation |

---

# Workflow Architecture

```text
                 User

                   │

                   ▼

          Chat Trigger

                   │

                   ▼

              AI Agent
        (OpenRouter + Memory)

                   │

                   ▼

         MarketStack Tool
     (Only for Stock Requests)

                   │

                   ▼

           Code Node
(Parse JSON returned by AI Agent)

                   │

                   ▼

               IF Node

          ┌────────┴────────┐

          ▼                 ▼

      Success          Unsupported
```

---

# Workflow Explanation

## 1. Chat Trigger

Starts the workflow whenever the user sends a message.

Example

```
Latest price of Infosys
```

---

## 2. AI Agent

Responsible for:

- Understanding user intent
- Determining whether the request is about a stock
- Identifying the stock ticker
- Calling the MarketStack tool when appropriate
- Returning a JSON response

Current Prompt:

```text
Use market stack tool only for publicly traded stocks.

Input must be a valid stock ticker.

If the user asks about an asset that is not a stock,
do not call this tool.

{{ $json.chatInput }}

Always return JSON.

{
 "status":"success",
 "assetType":"stock",
 "ticker":"AAPL",
 "message":"..."
}

If unsupported

{
 "status":"unsupported",
 "assetType":"commodity",
 "message":"Gold isn't supported"
}
```

---

## 3. MarketStack Tool

The AI Agent calls this tool only when:

```
status = success
```

Example

```
Infosys
↓

INFY
↓

MarketStack

↓

Latest Price
```

---

## 4. Code Node

### Why is it needed?

Although the prompt requests JSON, the LLM returns:
