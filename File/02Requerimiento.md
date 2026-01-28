# Requerimientos

1. El sistema permitirá al usuario seleccionar una de las siguientes opciones principales: **Jugar**, **Ver Marcador**, **Acceso Admin** o **Salir**.

2. Al seleccionar la opción **Ver Marcador**, el sistema mostrará una tabla con las siguientes columnas de información: **Id Jugador**, **Usuario**, **Puntuación** y **Última Jugada**.

3. Al seleccionar la opción **Salir**, el sistema finalizará la ejecución del programa.

4. Al seleccionar la opción **Jugar**, el usuario (UserPlayer) deberá registrar su nombre utilizando un teclado alfanumérico virtual con distribución **QWERTY**, desplazándose mediante el uso del **Joystick**.

5. En caso de que el usuario ya se encuentre registrado en la base de datos, podrá volver a registrarse utilizando el mismo nombre; al hacerlo, su puntuación se reiniciará y se actualizará en el registro previamente existente.

6. Una vez registrado el nombre del usuario, el sistema permitirá seleccionar la opción **Jugar** para iniciar la partida.

7. Al iniciar el juego, el sistema mostrará una pregunta seleccionada aleatoriamente entre las categorías: **Cultura General**, **Disney**, **Electrónica**, **Planetas**, **Tipos de Datos**, **Ecuador**, **Geografía** y **Videojuegos**, junto con cuatro opciones de respuesta, de las cuales únicamente una será correcta.

8. El usuario podrá desplazarse entre las diferentes opciones de respuesta y seleccionar una de ellas.

9. Si la respuesta seleccionada es correcta, el sistema incrementará en **10 puntos** la puntuación del jugador y avanzará a la siguiente pregunta.

10. Si la respuesta seleccionada es incorrecta, el sistema mostrará una ventana indicando que el jugador ha perdido, junto con su **puntuación final**.

11. El juego finalizará cuando el jugador haya respondido correctamente las **80 preguntas**, indicando que ha ganado y mostrando su puntuación final.

12. Si el usuario posee el rol de **Administrador (UserAdmin)**, podrá acceder al módulo **Acceso Admin**, donde el sistema mostrará una pantalla de autenticación en la que deberá ingresar su usuario y contraseña previamente registrados en la base de datos.

13. Una vez autenticado, el administrador podrá seleccionar entre las siguientes opciones: **Tabla de jugadores**, **Modificar Jugador** y **Buscar Jugador**.

14. Al seleccionar la opción **Tabla de jugadores**, el sistema mostrará una tabla con las siguientes columnas: **Id Jugador**, **Usuario**, **Puntuación**, **Estado**, **Fecha de Creación** y **Fecha de Modificación**.

15. Al seleccionar la opción **Modificar Jugador**, el sistema mostrará una tabla con las siguientes columnas: **Id Jugador**, **Usuario**, **Puntuación**, **Estado**, **Fecha de Creación** y **Fecha de Modificación**. El administrador podrá modificar los campos **Nombre**, **Estado** y **Puntuación**, ingresando el **Id** correspondiente del jugador.

16. Al seleccionar la opción **Buscar Jugador**, el sistema permitirá realizar la búsqueda mediante dos métodos: ingresando el **Id del jugador** o ingresando su **nombre de usuario**.

17. En cada pantalla del módulo administrativo, el administrador podrá seleccionar la opción **Regresar**, lo que permitirá volver a la pantalla anterior correspondiente.
