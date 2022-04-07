package com.beans;

import com.dao.DaoDepartamento;
import com.modelo.Departamento;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
//import javax.inject.Named;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


/**
 * Nombre de la clase:DepartamentoBean
 * Fecha:12/07/2018
 * Versión:1.0
 * CopyRight:ITCA-FEPADE
 * @author Giovanni Tzec
 */
//@Named(value = "departamentoBean")
@ManagedBean
@ViewScoped
public class DepartamentoBean {

    public DepartamentoBean() {
    }
    
    //Atributos generales
    private Departamento dep = new Departamento();
    private List<Departamento> listarDepto;
    DaoDepartamento dao = new DaoDepartamento();

    public Departamento getDep() {
        return dep;
    }

    public void setDep(Departamento dep) {
        this.dep = dep;
    }

    public List<Departamento> getListarDepto() {
        return listarDepto;
    }

    public void setListarDepto(List<Departamento> listarDepto) {
        this.listarDepto = listarDepto;
    }
    
    //metodos 
    public void registrar(){
        try {
            dao.insertar(dep);
            //para limpiar
            dep = new Departamento();
            //informacions
            //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Datos insertados correctamente"));
            addMessage("Exito", "Departamento registrado correctamente.");
            this.listarD();
        } catch (Exception e) {
        }
    }//fin de registrar
    
    
    
    public void listarD(){
        try {
            listarDepto = dao.mostrar();
            //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Datos mostrados correctamente"));
        } catch (Exception e) {
        }
    }//find e listarDepto
        
    public void seleccionar(Departamento depto){
        Departamento departVar;
        try 
        {            
            departVar = dao.leerId(depto); 
            if(departVar!=null){
                this.dep = departVar;
            }
            
        } catch (Exception e) {
        }
    }//fin del metodo seleccionar
    
    public void modificar(){
        try {
            dao.modificar(dep);
            this.listarD();
            addMessage("Exito", "Departamento modificado correctamente");
        } catch (Exception e) {
        }
    }//fin de modificar
    public void eliminar(Departamento depto){
        try {
            dao.eliminar(depto);
            addMessage("Exito", "Departamento eliminado correctamente.");
            this.listarD();
        } catch (Exception e) {
        }
    }//fin de eliminar
    
    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
}
