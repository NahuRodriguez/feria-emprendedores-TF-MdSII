package com.feria.descuentos;

public interface Descuento {
    double aplicar(double total, Venta venta);
}