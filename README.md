# TaskManager

Proyecto para administrar tareas

## Base de datos: 
La base de datos se encuentra en H2.

La creación de las tablas y relaciones se realiza al correr la aplicación mediante el archivo schema.sql.

La configuración de la base de datos se encuentra en el archivo application.properties.

## Compilación

El proyecto se compila utilizando la siguiente instrucción de maven:

```
mvn clean install
```

## Ejecución

Para correr el proyecto, se utiliza la siguiente istrucción de maven:

```
mvn spring-boot:run
```

## Aplicación: 
La aplicación se inicia desde la clase AppWeatherApplication como JavaApplication ya que corre con el servidor embebido de Tomcat.

## EndPoints

### Proyectos

Para el CRUD de proyectos, existen 5 endpoints:

- Para consultar el proyecto por id:

GET [http://localhost:8080/proyectos/{proyectoId}](http://localhost:8080/proyectos/{proyectoId})

- Para consultar todos los proyectos:

GET [http://localhost:8080/proyectos?page={pagina}&size={resultados}](http://localhost:8080/proyectos?page={pagina}&size={resultados})

Nota: los parámetros page y size son opcionales

- Para crear un proyecto:

POST [http://localhost:8080/proyectos](http://localhost:8080/proyectos)

#### Request
```
{
    "nombre":"proyecto 3",
    "descripcion":"practica evaluacion 3"
}
```

- Para actualizar un proyecto por id:

PUT [http://localhost:8080/proyectos/{proyectoId}](http://localhost:8080/proyectos/{proyectoId})

#### Request
```
{
    "nombre":"proyecto 3",
    "descripcion":"practica evaluacion 3"
}
```

- Para eliminar un proyecto por id:

DELETE [http://localhost:8080/proyectos/{proyectoId}](http://localhost:8080/proyectos/{proyectoId})

### Tareas

Para el CRUD de tareas, existen 5 endpoints:

- Para consultar la tarea por id:

GET [http://localhost:8080/tareas/{tareaId}](http://localhost:8080/tareas/{proyectoId})

- Para consultar todas las tareas:

GET [http://localhost:8080/tareas?page={pagina}&size={resultados}](http://localhost:8080/tareas?page={pagina}&size={resultados})

Nota: los parámetros page y size son opcionales

- Para crear una tarea:

POST [http://localhost:8080/tareas](http://localhost:8080/tareas)

#### Request
```
{
    "nombre":"tarea 31",
    "descripcion":"practica evaluacion",
    "proyectoId":3,
    "estado":"PENDIENTE"
}
```

- Para actualizar una tarea por id:

PUT [http://localhost:8080/tareas/{tareaId}](http://localhost:8080/tareas/{tareaId})

#### Request
```
{
    "nombre":"tarea 31",
    "descripcion":"practica evaluacion",
    "proyectoId":3,
    "estado":"COMPLETADA"
}
```

- Para eliminar una tarea por id:

DELETE [http://localhost:8080/tareas/{tareaId}](http://localhost:8080/tareas/{tareaId})