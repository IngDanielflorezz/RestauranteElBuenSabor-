package com.mycompany.restauranteelbuensabor;

public class ResumenFactura  {

    private final double subtotal;
    private final double iva;
    private final double propina;
    private final double total;

    public ResumenFactura(double subtotal, double iva,
                          double propina, double total) {
        this.subtotal = subtotal;
        this.iva      = iva;
        this.propina  = propina;
        this.total    = total;
    }

    public double getSubtotal() { return subtotal; }
    public double getIva()      { return iva;      }
    public double getPropina()  { return propina;  }
    public double getTotal()    { return total;    }

}