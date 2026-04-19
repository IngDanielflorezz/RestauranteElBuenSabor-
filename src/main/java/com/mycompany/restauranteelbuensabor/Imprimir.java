package com.mycompany.restauranteelbuensabor;

public class Imprimir {

    // ── Constantes de presentación
    private static final String SEPARADOR_LARGO  = "========================================";
    private static final String SEPARADOR_CORTO  = "----------------------------------------";
    private static final String NOMBRE_LOCAL      = "    RESTAURANTE EL BUEN SABOR";
    private static final String DIRECCION         = "    Calle 15 #8-32, Valledupar";
    private static final String NIT               = "    NIT: 900.123.456-7";

    // ── Mostrar carta del restaurante

    public static void mostrarCarta() {
        System.out.println(SEPARADOR_LARGO);
        System.out.println(NOMBRE_LOCAL);
        System.out.println("    --- NUESTRA CARTA ---");
        System.out.println(SEPARADOR_LARGO);

        for (int i = 0; i < Datos.nombresProductos.length; i++) {
            System.out.printf("%d. %-22s $%,.0f%n",
                    (i + 1),
                    Datos.nombresProductos[i],
                    Datos.preciosProductos[i]);
        }

        System.out.println(SEPARADOR_LARGO);
    }

    // ── Mostrar el pedido activo con subtotal

    public static void mostrarPedido() {
        System.out.println("--- PEDIDO ACTUAL ---");

        double subtotal = 0;

        for (int i = 0; i < Datos.nombresProductos.length; i++) {
            if (Datos.cantidadesPedido[i] > 0) {
                double subtotalLinea = Datos.preciosProductos[i] * Datos.cantidadesPedido[i];
                System.out.printf("%-20s x%-6d $%,.0f%n",
                        Datos.nombresProductos[i],
                        Datos.cantidadesPedido[i],
                        subtotalLinea);
                subtotal += subtotalLinea;
            }
        }

        System.out.println(SEPARADOR_CORTO);
        System.out.printf("%-27s $%,.0f%n", "Subtotal:", subtotal);
    }

    // ── Imprimir factura completa con detalle de ítems


    public static void imprimirFacturaCompleta(ResumenFactura resumen) {
        imprimirEncabezadoFactura();
        System.out.printf("FACTURA No. %03d%n", Datos.numeroFactura);
        System.out.println(SEPARADOR_CORTO);
        imprimirItemsPedido();
        System.out.println(SEPARADOR_CORTO);
        imprimirTotales(resumen);
        System.out.println(SEPARADOR_LARGO);
        System.out.println("Gracias por su visita!");
        System.out.println("El Buen Sabor - Valledupar");
        System.out.println(SEPARADOR_LARGO);
    }

    // ── Imprimir factura

    public static void imprimirFacturaResumen(ResumenFactura resumen) {
        imprimirEncabezadoFactura();
        System.out.printf("FACTURA No. %03d (RESUMEN)%n", Datos.numeroFactura);
        System.out.println(SEPARADOR_CORTO);
        imprimirTotales(resumen);
        System.out.println(SEPARADOR_LARGO);
    }


    private static void imprimirEncabezadoFactura() {
        System.out.println(SEPARADOR_LARGO);
        System.out.println(NOMBRE_LOCAL);
        System.out.println(DIRECCION);
        System.out.println(NIT);
        System.out.println(SEPARADOR_LARGO);
    }

    private static void imprimirItemsPedido() {
        for (int i = 0; i < Datos.nombresProductos.length; i++) {
            if (Datos.cantidadesPedido[i] > 0) {
                double subtotalLinea = Datos.preciosProductos[i] * Datos.cantidadesPedido[i];
                System.out.printf("%-20s x%-6d $%,.0f%n",
                        Datos.nombresProductos[i],
                        Datos.cantidadesPedido[i],
                        subtotalLinea);
            }
        }
    }

    private static void imprimirTotales(ResumenFactura resumen) {
        System.out.printf("%-27s $%,.0f%n", "Subtotal:",   resumen.getSubtotal());
        System.out.printf("%-27s $%,.0f%n", "IVA (19%):",   resumen.getIva());
        if (resumen.getPropina() > 0) {
            System.out.printf("%-27s $%,.0f%n", "Propina (10%):", resumen.getPropina());
        }
        System.out.println(SEPARADOR_CORTO);
        System.out.printf("%-27s $%,.0f%n", "TOTAL:",       resumen.getTotal());
    }

}