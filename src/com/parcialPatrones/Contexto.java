/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.parcialPatrones;

/**
 *
 * @author jorhak
 */
public class Contexto {
    private Serie serie;

    public Contexto() {
    }
    
    public void setSerie(Serie s){
        serie = s;
    }
    
    public String procesar(){
        return serie.metodoPlantilla();
    }
}
