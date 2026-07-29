# gRPC Java – Country-Capital Key-Value Server

A gRPC server written in Java that stores and retrieves country-to-capital mappings in-memory using a `HashMap`. The service is defined with Protocol Buffers and exposes two RPC methods over port `9090`.

## Prerequisites

- Java 8+ (OpenJDK 8 recommended)
- Maven 3.x

## Project Structure

```
common_proto_files/
  javamemcache.proto        # Protobuf service definition
src/main/java/com/grpc/javamemcache/
  MemCacheServer.java       # Server entry point (port 9090)
  MemCacheService.java      # RPC method implementations
  proto/
    Javamemcache.java       # Generated protobuf classes
    MemCacheGrpc.java       # Generated gRPC stubs
```

## Service Definition

The `MemCache` service (defined in `common_proto_files/javamemcache.proto`) exposes two RPCs:

| RPC     | Request        | Response  | Description                          |
|---------|----------------|-----------|--------------------------------------|
| `add`   | `EnterCountry` | `Status`  | Store a country → capital mapping    |
| `query` | `Capital`      | `Country` | Look up the capital for a country    |

### Messages

```protobuf
message EnterCountry { string country = 1; string capital = 2; }
message Capital      { string country = 1; }
message Country      { string capital = 1; }
message Status       { string responseMessage = 1; int32 responseCode = 2; }
```

## Build & Run

### 1. Build the project

```bash
mvn clean install
```

This compiles the protobuf definition and packages the JAR.

### 2. Run the server

```bash
mvn exec:java -Dexec.mainClass="com.grpc.javamemcache.MemCacheServer"
```

The server starts and listens on `localhost:9090`.

## Using a gRPC Client

You can test the service with [BloomRPC](https://github.com/bloomrpc/bloomrpc) or any other gRPC client:

1. Open the client and import `common_proto_files/javamemcache.proto`.
2. Set the target to `localhost:9090`.
3. Call `add` with a country and capital to store a mapping.
4. Call `query` with a country name to retrieve its capital.

## Dependencies

| Artifact                        | Version |
|---------------------------------|---------|
| `io.grpc:grpc-netty-shaded`     | 1.38.0  |
| `io.grpc:grpc-protobuf`         | 1.38.0  |
| `io.grpc:grpc-stub`             | 1.38.0  |
| `org.slf4j:slf4j-simple`        | 1.7.30  |
| `org.apache.tomcat:annotations-api` | 6.0.53 |

