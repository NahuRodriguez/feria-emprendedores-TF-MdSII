package com.feria.servicios;

import com.feria.modelos.Emprendedor;
import com.feria.modelos.Venta;

public class Reportes {

    public String generarReportePorCategoria(GestorFeria gestor, String categoria) {
        String reporte = "=== REPORTE DE EMPRENDEDORES - CATEGORIA: " + categoria + " ===\n";

        for (Emprendedor emprendedor : gestor.getEmprendedores()) {
            if (emprendedor.getCategoria().equals(categoria)) {
                reporte += emprendedor.mostrarInfo();
                reporte += emprendedor.validarConInfo();
                reporte += "---\n";
            }
        }

        return reporte;
    }

    public String getEmprendedoresPorCategoria(GestorFeria gestor, String categoria) {
        String resultado = "REPORTE CATEGORIA " + categoria + "\n";
        for (Emprendedor emprendedor : gestor.getEmprendedores()) {
            if (emprendedor.getCategoria().equals(categoria)) {
                resultado += emprendedor.getNombre() + "\n";
            }
        }
        return resultado;
    }

    public double calcularVentasTotales(GestorFeria gestor) {
        double total = 0;
        for (Venta venta : gestor.getVentas()) {
            total += venta.calcularTotalConDescuento();
        }
        return total;
    }

    public void imprimirResumenEjecutivo(GestorFeria gestor) {
        System.out.println("========== RESUMEN EJECUTIVO ==========");
        System.out.println("Total emprendedores: " + gestor.getEmprendedores().size());
        System.out.println("Total productos: " + gestor.getTotalProductos());
        System.out.println("Total ventas: " + gestor.getVentas().size());

        System.out.println("Total facturado: $" + calcularVentasTotales(gestor));

        System.out.println("Emprendedores con stock bajo: " + gestor.getEmprendedoresConStockBajo().size());
        System.out.println("=======================================");
    }
}