package com.dao;

import com.conexion.Conexion;
import com.modelo.Departamento;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Nombre de la clase:DaoDepartamento
 * Fecha:12/07/2018
 * Versión:1.0
 * CopyRight:ITCA-FEPADE
 * @author Giovanni Tzec
 */
public class DaoDepartamento extends Conexion{
    
    public void insertar(Departamento dep){
        String sql;
        PreparedStatement pr;
        
        try {
            if(this.conectar()==true){
                sql = "insert into departamento(nombre, cantEmpleado) values(?,?)";
                pr = this.getConn().prepareStatement(sql);
                pr.setString(1,dep.getNombre());
                pr.setInt(2,dep.getCantidadEmpleados());
                pr.executeUpdate();
                System.out.println("exito al insertar");
            }else{
                System.out.println("error en la conexion");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(this.desconectar()){
                System.out.println("Desconectado");
            }else{
                System.out.println("Conectado aun");
            }
        }
    }//fin de mostrar
    
    public void eliminar(Departamento dep){
        String sql;
        PreparedStatement pr;
        try {
            if(this.conectar()){
                
                sql = "delete from departamento where codigoDep=?";
                pr = this.getConn().prepareStatement(sql);
                pr.setInt(1,dep.getCodigoDepartamento());
                pr.executeUpdate();
                System.out.println("datos eliminados correctamente");
            }else{
                System.out.println("no se ha podido conectar eliminar");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(this.desconectar()){
                System.out.println("Desconectado");
            }else{
                System.out.println("Conectado aun");
            }
        }
    }//fin de eliminar
    
    public void modificar(Departamento dep){
        String sql;
        PreparedStatement pr;
        try {
            if(this.conectar()){
                sql = "update departamento set nombre=?, cantEmpleado=? "
                        + "where codigoDep=?";
                pr = this.getConn().prepareStatement(sql);
                pr.setString(1,dep.getNombre());
                pr.setInt(2,dep.getCantidadEmpleados());
                pr.setInt(3,dep.getCodigoDepartamento());
                pr.executeUpdate();
                System.out.println("datos modificados correctamente");
            }else{
                System.out.println("no se ha podido conectar");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(this.desconectar()){
                System.out.println("Desconectado");
            }else{
                System.out.println("Conectado aun");
            }
        }
    }//fin de modificar
    
    
    public List<Departamento> mostrar(){
        List<Departamento> dato= new ArrayList();
        try 
        {
        PreparedStatement pr;
        ResultSet rs;
        Departamento dp;
            if(this.conectar()){
                pr = this.getConn().prepareCall("select * from departamento");
                rs = pr.executeQuery();
                
                while(rs.next()){
                   dp = new Departamento();
                        dp.setCodigoDepartamento(rs.getInt("codigoDep"));
                        dp.setNombre(rs.getString("nombre"));
                        dp.setCantidadEmpleados(rs.getInt("cantEmpleado"));
                    
                    System.out.println("lista llena");
                    dato.add(dp);
                }
            }else{
                System.out.println("la lista esta vacia");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(this.desconectar()){
                System.out.println("Desconectado");
            }else{
                System.out.println("Conectado aun");
            }
        }
        return dato;
    }//fin de mostrar
    
    public Departamento leerId(Departamento dep){
        
        PreparedStatement pr;
        ResultSet rs;
        Departamento dp = null;
        
        try {
            if(this.conectar()){
                pr = this.getConn().prepareStatement("select codigoDep, nombre, cantEmpleado "
                        + "from departamento where codigoDep=?");
                pr.setInt(1, dep.getCodigoDepartamento());
                rs = pr.executeQuery();
                
                while(rs.next()){
                    dp = new Departamento();
                    dp.setCodigoDepartamento(rs.getInt("codigoDepartamento"));
                    dp.setNombre(rs.getString("nombre"));
                    dp.setCantidadEmpleados(rs.getInt("cantidadEmpleado"));
                    System.out.println("lista llena segun el id");
                    
                }        
            }else{
                System.out.println("la lista esta vacia");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(this.desconectar()){
                System.out.println("Desconectado");
            }else{
                System.out.println("Conectado aun");
            }
        }
        return dp;
    }//fin listar departamento  
}
