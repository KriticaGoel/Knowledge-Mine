
## Structured Output Parser
> A Structured Output Parser is a mechanism that forces an AI model to return data in a predefined structure (usually JSON) instead of free-form text.

Instead of asking an LLM:

    "Summarize this report."

and getting:

    Business is doing well.

    Revenue increased.

    Inventory has some issues.

you define a schema like:

    {
        "businessHealth": "string",
        "summary": "string",
        "revenue": {
            "status": "string",
            "value": "number"
        },
        "risks": [
            {
                "severity": "string",
                "issue": "string"
            }
        ]
    }

The model then returns:

    {
        "businessHealth": "Healthy",
        "summary": "Revenue increased while inventory remained stable.",
        "revenue": {
            "status": "Increasing",
            "value": 95000
        },
        "risks": [
            {
                "severity": "Medium",
                "issue": "Inventory mismatch in Store 103"
            }
        ]
    }

A Structured Output Parser doesn't just ask the AI to return JSON—it also validates the JSON against a schema. The schema defines:

* Which fields are required
* Which fields are optional
* The data type of each field
* Allowed values (enum)
* Minimum/maximum values
* String length
* Array size
* Nested object structure

#### Example 1: Required vs Optional Fields

JSON Schema
```
{
  "type": "object",
  "properties": {
    "businessHealth": {
      "type": "string"
    },
    "summary": {
      "type": "string"
    },
    "revenue": {
      "type": "number"
    },
    "recommendation": {
      "type": "string"
    }
  },
  "required": [
    "businessHealth",
    "summary",
    "revenue"
  ]
}
```

Here:

Required fields:

* ✅ businessHealth
* ✅ summary
* ✅ revenue

Optional field:

* recommendation

Valid Output
```
  {
  "businessHealth": "Healthy",
  "summary": "Revenue increased",
  "revenue": 95000
  }
```

Passes validation.

#### Example 2: Validate Data Types

JSON Schema
```
{
  "type": "object",
  "properties": {
    "score": {
      "type": "number"
    }
  },
  "required": [
    "score"
  ]
}
```

#### Example 3: Enum Validation
Allow only three values.

```
{
  "type": "object",
  "properties": {
    "businessHealth": {
      "type": "string",
      "enum": [
        "Healthy",
        "Warning",
        "Critical"
      ]
    }
  },
  "required": [
    "businessHealth"
  ]
}
```


#### Example 4: Number Validation

```
{
  "score": {
    "type": "number",
    "minimum": 0,
    "maximum": 100
  }
}
```

#### Example 5: String Length
```
{
  "summary": {
    "type": "string",
    "minLength": 20,
    "maxLength": 200
  }
}
```

#### Example 6: Array Validation
```
{
  "issues": {
    "type": "array",
    "minItems": 1,
    "items": {
      "type": "string"
    }
  }
}
```

#### Example 7: Nested Object Validation
```
{
  "type": "object",
  "properties": {
    "revenue": {
      "type": "object",
      "properties": {
        "trend": {
          "type": "string"
        },
        "amount": {
          "type": "number"
        }
      },
      "required": [
        "trend",
        "amount"
      ]
    }
  },
  "required": [
    "revenue"
  ]
}
```

#### Example 8: Restrict Extra Fields

Set additionalProperties: false to avoid AI inventing new fields.

```
{
  "type": "object",
  "additionalProperties": false,
  "properties": {
    "summary": {
      "type": "string"
    }
  }
}
```

Sample

```
{
  "type": "object",
  "additionalProperties": false,
  "properties": {
    "date": {
      "type": "string"
    },
    "businessHealth": {
      "type": "string",
      "enum": [
        "Healthy",
        "Warning",
        "Critical"
      ]
    },
    "revenue": {
      "type": "number",
      "minimum": 0
    },
    "recommendations": {
      "type": "array",
      "minItems": 1,
      "items": {
        "type": "string"
      }
    },
    "directorSummary": {
      "type": "string"
    }
  },
  "required": [
    "date",
    "businessHealth",
    "revenue",
    "recommendations",
    "directorSummary"
  ]
}
```

This guarantees:

* Date must exist.
* Business health must be Healthy, Warning, or Critical.
* Revenue must be a non-negative number.
* At least one recommendation must be present.
* No unexpected fields are allowed.

Valid JSON 

```
{
  "date": "2026-07-09",
  "businessHealth": "Warning",
  "revenue": 98500,
  "recommendations": [
    "Investigate inventory mismatches in Store 105.",
    "Monitor delayed order synchronization between OMS and RMS.",
    "Review failed payment transactions before end of day."
  ],
  "directorSummary": "Revenue remains strong, but inventory synchronization issues require attention. Immediate monitoring is recommended to prevent operational impact."
}
```


AI Prompt Example - Analyze the business report and return only valid JSON that conforms to the provided schema. Do not include explanations, markdown, or additional fields. Ensure all required fields are present and enum values exactly match the schema.


### How to integrate in n8n

In AI agent, enable Require Specific Output Format. 

This give option to add a node in output format

Add Structure Output Parser as a tool and define the schema


### How Validation work internally

```
                Prompt
                   │
                   ▼
         JSON Schema Attached
                   │
                   ▼
               AI Response
                   │
                   ▼
        Is it valid JSON?
              │         │
            Yes         No
             │           │
             ▼           ▼
      Schema Validation  Retry/Error
             │
     ┌───────┴────────┐
     │                │
   Valid          Invalid
     │                │
     ▼                ▼
 Continue      Retry / Fix / Fail
```