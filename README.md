# MovieFlix

API REST para gerenciamento de categorias de filmes. Feita com Spring Boot 3.5 e PostgreSQL.

## Tecnologias

- Java 17
- Spring Boot 3.5
- Spring Data JPA
- Spring Security
- Spring Validation
- Flyway
- PostgreSQL
- Docker Compose
- Lombok

## Estrutura

```
src/main/java/com/movieflix/
├── controllers/    -> endpoints REST
├── services/       -> regras de negocio
├── repositories/   -> acesso ao banco
└── entities/       -> modelos do banco
```

## Como rodar

### Com Docker

```bash
docker compose up --build
```

O servidor sobe em http://localhost:8080.

O banco de dados e configurado automaticamente pelo Docker Compose usando as variaveis do arquivo `.env`.
Exemplo de arquivo `.env`
```.env
POSTGRES_DB=movieflix
POSTGRES_PASSWORD=
POSTGRES_USER=

```

Também se deve inserir variaveis de ambiente como:
```.env
SPRING_DATASOURCE_URL=jdbc:postgresql://db:5432/movieflix
SPRING_DATASOURCE_USERNAME=
SPRING_DATASOURCE_PASSWORD=
```

### Sem Docker

Voce precisa ter um PostgreSQL rodando localmente e configurar as variaveis de ambiente:

```bash
SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/movieflix
SPRING_DATASOURCE_USERNAME=
SPRING_DATASOURCE_PASSWORD=
```

Depois:

```bash
./mvnw spring-boot:run
```

## Endpoints

### Categorias

| Metodo | Caminho                  | Descricao            |
|--------|--------------------------|----------------------|
| GET    | /movieflix/category      | Lista todas          |
| GET    | /movieflix/category/{id} | Busca por ID         |
| POST   | /movieflix/category      | Cria uma categoria   |
| DELETE | /movieflix/category/{id} | Remove uma categoria |

## Projeto em andamento

Este projeto ainda esta em desenvolvimento. A estrutura foi pensada para crescer de forma simples, com endpoints REST, separacao clara entre camadas e migrations gerenciadas pelo Flyway.
