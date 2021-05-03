# Wchallenge-jsonplaceholder

Develop an API to consume an external service and use its information, in addition, the scope of development has to be expanded by adding one more functionality that allows new information to persist in the system, related to the data of the external API.


Develop with java-Spring-boot


It was used PosgretSQL to DB, there is a docker-compose to create


To run the docker compose, you need the docker desktop in case of windows, if you don't have it here is the documentation:
[Docker Desktop]( https://docs.docker.com/docker-for-windows/install/)

To upload the DB, you open a new terminal in the project path and run the following command:
_docker-compose up -d_

## Import Collection to Postman

- To import collection to postman


![image](https://user-images.githubusercontent.com/60399935/116834046-056c1680-ab82-11eb-9780-ab22a87a8954.png)



- then go to where is json


![image](https://user-images.githubusercontent.com/60399935/116834078-22a0e500-ab82-11eb-80c2-c42b0010dd78.png)


![image](https://user-images.githubusercontent.com/60399935/116834142-51b75680-ab82-11eb-8361-6cd8530a8d11.png)



- Now you can see all request:

![image](https://user-images.githubusercontent.com/60399935/116834156-6693ea00-ab82-11eb-8154-11fdb94dd8d6.png)


### Now you can run the aplication as a Spring application and you can Request all



- This is the schema in the database

![image](https://user-images.githubusercontent.com/60399935/116834305-0d788600-ab83-11eb-89a1-75325519d7fd.png)


## If you waht see the api docs:
- After you run app you can navigate to:

http://localhost:8080/swagger-ui.html
 to see documentation

 ![image](https://user-images.githubusercontent.com/60399935/116835854-e3769200-ab89-11eb-81a3-091c676358de.png)
