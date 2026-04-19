package com.mycompany.restauranteelbuensabor;

import java.util.Scanner;

public class RestauranteElBuenSabor {

    private static final String SEPARADOR   = "=====================================";
    private static final int    MAX_INTENTOS = 3;


    private static Scanner scanner = new Scanner(System.in);

    // ── Punto de entrada ─────────────────────────────────────────────────────

    public static void main(String[] args) {
        mostrarEncabezadoRestaurante();
        ejecutarMenuPrincipal();
        scanner.close();
    }

    private static void ejecutarMenuPrincipal() {
        boolean aplicacionActiva  = true;
        int     intentosInvalidos = 0;

        while (aplicacionActiva) {
            mostrarOpcionesMenu();
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    procesarVerCarta();
                    intentosInvalidos = 0;
                    break;
                case 2:
                    procesarAgregarProducto();
                    intentosInvalidos = 0;
                    break;
                case 3:
                    procesarVerPedido();
                    intentosInvalidos = 0;
                    break;
                case 4:
                    procesarGenerarFactura();
                    intentosInvalidos = 0;
                    break;
                case 5:
                    procesarNuevaMesa();
                    intentosInvalidos = 0;
                    break;
                case 0:
                    aplicacionActiva = false;
                    System.out.println("Hasta luego!");
                    break;
                default:
                    intentosInvalidos = procesarOpcionInvalida(intentosInvalidos);
            }
        }
    }

    // ── Opción 1: Ver carta

    private static void procesarVerCarta() {
        System.out.println();
        Imprimir.mostrarCarta();
        System.out.println();
    }

    // ── Opción 2: Agregar producto al pedido

    private static void procesarAgregarProducto() {
        System.out.println("--- AGREGAR PRODUCTO ---");
        System.out.print("Numero de producto (1-" + Datos.nombresProductos.length + "): ");
        int numeroProducto = scanner.nextInt();
        System.out.print("Cantidad: ");
        int cantidad = scanner.nextInt();

        if (!esProductoValido(numeroProducto)) {
            mostrarErrorProductoInvalido(numeroProducto);
            return;
        }
        if (!esCantidadValida(cantidad)) {
            mostrarErrorCantidadInvalida(cantidad);
            return;
        }

        activarMesaSiEsNecesario();
        agregarProductoAlPedido(numeroProducto, cantidad);
        System.out.println();
    }

    // ── Opción 3: Ver pedido actual

    private static void procesarVerPedido() {
        System.out.println();
        if (Utilidades.hayProductosEnPedido()) {
            Imprimir.mostrarPedido();
        } else {
            System.out.println("No hay productos en el pedido actual.");
            System.out.println("Use la opcion 2 para agregar productos.");
        }
        System.out.println();
    }

    // ── Opción 4: Generar factura

    private static void procesarGenerarFactura() {
        System.out.println();
        if (!Utilidades.hayProductosEnPedido()) {
            System.out.println("No se puede generar factura.");
            System.out.println("No hay productos en el pedido.");
            System.out.println("Use la opcion 2 para agregar productos primero.");
            return;
        }
        ResumenFactura resumen = Proceso.calcularResumenPedido();
        Imprimir.imprimirFacturaCompleta(resumen);
        Proceso.cerrarFactura();
        System.out.println();
    }

    // ── Opción 5: Nueva mesa

    private static void procesarNuevaMesa() {
        System.out.println();
        Utilidades.reiniciarSesion();
        System.out.println("Mesa reiniciada. Lista para nuevo cliente.");
        System.out.println();
    }

    // ── Opción inválida


    private static int procesarOpcionInvalida(int intentosInvalidos) {
        intentosInvalidos++;
        System.out.println("Opcion no valida. Seleccione entre 0 y 5.");
        if (intentosInvalidos >= MAX_INTENTOS) {
            System.out.println("Demasiados intentos invalidos.");
            intentosInvalidos = 0;
        }
        return intentosInvalidos;
    }
    

    private static void mostrarEncabezadoRestaurante() {
        System.out.println(SEPARADOR);
        System.out.println("    RESTAURANTE EL BUEN SABOR");
        System.out.println("    Calle 15 #8-32, Valledupar");
        System.out.println("    NIT: 900.123.456-7");
        System.out.println(SEPARADOR);
    }

    private static void mostrarOpcionesMenu() {
        System.out.println("1. Ver carta");
        System.out.println("2. Agregar producto al pedido");
        System.out.println("3. Ver pedido actual");
        System.out.println("4. Generar factura");
        System.out.println("5. Nueva mesa");
        System.out.println("0. Salir");
        System.out.println(SEPARADOR);
        System.out.print("Seleccione una opcion: ");
    }

    private static boolean esProductoValido(int numeroProducto) {
        return numeroProducto > 0 && numeroProducto <= Datos.nombresProductos.length;
    }

    private static boolean esCantidadValida(int cantidad) {
        return cantidad > 0;
    }

    private static void mostrarErrorProductoInvalido(int numeroProducto) {
        if (numeroProducto <= 0) {
            System.out.println("El numero debe ser mayor a cero.");
        } else {
            System.out.println("Producto no existe. La carta tiene "
                    + Datos.nombresProductos.length + " productos.");
        }
        System.out.println();
    }

    private static void mostrarErrorCantidadInvalida(int cantidad) {
        if (cantidad == 0) {
            System.out.println("La cantidad no puede ser cero.");
        } else {
            System.out.println("Cantidad invalida. Ingrese un valor positivo.");
        }
        System.out.println();
    }

    private static void activarMesaSiEsNecesario() {
        if (Datos.estadoPedido == 0) {
            System.out.print("Ingrese numero de mesa: ");
            int mesaIngresada = scanner.nextInt();
            if (mesaIngresada > 0) {
                Datos.numeroMesa   = mesaIngresada;
                Datos.estadoPedido = 1;
            } else {
                // Si ingresan un número inválido se informa y se pide de nuevo
                System.out.println("Numero de mesa invalido. Se asigna mesa 1.");
                Datos.numeroMesa   = 1;
                Datos.estadoPedido = 1;
            }
        }
    }

    private static void agregarProductoAlPedido(int numeroProducto, int cantidad) {
        int    indice        = numeroProducto - 1;
        double subtotalLinea = Datos.preciosProductos[indice] * cantidad;
        Datos.cantidadesPedido[indice] += cantidad;
        System.out.println("Producto agregado al pedido.");
        System.out.printf ("  -> %s x%d  ($%,.0f)%n",
                Datos.nombresProductos[indice], cantidad, subtotalLinea);
    }

}