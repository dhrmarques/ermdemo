README

This document exists in order to help the installation and usage of the current ERM project.
The project itself is based on Spring Boot, using Spring Web, Spring Security for basic
authentication, Maven for artifact distribution and packaging, and PostgreSQL as the database.

Starting the database
Initially, the database must be created via Docker Compose (using the command docker-compose up)
in order to raise the PostgreSQL database (as well as an optional PG Admin 4 visualizer) container.
If successful, by running the command "docker-compose ps" there should be listed 2 containers:
the pgadm4_container (for PG Admin 4) and postgresql18_container (for the database). PostgreSQL
is configured for the default port (5432).

Notice: If there's the need to use previous versions of the PostgreSQL container (17 and before)
the data volume must be validated back to the original value ("/var/lib/postgresql/data"), as per
version 18 and onwards this path is treated as obsolete and should be changed, just like in the
current version of docker-compose.yml file.

(Optional - PG Admin 4)
PG Admin 4 here is an alternative for the database IDEs such as DBeaver to visualize the data.
It can be accessed through the port 5151 (e.g., http://localhost:5151), also configurable via
docker-compose.yml file.
The PG Admin 4 can be set up by logging in with the credentials "test@pgadm4.net|test" (which
can be changed on the docker-compose.yml file, located at the "docker" folder). There, the
server can be registered by using the "Object > Register" path, then setting up the host field
with the value "postgresql18_container" value, database as "postgresql_db" and both username and
password as "postgres".

Starting the Application
It can be done directly via IDE by running "./mvnw spring-boot:run" inside the "ermdemo" directory.
Also, it can be deployed as a container by creating the image from the Dockerfile inside the
/ermdemo folder (on the same level as the /src folder), and if running on the same host as the
PostgreSQL one, there's no need to include it on the same docker-compose (although it can be an
interesting improvement regarding the application itself).

The credentials for logging in with the initial application user are "test@test.com" and "test" as
password.