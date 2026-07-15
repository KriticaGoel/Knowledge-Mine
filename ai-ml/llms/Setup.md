
## Install OLLAMA and LLM
1. Download Ollama from https://ollama.com
2. Install it
3. Add to class path use command sysdm.cpl
4. open new terminal run commands
5. ollama --version
6. Start Ollama Service - ollama serve
7. Download Model GEMMA version 3 and downloading tiny size 270million and run on system - >ollama run gemma3:270m
8. To stop conversation press ctl+D
9. List all downloaded models -ollama list
10. Remove a specific model (Disk file removed) - ollama rm <model-name>
11. Verify it's removed - ollama list
12. currently running models- ollama ps
13. Stop Model (Model file still exists on disk but not on RAM) - ollama stop <model-name>
14. Start model - ollama run < model-name>
    ```
    Load model weights from disk
    Put them into RAM/VRAM
    Start inference
    ```
## Install Git (if not already installed):
1. Open a new PowerShell Prompt (start menu >> PowerShell). If you ever have permissions errors, try opening the Powershell by right clicking and selecting "run as Administrator"
2. Run the command git and see if it responds with details of the command or an error
3. If you get an error, download Git from https://git-scm.com/download/win
4. Run the installer and follow the prompts, using default options (press OK lots of times!)

### Create projects directory as needed
1. Open a new PowerShell prompt, as in prior step. You should be in your home directory, like D:\Workspace\AI-Core
2. Do you have a projects' directory? Find out by typing cd projects
3. If that has an error, then create a projects directory: mkdir projects then cd projects
4. Now you should be in D:\Workspace\AI-Core\projects
5. You can locate this anywhere convenient for you, but avoid any directories that are on your OneDrive

## Cursor Install Cursor if needed and open the project
Visit https://cursor.com

Click Download for Windows. Then run the installer. Accept and pick defaults for everything.

###  Installing the uv then doing a uv sync

uv is a modern Python package manager.

go to https://docs.astral.sh/uv/getting-started/installation/
Execute the command and close the power terminal and reopen it
check version - uv --version
Update UV - uv self update
to create complete environment uv sync

### Set up OpenAI Account
https://platform.openai.com

Generate Key
and add in project inside .env file

### Cursor plugin
Python
Jypiter

### pyproject.toml
this is dependency file contain all dependency at one place and use uv sync to download the listed dependencies in system

