
package com.mycompany.restauranteelbuensabor;

public class Proceso {


    private static final double TASA_IVA                 = 0.19;
    private static final double TASA_PROPINA              = 0.10;
    private static final double TASA_DESCUENTO_MULTIITEM  = 0.05;
    private static final double UMBRAL_PROPINA            = 50000;
    private static final int    MIN_ITEMS_PARA_DESCUENTO  = 3;


    public static ResumenFactura calcularResumenPedido() {
        double subtotalBruto   = calcularSubtotalBruto();
        int    totalProductos  = contarProductosDiferentes();
        double subtotalConDesc = aplicarDescuentoMultiitem(subtotalBruto, totalProductos);
        ResumenFactura resumen = aplicarIvaYPropina(subtotalConDesc);

        Datos.estadoPedido = 1;
        Datos.totalFactura = resumen.getTotal();

        return resumen;
    }

    // ── Registrar factura y cerrar pedido

    public static void cerrarFactura() {
        Datos.numeroFactura++;
        Datos.estadoPedido = 0;
    }


    private static double calcularSubtotalBruto() {
        double subtotal = 0;
        for (int i = 0; i < Datos.nombresProductos.length; i++) {
            if (Datos.cantidadesPedido[i] > 0) {
                subtotal += Datos.preciosProductos[i] * Datos.cantidadesPedido[i];
            }
        }
        return subtotal;
    }

    private static int contarProductosDiferentes() {
        int contador = 0;
        for (int i = 0; i < Datos.cantidadesPedido.length; i++) {
            if (Datos.cantidadesPedido[i] > 0) {
                contador++;
            }
        }
        return contador;
    }

    private static double aplicarDescuentoMultiitem(double subtotal, int totalProductos) {
        if (totalProductos > MIN_ITEMS_PARA_DESCUENTO) {
            return subtotal - (subtotal * TASA_DESCUENTO_MULTIITEM);
        }
        return subtotal;
    }

    private static ResumenFactura aplicarIvaYPropina(double subtotal) {
        double iva     = subtotal * TASA_IVA;
        double base    = subtotal + iva;
        double propina = 0;

        // La propina solo aplica cuando el total supera el umbral definido
        if (base > UMBRAL_PROPINA) {
            propina = base * TASA_PROPINA;
        }

        double total = base + propina;
        return new ResumenFactura(subtotal, iva, propina, total);
    }

}