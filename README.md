# Spring Boot Blog backend

## Description

* This is a simple blog backend application built with Spring Boot and Spring Data JPA using Postgres. It provides RESTful APIs for creating, reading, updating, and deleting blog posts.

## Running the Application

#### Just use run on BlogApiApplication if on IntelliJ IDEA or use the following command if using Gradle

```bash
./gradlew bootRun
```

## Database

### PostgreSQL

#### To Login to Postgres

```bash
psql postgres # for MacOS 
sudo -u postgres psql postgres # for Linux
```
#### To Create a Database

```sql
CREATE DATABASE blog;
CREATE USER blog_user WITH ENCRYPTED PASSWORD 'blog_password';
GRANT ALL PRIVILEGES ON DATABASE blog TO blog_user;
```

## API Endpoints

### `POST /users`
create a new user

### `POST /users/login`
### `GET /profiles`📄
### `GET /profiles/{username}`
### `GET /articles` 📄
get all articles (default page size 10) available filters

-`/articles?tag=stocks`
-`/articles?author=asddsd`
-`/articles?page=3&size=10`

### `GET /articles/{article-slug}`
### `POST /articles` 🔐
create a new article

### `PATCH /articles/{article-slug}` 🔐👤
edit an article

### `GET /article/{article-slug}/comments` 📄
get all comments of an article

### `POST /article/{article-slug}/comments` 🔐
### `DELETE /article/{article-slug}/comments/{comment-id}` 🔐👤

