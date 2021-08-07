# Accessing MySQL Database (Amazon Corretto OpenJDK 11 /Spring Boot).

This is a Java/Spring Boot template for building a microservice to communicate with MySQL database.  
This service demonstrates use of Apache Commons DBCP2 to manage connection pool, DataSource, JdbcTemplate and NamedParameterJdbcTemplate required to perform database operations.

# Repository structure

The main files in this repository are:

- `pom.xml:` *specifies required dependency to build and package the application.*

- `src/main/java:` *contains actual java application business logic ,service and persistence layer.*

- `src/main/resources/application.properties:` *contains values (typically configured by developer), properties required to run micoservice in a desired environment.*

- `mysql_scripts:` *database scripts to create schema,insert dummy data into MySQL.*

# Usage

* Download the repository from GitHub.

* Execute maven command to build application or directly run through IDE.

```
..\springboot-dbcp2-jdbc-mysql> mvn clean package -e -U

```

* Run main class <B> `BootDbcp2MySqlApplication.class` </B> to execute the application and it's business layer.

# License

Licensed under GNU General Public License v3.0. Please see [LICENSE](LICENSE) for details.

**Happy Learning !**
