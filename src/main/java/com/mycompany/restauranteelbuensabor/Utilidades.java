package com.mycompany.restauranteelbuensabor;

public class Utilidades {

    // ── Verificar si el pedido tiene al menos un producto

    public static boolean hayProductosEnPedido() {
        for (int i = 0; i < Datos.cantidadesPedido.length; i++) {
            if (Datos.cantidadesPedido[i] > 0) {
                return true;
            }
        }
        return false;
    }

    // ── Reiniciar completamente la sesión de la mesa

    public static void reiniciarSesion() {
        reiniciarCantidades();
        Datos.totalFactura  = 0;
        Datos.estadoPedido  = 0;
        Datos.numeroMesa    = 0;
    }

    // ── Método privado de apoyo

    private static void reiniciarCantidades() {
        for (int i = 0; i < Datos.cantidadesPedido.length; i++) {
            Datos.cantidadesPedido[i] = 0;
        }
    }

}