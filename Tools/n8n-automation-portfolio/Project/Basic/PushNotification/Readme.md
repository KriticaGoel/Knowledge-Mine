# AI Push Notification Agent (n8n)

An AI-powered n8n workflow that receives a chat message, processes it using an OpenAI model, and sends push notifications to a mobile device via Pushover.

## Features

- Chat-based AI assistant
- Uses OpenAI GPT-5 Mini
- Maintains conversation memory
- Sends push notifications
- Can access current date and time

## Workflow
```
Chat Trigger
│
▼
AI Agent
├── OpenAI Chat Model
├── Simple Memory
├── Date & Time Tool
└── Pushover Tool
│
▼
Push Notification
```

## Requirements

- n8n
- OpenAI API Key
- Pushover Account
- Pushover User Key
- Pushover API Token

## Installation

1. Clone this repository.

2. Import `workflow.json` into n8n.

3. Configure credentials:
    - OpenAI
    - Pushover

4. Activate the workflow.

## Usage

Example prompts:

- Notify me that my deployment has completed.
- Send a reminder to drink water.
- Notify me at today's date and time.
- Send "Server restarted successfully" to my phone.

The AI determines when to invoke the Pushover tool and sends the generated message as a push notification.

## Technologies

- n8n
- OpenAI GPT-5 Mini
- Pushover
- AI Agent
- LangChain Memory

## Repository Structure

```
PushNotification.json
README.md
workflow.md
WorkFlow.png
```

## Future Improvements

- WhatsApp notifications
- Slack integration
- Email notifications
- Microsoft Teams alerts
- Telegram Bot
- Multiple notification channels
- Logging every notification to Google Sheets
  Supporting Slack, Teams, WhatsApp, and Email in addition to Pushover
  Adding severity levels (Info, Warning, Critical)
  Including retry logic if notification delivery fails
  Recording execution history in a database