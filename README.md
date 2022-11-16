
# Air Transportation

Air Transportation System to Register,Road,Check available Transportation By Providing APIs




## Getting Statred

To build this project

```bash
  ./gradlew build
```

To run this project 

```bash
  ./gradlew bootRun
```


## Build With

[![MIT License](https://spring.io/images/spring-logo-9146a4d3298760c2e7e49595184e1975.svg)](https://choosealicense.com/licenses/mit/)


## API Reference
#### Register Transportation

```http
  POST /api/transportations
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `transportationObj` | `Obj` | **Required**. Transportation  |


#### Get all items in specific transportation

```http
  GET /api/transportations/items/${id}
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `transportation_id` | `long` | **Required**. Transportation Id |

#### Get available transportation to load

```http
  GET /api/transportations/available
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `long` | **Required**. Id of transportation |

#### Check Batter Capacity For specific Transportation

```http
  GET /api/transportations/battery/${id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `long` | **Required**. Id of transportation |

```http
  POST /api/transportations/items/load/{id}
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `transportationObj` | `Obj` | **Required**. Transportation  |
| `itemsIds` | `List<Long>` | **Required**. List Ids Of Items to be loaded  |





## Documentation

[Postman Collection](https://drive.google.com/drive/folders/1mU9irjh3rr-tn68FIiaSem7LS5vDXQur?usp=sharing)

