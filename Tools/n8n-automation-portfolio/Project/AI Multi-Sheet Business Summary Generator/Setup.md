From zero

Install n8n
Google OAuth
OpenAI
Import workflow
Configure credentials
Run workflow

# ⚙️ Setup Guide

> Complete setup guide to run the **AI Business Intelligence Report Generator** from scratch.

---

# 📌 Quick Revision

**Time Required:** 15-20 Minutes

## Prerequisites

- Google Account
- OpenRouter Account
- n8n Cloud or Self-hosted
- Internet Connection

---

# 📋 Overview

Before running the workflow, complete the following steps:

```
1. Install n8n
        │
        ▼
2. Create OpenRouter API Key
        │
        ▼
3. Configure Google OAuth
        │
        ▼
4. Import Workflow
        │
        ▼
5. Configure Credentials
        │
        ▼
6. Test Workflow
```

---

# Step 1: Install n8n

There are two ways to use n8n.

## Option 1 - n8n Cloud (Recommended)

1. Open

```
https://n8n.io/
```

2. Create an account.

3. Create a new workflow.

---

## Option 2 - Self Hosted

Install Docker.

Run

```bash
docker run -it --rm \
-p 5678:5678 \
-v ~/.n8n:/home/node/.n8n \
n8nio/n8n
```

Open

```
http://localhost:5678
```

---

# Step 2: Create OpenRouter API Key

This project uses **OpenRouter** as the AI model provider.

## Create Account

Open

```
https://openrouter.ai/
```

Create an account.

---

## Generate API Key

Navigate to

```
Settings

↓

API Keys

↓

Create API Key
```

Copy the generated API key.

Example

```
sk-or-xxxxxxxxxxxxxxxx
```

Store it securely.

---

# Step 3: Configure OpenRouter in n8n

Open

```
Credentials

↓

Create Credential
```

Select

```
OpenRouter Chat Model
```

If using the OpenAI-compatible node, configure:

| Property | Value |
|----------|-------|
| Base URL | `https://openrouter.ai/api/v1` |
| API Key | Your OpenRouter API Key |
| Model | Your preferred model (for example, `openai/gpt-oss-20b`) |

Save the credential.

---

# Step 4: Configure Google OAuth

Google OAuth is required to allow the workflow to read and write Google Sheets.

---

## Create Google Cloud Project

Open

```
https://console.cloud.google.com/
```

Create a new project.

---

## Enable APIs

Enable the following APIs:

- Google Sheets API
- Google Drive API

---

## Create OAuth Credentials

Navigate to

```
APIs & Services

↓

Credentials

↓

Create Credentials

↓

OAuth Client ID
```

Create a Web Application OAuth client.

---

## Configure OAuth Consent Screen

Add:

- Application Name
- Support Email
- Developer Contact

Save.

---

## Copy Credentials

You will receive:

```
Client ID

Client Secret
```

---

# Step 5: Configure Google Sheets Credential in n8n

Open

```
Credentials

↓

New Credential

↓

Google Sheets OAuth2
```

Provide

- Client ID
- Client Secret

Authorize your Google account.

After successful authentication, save the credential.

---

# Step 6: Import Workflow

Open n8n.

Click

```
Import Workflow
```

Select

```
workflow.json
```

The workflow will be imported into your workspace.

---

# Step 7: Configure Workflow Credentials

Open each node that requires authentication.

Verify that the correct credential is selected.

Typical credential configuration:

| Node | Credential |
|------|------------|
| AI Agent | OpenRouter Credential |
| Google Sheets Tool | Google OAuth Credential |
| Append Row | Google OAuth Credential |
| Pushover (Optional) | Pushover Credential |

Resolve any missing credential warnings before executing the workflow.

---

# Step 8: Update Google Sheet Configuration

Replace the Spreadsheet IDs with your own Google Sheets.

Typical sheets used by this workflow:

- Sales
- Inventory
- Support
- Feedback
- Director Report
- Technical Report

Ensure the authenticated Google account has access to these spreadsheets.

---

# Step 9: Test the Workflow

Run the workflow.

Example input:

```
Generate today's business report
```

Expected execution flow:

```
Chat Trigger

↓

AI Agent

↓

Read Google Sheets

↓

Generate Reports

↓

Append Reports

↓

Send Notification
```

---

# Expected Output

The workflow should:

- Read business data from Google Sheets
- Generate a Director Report
- Generate a Technical Report
- Append both reports to Google Sheets
- Send a completion notification (if configured)

---

# Troubleshooting

## OpenRouter Authentication Failed

Possible causes:

- Invalid API key
- Incorrect Base URL
- Insufficient API credits

Verify the API key and endpoint.

---

## Google Authentication Failed

Possible causes:

- Incorrect Client ID
- Incorrect Client Secret
- OAuth consent screen not configured
- Missing Google Sheets API

Verify the Google Cloud configuration.

---

## Permission Denied

The authenticated Google account does not have access to the spreadsheet.

Grant access to the account used by the OAuth credential.

---

## Workflow Cannot Read Google Sheets

Verify:

- Spreadsheet ID
- Sheet Name
- OAuth credential
- API permissions

---

## AI Agent Does Not Respond

Verify:

- OpenRouter credential
- Model selection
- API quota
- Internet connectivity

---

# Verification Checklist

Before running the workflow, ensure:

- [ ] n8n is installed and accessible.
- [ ] OpenRouter API key is configured.
- [ ] Google OAuth credential is authorized.
- [ ] Google Sheets API is enabled.
- [ ] Workflow is imported.
- [ ] Spreadsheet IDs are updated.
- [ ] All nodes have valid credentials.
- [ ] Workflow executes without credential errors.

---

# Connected Concepts

This setup is related to:

- AI Agent
- OpenRouter
- Google Sheets Tool
- OAuth2 Authentication
- Structured Output Parser
- Workflow Configuration

---

# Revision Summary

```
Install n8n

↓

Create OpenRouter API Key

↓

Configure Google OAuth

↓

Import Workflow

↓

Configure Credentials

↓

Update Google Sheets

↓

Run Workflow
```

If you can remember this sequence, you can recreate the project environment from scratch.