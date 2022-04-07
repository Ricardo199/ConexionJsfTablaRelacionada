package com.beans;

import com.dao.DaoEmpleado;
import com.modelo.Empleado;
import javax.inject.Named;
//import javax.enterprise.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.component.datatable.DataTable;

/**
 * Nombre de la clase:EmpleadoBean
 * Fecha:12/07/2018
 * Versión:1.0
 * CopyRight:ITCA-FEPADE
 * @author Giovanni Tzec
 */
@Named(value = "empleadoBean")
//@SessionScoped
public class EmpleadoBean implements Serializable {

    private Empleado empObjetivo;
    private ArrayList<Empleado> listaEmpleados;    
    
    public EmpleadoBean() {
        empObjetivo = new Empleado();
    }  
    
    public Empleado getEmpObjetivo() {
        return empObjetivo;
    }

    public void setEmpObjetivo(Empleado empleado) {
        this.empObjetivo = empleado;
    }

    public ArrayList<Empleado> getListaEmpleados() {
        return listaEmpleados;
    }

    public void setListaEmpleados(ArrayList<Empleado> listaEmpleados) {
        this.listaEmpleados = listaEmpleados;
    }
    
    public void insertarEmpleado(){
        DaoEmpleado daoEmp;
        
        daoEmp = new DaoEmpleado();
        daoEmp.insertarDb(this.empObjetivo);
        
        listarEmpleados();
        this.empObjetivo = new Empleado();
        
        //mostrando menaje
        FacesContext context = FacesContext.getCurrentInstance();
        
        context.addMessage(null, new FacesMessage("Exito","Empleado insertado correctamente"));
    }
    
    public void modificarEmpleado(){
        DaoEmpleado daoEmp;
        
        daoEmp = new DaoEmpleado();
        daoEmp.modificarDb(this.empObjetivo);
        
        listarEmpleados();
        this.empObjetivo = new Empleado();
        
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Exito","Empleado modificado correctamente"));
    }

    public void listarEmpleados(){
        DaoEmpleado daoEmp;
        
        daoEmp = new DaoEmpleado();
        this.listaEmpleados = daoEmp.listarDb();
        this.empObjetivo = new Empleado();
    }
    
    public void delete(Empleado base){
        DaoEmpleado daoEmp;
        Empleado prueba;
        int id;

        daoEmp = new DaoEmpleado();
        id = base.getCodigoEmpleado();
        
        prueba = new Empleado();
        prueba.setCodigoEmpleado(id);
        
        daoEmp.eliminarDb(base);
        
        this.listarEmpleados();
        this.empObjetivo = new Empleado();
        
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Exito", "Empleado eliminado correctamente"));
    }
    
    public void buscarEmpleado(Empleado base){
        DaoEmpleado daoEmp;
        Empleado emp;

        daoEmp = new DaoEmpleado();
        emp = new Empleado();
        emp.setCodigoEmpleado(base.getCodigoEmpleado());
        
        this.empObjetivo = daoEmp.buscarOjetoDb(emp);
    }
}
