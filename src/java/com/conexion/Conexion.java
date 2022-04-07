
package com.conexion;

import java.sql.*;
/**
 *
 * Nombre de la clase:Conexion
 * Fecha:12/07/2018
 * Versión:1.0
 * CopyRight:ITCA-FEPADE
 * @author Giovanni Tzec
 */
public class Conexion {
    private Connection conn;

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }
    
    public boolean conectar(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            this.conn = 
            DriverManager.getConnection("jdbc:mysql://localhost:3306/empresajsf",
            "root", "");
            return true;
        }catch(SQLException | ClassNotFoundException e){
            e.printStackTrace();
            return false;
        }     
    }
    
    public boolean desconectar(){
        try{
            if(this.conn != null){
                if(!this.conn.isClosed()){
                    this.conn.close();
                }
            }
            return true;
        }catch(SQLException e){
            e.printStackTrace();
            return false;     
        }
    }
}
