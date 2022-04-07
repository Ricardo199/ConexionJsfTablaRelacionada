package com.utilidades;

import java.util.ArrayList;

/**
 * Nombre de la clase:UtilidadesVarias
 * Fecha:12/07/2018
 * Versión:1.0
 * CopyRight:ITCA-FEPADE
 * @author Giovanni Tzec
 */
public final class UtilidadesVarias {
    public static final ArrayList<String> separarString(String completo, String separador){
        ArrayList<String> flag;
        String [] completoVector; 
        System.out.println("completo:" + completo);
        //invocar procedimiento
        completoVector = completo.split(separador);
        
        flag = new ArrayList<>();
        for(String str:completoVector){
            flag.add(str);
        }
        
        return flag;
    }
    
    public static final String unirString(ArrayList<String> partes){
        String flag;
        String str;       
        flag="";
        for(int i=0; i< partes.size(); i++){
            str = partes.get(i);
            
            if(i == partes.size() - 1){
                flag += str;
            }else{
                flag += str + ",";
            }
        }
        
        return flag;
    }
}
