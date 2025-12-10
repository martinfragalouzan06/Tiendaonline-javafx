Fondo de Armario – Aplicación JavaFX

Proyecto desarrollado en JavaFX que permite gestionar un fondo de armario personal.
Incluye sistema de inicio de sesión, administración de prendas, categorías y etiquetas, con persistencia en base de datos MySQL.

Funcionalidades principales
Gestión de usuarios

Inicio de sesión mediante usuario y contraseña.

Cada usuario accede únicamente a sus propias prendas.

Gestión de prendas

Añadir, editar y eliminar prendas.

Atributos de cada prenda: nombre, color, talla y categoría.

Visualización en una tabla mediante JavaFX TableView.

Contador automático del número de etiquetas asociadas.

Gestión de etiquetas

Cada prenda puede tener múltiples etiquetas.

Interfaz para asignar y desasignar etiquetas mediante listas.

Relación muchos-a-muchos gestionada mediante la tabla intermedia prenda_etiqueta.

Tecnologías utilizadas

Java 21

JavaFX 21

MySQL 8+

JDBC (mysql-connector-j)

IntelliJ IDEA (entorno recomendado)

Estructura de la base de datos

Tablas principales:

usuarios

categorias

prendas

etiquetas

prenda_etiqueta

El proyecto incluye un script SQL para crear todas las tablas necesarias.

Instalación y ejecución

Crear la base de datos ejecutando el archivo SQL proporcionado.

Ajustar la conexión a MySQL en la clase DatabaseConnection.

Configurar las librerías JavaFX en el proyecto.

Ejecutar la clase MainApplication para iniciar la aplicación.
