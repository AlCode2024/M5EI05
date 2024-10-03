# Sistema de Registro de Usuarios 5.0

Sistema de Gestión de usuarios donde agregamos la funcion para filtrar por edad.
Para probar este sistema se genero una lista ficticia de usuarios, que estan cargados por defecto.

## Características
- Agregar usuarios con datos como nombre, edad, trabajo, referencia.
- Listar todos los usuarios registrados.
- Filtrar y mostrar usuarios cuya edad sea igual o mayor a un número ingresado.
- Eliminar usuarios por su nombre.

### Clases Principales

#### 1. `Usuario`
Esta clase representa a un usuario en el sistema. Tiene los siguientes atributos:
- `nombre`: El nombre del usuario (obligatorio).
- `edad`: La edad del usuario (obligatorio).
- `trabajo`: El trabajo del usuario (opcional).
- `referencia`: Una referencia asociada con el usuario (opcional).

Incluye una función `mostrarDatos()` que imprime los datos del usuario de forma legible en la consola.

#### 2. `ListaUsuarios`
Esta clase contiene una lista de objetos `Usuario` y proporciona las funciones para agregar, eliminar, listar y filtrar usuarios. Al iniciar el sistema, se crea una lista de usuarios por defecto.

### Filtrado de Usuarios por Edad

La función **`mostrarUsuariosPorEdad(minEdad: Int)`** Filtra los usuarios y muestran los que cumplen con la condicion de tener una edad mayor o igual a la ingresada en la consulta.

`Filter` es un método que se puede aplicar a colecciones (listas, arrays, etc.) para seleccionar solo los elementos que cumplen con una condición. En otras palabras, crea una nueva lista a partir de la original, pero solo con los elementos que pasen un "filtro" que le damos como parámetro.

usuarios.filter { it.edad >= minEdad }

A la lista usuarios le agregamos el metodo filter ( usuarios.filter) y declaramos la condición que deben cumplir ({ it.edad >= minEdad }).

```kotlin
fun mostrarUsuariosPorEdad(minEdad: Int) {
    val usuariosFiltrados = usuarios.filter { it.edad >= minEdad }.sortedBy { it.edad }
    if (usuariosFiltrados.isNotEmpty()) {
        println("----- Usuarios con edad igual o mayor a $minEdad años -----")
        for (usuario in usuariosFiltrados) {
            usuario.mostrarDatos()
        }
    } else {
        println("No hay usuarios con edad igual o mayor a $minEdad años.")
    }
}


