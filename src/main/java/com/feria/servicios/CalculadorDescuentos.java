package com.feria.servicios;

import com.feria.modelos.Venta;

public class CalculadorDescuentos {

    public double calcular(Venta venta) {
        double total = venta.getCantidad() * venta.getPrecioUnitario();

        if (venta.getCantidad() > 10) {
            total *= 0.9;
        }

        if (total > 5000) {
            total *= 0.95;
        }

        return total;
    }
}