# 🤖 AI Slack Assistant (n8n)

An AI-powered Slack assistant built using n8n, OpenAI GPT-5 Mini, and Slack APIs.

Whenever the bot is mentioned in a Slack channel, it understands the user's message using GPT-5 Mini and responds directly in the same Slack conversation.

---

# 🚀 Features

- Slack App Mention Trigger
- AI-powered natural language understanding
- Automatic response generation
- Sends replies back to Slack
- Easy to extend with AI tools

---

# Architecture

```
               Slack Channel
                     │
          User mentions Bot
                     │
                     ▼
              Slack Trigger
                     │
                     ▼
                AI Agent
              (GPT-5 Mini)
                     │
                     ▼
           Send Message to Slack
                     │
                     ▼
            Reply in Slack Channel
```

---

# Example Conversation

**User**

```
@AIBot Explain Java Streams.
```

↓

**AI**

```
Java Streams provide a functional way to process collections using operations such as map(), filter(), reduce(), and collect().
```

---

# Technologies Used

- n8n
- Slack API
- OpenAI GPT-5 Mini
- AI Agent

---

# Workflow Components

## Slack Trigger

Starts the workflow whenever the bot is mentioned in a Slack channel.

---

## AI Agent

Acts as the brain of the workflow.

Receives the user's message and generates an intelligent response.

---

## OpenAI Chat Model

Provides reasoning and natural language understanding.

---

## Send Message

Posts the AI-generated response back into the Slack channel.

---

# Installation

1. Clone the repository.

2. Import `workflow.json` into n8n.

3. Configure:
    - Slack OAuth Credentials
    - OpenAI API Key

4. Activate the workflow.

---

# Project Structure

```
SlackIntegration.json
README.md
workflow.md
integrationStep.md
```

---

# Future Enhancements

- Google Search integration
- Google Sheets integration
- Jira ticket creation
- Calendar scheduling
- PDF summarization
- Company knowledge base (RAG)
- Memory support
- Multi-channel support (Teams, Discord, Telegram)