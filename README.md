# Users Client Challenge

* Main Class: com.sidgul.challenge.users.client.UsersClientDriver
* **Please note that this was a change in requirement to keep the UsersClient class highly cohesive. So, separated the main method to UsersClientDriver**
* UsersClient uses
  - _configuration_ (Properties) to get the API URL to call to get users data
  - _UsersClientFactory_ - uses the factory pattern to isolate the creation of IUsersClient
  - _IUsersClient_ uses the strategy pattern to abstract better quality implementations for the UsersClient
  - _IHttpClient_ - uses the adapter pattern to provide a quick and dirty HTTP client abstraction. Can also be used to plugin MockClients while testing.
  - _IUsersUnmarshaller_ - uses teh adapter pattern to provide a quick and dirty JSON unmarshaller for the constructing objects out of JSON data from the API. When hitting a different API a new unmarshalling implementation can be plugged in.
* Use the below maven commands to build, test, execute and to get documentation
* Configure using application.properties (see details below)

## Pre-requisites
* Java JDK 17+
* Maven 3.6+

## Usage

### Configure
Use src/main/resources/application.properties to configure the application.
Use the below property in application.properties to configure the REST API URL:
```properties
users.client.api.url = https://reqres.in/api/users
```

### Build
Use maven to build:
```shell
mvn clean install
```

### Test
Use maven to test:
```shell
mvn test
```

### Run
Use maven to execute the main method in com.sidgul.challenge.users.client.UsersClientDriver. 
**Please note that this was a change in requirement to keep the UsersClient class highly cohesive. So, separated the main method to UsersClientDriver**
```shell
mvn exec:java
```

## Documentation
You can generate javadoc using the following:
```shell
mvn javadoc:javadoc
```
Documentation will be accessible at target/site/apidocs/index.html 

## Releases

### Version 0.1.0

## License

The MIT License (MIT)

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
