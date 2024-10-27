import java.util.LinkedList
import java.util.Queue
import java.util.Stack

data class Pupusa(val tipo: String, val cantidad: Int)

data class Orden(val cliente: String, val pupusas: List<Pupusa>)

fun main() {
    val ordenesPendientes: Queue<Orden> = LinkedList()
    val ordenesDespachadas: Stack<Orden> = Stack()

    while (true) {
        println("\nBienvenido a la Pupusería 'El Comalito'")
        println("Seleccione una opción:")
        println("1. Registrar una nueva orden")
        println("2. Ver órdenes pendientes")
        println("3. Despachar orden")
        println("4. Ver órdenes despachadas")
        println("5. Salir")
        print("Opción: ")
        
        when (readLine()!!.toInt()) {
            1 -> registrarOrden(ordenesPendientes)
            2 -> verOrdenesPendientes(ordenesPendientes)
            3 -> despacharOrden(ordenesPendientes, ordenesDespachadas)
            4 -> verOrdenesDespachadas(ordenesDespachadas)
            5 -> {
                println("Cerrando el programa...")
                break
            }
            else -> println("Opción no válida. Intente nuevamente.")
        }
    }
}

fun registrarOrden(ordenesPendientes: Queue<Orden>) {
    print("Ingrese el nombre del cliente: ")
    val cliente = readLine()!!

    print("¿Cuántos tipos de pupusas desea ordenar?: ")
    val cantidadTipos = readLine()!!.toInt()

    val pupusas = mutableListOf<Pupusa>()
    for (i in 1..cantidadTipos) {
        print("Ingrese el tipo de pupusa #$i: ")
        val tipo = readLine()!!
        print("Ingrese la cantidad de pupusas de $tipo: ")
        val cantidad = readLine()!!.toInt()
        pupusas.add(Pupusa(tipo, cantidad))
    }

    val orden = Orden(cliente, pupusas)
    ordenesPendientes.add(orden)
    println("Orden registrada para $cliente: ${pupusas.joinToString { "${it.cantidad} pupusas de ${it.tipo}" }}")
}

fun verOrdenesPendientes(ordenesPendientes: Queue<Orden>) {
    if (ordenesPendientes.isEmpty()) {
        println("No hay órdenes pendientes.")
    } else {
        println("Órdenes pendientes:")
        ordenesPendientes.forEachIndexed { index, orden ->
            val detalles = orden.pupusas.joinToString { "${it.cantidad} pupusas de ${it.tipo}" }
            println("${index + 1}. ${orden.cliente}: $detalles")
        }
    }
}

fun despacharOrden(ordenesPendientes: Queue<Orden>, ordenesDespachadas: Stack<Orden>) {
    if (ordenesPendientes.isEmpty()) {
        println("No hay órdenes pendientes para despachar.")
    } else {
        val ordenDespachada = ordenesPendientes.poll()
        ordenesDespachadas.push(ordenDespachada)
        val detalles = ordenDespachada.pupusas.joinToString { "${it.cantidad} pupusas de ${it.tipo}" }
        println("Despachando la orden de: ${ordenDespachada.cliente}: $detalles")
    }
}

fun verOrdenesDespachadas(ordenesDespachadas: Stack<Orden>) {
    if (ordenesDespachadas.isEmpty()) {
        println("No hay órdenes despachadas.")
    } else {
        println("Órdenes despachadas:")
        ordenesDespachadas.forEachIndexed { index, orden ->
            val detalles = orden.pupusas.joinToString { "${it.cantidad} pupusas de ${it.tipo}" }
            println("${index + 1}. ${orden.cliente}: $detalles")
        }
    }
}