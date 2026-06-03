    Application Startup
        │
        ▼
    RegistryLoader
         │
    Create expensive objects once
        │
        ▼
    +-------------------+
    | Student Registry  |
    |-------------------|
    | ENG → template    |
    | MED → template    |
    | MBA → template    |
    +-------------------+

================ Runtime ================

    Client
      │
    registry.get("ENG")
      │
      ▼
    Clone Template
      │
      ▼
    New Object Returned