
# Backend Challenge: URL Shorter

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)

This project is an API built using **Java, Java Spring and PostgresSQL as the database.**

The API was developed for the Back-End Brasil challenge, wich you can find it [here](https://github.com/backend-br/desafios/blob/master/url-shortener/PROBLEM.md).

## Table of Contents

- [Installation](#installation)
- [Usage](#usage)
- [API Endpoints](#api-endpoints)
- [Database](#database)

## Installation

1. Clone the repository:

```bash
git clone https://github.com/nathanflp/encurtador-de-link
```

2. Install dependencies with Maven

3. Install [PostgresSQL](https://www.postgresql.org/)

## Usage

1. Start the application with Maven
2. The API will be accessible at http://localhost:8080


## API Endpoints
The API provides the following endpoints:

```markdown
List of all detailed endpoints - http://localhost:8080/swagger-ui.html

GET /{id} - Redirects to the registered url.

POST /encurtar-url - Shorten the received url .

```

## Database
The project utilizes [PostgresSQL](https://www.postgresql.org/) as the database. The necessary database migrations are managed using Flyway.
