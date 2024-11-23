# jersey-async-rest
Async Jersey REST implementation

### APIs 
1. Ping API
    ```bash
    curl 'http://localhost:8080/api/ping' \
      -H 'Accept: application/json' \
      -H 'Connection: keep-alive' \
      --compressed
    ```
    Response:
    ```json
    {"message":"pong"}
    ```

2. Async API (This request will take approx 4 second to return response)
    ```bash
    curl 'http://localhost:8080/api/async-ping' \
      -H 'Accept: application/json' \
      -H 'Connection: keep-alive' \
      --compressed
    ```
    ```json
    {"message":"Response from async operation !!!"}
    ```

3. For Streaming Input Stream using Async Jersey REST
    ```bash
    curl 'http://localhost:8080/api/stream' \
      -H 'Accept: application/json' \
      -H 'Connection: keep-alive' \
      --compressed
    ```

### build and run application
build and run application from gradle
```bash
./gradlew clean build run
```

### run docker
run application from docker
```bash
docker build -t jersey-async-rest/jersey_async_app:latest .
docker run --name jersey_async_app -p 8080:8080 jersey-async-rest/jersey_async_app:latest
```
### stop docker
stop running application from docker
```bash
docker stop jersey_async_app
```
