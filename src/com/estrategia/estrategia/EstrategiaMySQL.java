/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.estrategia.estrategia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author jorhak
 */
public class EstrategiaMySQL implements IEstrategia{

    private static final String host = "192.168.0.21";
    private static final String database = "Contactos";
    private static final String user = "root";
    private static final String password = "perpetuaeternidad";
    private static final int port = 3322;

    private Connection con;

    private static EstrategiaMySQL instance;

    private EstrategiaMySQL() {
        connect();
    }

    public static EstrategiaMySQL getInstance() {
        if (instance == null) {
            instance = new EstrategiaMySQL();
        }
        return instance;
    }

    @Override
    public void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://" + host + ":" + port + "/" + database;
            con = DriverManager.getConnection(url, user, password);
            con.setAutoCommit(true);
            System.out.println("conexion realizada con exito a MySQL");
        } catch (Exception e) {
            con = null;
            System.out.println("conexion fallida");
        }
    }

    @Override
    public Connection getConnection() {
        if (con == null) {
            connect();
        }
        return con;
    }

    @Override
    public void closeConnection() {
        try {
            con.close();
        } catch (SQLException ex) {
        }
        con = null;
    }

    
}
