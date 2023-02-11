/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.estrategia.estrategia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author jorhak
 */
public class Conexion {

    private Connection con;
    private IEstrategia estrategia;

    public Conexion() {
        estrategia = EstrategiaMySQL.getInstance();
        con = estrategia.getConnection();
    }

    public void setEstrategia(IEstrategia estrategia) {
        this.estrategia = estrategia;
        this.con = this.estrategia.getConnection();
    }

    public IEstrategia getEstrategia() {
        return estrategia;
    }

    public Connection getConexion() {
        return con;
    }

    public boolean executeSQL(String sql) {
        try ( Statement consulta = con.createStatement();) {
            consulta.executeUpdate(sql);
        } catch (SQLException ex) {
            con = null;
            estrategia.connect();
            return false;
        }
        return true;
    }

    public List<Map<String, String>> executeSQLResultList(String sql) {
        List<Map<String, String>> data = new LinkedList<>();
        try ( PreparedStatement consulta = con.prepareStatement(sql);  ResultSet resultado = consulta.executeQuery();) {
            String columnNames[] = columnName(resultado);
            while (resultado.next()) {
                Map<String, String> map = new HashMap<>();
                for (int index = 0; index < columnNames.length; index++) {
                    map.put(columnNames[index], resultado.getObject(index + 1).toString());
                }
                data.add(map);
            }
        } catch (Exception e) {
            con = null;
            estrategia.connect();
        }
        return data;
    }

    public boolean executeSQL(PreparedStatement preparedStatement) {
        int state = 0;
        try {
            state = preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            con = null;
            estrategia.connect();
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
            }
        }
        return state != 0;
    }

    public boolean delete(String sql, Object... primaryKey) {
        boolean proccessed = false;
        try ( PreparedStatement statement = estrategia.getConnection().prepareStatement(sql);) {
            for (int index = 0; index < primaryKey.length; index++) {
                statement.setString(index + 1, primaryKey[index].toString());
            }
            proccessed = executeSQL(statement);
        } catch (SQLException e) {
            con = null;
        }
        return proccessed;
    }

    public List<Map<String, Object>> executeSQLResultList(PreparedStatement preparedStatement) {
        List<Map<String, Object>> data = new LinkedList<>();
        try ( ResultSet resultado = preparedStatement.executeQuery();) {
            String columnNames[] = columnName(resultado);
            while (resultado.next()) {
                Map<String, Object> map = new HashMap<>();
                for (int index = 0; index < columnNames.length; index++) {
                    map.put(columnNames[index], resultado.getObject(index + 1));
                }
                data.add(map);
            }
        } catch (Exception e) {
            con = null;
            estrategia.connect();
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
            }
        }
        return data;
    }

    private String[] columnName(ResultSet resultSet) {
        String columns[] = null;
        try {
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            columns = new String[columnCount];
            for (int index = 0; index < columnCount; index++) {
                columns[index] = metaData.getColumnName(index + 1);
            }
        } catch (SQLException e) {
            con = null;
            estrategia.connect();
        }
        return columns;
    }

}
