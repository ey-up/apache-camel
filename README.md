# Apache Camel Example: Logging POST Request Body

When you execute the following cURL command:
### 
```
curl --location --request POST 'http://localhost:8080/camel/log' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "John",
    "age": 30,
    "school": "Cambridge"
}
'
```


It will send a POST request with a JSON body. Apache Camel will then log the body of the request and save it into an `output.txt` file.

This is an example of how Apache Camel's logging component can be used to capture incoming request data and store it for later use or debugging purposes.
