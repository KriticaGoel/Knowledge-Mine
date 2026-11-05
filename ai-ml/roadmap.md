Phase A — Engineering foundations

Modules 1–4 · Java, Spring Boot, Python, SQL, HTTP, testing

Build the reliable backend that AI capabilities will run inside.




Phase B — LLM application fundamentals

Modules 5–8 · AI/LLM concepts, model APIs, prompting, structured outputs

Learn to integrate models predictably into software.




Phase C — Knowledge and retrieval

Modules 9–11 · Embeddings, vector search, RAG, ingestion, evaluation

Make AI answer from your data and measure whether the answers are correct.




Phase D — Tools and controlled automation

Modules 12–14 · Tool calling, workflows, agents, memory, MCP

Connect the model to real business systems without handing it uncontrolled access.




Phase E — Production readiness

Modules 15–19 · Security, testing, observability, performance, deployment, system design

Make the system secure, testable, measurable, scalable, and maintainable.




Phase F — Expertise and career readiness

Modules 20–21 · Portfolio, advanced AI topics, interview preparation

Demonstrate your skills through projects, technical decisions, and interview answers. 
 

1.Python for the AI ecosystem 
   - Syntax, variables, types, operators and control flow.
   - Strings, lists, tuples, sets and dictionaries.
   - Functions, arguments (default arguments, *args, **kwargs), return values, Scope,  lambdas and comprehensions.
   - Classes, objects, inheritance.
   - Exceptions, modules, packages and imports.
   - File handling, JSON and CSV.
   - List/dictionary manipulation and data transformations.
   - Virtual environments, pip, dependency management and environment variables.
   - HTTP requests, REST APIs and API authentication.
   - Type hints,unions and protocols at a basic level, dataclasses and basic logging.
   - async/await fundamentals.
   - Unit testing with pytest.
   - NumPy arrays and vector operations.
   - Basic pandas DataFrame operations.
   - Reading and cleaning datasets.
   - Basic plotting when investigating evaluation results.
   - Jupyter notebooks for experiments.
   - Using a model SDK and embedding APIs from Python. 
  
Practice project: Call an LLM API from Python, process a support ticket, validate the returned JSON, and handle API failures.

Do not prioritize yet: advanced NumPy mathematics, data-science visualization, or extensive Python frameworks.

2. AI, machine learning and LLM fundamentals
   - AI vs ML vs deep learning vs generative AI.
   - Supervised, unsupervised and reinforcement learning—high-level distinctions.
   - Training, inference, parameters, weights and model checkpoints.
   - Neural network fundamentals: neurons, layers, activation functions.
   - What transformers are and why they are used for language models.
   - Tokens and tokenization.
   - Embeddings versus generated tokens.
   - Context windows and context limits.
   - Transformer attention and self-attention—conceptual understanding.
   - Pretraining versus fine-tuning versus inference.
   - Temperature, top-p and output randomness.
   - Open-source/open-weight versus hosted models.
   - Model selection: quality, latency, context size, privacy and cost.
   - Hallucinations and why fluent answers can still be wrong.
   - Multimodal models: text, image, audio—overview.

     Interview checkpoint: Explain what happens when an LLM receives a prompt and generates a response. You do not need to implement a transformer from scratch.
3. LLM APIs, prompting and structured outputs (JAVA)
   
   - API keys, authentication, HTTP requests and response parsing.
   - Chat/message formats and system, user and assistant messages.
   - Prompt structure and instruction hierarchy.
   - Zero-shot and few-shot prompting.
   - Context construction and prompt templates.
   - Output constraints and structured JSON generation.
   - Stop sequences and token limits.
   - Model errors, timeouts, rate limits and quota failures.
   - Retry with backoff, request timeouts and cancellation.
   - Streaming responses.
   - Token consumption and cost estimation.
   - Model routing and choosing a model for a task.
   - API versioning and provider abstraction.
   - Prompt versioning and regression testing.
   - Managing secrets safely.
   
Practice project: Build a ticket classifier that produces a schema-validated response with category, priority, evidence and explanation.
4. Spring AI in depth
  
- Spring AI architecture and supported model providers.
- Chat model APIs and message abstractions.
- Prompt templates.
- Model configuration and options.
- Chat memory versus persistent application storage.
- Structured output converters and validation.
- Streaming chat.
- Embedding model integration.
- Vector store abstraction and retrieval APIs.
- Advisors and request/response processing pipelines.
- Tool/function calling.
- Observability and model-provider error handling.
- Provider-specific configuration and testing with mock responses.
  Practice project: A Spring Boot REST service that calls an LLM and returns validated JSON, with timeouts, logs and tests.
- 
5. Reliable LLM application workflows

   Before RAG and agents, learn how to make a simple LLM application dependable.
   - Request/response lifecycle.
   - DTOs and schema validation.
   - JSON parsing and malformed output handling.
   - Deterministic business rules versus probabilistic model output.
   - Input normalization and output validation.
   - Synchronous versus asynchronous processing.
   - Streaming versus complete responses.
   - Timeouts, retries, backoff and circuit breakers.
   - Idempotency for operations that may be retried.
   - Rate limiting and concurrency control.
   - Caching and when caching AI responses is safe.
   - Fallback behavior and graceful degradation.
   - Separating model orchestration from business logic.
   - Correlation IDs and tracing a request across services.
   - Human review for high-impact or uncertain outcomes.
Practice checkpoint: Your service must return a controlled error if the LLM is unavailable; it must not create an invalid order or invent a successful result.
6. Embeddings, search and vector databases
   - What an embedding represents.
   - Embedding dimensions and similarity.
   - Cosine similarity, dot product and Euclidean distance.
   - Semantic search versus keyword search.
   - Embedding models and model selection.
   - Why document and query embedding models must be compatible.
   - Vector indexes and nearest-neighbor search.
   - Approximate nearest neighbor (ANN) search—conceptual understanding.
   - Vector-store schemas and metadata.
   - Metadata filtering and tenant isolation.
   - Upserts, deletions and document updates.
   - Indexing latency and retrieval latency.
   - Hybrid search: keyword plus vector retrieval.
   - Reranking—what it does and when to use it.
   - Vector-store trade-offs: local, managed and database-integrated options.

Choose one vector store initially. Learn its actual integration and retrieval behavior before comparing multiple databases.

Practice project: Store delivery-policy documents and retrieve relevant passages for a given question.
7. RAG (Retrieval-Augmented Generation)
   RAG is one of the most valuable skills for your target role.

   * Document ingestion
     - PDF, text, Markdown and HTML ingestion.
     - Document loaders and parsing.
     - OCR basics for scanned documents.
     - Cleaning, normalization and deduplication.
     - Chunk size, overlap and chunking strategies.
     - Metadata extraction and document IDs.
     - Embedding generation and indexing.
     - Incremental updates and deletion of stale content.
   * Retrieval
     - Query preprocessing and query rewriting.
     - Semantic retrieval and top-k selection.
     - Similarity thresholds.
     - Metadata filters.
     - Hybrid retrieval.
     - Reranking.
     - Multi-query retrieval—advanced.
     - Parent-child retrieval—advanced.
   * Generation
     - Combining the question and retrieved context.
     - Context ordering and token budgets.
     - Grounded answers and source citations.
     - Handling insufficient or conflicting evidence.
     - Abstaining instead of fabricating an answer.
     - Conversation history and conversational RAG.
     - Retrieval access controls.
   * Advanced RAG
     - Query decomposition.
     - Multi-hop retrieval.
     - Corrective or adaptive retrieval.
     - Graph RAG—learn later, only if justified.
     - Multimodal RAG—learn later.

Practice project: Create a document assistant that answers questions about delivery policies, cites the source passage, and admits when the documents do not contain the answer
 
8. RAG evaluation and AI testing

   A working demo is not proof that your RAG system is good.
   - Build a representative evaluation dataset.
   - Define expected answers and supporting source passages.
   - Retrieval precision and recall.
   - Context relevance and context coverage.
   - Answer correctness and faithfulness to retrieved evidence.
   - Citation correctness.
   - Hallucination and abstention tests.
   - Compare chunk sizes, embedding models and retrieval settings.
   - Test queries with no relevant documents.
   - Regression tests when prompts or models change.
   - Human evaluation and model-assisted evaluation.
   - Offline evaluation versus production monitoring.
   - Latency and cost measurement.
   - Common RAG evaluation tools and frameworks—know the concepts before selecting one.

Practice checkpoint: Demonstrate how you discovered a retrieval failure, changed the configuration, and measured whether the result improved.

9. Tool/function calling and business workflows
   
Learn this before implementing autonomous agents.
   - What tool calling is.
   - Tool definitions, names, descriptions and JSON schemas.
   - How a model requests a tool call.
   - How your application validates and executes the request.
   - Passing tool results back to the model.
   - Multiple tool calls and sequencing.
   - Tool errors and invalid arguments.
   - Timeouts, retries and bounded execution.
   - Authorization outside the model.
   - Read-only versus state-changing tools.
   - Idempotency and duplicate operations.
   - Audit logs and approval for sensitive actions.

Practice project: Let an assistant query a mock order-status API and inventory API. The Java application—not the LLM—must enforce the customer's permissions

10. AI Agents workflow
    - Agent versus a fixed workflow.
    - Model-driven planning and tool selection.
    - Observe–decide–act loops.
    - State and memory.
    - Multi-step task execution.
    - Stop conditions and iteration limits.
    - Tool selection and error recovery.
    - Planning versus deterministic orchestration.
    - Single-agent versus multi-agent systems.
    - Human-in-the-loop approval.
    - Workflow checkpoints and resumability.
    - Persistent state and session management.
    - Agent evaluation and reproducibility.
    - Failure loops, excessive tool calls and runaway costs.
    - When an agent is unnecessary and a normal service is better.

Practice project: An operations assistant that retrieves a delivery policy, checks authorized order status, summarizes the evidence and recommends a next step. Start with a controlled workflow; add autonomous planning only if it improves results.

11. AI memory and conversational systems
  
Core concepts; implementation as needed
  - Conversation history versus durable memory.
  - Short-term versus long-term memory.
  - Chat history storage and retrieval.
  - Context-window management and summarization.
  - Session isolation and user identity.
  - Memory expiration and deletion.
  - Preventing data leakage between users.
  - When to use a database instead of a vector store for structured state.

Do not assume that putting all previous messages into a prompt is a scalable memory strategy.

12. AI security and guardrails
    
    Core concepts; implementation as needed
    - Prompt injection and indirect prompt injection through retrieved documents.
    - System instructions versus untrusted user/document content.
    - Data leakage and sensitive information exposure.
    - Authentication and authorization for tools.
    - Tenant isolation in retrieval systems.
    - Output validation and safe rendering.
    - Input validation and abuse prevention.
    - PII detection and redaction where appropriate.
    - Secrets and API-key management.
    - Data retention and deletion policies.
    - Guardrails for high-impact operations.
    - Human approval for consequential actions.
    - Content moderation where relevant.
    - Auditability and traceability.
    - Threat modelling for an LLM-powered application.

Key principle: A prompt telling the model not to expose data is not a replacement for access control enforced by your application.
13. Testing and quality engineering
    - Unit tests for normal business logic.
    - Mocking model responses.
    - Integration tests for model and vector-store adapters.
    - Contract tests for internal and external APIs.
    - End-to-end tests.
    - Schema validation tests.
    - RAG evaluation tests.
    - Tool authorization tests.
    - Prompt injection and data leakage tests.
    - Timeouts, rate limits and provider outage tests.
    - Regression suites for prompt/model changes.
    - Load and concurrency testing.
    - Reproducible test datasets.
    - Test isolation and avoiding accidental API costs.

Deliverable: A repeatable test suite that can catch broken behavior when the model, prompt or retrieval configuration changes.
14. Observability, performance and cost
    - Structured application logging.
    - Correlation IDs and distributed tracing.
    - Tracking prompt, retrieval, model and tool-call stages.
    - Latency measurements and p50/p95.
    - Token usage and per-request cost.
    - Model error and timeout rates.
    - Retrieval quality and citation failure rates.
    - Rate limits and provider quotas.
    - Caching strategies and cache invalidation.
    - Batching where supported.
    - Model selection and fallback trade-offs.
    - Connection pools and concurrency limits.
    - Monitoring cost and latency regressions.
    - Alerts and operational dashboards.
    - Privacy-aware logging.
15. Deployment and operations
    - Environment-based configuration.
    - Docker images and containers.
    - Secrets management.
    - Health and readiness endpoints.
    - CI/CD fundamentals.
    - Integration-test execution in pipelines.
    - Database migrations.
    - Deploying a Spring Boot service.
    - Basic cloud concepts: compute, storage, networking and identity.
    - HTTPS, DNS and reverse proxies.
    - Monitoring and log access.
    - Rollbacks and configuration changes.
    - Model-provider outages and fallback plans.
    - Resource sizing and deployment cost. 
Defer initially: deep Kubernetes, complex MLOps platforms and elaborate multi-cloud infrastructure unless the jobs you target require them.
16. GenAI system design 

You need to explain how your AI feature works as part of a larger production system.
    - AI-enabled REST API design.
    - Request validation and response contracts.
    - Synchronous versus asynchronous AI processing.
    - Queue-based workloads and background processing.
    - Rate limiting and quotas.
    - Caching and invalidation.
    - Database and vector-store choices.
    - Document ingestion pipelines.
    - RAG architecture and retrieval scaling.
    - Model-provider abstraction.
    - Concurrency and connection-pool sizing.
    - Resilience and failure isolation.
    - Tenant isolation and data permissions.
    - Latency and cost budgets.
    - Idempotency and duplicate work.
    - Audit trails and human approval.
    - Monitoring, alerting and incident response.
    - Capacity planning and scaling bottlenecks.
    - Trade-offs between hosted and locally deployed models. 

Design exercises
1. Design a PDF knowledge assistant for 100,000 documents.
2. Design a retail customer-support assistant that checks live order status.
3. Design an AI service for thousands of concurrent users.
4. Design a safe workflow that recommends refunds but cannot issue them without authorization.
5. Diagnose a RAG application with high latency and poor answer quality.