/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.estrategia.contacto;

import com.estrategia.estrategia.Conexion;
import com.estrategia.estrategia.EstrategiaMySQL;
import com.estrategia.estrategia.EstrategiaPostgres;
import com.estrategia.estrategia.IEstrategia;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author jorhak
 */
public class MContacto {

    private int id;
    private String nombre;
    private String telefono;
    private String direccion;
    private Conexion c;

    public MContacto(int id, String nombre, String telefono, String direccion) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    public MContacto() {
        this(0, "", "", "");
        c = new Conexion();
    }
    
    public void CambiarEstrategia(boolean estrategia){
        c.setEstrategia(estrategia ? EstrategiaMySQL.getInstance() : EstrategiaPostgres.getInstance());
        c.procesar();
    }

    public void SetID(String id) {
        this.id = Integer.parseInt(id);
    }

    public void SetDato(Map<String, String> dato) {
        this.id = Integer.parseInt(dato.getOrDefault("id", "0"));
        this.nombre = dato.getOrDefault("nombre", "");
        this.telefono = dato.getOrDefault("telefono", "");
        this.direccion = dato.getOrDefault("direccion", "");
    }

    public boolean Registrar() {
        boolean proccessed = false;
        String sql = "insert into contacto (nombre, telefono, direccion) "
                + "values (?,?,?);";

        try {
            PreparedStatement statement = c.getConexion().prepareStatement(sql);
            statement.setString(1, this.nombre);
            statement.setString(2, this.telefono);
            statement.setString(3, this.direccion);

            proccessed = c.executeSQL(statement);
        } catch (SQLException e) {
        }

        return proccessed;
    }

    public boolean Modificar() {
        boolean proccessed = false;
        String sql = "update contacto "
                + "set nombre=?, telefono=?, direccion=? "
                + "where id=?;";

        try {
            PreparedStatement statement = c.getConexion().prepareStatement(sql);
            statement.setString(1, this.nombre);
            statement.setString(2, this.telefono);
            statement.setString(3, this.direccion);
            statement.setInt(4, this.id);

            proccessed = c.executeSQL(statement);
        } catch (SQLException e) {
        }

        return proccessed;
    }

    public boolean Eliminar() {
        String sql = "delete from contacto where id=?;";
        return c.delete(sql, this.id);
    }

    public Map<String, String> BuscarID() {
        return BuscarColumnaValor("id", this.id);
    }

    public Map<String, String> BuscarColumnaValor(String columnName, Object columnValue) {
        String sql = "select * from contacto where %s='%s' limit 1;";
        sql = String.format(sql, columnName, columnValue);

        List<Map<String, String>> resultado = c.executeSQLResultList(sql);
        return !resultado.isEmpty() ? resultado.get(0) : null;
    }

    public List<Map<String, String>> Listar() {
        String sql = "select * from contacto order by 1;";
        return c.executeSQLResultList(sql);
    }

}
