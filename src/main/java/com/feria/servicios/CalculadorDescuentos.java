package com.feria.servicios;

import java.util.ArrayList;
import java.util.List;

import com.feria.descuentos.Descuento;
import com.feria.descuentos.DescuentoPorCantidad;
import com.feria.descuentos.DescuentoPorMonto;
import com.feria.modelos.Venta;

public class CalculadorDescuentos {

    private List<Descuento> descuentos;

    public CalculadorDescuentos() {
        descuentos = new ArrayList<>();
        descuentos.add(new DescuentoPorCantidad());
        descuentos.add(new DescuentoPorMonto());
    }

    public double calcular(Venta venta) {

        double total = venta.getCantidad() * venta.getPrecioUnitario();

        for (Descuento descuento : descuentos) {
            total = descuento.aplicar(total, venta);
        }

        return total;
    }
}