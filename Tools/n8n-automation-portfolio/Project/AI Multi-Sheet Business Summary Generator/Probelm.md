A manager has data spread across multiple Google Sheets:

Sales Report

Customer Feedback

Support Tickets

Inventory

Employee Productivity

Every morning, they want one consolidated summary.

Instead of manually reading all sheets, the AI does it automatically.


### Architecture
```
                  Scheduler
                  (9 AM Daily)
                       │
                       ▼
                Read Google Sheet 1
                  (Sales)
                       │
                Read Google Sheet 2
                (Support Tickets)
                       │
                Read Google Sheet 3
                (Inventory)
                       │
                Read Google Sheet 4
                (Customer Feedback)
                       │
                       ▼
                 Merge All Data
                       │
                       ▼
                   AI Agent
        ┌──────────────┼──────────────┐
        │              │              │
        ▼              ▼              ▼
Business Summary   Insights      Recommendations
│
▼
Write Summary to Google Sheet
│
(Optional)
Slack / Telegram / Email
```