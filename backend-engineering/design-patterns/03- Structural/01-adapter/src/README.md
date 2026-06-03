# Adapter Pattern - Third-Party System Configuration

This example adapts a third-party REST API response into the local `SystemConfiguration` model and then persists it using the existing save flow.

## How It Works

- `HttpThirdPartySystemConfigApi` calls the third-party API.
- `BasicThirdPartyConfigMapper` maps the raw payload to `SystemConfiguration`.
- `SystemConfigurationAdapter` wires the API and mapper together.
- `SaveSystemConfiguration` persists the data (simulated with console output).

## Run

### Use the fake API (default)

```powershell
javac D:\Workspace\LLD\src\designPattern\structural\adapter\*.java
java -cp D:\Workspace\LLD\src designPattern.structural.adapter.Main
```

### Use the real third-party API

Set the env vars and run the same commands:

```powershell
$env:THIRD_PARTY_URL = "https://example.com/path"
$env:THIRD_PARTY_TOKEN = "<your-token>"
javac D:\Workspace\LLD\src\designPattern\structural\adapter\*.java
java -cp D:\Workspace\LLD\src designPattern.structural.adapter.Main
```

