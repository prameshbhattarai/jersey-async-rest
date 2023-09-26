# jersey-async-rest
Async Jersey REST implementation

### APIs 
1. Ping API
    ```bash
    curl 'http://localhost:8080/api/ping' \
      -H 'Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7' \
      -H 'Accept-Language: en-US,en;q=0.9,sv;q=0.8' \
      -H 'Cache-Control: max-age=0' \
      -H 'Connection: keep-alive' \
      -H 'Sec-Fetch-Dest: document' \
      -H 'Sec-Fetch-Mode: navigate' \
      -H 'Sec-Fetch-Site: none' \
      -H 'Sec-Fetch-User: ?1' \
      -H 'Upgrade-Insecure-Requests: 1' \
      -H 'User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/117.0.0.0 Safari/537.36' \
      -H 'sec-ch-ua: "Google Chrome";v="117", "Not;A=Brand";v="8", "Chromium";v="117"' \
      -H 'sec-ch-ua-mobile: ?0' \
      -H 'sec-ch-ua-platform: "macOS"' \
      --compressed
    ```
    Response:
    ```json
    {"message":"pong"}
    ```

2. Async API (This request will take approx 4 second to return response)
    ```bash
    curl 'http://localhost:8080/api' \
      -H 'Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7' \
      -H 'Accept-Language: en-US,en;q=0.9,sv;q=0.8' \
      -H 'Connection: keep-alive' \
      -H 'Sec-Fetch-Dest: document' \
      -H 'Sec-Fetch-Mode: navigate' \
      -H 'Sec-Fetch-Site: none' \
      -H 'Sec-Fetch-User: ?1' \
      -H 'Upgrade-Insecure-Requests: 1' \
      -H 'User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/117.0.0.0 Safari/537.36' \
      -H 'sec-ch-ua: "Google Chrome";v="117", "Not;A=Brand";v="8", "Chromium";v="117"' \
      -H 'sec-ch-ua-mobile: ?0' \
      -H 'sec-ch-ua-platform: "macOS"' \
      --compressed
    ```
    ```json
    {"message":"Response from async operation !!!"}
    ```

### build and run application
build and run application from gradle
```bash
./gradlew clean build run
```

### run docker
run application from docker
```bash
docker build -t jersey_async_app .  
docker container rm jersey_async_app
docker run --name jersey_async_app -p 8080:8080 jersey_async_app
```
