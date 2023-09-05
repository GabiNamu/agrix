# Agrix 🌾
```diff
@@ About @@
```
Agrix is a system that assists in the management and monitoring of farms with the aim of improving efficiency in crop cultivation.

```diff
@@ Skills @@
```
- knowledge of the Spring ecosystem to create API routes;
- Dependency injection to connect the control, service, and persistence layers;
- Spring Data JPA to implement entities and repositories for database persistence, as well as to implement custom queries;
- Error handling in Spring Web;
- Created the Dockerfile to configure the application for execution in Docker;
- Date fields in the API routes and in the database;
- Unit tests to ensure the quality and proper functioning of the implementation, with adequate code coverage;
- knowledge of Spring Security to add authentication to the project;
- Ensured that different routes adhere to specific authorization rules.
  
```diff
@@ routes @@
```
POST `/farms`:
<details>
  <summary>🔍 Formato/exemplo de requisição e resposta</summary><br />

Exemplo de requisição:
```json
{
  "name": "Fazendinha",
  "size": 5
}
```

Exemplo de resposta:

```json
{
  "id": 1,
  "name": "Fazendinha",
  "size": 5
}
```
</details>

POST `/farms/{farmId}/crops`:
<details>
  <summary>🔍 Formato/exemplo de requisição e resposta</summary><br />

Exemplo de requisição na rota `/farms/1/crops` (supondo que exista uma fazenda com `id = 1`):

```json
{
  "name": "Couve-flor",
  "plantedArea": 5.43,
  "plantedDate": "2022-12-05",
  "harvestDate": "2023-06-08"
}
```

Exemplo de resposta:

```json
{
  "id": 1,
  "name": "Couve-flor",
  "plantedArea": 5.43,
  "plantedDate": "2022-12-05",
  "harvestDate": "2023-06-08",
  "farmId": 1
}
```

Note que o `id` da resposta se refere à plantação, e que o da fazenda está em `farmId`.

</details>

GET `/farms/{farmId}/crops`:
<details>
  <summary>🔍 Formato/exemplo de resposta</summary><br />

Exemplo de resposta para a rota `/farms/1/crops` (supondo que exista uma fazenda com `id = 1`):

```json
[
  {
    "id": 1,
    "name": "Couve-flor",
    "plantedArea": 5.43,
    "plantedDate": "2022-12-05",
    "harvestDate": "2023-06-08",
    "farmId": 1
  },
  {
    "id": 2,
    "name": "Alface",
    "plantedArea": 21.3,
    "plantedDate": "2022-02-15",
    "harvestDate": "2023-02-20",
    "farmId": 1
  }
]
```

</details>

GET `/crops`:
<details>
  <summary>🔍 Formato/exemplo de resposta</summary><br />

```json
[
  {
    "id": 1,
    "name": "Couve-flor",
    "plantedArea": 5.43,
    "plantedDate": "2022-02-15",
    "harvestDate": "2023-02-20",
    "farmId": 1
  },
  {
    "id": 2,
    "name": "Alface",
    "plantedArea": 21.3,
    "plantedDate": "2022-02-15",
    "harvestDate": "2023-02-20",
    "farmId": 1
  }
]
```

</details>

GET `/crops/{id}`:
<details>
  <summary>🔍 Formato/exemplo de resposta</summary><br />

Exemplo de resposta para a rota `/crops/3` (supondo que exista uma plantação com `id = 3`:

```json
{
  "id": 3,
  "name": "Tomate",
  "plantedArea": 1.9,
  "plantedDate": "2023-05-22",
  "harvestDate": "2024-01-10",
  "farmId": 2
}
```

</details>

GET `/crops/search`:
<details>
  <summary>🔍 Formato/exemplo de resposta</summary><br />
  - deve receber dois parâmetros por query string para busca:
    - `start`: data de início
    - `end`: data de fim

Exemplo de resposta para a rota `/crops/search?start=2023-01-07&end=2024-01-10`:

```json
[
  {
    "id": 1,
    "name": "Couve-flor",
    "plantedArea": 5.43,
    "plantedDate": "2022-02-15",
    "harvestDate": "2023-02-20",
    "farmId": 1
  },
  {
    "id": 3,
    "name": "Tomate",
    "plantedArea": 1.9,
    "plantedDate": "2023-05-22",
    "harvestDate": "2024-01-10",
    "farmId": 2
  }
]
```

</details>

POST `/fertilizers`:
<details>
  <summary>🔍 Formato/exemplo de requisição e resposta</summary><br />

Exemplo de requisição:

```json
{
  "name": "Compostagem",
  "brand": "Feita em casa",
  "composition": "Restos de alimentos"
}
```

Exemplo de resposta:

```json
{
  "id": 1,
  "name": "Compostagem",
  "brand": "Feita em casa",
  "composition": "Restos de alimentos"
}
```

</details>

GET `/fertilizers`:
<details>
  <summary>🔍 Formato/exemplo de resposta</summary><br />

```json
[
  {
    "id": 1,
    "name": "Compostagem",
    "brand": "Feita em casa",
    "composition": "Restos de alimentos"
  },
  {
    "id": 2,
    "name": "Húmus",
    "brand": "Feito pelas minhocas",
    "composition": "Muitos nutrientes"
  },
  {
    "id": 3,
    "name": "Adubo",
    "brand": "Feito pelas vaquinhas",
    "composition": "Esterco"
  }
]
```
</details>

GET `/fertilizers/{id}`:
<details>
  <summary>🔍 Formato/exemplo de resposta</summary><br />

Exemplo de resposta da rota `/fertilizers/3` (supondo que exista um fertilizante com `id = 3`):

```json
{
  "id": 3,
  "name": "Adubo",
  "brand": "Feito pelas vaquinhas",
  "composition": "Esterco"
}
```

</details>

POST `/crops/{cropId}/fertilizers/{fertilizerId}`:
<details>
  <summary>🔍 Formato/exemplo de requisição e resposta</summary><br />
  Rota para criar a associação entre uma plantação e um fertilizante.

Exemplo de resposta para a rota `/crops/1/fertilizers/2` (supondo que exista uma plantação com `id = 1` e um fertilizante com `id = 2`):

```text
Fertilizante e plantação associados com sucesso!
```

</details>

```diff
@@ tecnologies @@
```
- Java;
- JUnit;
- Docker;
- Mysql;
- JWT;
- Spring
