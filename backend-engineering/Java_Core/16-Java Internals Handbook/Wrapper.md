
## Wrapper Classes

| Primitive | Wrapper     |
| --------- | ----------- |
| `byte`    | `Byte`      |
| `short`   | `Short`     |
| `int`     | `Integer`   |
| `long`    | `Long`      |
| `float`   | `Float`     |
| `double`  | `Double`    |
| `char`    | `Character` |
| `boolean` | `Boolean`   |

## Why do we need wrappers?

Wrappers are needed to allow primitive types to be used in contexts that require objects,
such as in collections (e.g., `ArrayList<Integer>`), and
to provide utility methods for converting between primitives and their object representations.

```
ArrayList<int> numbers;       // ❌
ArrayList<Integer> numbers;   // ✅
```

## Autoboxing and Unboxing

Autoboxing is the automatic conversion that the Java compiler makes between
primitive types and their corresponding wrapper classes.
For example, converting an `int` to an `Integer`, a `double` to a `Double`, etc.

```
int x = 10;

Integer y = x;

Integer y = Integer.valueOf(x);
```

Unboxing is the reverse process, where the Java compiler automatically converts a
wrapper object to its corresponding primitive type.

```
Integer y = 10;  // Autoboxing
int x = y;       // Unboxing

int y = x.intValue();
```

> == compares values for primitives and references for objects. When a wrapper is compared with a primitive, unboxing can occur and the values are compared.

## Wrapper Arithmetic

```
Integer a = 10;
Integer b = 20;

Integer c = a + b;
```

```
Integer a
    ↓ unbox
int 10

Integer b
    ↓ unbox
int 20

10 + 20
    ↓
int 30
    ↓ autobox
Integer 30
```

>Wrapper arithmetic → unboxing → operation → boxing if required

## Null + Unboxing

```
Integer x = null;

int y = x;
```

Compiles successfully but throws:

NullPointerException

Because conceptually:
```
x.intValue();
```
You cannot unbox null.

## Integer Cache

Java caches commonly used Integer objects.

Guaranteed cache:
```
-128 to 127
```
Therefore:
```
Integer a = 100;
Integer b = 100;

a == b       // true
a.equals(b)  // true
```
Both can point to the same cached object.

But:
```
Integer a = 1000;
Integer b = 1000;

a == b       // normally false
a.equals(b)  // true
```
Because 1000 is outside the guaranteed cache range.

Golden rule

>Never use == to compare wrapper values. Use equals().

## Wrapper Caching

| Wrapper     | General caching              |
| ----------- | ---------------------------- |
| `Byte`      | ✅ All values                 |
| `Short`     | ✅ Small values               |
| `Integer`   | ✅ `-128` to `127` guaranteed |
| `Long`      | ✅ `-128` to `127` guaranteed |
| `Character` | ✅ `0` to `127` guaranteed    |
| `Boolean`   | ✅ `true` / `false`           |
| `Float`     | ❌ No general cache           |
| `Double`    | ❌ No general cache           |
