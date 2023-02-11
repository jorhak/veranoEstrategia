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
public class EstrategiaPostgres implements IEstrategia{
    private static final String host = "192.168.0.24";
    private static final String database = "contactos";
    private static final String user = "postgres";
    private static final String password = "perpetuaeternidad";
    private static final int port = 5454;

    private Connection con;

    private static EstrategiaPostgres instance;

    private EstrategiaPostgres() {
        connect();
    }

    public static EstrategiaPostgres getInstance() {
        if (instance == null) {
            instance = new EstrategiaPostgres();
        }
        return instance;
    }

    @Override
    public void connect() {
        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://" + host + ":" + port + "/" + database;
            con = DriverManager.getConnection(url, user, password);
            con.setAutoCommit(true);
            System.out.println("conexion realizada con exito a Postgres");
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
