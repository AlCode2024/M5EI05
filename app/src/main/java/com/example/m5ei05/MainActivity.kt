// Clase Usuario
class Usuario(
    val nombre: String,
    val edad: Int,
    val trabajo: String?,
    val referencia: String?
) {
    // Función datos
    fun mostrarDatos() {
        val datos = mutableListOf<String>()
        datos.add(nombre)
        datos.add("$edad años")
        if (trabajo != null) datos.add(trabajo)
        if (referencia != null) datos.add("Referenciado por $referencia")
        println(datos.joinToString(" - "))
    }
}

// Lista de Usuarios
class ListaUsuarios {
    private val usuarios = mutableListOf<Usuario>()

    // Cargar usuarios por defecto (jugadores de Colo Colo)
    init {
        usuarios.add(Usuario("Arturo Vidal", 36, "Futbolista", "Marcelo Barticciotto"))
        usuarios.add(Usuario("Esteban Paredes", 43, "Entrenador", "Héctor Tapia"))
        usuarios.add(Usuario("Claudio Bravo", 41, "Arquero", "Francisco Valdés"))
        usuarios.add(Usuario("Matías Fernández", 38, "Futbolista", "Jaime Pizarro"))
        usuarios.add(Usuario("Alfredo Andres", 44, "Capitan", "Messi"))
    }

    // Agregar un usuario
    fun agregarUsuario(usuario: Usuario) {
        usuarios.add(usuario)
        println("Usuario agregado: ${usuario.nombre}")
    }

    // Eliminar un usuario
    fun eliminarUsuario(nombre: String) {
        val usuarioAEliminar = usuarios.find { it.nombre == nombre }
        if (usuarioAEliminar != null) {
            usuarios.remove(usuarioAEliminar)
            println("Usuario eliminado: $nombre")
        } else {
            println("Usuario no encontrado: $nombre")
        }
    }

    // Mostrar la lista de usuarios
    fun mostrarLista() {
        if (usuarios.isNotEmpty()) {
            println("----- Lista de Usuarios Registrados -----")
            for (usuario in usuarios) {
                usuario.mostrarDatos() // Muestra datos
            }
        } else {
            println("No se ha registrado ningún usuario.")
        }
    }

    // Mostrar usuarios cuya edad sea igual o mayor a cierta edad en orden ascendente
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
}

fun main() {
    println("Bienvenido al sistema de registro de usuarios 2.0")

    val listaUsuarios = ListaUsuarios()

    // Crear un usuario
    fun crearUsuario(): Usuario? {
        println("Ingrese el nombre del usuario (Obligatorio):")
        val nombre = readLine()?.takeIf { it.isNotBlank() } ?: run {
            println("No ingresó el nombre, no se ha creado ningún usuario.")
            return null
        }

        var edad: Int? = null
        while (edad == null) {
            println("Ingrese la edad del usuario (Obligatorio):")
            edad = readLine()?.toIntOrNull()
            if (edad == null) {
                println("Debe ingresar una edad válida.")
            }
        }

        println("Ingrese el trabajo del usuario:")
        val trabajo = readLine().takeIf { it?.isNotBlank() == true }

        println("¿Quién lo referencia?:")
        val referencia = readLine()?.takeIf { it.isNotBlank() }

        return Usuario(nombre, edad, trabajo, referencia)
    }

    // Menu principal para iniciar la operación
    fun mostrarMenu() {
        println("\nOpciones:")
        println("1. Ingresar usuario")
        println("2. Consultar lista")
        println("3. Consultar usuarios por edad mínima")
        println("4. Eliminar usuario")
        println("5. Salir")
    }

    // Ciclo del menú
    var opcion: String?
    do {
        mostrarMenu()
        opcion = readLine()

        when (opcion) {
            "1" -> {
                val usuario = crearUsuario()
                if (usuario != null) {
                    listaUsuarios.agregarUsuario(usuario)
                }
            }
            "2" -> listaUsuarios.mostrarLista()
            "3" -> {
                println("Escriba la edad mínima de los usuarios que desea ver:")
                val edadMinima = readLine()?.toIntOrNull()
                if (edadMinima != null) {
                    listaUsuarios.mostrarUsuariosPorEdad(edadMinima)
                } else {
                    println("Edad no válida.")
                }
            }
            "4" -> {
                println("Ingrese el nombre del usuario a eliminar:")
                val nombreEliminar = readLine()
                if (!nombreEliminar.isNullOrBlank()) {
                    listaUsuarios.eliminarUsuario(nombreEliminar)
                } else {
                    println("No se ha ingresado un nombre válido para eliminar.")
                }
            }
            "5" -> println("Saliendo del sistema. Gracias por su preferencia.")
            else -> println("Opción no válida. Por favor, ingrese una opción válida.")
        }
    } while (opcion != "5")
}
