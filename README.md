# TO-DO List 📝

This aplication is to implemented with Springboot and it's used to administrate your activities with differetent tasks and coordinate with other users.

## Technologies

- Java 21
- Springboot
- JPA/Hibernate
- Maven

## Setup

1. Clona el repositorio
```bash
git clone https://github.com/tu-usuario/tu-proyecto.git
```

2. Go to the directory

``` bash
./mvnw spring-boot:run
```
## Pruebas

```bash
./mvnw test
```

## Endpoints

| Method | Path | Description |
|--------|------|-------------|
| GET | /tasks | List Tasks |
| GET | /user | List Users |
| GET |/tasks |List Tasks
| GET |/user |List Users
| GET| /categories |List Categories
| GET| /tasks/{id} | Get Task by Id
| GET| /user/{id} | Get User by Id
| GET| /categories/{id} | Get Categoriy by Id
| POST| /tasks | Create Task
| POST| /user | Create User
| POST| /categories | Create Category
| PUT| /task/{id} | Update Task
| PUT| /users/{id} | Update User
| PUT| /categories/{id} | Update Category
| DELETE| /task/{id} | Delete Task by Id
| DELETE| /users/{id} | Delete User by Id
| DELETE| /categories/{id} | Delete Category by Id
| POST |/users/{userId}/assign/{taskId} | Assign User to Task
| DELETE |/users/{userId}/remove/{taskId} | Delete User from Task

## Autor

- [Eduardo](https://github.com/Eduardo-Guerra-Alvarez)