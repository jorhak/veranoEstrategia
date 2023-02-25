/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.parcialPatrones;

/**
 *
 * @author jorhak
 */
public abstract class Serie {
    private int n;

    public Serie(int n) {
        this.n = n;
    }
    
    
    
    public String metodoPlantilla(){
        int contador =1;
        int elemento = 0;
        String serie = "";
        
        while(contador <= n){
            elemento = ejecutar(contador);
            contador++;
            serie+= elemento +"+";
        }
        return serie;
    }
    
    public abstract int ejecutar(int valor);
}
