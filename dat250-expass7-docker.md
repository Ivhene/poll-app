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
![image](https://github.com/user-attachments/assets/73c067af-f2e1-48a2-9d28-3b2d5e270ed0)


Logs:
![image](https://github.com/user-attachments/assets/aa3f6b15-a5c3-43a5-a312-8ac9d532fdff)

![image](https://github.com/user-attachments/assets/acd33103-9d2a-442d-82b5-d6cd9372cfc0)



## Task 2: Building you own dockerized application
