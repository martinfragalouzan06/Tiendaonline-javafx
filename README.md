# 👗 Fondo de Armario – Aplicación JavaFX

> Proyecto desarrollado en JavaFX que permite gestionar un fondo de armario personal.  
> Incluye sistema de inicio de sesión, administración de prendas, categorías y etiquetas, con persistencia en base de datos MySQL.

---

## ✨ Funcionalidades Principales

- 👤 **Gestión de Usuarios**
  - Inicio de sesión mediante usuario y contraseña.
  - Cada usuario accede únicamente a sus propias prendas.

- 👕 **Gestión de Prendas**
  - Añadir, editar y eliminar prendas.
  - Atributos: nombre, color, talla, categoría.
  - Visualización en tabla JavaFX TableView.
  - Contador automático del número de etiquetas asociadas.

- 🏷️ **Gestión de Etiquetas**
  - Cada prenda puede tener varias etiquetas.
  - Interfaz para asignar y desasignar etiquetas mediante dos listas.
  - Relación muchos-a-muchos gestionada mediante la tabla `prenda_etiqueta`.

---

## 🛠️ Tecnologías Utilizadas

- Java 21
- JavaFX 21
- MySQL 8+
- IntelliJ IDEA

---

## 🗄️ Estructura de la Base de Datos

Tablas principales:

- **usuarios**
- **categorias**
- **prendas**
- **etiquetas**
- **prenda_etiqueta**

Incluye script SQL para crear las tablas.

---

## 🚀 Instalación y Ejecución

1. **Crear la base de datos** ejecutando el script SQL incluido.
2. **Configurar la clase `DatabaseConnection`** con tus credenciales MySQL.
3. **Asegurarse que JavaFX está configurado** en el IDE.
4. **Ejecutar la clase `MainApplication`**.

---

## 📄 Licencia

Este proyecto se encuentra bajo la licencia [MIT](LICENSE).

---

## 👤 Autor

[martinfragalouzan06](https://github.com/martinfragalouzan06)
