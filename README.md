# Project Overview: Simple J2EE with EJB Rest, JBoss JBPM, and Push Message Integration (Including AI)

## Features

* **Message Storage:** All user messages are stored in MongoDB, and the topic under discussion is intelligently identified by AI.
* **Dynamic Process Management:** Admins can add new processes and start any added process from the admin panel.
* **User Behavior Tracking:** All user behaviors are logged and stored in MongoDB.

## Configuration

**This project consists of three distinct parts, each running on a different server. The configurations mentioned below are for a single server that hosts all three parts.**

### Main Project (TomEE)

- **URL:** `http://localhost:8080`
- **JMX Port:** `1101` or `1102` or ...

### Push Message Chat Server (Tomcat)

- **URL:** `http://localhost:80`
- **JMX Port:** `1100`

### Process Engine Server (RMI)

- **RMI Port:** `1099`

### Important Notes

* **MongoDB Logs:**
  - All logs are stored in MongoDB at `localhost:27017`. Ensure that the server is online before running the project.

* **OracleDB and TomEE Config:**
  - Find OracleDB commands and TomEE configuration for JavaStandardSecurity in the `/Config` directory.

* **Process Engine Server:**
  - On the first run of the Process Engine Server, uncomment the "Create-Drop for Hibernate" to create the database schema.

* **AI Dataset:**
  - The dataset for AI is located in the `/Webpush+ai` directory and must be copied to the Desktop.

**Please refer to the respective directories for detailed OracleDB commands, TomEE configuration, and necessary AI datasets.**
