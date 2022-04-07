package com.modelo;

/**
 *
 * Nombre de la clase:Departamento
 * Fecha:12/07/2018
 * Versión:1.0
 * CopyRight:ITCA-FEPADE
 * @author Giovanni Tzec
 */
public class Departamento {
//<editor-fold defaultstate="collapsed" desc="Atributos">
    private int codigoDepartamento;
    private String nombre;
    private int cantidadEmpleados;
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructores">
    public Departamento() {
    }

    public Departamento(int codigoDepartamento, String nombre, int cantidadEmpleados) {
        this.codigoDepartamento = codigoDepartamento;
        this.nombre = nombre;
        this.cantidadEmpleados = cantidadEmpleados;
    }
//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Getter y Setter">
    


    public int getCodigoDepartamento() {
        return codigoDepartamento;
    }

    public void setCodigoDepartamento(int codigoDepartamento) {
        this.codigoDepartamento = codigoDepartamento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidadEmpleados() {
        return cantidadEmpleados;
    }

    public void setCantidadEmpleados(int cantidadEmpleados) {
        this.cantidadEmpleados = cantidadEmpleados;
    }
//</editor-fold>
}