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
public class EstrategiaSQLServer implements IEstrategia {

    private static final String host = "192.168.0.24";
    private static final String database = "Contactos";
    private static final String user = "sa";
    private static final String password = "";
    private static final int port = 1433;

    private Connection con;

    private static EstrategiaSQLServer instance;

    private EstrategiaSQLServer() {
        connect();
    }

    public static EstrategiaSQLServer getInstance() {
        if (instance == null) {
            instance = new EstrategiaSQLServer();
        }
        return instance;
    }

    @Override
    public void connect() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://" + host + ":" + port + "\\" + database;
            con = DriverManager.getConnection(url, user, password);
            con.setAutoCommit(true);
            System.out.println("conexion realizada con exito a SQLServer");
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
