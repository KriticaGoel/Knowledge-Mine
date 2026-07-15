Annotations are used for the following purposes:

1. **Information for the compiler** — Annotations can be used by the compiler to detect errors or suppress warnings.
2. **Compile-time and deployment-time processing** — Software tools can process annotation information to generate code,
   XML files, and so forth.

![Annotation.png](..%2F..%2F..%2Fresources%2FAnnotation.png)
### Categories of Annotations

Annotations can be divided into the following categories:

1. **Marker annotations** - These annotations contain no members. They simply mark the declaration to which they apply
   with some additional information. Example: @Override, @Deprecated, @SuppressWarnings.
2. **Single-value annotations** - These annotations contain a single member. Example: @SuppressWarnings("unchecked").
3. **Full annotations** - These annotations contain multiple members. Example: @Author(name = "John Doe", date = "
   3/17/2002")