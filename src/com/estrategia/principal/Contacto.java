/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.estrategia.principal;

import com.estrategia.contacto.CContacto;
import com.estrategia.contacto.MContacto;
import com.estrategia.contacto.VContacto;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 *
 * @author jorhak
 */
public class Contacto {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

        MContacto modelo = new MContacto();
        VContacto vista = new VContacto();
        CContacto controlador = new CContacto(modelo, vista);
        controlador.Iniciar();
    }
    
}
