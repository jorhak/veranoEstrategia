/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.estrategia.contacto;

import com.estrategia.estrategia.EstrategiaMySQL;
import com.estrategia.estrategia.EstrategiaPostgres;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author jorhak
 */
public class CContacto implements ActionListener{
    private MContacto modelo;
    private VContacto vista;

    public CContacto(MContacto modelo, VContacto vista) {
        this.modelo = modelo;
        this.vista = vista;

        vista.btnNuevo.addActionListener(this);
        vista.btnRegistrar.addActionListener(this);
        vista.btnModificar.addActionListener(this);
        vista.btnEliminar.addActionListener(this);
        vista.btnBuscar.addActionListener(this);
        vista.btnRefrescar.addActionListener(this);
        vista.btnObtener.addActionListener(this);
        vista.btnCancelar.addActionListener(this);
        vista.radioMySQL.addActionListener(this);
        vista.radioPostgres.addActionListener(this);
    }
    
    
    public void CambiarEstrategia(){
        
        if(vista.radioPostgres.isSelected()){
            vista.radioMySQL.setSelected(false);
            modelo.CambiarEstrategia(false);
        }
        
        if(vista.radioMySQL.isSelected()){
            vista.radioPostgres.setSelected(false);
            modelo.CambiarEstrategia(true);
        }
        
        Listar();
        
    }

    public void Iniciar() {
        vista.setTitle("Registrar Categoria");
        vista.pack();
        vista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
        vista.textID.setVisible(false);
        Listar();
    }


    public void Nuevo() {
        vista.textNombre.setEnabled(true);
        vista.textTelefono.setEnabled(true);
        vista.textDireccion.setEnabled(true);
        vista.btnRegistrar.setEnabled(true);
        vista.textNombre.requestFocusInWindow();
    }

    public void Registrar() {
        Map<String, String> dato = new LinkedHashMap();
        dato.put("nombre", vista.textNombre.getText());
        dato.put("telefono", vista.textTelefono.getText());
        dato.put("direccion", vista.textDireccion.getText());

        modelo.SetDato(dato);
        modelo.Registrar();
        Listar();
    }

    public void Modificar() {
        Map<String, String> dato = new LinkedHashMap();
        dato.put("id", vista.textID.getText());
        dato.put("nombre", vista.textNombre.getText());
        dato.put("telefono", vista.textTelefono.getText());
        dato.put("direccion", vista.textDireccion.getText());
        modelo.SetDato(dato);
        modelo.Modificar();
        Listar();
    }

    public void Eliminar() {
        int fila = vista.tablaContacto.getSelectedRow();
        if (fila >= 0) {
            vista.textID.setText(vista.tablaContacto.getValueAt(fila, 0).toString());
            modelo.SetID(vista.textID.getText());
            modelo.Eliminar();
            Listar();
        } else {
            JOptionPane.showMessageDialog(null, "No se ha seleccionado la fila y no se ha eliminado la categoria.");
        }
    }

    public void Listar() {
        vista.Actualizar(modelo.Listar());
    }



    public void Cancelar() {
        vista.textID.setText("");
        vista.textNombre.setText("");
        vista.textTelefono.setText("");
        vista.textDireccion.setText("");
        vista.textBuscar.setText("");
        vista.textNombre.setEnabled(false);
        vista.textTelefono.setEnabled(false);
        vista.textDireccion.setEnabled(false);
        vista.btnRegistrar.setEnabled(false);
        vista.btnModificar.setEnabled(false);
        vista.textNombre.requestFocusInWindow();
    }

    public void Obtener() {
        Nuevo();
        vista.btnRegistrar.setEnabled(false);
        vista.btnModificar.setEnabled(true);
        int fila = vista.tablaContacto.getSelectedRow();
        if (fila >= 0) {
            vista.textID.setText(vista.tablaContacto.getValueAt(fila, 0).toString());
            vista.textNombre.setText(vista.tablaContacto.getValueAt(fila, 1).toString());
            vista.textTelefono.setText(vista.tablaContacto.getValueAt(fila, 2).toString());
            vista.textDireccion.setText(vista.tablaContacto.getValueAt(fila, 3).toString());

        } else {
            JOptionPane.showMessageDialog(null, "No se ha seleccionado la fila para obtener la categoria.");
        }
    }

    public void Buscar() {
        List<Map<String, String>> dato = new LinkedList<>();
        modelo.SetID(vista.textBuscar.getText());
        dato.add(modelo.BuscarID());
        vista.Actualizar(dato);
    }

    public void Refrescar() {
        Listar();
    }

    public void actionPerformed(ActionEvent e) {
        if(vista.btnNuevo == e.getSource()){
            try {
                Nuevo();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null,"No se pudieron activar los text y botton>>>>"+ex.getMessage()  );
            }
        }
        if(vista.btnCancelar == e.getSource()){
            try {
                Cancelar();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null,"No se pudieron vaciar los TextField>>>>"+ex.getMessage()  );
            }
        }
        if(vista.btnRegistrar == e.getSource()){
            try {
                Registrar();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null,"No se pudo registrar el contacto>>>>"+ex.getMessage()  );
            }
        }
        if(vista.btnModificar == e.getSource()){
            try {
                Modificar();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null,"No se pudo modificar el contacto>>>>"+ex.getMessage()  );
            }
        }
        if(vista.btnEliminar == e.getSource()){
            try {
                Eliminar();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null,"No se pudo eliminar el contacto>>>>"+ex.getMessage()  );
            }
        }
        if(vista.btnObtener == e.getSource()){
            try {
                Obtener();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null,"No se pudo obtener la fila>>>>"+ex.getMessage()  );
            }
        }
        if(vista.btnBuscar == e.getSource()){
            try {
                Buscar();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null,"No se pudo encontrar el contacto>>>>"+ex.getMessage()  );
            }
        }
        if(vista.btnRefrescar == e.getSource()){
            try {
                Refrescar();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null,"No se pudo refrescar la tabla>>>>"+ex.getMessage()  );
            }
        }
        if(vista.radioPostgres == e.getSource()){
            try {
                CambiarEstrategia();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null,"No se pudo refrescar la tabla>>>>"+ex.getMessage()  );
            }
        }
        if(vista.radioMySQL == e.getSource()){
            try {
                CambiarEstrategia();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null,"No se pudo refrescar la tabla>>>>"+ex.getMessage()  );
            }
        }
    }
}
