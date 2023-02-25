/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.parcialPatrones;

/**
 *
 * @author jorhak
 */
public class SerieA extends Serie{

    public SerieA(int n) {
        super(n);
    }


    @Override
    public int ejecutar(int valor) {
        int contador =1;
        int inicio = 1;
        
        while (contador<valor){
            inicio+=2;
            contador++;
        }
        return inicio;
    }
    
}
