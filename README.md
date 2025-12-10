Fondo de Armario – Aplicación JavaFX

Proyecto desarrollado en JavaFX que permite gestionar un fondo de armario personal.
Incluye sistema de inicio de sesión, administración de prendas, categorías y etiquetas, con persistencia en base de datos MySQL.

Funcionalidades principales
Gestión de usuarios

Inicio de sesión mediante usuario y contraseña.

Cada usuario accede únicamente a sus propias prendas.

Gestión de prendas

Añadir, editar y eliminar prendas.

Atributos: nombre, color, talla, categoría.

Visualización en tabla JavaFX TableView.

Contador automático del número de etiquetas asociadas.

Gestión de etiquetas

Cada prenda puede tener varias etiquetas.

Interfaz para asignar y desasignar etiquetas mediante dos listas.

Relación muchos-a-muchos gestionada mediante la tabla prenda_etiqueta.

Tecnologías utilizadas

Java 21

JavaFX 21

MySQL 8+

JDBC

IntelliJ IDEA

Estructura de la base de datos

Tablas principales:

usuarios

categorias

prendas

etiquetas

prenda_etiqueta

Incluye script SQL para crear las tablas.

Instalación y ejecución

Crear la base de datos ejecutando el script SQL incluido.

Configurar la clase DatabaseConnection con tus credenciales MySQL.

Asegurar que JavaFX está configurado en el IDE.

Ejecutar la clase MainApplication.
