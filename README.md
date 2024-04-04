<img src="https://cdn.icon-icons.com/icons2/632/PNG/512/users_icon-icons.com_57999.png" width="20%" style="float: right">
<h1>Proyecto Creación de Usuarios</h1>

<hr>
<p>Este proyecto tiene como objetivo proporcionar una API para registrar nuevos usuarios en un sistema. Proporciona endpoints RESTful que permiten a los clientes enviar datos de usuario, como nombre, correo electrónico, contraseña y detalles de contacto, para crear una cuenta de usuario.
</p>
<br>
<h2>📑 Características Principales:</h2>
<ul>
<li>Registro de nuevos usuarios con información básica y detalles de contacto.</li>
<li>Validación de formato de correo electrónico y contraseña durante el proceso de registro.</li>
<li>Almacenamiento seguro de contraseñas utilizando técnicas de hash y sal.</li>
<li>Generación de token de autenticación para usuarios registrados. </li>
</ul>
<br>
<h2>💻Tecnologías Utilizadas:</h2>
<ul>
<li><b>Java 17</b>: Lenguaje de programación principal del proyecto.</li>
<li><b>Spring Boot 3.2.4</b>: Framework utilizado para desarrollar la API REST.</li>
<li><b>Maven</b>: Herramienta de gestión de dependencias y construcción del proyecto.</li>
<li><b>Jackson</b>: Biblioteca para el procesamiento de datos JSON. </li>
<li><b>Mockito</b>: Framework de pruebas unitarias para Java. </li>
<li><b>Swagger</b>: Herramienta para diseñar, crear y documentar APIs REST de manera sencilla. </li>
<li><b>EclipseLink</b>: Implementación de la especificación JPA (Java Persistence API) para el mapeo objeto-relacional. </li>
<li><b>Spring Data JPA</b>: Parte del proyecto Spring Data, proporciona una capa de abstracción para el manejo de la persistencia de datos basada en JPA. </li>
<li><b>H2 Database</b>: Base de datos relacional embebida escrita en Java, utilizada para el desarrollo y pruebas de la aplicación. </li>
</ul>
<br>
<h2>📍Endpoints</h2>
<h3>Crear Usuario</h3>
<ul>
<li>URL: /api/users</li>
<li>Método HTTP: POST</li>
<li>Descripción: Este endpoint permite a los clientes registrar un nuevo usuario proporcionando los datos requeridos en el cuerpo de la solicitud.</li>
<li>Datos de Entrada:
<pre>
{
  "name": "Nombre del Usuario",
  "email": "correo@example.com",
  "password": "contraseña123",
  "phones": [
    {
      "number": "123456789",
      "citycode": "1",
      "contrycode": "57"
    }
  ]
}
</pre>
</li>
<li>Datos de Salida: Si el usuario se crea con éxito, se devuelve un objeto JSON con los detalles del usuario creado, incluido un token de autenticación. 
<pre>
{
  "id": "ID del Usuario",
  "created": "Fecha y Hora de Creación",
  "lastLogin": "Último Inicio de Sesión",
  "token": "Token de Autenticación",
  "isactive": "Estado de la Cuenta (activo/inactivo)"
}
</pre>
</li>
</ul>
<br>
<h3>Listar Usuario</h3>
<ul>
<li>URL: /api/users</li>
<li>Método HTTP: GET</li>
<li>Descripción: Este endpoint devuelve una lista de todos los usuarios registrados en el sistema.</li>
<li>Datos de Entrada: No se requieren parámetros de entrada.</li>
<li>Datos de Salida: 
<pre>
[
    {
        "name": "Juan Rodriguez",
        "email": "juanflo@dominio.cl",
        "password": "ADT_2024jf",
        "created": "2024-04-04 03:02:10",
        "modified": null,
        "lastLogin": "2024-04-04 03:02:10",
        "isactive": 1,
        "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqdWFuZmxvQGRvbWluaW8uY2wifQ.fDyQPVnFGSzezDQ_usq8wP6oXr-vhH116rGgk8X7x63JiubliPIB6JpJ9qhkr1qKodD5U6AwLOBXfKFoBV3w_g",
        "phones": [
            {
                "number": "1234567",
                "citycode": "1",
                "contrycode": "57"
            }
        ]
    }
]
</pre>
</li>
</ul>
<br>
<h2>🛠️Manejo de Errores</h2>
<p>En caso de que ocurra algún error durante el proceso de registro, se devolverá una respuesta con el código de estado correspondiente y un mensaje descriptivo del error.</p>
<ul>
<li><b>Formato de Correo Electrónico Inválido</b>:
    <ul>
        <li> Código de Estado: 400 Bad Request</li>
        <li> Mensaje de Error: "El formato del correo electrónico es inválido"</li>
    </ul>

</li>
<li><b>Contraseña Inválida</b>:
  <ul>
        <li> Código de Estado: 400 Bad Request</li>
        <li> Mensaje de Error: "El formato de la contraseña es inválido"</li>
    </ul>
</li>
<li><b>Correo Electrónico Ya registrado</b>:
 <ul>
        <li> Código de Estado: 409 Conflict</li>
        <li> Mensaje de Error: "El correo electrónico ya está registrado"</li>
    </ul>
</li>
</ul>
<br>
<h2>🛢️Base de Datos</h2>
<p>El proyecto utiliza H2 Database, una base de datos relacional en memoria, para almacenar los datos de los usuarios registrados y sus detalles de contacto. H2 es una opción popular para entornos de desarrollo y pruebas debido a su facilidad de uso y su capacidad para ejecutarse en memoria, lo que permite una configuración rápida y sin necesidad de una instalación de base de datos externa.</p>
<img src="http://imgfz.com/i/3ubIZgG.png">

<br>
<h2>🏗️Arquitectura</h2>
<img src="http://imgfz.com/i/RUh1rT6.png">
<br>
<h2>🛠️Como Probar la API con Postman</h2>
<p>A continuación, se detallan los pasos para probar los endpoints de la API utilizando Postman:</p>
<h3>EndPoint - Creación de Usuario</h3>
<ol>
<li> Seleccionar la acción POST y luego ingresar la url : http://localhost:8080/api/users </li>
<li> Seleccionar la opcion raw </li>
<li> Seleccionar la opcion JSON </li>
<li> Ingresar el siguiente json:
<pre>
{
  "name": "Juan Rodriguez",
  "email": "juanflo@dominio.cl",
  "password": "ABC_2024jf",
  "phones": [
    {
      "number": "1234567",
      "citycode": "1",
      "contrycode": "57"
    }
  ]
}
</pre>
</li>
<li> Seleccionar el botón Send</li>
<li> Ver los resultados</li>
<li> Demostración:
<img src="http://imgfz.com/i/vpGQC5t.png">
</li>
<li> Validaciones:
        <ul>
                <li> El EndPoint impide el registro de un usuario si el correo electrónico proporcionado ya existe en la base de datos.</li>
                <li> El EndPoint impide el registro de un usuario si el correo electrónico proporcionado no tiene el formato  <b>aaaaaaa@dominio.cl</b>  .</li>
                <li> El EndPoint impide el registro de un usuario si el password no cumple con tener  al menos una letra (mayúscula o minúscula),  al menos un dígito numérico, al menos uno de los caracteres especiales. Puede contener letras (mayúsculas o minúsculas), dígitos o cualquiera de los caracteres especiales @$!%*?&.  .</li>
        </ul>
</li>
</ol>

<br>
<h3>EndPoint - Listar de Usuarios</h3>
<ol>
<li> Seleccionar la acción GET y luego ingresar la url : http://localhost:8080/api/users </li>
<li> Seleccionar el botón Send</li>
<li> Ver los resultados</li>
<li> Demostración:
<img src="http://imgfz.com/i/F4E6WQB.png">
</li>
</ol>

<br>
<h2>🛠️Como Probar la API con Swagger</h2>
<p>A continuación, se detallan los pasos para probar los endpoints de la API utilizando Swagger :</p>
<p>URL: http://localhost:8080/swagger-ui/index.html#/ </p>
<h3>EndPoint - Creación de Usuario</h3>

<ol>
<li> Seleccionar botón POST</li>
<li> Ingresar el siguiente json:
<pre>
{
  "name": "Juan Rodriguez",
  "email": "juanflo@dominio.cl",
  "password": "ABC_2024jf",
  "phones": [
    {
      "number": "1234567",
      "citycode": "1",
      "contrycode": "57"
    }
  ]
}
</pre>
</li>
<li> Seleccionar el botón Execute</li>
<li> Ver los resultados</li>
<li> Demostración:
<img src="http://imgfz.com/i/HevpEKy.png">
</li>
</ol>
<br>
<h3>EndPoint - Listar de Usuarios</h3>
<ol>
<li> Seleccionar el botón GET </li>
<li> Seleccionar el botón Execute</li>
<li> Ver los resultados</li>
<li> Demostración:
<img src="http://imgfz.com/i/FUJDpnQ.png">
</li>
</ol>

<br>
<h2>🛠️Ejecutar Prueba Unitaria</h2>
<p>A continuación, se detallan los pasos para ejecutar los Test de Prueba Unitaria :</p>
<ol>
<li> Seleccionar la clase UserControllerTest ubicado dentro de la carpeta test </li>
<li> Ejecutar los Test de la clase, seleccionar Run UserControllerTest </li>
<li> Ver los resultados </li>
<li> Demostración:
<img src="http://imgfz.com/i/AaNWPEs.png">
</li>
</ol>
<h2>‍👨‍💻Autor</h2>
<ul>
<li><b>José Miguel Flores Ingaruca</b> - <a href="https://www.linkedin.com/in/jose-miguel-flores-ingaruca/">Linkedin</a> </li> 
</ul>




