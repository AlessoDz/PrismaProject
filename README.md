# Gestor de Matrículas, Control de Notas y Seguimiento de Horarios - Prisma

# Descripción

Este proyecto es una solución integral para la gestión educativa de la institución privada "Prisma". La aplicación incluye funcionalidades para la gestión de matrículas, control de notas y seguimiento de horarios de actividades, proporcionando una plataforma eficiente y fácil de usar tanto para administradores como para solicitantes, administradores, estudiantes y docentes.

# Funcionalidades

- Gestión de Matrículas: Permite registrar y administrar las matrículas de los estudiantes, facilitando la inscripción y seguimiento del estado académico de cada alumno.
- Control de Notas: Sistema para el ingreso, modificación y consulta de notas académicas, con la capacidad de generar reportes y estadísticas de rendimiento.
- Seguimiento de Horarios: Herramienta para gestionar y visualizar los horarios de clases y actividades, asegurando la organización y coordinación efectiva de las actividades académicas.

# Tecnologías Utilizadas

- Lenguaje de Programación: Java
- Servlets (para el desarrollo del frontend y backend en un solo entorno)
- Base de Datos: MySQL
- Editor de Código Recomendado: [Eclipse](https://www.eclipse.org/), [IntelliJ IDEA](https://www.jetbrains.com/idea/), o cualquier editor compatible con Java.

# Instalación

1. Clona el repositorio:
   ```bash
   git clone https://github.com/usuario/repositorio.git
   ```

2. Configura el entorno:
   - Asegúrate de tener [Java JDK](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) instalado en tu sistema.
   - Configura [MySQL](https://dev.mysql.com/downloads/) y crea una base de datos para el proyecto.

3. Configura la base de datos:
   - Importa el archivo de esquema SQL (`prisma.sql`) que se encuentra en el repositorio para crear las tablas necesarias.
   - Actualiza las configuraciones de conexión a la base de datos en el archivo `src\main\java\pe\edu\utp\BaseDatos\ConexionBD.java`.

4. Compila y despliega la aplicación:
   - Importa el proyecto en un editor compatible con Java como [Eclipse](https://www.eclipse.org/) o [IntelliJ IDEA](https://www.jetbrains.com/idea/).
   - Compila el proyecto.
   - Despliega la aplicación en un servidor de aplicaciones compatible con Servlets, como Apache Tomcat.

5. Inicia el servidor:
   - Si estás utilizando Apache Tomcat, inicia el servidor desde el entorno de desarrollo o desde la línea de comandos.

# Uso

1. Accede a la aplicación a través del navegador en `http://localhost:8080`.
2. Utiliza las diferentes secciones para gestionar matrículas, controlar notas y seguir horarios.
3. Consulta la documentación interna de la aplicación para guías detalladas sobre cada funcionalidad.

# Contribuidores

Este proyecto ha sido desarrollado por un grupo de estudiantes de la Universidad Tecnológica del Perú. El equipo está conformado por:

- Víctor Enrique Cabanillas Rojas
- Mery Condori
- Marcio Alessandro De La Cruz Capuñay
- Juan Sánchez Pisfil

# Contacto

Para consultas o sugerencias, puedes contactar a los desarrolladores a través de los siguientes canales:

- Correo Electrónico:
  - Víctor Enrique Cabanillas Rojas: victor.cabanillas@gmail.com
  - Mery Condori: mery.condori@gmail.com
  - Marcio Alessandro De La Cruz Capuñay: alessandrodzcap31@gmail.com
  - Juan Sánchez Pisfil: juan.sanchez@gmail.com

- Redes Sociales:
  - LinkedIn de Víctor Enrique Cabanillas Rojas: [linkedin.com/in/victorcabanillas](https://linkedin.com/in/victorcabanillas)
  - LinkedIn de Mery Condori: [linkedin.com/in/merycondori](https://linkedin.com/in/merycondori)
  - LinkedIn de Marcio Alessandro De La Cruz Capuñay: [linkedin.com/in/alessandro-de-la-cruz-capuñay](https://www.linkedin.com/in/alessandro-de-la-cruz-capu%C3%B1ay-b4944129a/?jobid=1234)
  - LinkedIn de Juan Sánchez Pisfil: [linkedin.com/in/juansanchez](https://linkedin.com/in/juansanchez)

---

¡Gracias por tu interés en nuestro proyecto! Esperamos que esta herramienta sea de gran utilidad para la gestión educativa en la institución Prisma.

```
