/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import java.io.File;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JFileChooser;
import negocio.GestorVehiculoPersona;
import negocio.Multa;

/**
 *
 * @author Personal
 */
public class GUIRegistrarMulta extends javax.swing.JInternalFrame {

    String ruta = null;
    int cont;
    String placa, fecha, descripcion;
    private Multa objMulta;
    private GestorVehiculoPersona obJGestor = new GestorVehiculoPersona();

    public GUIRegistrarMulta() {
        initComponents();
        mostrarLabel();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlPrincipal = new javax.swing.JPanel();
        lbPlaca = new javax.swing.JLabel();
        txtPlaca = new javax.swing.JTextField();
        lbvplaca = new javax.swing.JLabel();
        lbDescripcion = new javax.swing.JLabel();
        txtDescripcion = new javax.swing.JTextField();
        lbvdesc = new javax.swing.JLabel();
        lbFoto = new javax.swing.JLabel();
        btnAbrir = new javax.swing.JButton();
        lbvfoto = new javax.swing.JLabel();
        lbText = new javax.swing.JLabel();
        btnRegMulta = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Registrar Multa Conductor");

        pnlPrincipal.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.light"));
        pnlPrincipal.setLayout(new java.awt.GridLayout(4, 3));

        lbPlaca.setText("Placa:");
        pnlPrincipal.add(lbPlaca);
        pnlPrincipal.add(txtPlaca);

        lbvplaca.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 18)); // NOI18N
        lbvplaca.setForeground(new java.awt.Color(255, 0, 0));
        lbvplaca.setText("Requerido");
        pnlPrincipal.add(lbvplaca);

        lbDescripcion.setText("Descripción");
        pnlPrincipal.add(lbDescripcion);
        pnlPrincipal.add(txtDescripcion);

        lbvdesc.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 18)); // NOI18N
        lbvdesc.setForeground(new java.awt.Color(255, 0, 0));
        lbvdesc.setText("Requerido");
        pnlPrincipal.add(lbvdesc);

        lbFoto.setText("Abrir Foto:");
        pnlPrincipal.add(lbFoto);

        btnAbrir.setText("Abrir");
        btnAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbrirActionPerformed(evt);
            }
        });
        pnlPrincipal.add(btnAbrir);

        lbvfoto.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 18)); // NOI18N
        lbvfoto.setForeground(new java.awt.Color(255, 0, 0));
        lbvfoto.setText("Requerido");
        pnlPrincipal.add(lbvfoto);
        pnlPrincipal.add(lbText);

        btnRegMulta.setText("Registrar Multa");
        btnRegMulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegMultaActionPerformed(evt);
            }
        });
        pnlPrincipal.add(btnRegMulta);

        getContentPane().add(pnlPrincipal, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegMultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegMultaActionPerformed
        // TODO add your handling code here:
        String respuesta;
        validarCampos();
        System.out.println(ruta);
        if (getCont() == 0) {
            try {
                objMulta = new Multa(getPlaca(), getDescripcion(), getFecha(), ruta);
                respuesta = obJGestor.regMultaVehiculo(objMulta);
                if (respuesta.equals("No se encontro vehiculo.")) {
                    Utilidades.Utilidades.mensajeError(respuesta, "Error");
                } else if (respuesta.equals("Multa agragada con exito.")) {
                    Utilidades.Utilidades.mensajeExito(respuesta, "Exito");
                } else {
                    Utilidades.Utilidades.mensajeError(respuesta, "Error");
                }
            } catch (Exception ex) {
                Utilidades.Utilidades.mensajeError(ex.getMessage(), "Error");
            }
        }
    }//GEN-LAST:event_btnRegMultaActionPerformed

    private void btnAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbrirActionPerformed
        JFileChooser jf = new JFileChooser();
        jf.showOpenDialog(this);
        File archivo = jf.getSelectedFile();
        lbText.setText(archivo.getAbsolutePath());
        if (!lbText.equals("")){
            ruta = archivo.getAbsolutePath();
        }        
    }//GEN-LAST:event_btnAbrirActionPerformed
    public void validarCampos() {
        if(getPlaca().equals("")){
            lbvplaca.setVisible(true);
            cont++;
        }else{
            lbvplaca.setVisible(false);
        }
        if(getDescripcion().equals("")){
            lbvdesc.setVisible(true);
            cont++;
        }else{
            lbvdesc.setVisible(false);
        }
        if(lbText.equals("")){
            lbvfoto.setVisible(true);
            cont++;
        }else{
            lbvfoto.setVisible(false);
        }
    }

    public void mostrarLabel() {
        lbvdesc.setVisible(false);
        lbvfoto.setVisible(false);
        lbvplaca.setVisible(false);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAbrir;
    private javax.swing.JButton btnRegMulta;
    private javax.swing.JLabel lbDescripcion;
    private javax.swing.JLabel lbFoto;
    private javax.swing.JLabel lbPlaca;
    private javax.swing.JLabel lbText;
    private javax.swing.JLabel lbvdesc;
    private javax.swing.JLabel lbvfoto;
    private javax.swing.JLabel lbvplaca;
    private javax.swing.JPanel pnlPrincipal;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JTextField txtPlaca;
    // End of variables declaration//GEN-END:variables

    public String getPlaca() {
        placa = txtPlaca.getText();
        return placa;
    }

    public String getFecha() {
        java.util.Date now = new java.util.Date(System.currentTimeMillis());
        SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat hour = new SimpleDateFormat("HH:mm:ss");
        String fecha = date.format(now) + " " + hour.format(now);
        return fecha;
    }

    public String getDescripcion() {
        descripcion = txtDescripcion.getText();
        return descripcion;
    }

    public int getCont() {
        return cont;
    }
}
