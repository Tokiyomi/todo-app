# todo-app
Spring Boot Java Project: Todo-App using in-memory storage with Java Collections

# Run this project
Enter to the root project folder and run the following command:
```
./mvnw spring-boot:run
```

Project runs in [http://localhost:9090](http://localhost:9090)

# Controller-Service-Repository Pattern

 - **Controller:** The Controller layer, at the top of this picture, is solely responsible for exposing the functionality so that it can be consumed 
 by external entities (including a UI component).
 - **Service:** The Service layer is where all the business logic should go.
 - **Reposiroty:** Java Collections CRUD operations are here.
 
 
![spring-pattern](https://t1.daumcdn.net/cfile/tistory/999D30415CEA2C1011)


