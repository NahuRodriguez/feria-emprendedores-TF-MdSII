package com.feria.servicios;

import com.feria.modelos.Producto;

public class ServicioStock {

    public void actualizarStock(Producto producto, int cantidad) {
        producto.restarStock(cantidad);
    }
}