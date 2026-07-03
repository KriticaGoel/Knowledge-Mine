https://openrouter.ai/
Create API key

https://n8n.io/

https://kritica.app.n8n.cloud/

n8n
add chat -> ai agent -> openrouterapi

**LLM** :

An AI program that generates text to answer questions or decide on actions to take.

Give input -> Generate Output

It's a pattern matcher that uses statistics to predict likely responses, from analyzing vast amounts of data.

GPT is a LLM and ChatGPT is an application(end user product) that uses GPT llm to answer users question


**AI Agent** :

An agent run tools in a loop to achieve a goal
^
|
AI systems where an LLM controls the workflow
^
|
AI system that can do work for u independently


Second Program add Simple Memory

add chat -> ai agent -> openrouterapi -> Simple memory



Language support python or js

There are two type parameter in node 
1. Fixed
2. Experssion

* Anything in {{Expression}}  in json

* To read json of current node
  * {{$json.name}}
  * {{$json.address.city}}

* To read data from previous node
  * {{ $node[''Previous node name'].json }}

* To turn json into text
   * {{JSON.stringify($json}}

### Authentication Pattern
1. API based
2. Login-styled, preconfigured OAUTH2 - n8n already done the setup like google sheet 
3. OAuth2 - Authenticate third party by own

Integrations:
1. MarketStack Tool
2. Gmail / Google Sheet
2. Push Notification using push over
3. Telegram
4. Slack

Command create a bot in telegram
1. Search BotFather
2. click StartBot
3. /newbot
4. follow the instructions
5. Integrate telegram in n8n
6. Create trigger Telegram so that if any message came on telegram workflow execute
7. create ai agent -> language model - memory - tool nad next action - execeute it
8. in AI agent change source of prompt to telegram and the prompt
![img.png](Basic/2-way%20integration%20using%20Telegram/resources/img.png)
9. Simple memory -> define the prompt and add previous node chat id so that it capture data
![SimpleMemory.png](Basic/2-way%20integration%20using%20Telegram/resources/SimpleMemory.png)
10. Edit next action telegram to add message and chat dynamically
![Message.png](Basic/2-way%20integration%20using%20Telegram/resources/Message.png)
![Chat ID.png](Basic/2-way%20integration%20using%20Telegram/resources/Chat%20ID.png)


