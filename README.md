# TV Maze API Middleware

Middleware sobre los servicios de [TV Maze](https://www.tvmaze.com/api) con cache en MongoDB
y sistema de comentarios y calificaciones por show.

## Requisitos

- Java 21
- Maven 3.9+
- Docker (para MongoDB local) o cluster de MongoDB Atlas

## Base de datos local con Docker

```bash
docker compose up -d
```

Esto levanta MongoDB en `mongodb://localhost:27017/tvmaze`.

Para usar MongoDB Atlas en su lugar, definir la variable de entorno:

```bash
export MONGODB_URI="mongodb+srv://<usuario>:<password>@<cluster>.mongodb.net/tvmaze"
```

## Ejecución

```bash
mvn spring-boot:run
```

## Endpoints

| Metodo | Ruta | Descripcion |
|--------|------|-------------|
| GET | `/api/search?search_query=girls` | Busqueda de shows + comentarios |
| GET | `/api/show/{showId}` | Detalle del show con cache en Mongo + comentarios |
| POST | `/api/comments` | Guarda comentario y calificacion (0-5) |

### Ejemplo de body para comentarios

```json
{
  "showId": 1,
  "comment": "Excelente serie",
  "rating": 5
}
```
