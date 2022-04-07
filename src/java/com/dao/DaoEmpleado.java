
package com.dao;

import com.conexion.Conexion;
import com.modelo.Departamento;
import com.modelo.Empleado;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import com.utilidades.UtilidadesVarias;

/**
 * Nombre de la clase:DaoEmpleado
 * Fecha:12/07/2018
 * Versión:1.0
 * CopyRight:ITCA-FEPADE
 * @author Giovanni Tzec
 */
public class DaoEmpleado extends Conexion{
    
    public int insertarDb(Empleado emp){
        //variable
        int flag;
        PreparedStatement pstmt;
        String sql;
        
        //procedimiento
        flag = 0;
        try{
            if(this.conectar()){
                sql = "insert into empleado(nombre, genero, "
                        + "intereses, edad, direccion, cargo, codigoDepartamento)"
                        + "values(?,?,?,?,?,?,?);";
                pstmt = this.getConn().prepareStatement(sql);
                
                pstmt.setString(1, emp.getNombre());
                pstmt.setString(2, emp.getGenero());
                pstmt.setString(3, UtilidadesVarias.unirString((ArrayList<String>) emp.getIntereses()));
                pstmt.setInt(4, emp.getEdad());
                pstmt.setString(5, emp.getDireccion());
                pstmt.setString(6, emp.getCargo());
                pstmt.setInt(7, emp.getDepartamento().getCodigoDepartamento());
                
                pstmt.executeUpdate();
                
                pstmt.close();     
                
                flag = 1;
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            desconectar();
        }
        
        return flag;
    }
    
    public int modificarDb(Empleado emp){
        int flag;
        PreparedStatement pstmt;
        String sql;
        
        //procedimiento
        flag = 0;
        try{
            if(this.conectar()){
                sql = "update empleado set nombre=?, genero=?, intereses=?, "
                        + "edad=?, direccion=?, cargo=?, codigoDepartamento=? "
                        + "where codigoEmpleado=?;";
                pstmt = this.getConn().prepareStatement(sql);
                
                pstmt.setString(1, emp.getNombre());
                pstmt.setString(2, emp.getGenero());
                pstmt.setString(3, UtilidadesVarias.unirString((ArrayList<String>) emp.getIntereses()));
                pstmt.setInt(4, emp.getEdad());
                pstmt.setString(5, emp.getDireccion());
                pstmt.setString(6, emp.getCargo());
                pstmt.setInt(7, emp.getDepartamento().getCodigoDepartamento());             
                pstmt.setInt(8, emp.getCodigoEmpleado());
                
                pstmt.executeUpdate();
                
                pstmt.close();     
                
                flag = 1;
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            desconectar();
        }
        
        return flag;
    }
    
    public int eliminarDb(Empleado emp){
        int flag;
        PreparedStatement pstmt;
        String sql;
        
        //procedimiento
        flag = 0;
        try{
            if(this.conectar()){
                sql = "delete from empleado where codigoEmpleado=?;";
           
                pstmt = this.getConn().prepareStatement(sql);
                          
                pstmt.setInt(1, emp.getCodigoEmpleado());
                
                pstmt.executeUpdate();
                
                pstmt.close();     
                
                flag = 1;
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            desconectar();
        }
        
        return flag;
    }
    
    public ArrayList<Empleado> listarDb(){
        //variable
        ArrayList<Empleado> flag;
        PreparedStatement pstmt;
        ResultSet rs;
        String sql;
        Departamento depTemp;
        
        //procedimiento
        flag = new ArrayList<Empleado>();
        try{
            if(this.conectar()){
                sql = "select codigoEmpleado, emp.nombre, genero, intereses, edad, "
                        + "direccion, cargo, "
                        + "dep.codigoDepartamento, dep.nombre as departamento "
                        + "from empleado as emp "
                        + "inner join departamento as dep on emp.codigoDepartamento = dep.codigoDepartamento "
                        + "order by codigoEmpleado;";
                pstmt = this.getConn().prepareStatement(sql);
                
                rs = pstmt.executeQuery();
                
                while(rs.next()){
                    depTemp = new Departamento();
                    depTemp.setCodigoDepartamento(rs.getInt("codigoDepartamento"));
                    depTemp.setNombre(rs.getString("departamento"));
                    
                    flag.add(new Empleado(rs.getInt("codigoEmpleado"),
                                            rs.getString("nombre"),
                                            rs.getString("genero"),
                                            UtilidadesVarias.separarString(rs.getString("intereses"), ","),
                                            rs.getInt("edad"),
                                            rs.getString("direccion"),
                                            rs.getString("cargo"),
                                            depTemp));
                } 
                rs.close();
                pstmt.close();     
            }
        }catch(Exception e){
            flag = null;
            e.printStackTrace();
        }finally{
            desconectar();
        }
        
        return flag;
    }
    
    public Empleado buscarOjetoDb(Empleado objetivo){
        //variable
        Empleado flag;
        PreparedStatement pstmt;
        ResultSet rs;
        String sql;
        Departamento depTemp;
        
        //procedimiento
        flag = null;
        try{
            System.out.println("el objetivo a buscar es: " + objetivo.getCodigoEmpleado());
            if(this.conectar()){
                sql = "select codigoEmpleado, emp.nombre, genero, intereses, edad, "
                        + "direccion, cargo, "
                        + "dep.codigoDepartamento, dep.nombre as departamento "
                        + "from empleado as emp "
                        + "inner join departamento as dep on emp.codigoDepartamento = dep.codigoDepartamento "
                        + "where codigoEmpleado = ?  "
                        + "order by codigoEmpleado;";
                pstmt = this.getConn().prepareStatement(sql);
                
                pstmt.setInt(1, objetivo.getCodigoEmpleado());
                
                rs = pstmt.executeQuery();
                
                rs.next();
                depTemp = new Departamento();
                depTemp.setCodigoDepartamento(rs.getInt("codigoDepartamento"));
                depTemp.setNombre(rs.getString("departamento"));

                flag = new Empleado(rs.getInt("codigoEmpleado"),
                                        rs.getString("nombre"),
                                        rs.getString("genero"),
                                        UtilidadesVarias.separarString(rs.getString("intereses"), ","),
                                        rs.getInt("edad"),
                                        rs.getString("direccion"),
                                        rs.getString("cargo"),
                                        depTemp);
                
                rs.close();
                pstmt.close();     
            }
            
            System.out.println("nombre recogido: " + flag.getNombre());
        }catch(Exception e){
            flag = null;
            e.printStackTrace();
        }finally{
            desconectar();
        }
        
        return flag;
    }
}