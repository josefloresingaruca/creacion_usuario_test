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
<li>Java: Lenguaje de programación principal del proyecto - JAVA VERSIÓN 17.</li>
<li>Spring Boot: Framework utilizado para desarrollar la API REST.</li>
<li>Maven: Herramienta de gestión de dependencias y construcción del proyecto.</li>
<li>Jackson: Biblioteca para el procesamiento de datos JSON. </li>
<li>Mockito: Framework de pruebas unitarias para Java. </li>
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
<h2>🛠️Manejo de Errores</h2>
<p>En caso de que ocurra algún error durante el proceso de registro, se devolverá una respuesta con el código de estado correspondiente y un mensaje descriptivo del error.</p>
<ul>
<li>Formato de Correo Electrónico Inválido:
    <ul>
        <li> Código de Estado: 400 Bad Request</li>
        <li> Mensaje de Error: "El formato del correo electrónico es inválido"</li>
    </ul>

</li>
<li>Contraseña Inválida:
  <ul>
        <li> Código de Estado: 400 Bad Request</li>
        <li> Mensaje de Error: "El formato de la contraseña es inválido"</li>
    </ul>
</li>
<li>Correo Electrónico Ya registrado:
 <ul>
        <li> Código de Estado: 409 Conflict</li>
        <li> Mensaje de Error: "El correo electrónico ya está registrado"</li>
    </ul>
</li>
</ul>
<br>
<h2>🛢️Base de Datos</h2>
<img src="http://imgfz.com/i/3ubIZgG.png">

<br>
<h2>🏗️Arquitectura</h2>
<img src="http://imgfz.com/i/RUh1rT6.png">
