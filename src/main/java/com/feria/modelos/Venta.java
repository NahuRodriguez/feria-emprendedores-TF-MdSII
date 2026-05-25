package com.feria.modelos;

import java.time.LocalDate;

public class Venta {

    static private int nextIdVenta = 1;
    private String idVenta;
    private Emprendedor emprendedor;
    private Producto producto;
    private int cantidad;
    private double precioUnitario;
    private LocalDate fecha;
    private boolean pagoRealizado;

    private static String getNextIdVenta() {
        return "V" + String.format("%03d", nextIdVenta++);
    }

    public Venta(Emprendedor emprendedor, Producto producto, int cantidad, double precioUnitario, LocalDate fecha) {
        this.idVenta = getNextIdVenta();
        this.emprendedor = emprendedor;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.fecha = fecha;
        this.pagoRealizado = false;
    }

    public double calcularTotalConDescuento() {
        double total = cantidad * precioUnitario;

        if (cantidad > 10) {
            total = total * 0.9;
        }

        if (total > 5000) {
            total = total * 0.95;
        }

        return total;
    }

    public void actualizarStock(Producto producto) {
        producto.setStock(producto.getStock() - this.cantidad);
        System.out.println("Stock actualizado");
    }

    public void registrarPago() {
        registrarPago(true);
    }

    public void registrarPago(boolean print) {
        this.pagoRealizado = true;
        if (print) System.out.println("Pago registrado");
    }

    public String generarRecibo() {
        String recibo = "=== RECIBO DE VENTA ===\n";
        recibo += "Venta ID: " + idVenta + "\n";
        recibo += "Fecha: " + fecha + "\n";
        recibo += "Producto: " + producto.getNombre() + "\n";
        recibo += "Cantidad: " + cantidad + "\n";
        recibo += "Precio unitario: $" + precioUnitario + "\n";
        recibo += "Total con descuentos: $" + calcularTotalConDescuento() + "\n";
        recibo += "Pago: " + (pagoRealizado ? "Realizado" : "Pendiente") + "\n";
        return recibo;
    }

    public void cobrar() {
        System.out.println("Cobrada venta " + idVenta + " por $" + calcularTotalConDescuento());
    }

    public String getId() {
        return idVenta;
    }

    public void setId(String idVenta) {
        this.idVenta = idVenta;
    }

    public Emprendedor getEmprendedor() {
        return emprendedor;
    }

    public void setEmprendedor(Emprendedor emprendedor) {
        this.emprendedor = emprendedor;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public boolean isPagoRealizado() {
        return pagoRealizado;
    }

    public void setPagoRealizado(boolean pagoRealizado) {
        this.pagoRealizado = pagoRealizado;
    }
}