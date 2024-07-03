# TestCorven

## Recomendaciones Iniciales

Antes de iniciar la inyección de datos automáticas, abre la aplicación en Swagger:

> [App deployada](https://testcoven.azurewebsites.net/swagger-ui/index.html)

La aplicación está deployada en Azure Web App con infraestructura compartida, lo cual puede demorar la primera carga. Utiliza una base de datos H2 para mantener los datos. Esta elección presenta desventajas, pero se hizo para evitar acumular más latencia además de la propia de la plataforma Azure Web App con infraestructura compartida. En caso de reinicio de la aplicación por un ataque DDOS o saturación, los datos se borrarán.

### Tecnologías

- Spring Boot
- Java 17
- H2
- JPA (Transaccional para mantener consistencia)
- Swagger **(Documentación)**
- Maven
- IntelliJ IDEA

Para casos de error utiliza excepciones para retornar distinto mensajes.
Lo mejor seria una centralizacion del gestor de excepciones.

## Build

Ejecutar los siguientes comandos:

```cmd
git clone https://github.com/fabian3117/TestCorven.git
cd ./TestCorven
./mvnm.cmd package
java -jar ./target/user-api-0.0.1-SNAPSHOT.jar
``` 
> http://localhost:8085/swagger-ui/index.html
- En local corre en puerto 8085
En caso de querer visualizar el codigo usando inteliji o VSCode abrir carpeta raiz TestCorven

## Datos de carga.
> POST.
http://localhost:8085/Persona/savePerson

```json
{
  "telefono": "1123981056",
  "dni": "39870460",
  "pais": "argentina",
  "sexo": "HOMBRE",
  "nombre": "Federico",
  "id": "string",
  "edad": 18, //Si es menor no se ingresa
  "documento": "DNI",
  "contactosReferencia": [
    {
      "telefono": "string",
      "dni": "string",
      "pais": "string",
      "sexo": "string",
      "nombre": "string",
      "id": "string",
      "relacion": "PADRE"
    }
  ]
}
```
## Ver datos guardados.
> GET
http://localhost:8085/Persona/getAllPerson
#### Ejemplo respuesta:
```json
[
  {
    "telefono": "string",
    "dni": "string",
    "pais": "string",
    "sexo": "HOMBRE",
    "nombre": "string",
    "id": "DNIstringstringHOMBRE",
    "edad": 18,
    "documento": "DNI",
    "contactosReferencia": [
      {
        "telefono": "string",
        "dni": "string",
        "pais": "string",
        "sexo": "HOMBRE",
        "nombre": "string",
        "id": "DNIstringstringHOMBRE",
        "relacion": "PADRE"
      }
    ]
  }
]
```

# Enum: TipoSexo

El enum `TipoSexo` representa los tipos de sexo disponibles.

## Valores

| Nombre   | Descripción |
|----------|-------------|
| HOMBRE   | HOMBRE      |
| MUJER    | MUJER       |
| OTRO     | OTRO        |

# Enum: TypeDoc

El enum `TypeDoc` representa los  tipos de documentos

## Valores

| Nombre   | Descripción |
|----------|-------------|
| PASSPORT   | PASAPORTE      |
| DNI    | DNI       |

## Generacion de ID

```java
 public static String codificacion(PersonEntity personEntity){
        return personEntity.getDocumento().getValor()
            .concat(personEntity.getDni())
            .concat(personEntity.getPais())
            .concat(personEntity.getSexo().toString());
    }
```
## Tabla de Constantes respuestas para HTTPS

| Nombre                   | Valor                       |
|--------------------------|-----------------------------|
| SuccessOperationString   | "Operación exitosa"         |
| SuccessOperationCode     | 200                         |
| ErrorOperationString     | "Fallo"                     |
| ErrorOperationCode       | 500                         |
