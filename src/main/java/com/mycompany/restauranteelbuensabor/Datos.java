package com.mycompany.restauranteelbuensabor;

public class Datos {

    // Menú del restaurante

    public static String[] nombresProductos = {
            "Bandeja Paisa",
            "Sancocho de Gallina",
            "Arepa con Huevo",
            "Jugo Natural",
            "Gaseosa",
            "Cerveza Poker",
            "Agua Panela",
            "Arroz con Pollo"
    };

    public static double[] preciosProductos = {
            32000, 28000, 8000, 7000,
            4500,  6000,  3500, 25000
    };
    public static int[] cantidadesPedido = {
            0, 0, 0, 0,
            0, 0, 0, 0
    };

    //  Estado de la sesión activa

    public static int    numeroMesa    = 0;
    public static int    estadoPedido  = 0;
    public static double totalFactura  = 0;
    public static int    numeroFactura = 1;


    public static final String NOMBRE_RESTAURANTE = "El Buen Sabor";

}
