/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.parcialPatrones;

/**
 *
 * @author jorhak
 */
public class SerieB extends Serie{

    public SerieB(int n) {
        super(n);
    }



    @Override
    public int ejecutar(int valor) {
        double elemento = Math.pow(valor,2);
        String str = String.valueOf(elemento);
        int intNumero = Integer.parseInt(str.substring(0, str.indexOf(".")));
        return intNumero;
    }
    
}
