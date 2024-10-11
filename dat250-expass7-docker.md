# DAT250 Experiment 7 - Docker - Documenting the experiments

## Setup

Docker running on the machine
![image](https://github.com/user-attachments/assets/dd7fb92d-8fbb-4714-a420-87dab8fa572f)

## Task 1: Using a Dockerized application: PostgreSQL

Downloading the docker image
![image](https://github.com/user-attachments/assets/29636e4e-3662-48d0-b6d6-2b15b01e757d)

Then used the following command to run

```bash
docker run -p 5432:5432 -e POSTGRES_USER=user -e POSTGRES_PASSWORD=testpassword123 -d --name my-postgres --rm postgres
```

- p uses the default postgres port, and it makes sure to map the containers internal port to the same port on the local machine
- e Only required environment variable is the password, but also added a username since I tried once not to do this and I wasnt able to use it since I couldnt generate a valid connection string

proof its running after the command is executed
![image](https://github.com/user-attachments/assets/7bc64d1d-b0ca-48f5-a97a-d3e7c4248167)

Logs (not the final one, but figured it would be a waste of time to show that I had done this again):
![image](https://github.com/user-attachments/assets/aa3f6b15-a5c3-43a5-a312-8ac9d532fdff)

![image](https://github.com/user-attachments/assets/acd33103-9d2a-442d-82b5-d6cd9372cfc0)

Updated these settings in persistence.xml. I already used postgres in the previous assignment

```xml
            <!-- Database connection details -->
            <property name="javax.persistence.jdbc.url" value="jdbc:postgresql://localhost:5432/postgres"/>
            <property name="javax.persistence.jdbc.user" value="user"/>
            <property name="javax.persistence.jdbc.password" value="testpassword123"/>
```

The tests succeded on first try for me
![image](https://github.com/user-attachments/assets/9439b7c4-3c4b-4fae-b700-16f1b24309c6)

The tables were automatically generated in the "public" schema, which is there by default.

Code for working towards docker database can be found [here](https://github.com/Ivhene/dat250-jpa-tutorial)

## Task 2: Building you own dockerized application

The initial Dockerfile can be found [here](https://github.com/Ivhene/poll-app/blob/58441d4d1db13ae6882056bf8b28c758f83041a6/Dockerfile)

![image](https://github.com/user-attachments/assets/7b69ee8e-4f7e-4de2-9f1f-493cf8ccb040)

![image](https://github.com/user-attachments/assets/749f3fd6-d7be-4e76-8a1e-e0a7dc8000de)

![image](https://github.com/user-attachments/assets/25b94e01-4656-4c23-b6f3-9d10d8b7b122)

The above images shows the application working before the improvements. For the improvements I had to use wsl because of getting errors when building on Windows.

The updated Dockerfile can be found [here](https://github.com/Ivhene/poll-app/blob/main/Dockerfile)

![image](https://github.com/user-attachments/assets/28143c29-25ea-463c-b16f-ea310fdab2f0)

![image](https://github.com/user-attachments/assets/f1268f4a-268d-46bb-b2d9-c3ef1ac0130d)

The website looks the same as it did before so no point showing that image again, but it is working.


