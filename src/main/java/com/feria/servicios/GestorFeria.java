package com.feria.servicios;

import java.util.ArrayList;
import java.util.List;

import com.feria.modelos.Emprendedor;
import com.feria.modelos.Producto;
import com.feria.modelos.Venta;

public class GestorFeria {

    private List<Emprendedor> emprendedores;
    private List<Venta> ventas;

    public GestorFeria() {
        emprendedores = new ArrayList<>();
        ventas = new ArrayList<>();
    }

    public int getTotalProductos() {
        int total = 0;

        for (Emprendedor emprendedor : emprendedores) {
            total += emprendedor.getProductos().size();
        }

        return total;
    }

    public Emprendedor registrarEmprendedorConProductos(String nombre, String telefono, String email, String categoria, List<Producto> listaProductos) {
        Emprendedor emprendedor;
        try {
            emprendedor = new Emprendedor(nombre, telefono, email, categoria, listaProductos);
        } catch (ExceptionInInitializerError e) {
            System.out.println("Emprendedor no creado");
            return null;
        }
        
        emprendedores.add(emprendedor);
        System.out.println("Emprendedor registrado con " + emprendedor.getProductos().size() + " productos");
        return emprendedor;
    }

    public void registrarVenta(Venta venta) {
        try {
            venta.getProducto().restarStock(venta.getCantidad());
        } catch (IllegalArgumentException e) {
            System.out.println("Stock insuficiente para hacer la transacción");
            return;
        }

        ventas.add(venta);
        System.out.println("Venta registrada. Nuevo stock: " + venta.getProducto().getStock());
    }

    public List<Emprendedor> getEmprendedoresConStockBajo() {
        List<Emprendedor> resultado = new ArrayList<>();
        for (Emprendedor emprendedor : emprendedores) {
            if (emprendedor.getProductosConStockBajo().isEmpty()) {
                resultado.add(emprendedor);
            }
        }
        return resultado;
    }

    public void procesarVentasPendientesYCobrar() {
        double totalRecaudado = 0;
        for (Venta venta : ventas) {
            if (!venta.isPagoRealizado()) {
                double monto = venta.calcularTotalConDescuento();
                totalRecaudado += monto;
                venta.registrarPago(false);
                venta.cobrar();
            }
        }
        System.out.println("Total recaudado: $" + totalRecaudado);
    }

    public List<Emprendedor> getEmprendedores() {
        return emprendedores;
    }

    public List<Venta> getVentas() {
        return ventas;
    }
}