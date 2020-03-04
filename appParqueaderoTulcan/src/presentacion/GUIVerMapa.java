package presentacion;

import Utilidades.Utilidades;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import negocio.Bahia;
import negocio.GestorParqueadero;
import negocio.Ingreso;

public class GUIVerMapa extends javax.swing.JInternalFrame implements ActionListener {
    private final GestorParqueadero gestorIngreso = new GestorParqueadero();
    private ArrayList<javax.swing.JButton> misBotones;
    private String placa;
    private int modVision;

    public GUIVerMapa(int modoVision, String prmPlaca) {
        misBotones = new ArrayList<javax.swing.JButton>();
        placa = prmPlaca;
        modVision = modoVision;
        initComponents();
        addActionListener();
        llenarArrayBotones();
        cargarMapa();
        if (modVision == 0) {
            for (int i = 0; i < misBotones.size(); i++) {
                misBotones.get(i).setEnabled(false);
            }
        }
    }

    private void regIngreso(int bahId) {
        String confirmacion;
        Calendar fecha = new GregorianCalendar();
        int año = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH);
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        String fechaEntrada = Integer.toString(año) + "/" + Integer.toString(mes) + "/" + Integer.toString(dia);
        Ingreso ing = new Ingreso(placa, Integer.toString(bahId), fechaEntrada, null);
        confirmacion = gestorIngreso.registrarIngreso(placa, Integer.toString(bahId), fechaEntrada, fechaEntrada);
        Utilidades.mensajeExito(confirmacion, "Registro Exitoso.");
        this.dispose();

    }

    private void regSalida(int BahId) {
        String confirmacion;
        Calendar fecha = new GregorianCalendar();
        int año = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH);
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        String fechaSalida = Integer.toString(año) + "/" + Integer.toString(mes) + "/" + Integer.toString(dia);

        confirmacion = gestorIngreso.registrarSalida(BahId, fechaSalida);
        Utilidades.mensajeExito(confirmacion, "Salida Confirmada.");
    }

    public void actionPerformed(ActionEvent evento) {
        String placa;
        int bahId = 0;
        int opc = 0;
        if (getModoVision() == 1) {
            opc = JOptionPane.showConfirmDialog(null, "Desea registrar esta Bahia", "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (opc == 0) {
                for (int i = 0; i < misBotones.size(); i++) {
                    if (evento.getSource() == misBotones.get(i)) {
                        if (misBotones.get(i).getBackground() == Color.YELLOW) {
                            Utilidades.mensajeAdvertencia("Bahia Ocupada", "Advertencia");
                        } else {
                            bahId = i+1;
                            misBotones.get(i).setBackground(Color.YELLOW);
                        }
                        break;
                } 
                    
                }
                
            }
        } else if (getModoVision() == 2) {
            opc = JOptionPane.showConfirmDialog(null, "Desea liberar la Bahia", "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (opc == 0) {
                for (int i = 0; i < misBotones.size(); i++) {
                    if (evento.getSource() == misBotones.get(i)) {
                        if (misBotones.get(i).getBackground() == Color.GREEN) {
                            Utilidades.mensajeAdvertencia("Bahia libre", "Advertencia");
                        } else {
                            bahId = i+1;
                            System.out.println("presentacion.GUIVerMapa.actionPerformed()");
                            misBotones.get(i).setBackground(Color.GREEN);
                        }
                        break;
                } 
                    
                }
               
            }
        }
         if (getModoVision() == 1) {
            regIngreso(bahId);
            
        } else if (getModoVision() == 2) {
            regSalida(bahId);
        }
       
    }

    private void addActionListener() {
        //Se añaden todos los botones al Listener para usarlos en el mismo metodo
        btnMotos1.addActionListener(this);
        btnMotos2.addActionListener(this);
        btnMotos3.addActionListener(this);
        btnMotos4.addActionListener(this);
        btnMotos5.addActionListener(this);
        btnMotos6.addActionListener(this);
        btnMotos7.addActionListener(this);
        btnMotos8.addActionListener(this);
        btnMotos9.addActionListener(this);
        btnMotos10.addActionListener(this);
        btnMotos11.addActionListener(this);
        btnMotos12.addActionListener(this);

        btnIpet13.addActionListener(this);
        btnIpet14.addActionListener(this);
        btnIpet15.addActionListener(this);
        btnIpet16.addActionListener(this);
        btnIpet17.addActionListener(this);
        btnIpet18.addActionListener(this);
        btnIpet19.addActionListener(this);
        btnIpet20.addActionListener(this);
        btnIpet21.addActionListener(this);
        btnIpet22.addActionListener(this);
        btnIpet23.addActionListener(this);
        btnIpet24.addActionListener(this);

        btnIng25.addActionListener(this);
        btnIng26.addActionListener(this);
        btnIng27.addActionListener(this);
        btnIng28.addActionListener(this);
        btnIng29.addActionListener(this);
        btnIng30.addActionListener(this);
        btnIng31.addActionListener(this);
        btnIng32.addActionListener(this);
        btnIng33.addActionListener(this);
        btnIng34.addActionListener(this);
        btnIng35.addActionListener(this);
        btnIng36.addActionListener(this);
    }

    private void llenarArrayBotones() {

        //Parqueadero Motos
        misBotones.add(btnMotos1);
        misBotones.add(btnMotos2);
        misBotones.add(btnMotos3);
        misBotones.add(btnMotos4);
        misBotones.add(btnMotos5);
        misBotones.add(btnMotos6);
        misBotones.add(btnMotos7);
        misBotones.add(btnMotos8);
        misBotones.add(btnMotos9);
        misBotones.add(btnMotos10);
        misBotones.add(btnMotos11);
        misBotones.add(btnMotos12);

        //Parqueadero Ipet
        misBotones.add(btnIpet13);
        misBotones.add(btnIpet14);
        misBotones.add(btnIpet15);
        misBotones.add(btnIpet16);
        misBotones.add(btnIpet17);
        misBotones.add(btnIpet18);
        misBotones.add(btnIpet19);
        misBotones.add(btnIpet20);
        misBotones.add(btnIpet21);
        misBotones.add(btnIpet22);
        misBotones.add(btnIpet23);
        misBotones.add(btnIpet24);

        //Parqueadero Motos
        misBotones.add(btnIng25);
        misBotones.add(btnIng26);
        misBotones.add(btnIng27);
        misBotones.add(btnIng28);
        misBotones.add(btnIng29);
        misBotones.add(btnIng30);
        misBotones.add(btnIng31);
        misBotones.add(btnIng32);
        misBotones.add(btnIng33);
        misBotones.add(btnIng34);
        misBotones.add(btnIng35);
        misBotones.add(btnIng36);
    }

    private void cargarMapa() {
        ArrayList<Bahia> bahias = null;
        bahias = gestorIngreso.Buscar_Bahias();

        for (int i = 0; i < bahias.size(); i++) {
            if (bahias.get(i).getBahEstado().equals("1")) {
                misBotones.get(i).setBackground(Color.YELLOW);
            } else {
                if (bahias.get(i).getBahEstado().equals("0")) {
                    misBotones.get(i).setBackground(Color.GREEN);
                }
            }
        }
    }

    private int getModoVision() {
        return modVision;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMapa = new javax.swing.JPanel();
        btnMotos1 = new javax.swing.JButton();
        btnMotos2 = new javax.swing.JButton();
        btnMotos3 = new javax.swing.JButton();
        btnMotos4 = new javax.swing.JButton();
        btnMotos5 = new javax.swing.JButton();
        btnMotos6 = new javax.swing.JButton();
        btnMotos7 = new javax.swing.JButton();
        btnMotos8 = new javax.swing.JButton();
        btnMotos9 = new javax.swing.JButton();
        btnMotos10 = new javax.swing.JButton();
        btnMotos11 = new javax.swing.JButton();
        btnMotos12 = new javax.swing.JButton();
        btnIpet13 = new javax.swing.JButton();
        btnIpet14 = new javax.swing.JButton();
        btnIpet15 = new javax.swing.JButton();
        btnIpet16 = new javax.swing.JButton();
        btnIpet17 = new javax.swing.JButton();
        btnIpet18 = new javax.swing.JButton();
        btnIpet19 = new javax.swing.JButton();
        btnIpet20 = new javax.swing.JButton();
        btnIpet21 = new javax.swing.JButton();
        btnIpet22 = new javax.swing.JButton();
        btnIpet23 = new javax.swing.JButton();
        btnIpet24 = new javax.swing.JButton();
        btnIng25 = new javax.swing.JButton();
        btnIng26 = new javax.swing.JButton();
        btnIng27 = new javax.swing.JButton();
        btnIng28 = new javax.swing.JButton();
        btnIng29 = new javax.swing.JButton();
        btnIng30 = new javax.swing.JButton();
        btnIng31 = new javax.swing.JButton();
        btnIng32 = new javax.swing.JButton();
        btnIng33 = new javax.swing.JButton();
        btnIng34 = new javax.swing.JButton();
        btnIng35 = new javax.swing.JButton();
        btnIng36 = new javax.swing.JButton();
        pngMapa = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        pnlMapa.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnMotos1.setText("1");
        pnlMapa.add(btnMotos1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 370, 50, -1));

        btnMotos2.setText("2");
        pnlMapa.add(btnMotos2, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 370, 50, -1));

        btnMotos3.setText("3");
        pnlMapa.add(btnMotos3, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 370, 50, -1));

        btnMotos4.setText("4");
        pnlMapa.add(btnMotos4, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 400, 50, -1));

        btnMotos5.setText("5");
        pnlMapa.add(btnMotos5, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 400, 50, -1));

        btnMotos6.setText("6");
        pnlMapa.add(btnMotos6, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 400, 50, -1));

        btnMotos7.setText("7");
        pnlMapa.add(btnMotos7, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 430, 50, -1));

        btnMotos8.setText("8");
        pnlMapa.add(btnMotos8, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 430, 50, -1));

        btnMotos9.setText("9");
        pnlMapa.add(btnMotos9, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 430, 50, -1));

        btnMotos10.setText("10");
        pnlMapa.add(btnMotos10, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 460, 50, -1));

        btnMotos11.setText("11");
        pnlMapa.add(btnMotos11, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 460, 50, -1));

        btnMotos12.setText("12");
        pnlMapa.add(btnMotos12, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 460, 50, -1));

        btnIpet13.setText("1");
        pnlMapa.add(btnIpet13, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 180, 50, -1));

        btnIpet14.setText("2");
        pnlMapa.add(btnIpet14, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 180, 50, -1));

        btnIpet15.setText("3");
        pnlMapa.add(btnIpet15, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 180, 50, -1));

        btnIpet16.setText("4");
        pnlMapa.add(btnIpet16, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 210, 50, -1));

        btnIpet17.setText("5");
        pnlMapa.add(btnIpet17, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 210, 50, -1));

        btnIpet18.setText("6");
        pnlMapa.add(btnIpet18, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 210, 50, -1));

        btnIpet19.setText("7");
        pnlMapa.add(btnIpet19, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 240, 50, -1));

        btnIpet20.setText("8");
        pnlMapa.add(btnIpet20, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 240, 50, -1));

        btnIpet21.setText("9");
        pnlMapa.add(btnIpet21, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 240, 50, -1));

        btnIpet22.setText("10");
        pnlMapa.add(btnIpet22, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 270, 50, -1));

        btnIpet23.setText("11");
        pnlMapa.add(btnIpet23, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 270, 50, -1));

        btnIpet24.setText("12");
        pnlMapa.add(btnIpet24, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 270, 50, -1));

        btnIng25.setText("1");
        pnlMapa.add(btnIng25, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 180, 50, -1));

        btnIng26.setText("2");
        pnlMapa.add(btnIng26, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 180, 50, -1));

        btnIng27.setText("3");
        pnlMapa.add(btnIng27, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 220, 50, -1));

        btnIng28.setText("4");
        pnlMapa.add(btnIng28, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 220, 50, -1));

        btnIng29.setText("5");
        pnlMapa.add(btnIng29, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 250, 50, -1));

        btnIng30.setText("6");
        pnlMapa.add(btnIng30, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 250, 50, -1));

        btnIng31.setText("7");
        pnlMapa.add(btnIng31, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 290, 50, -1));

        btnIng32.setText("8");
        pnlMapa.add(btnIng32, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 290, 50, -1));

        btnIng33.setText("9");
        pnlMapa.add(btnIng33, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 340, 50, -1));

        btnIng34.setText("10");
        pnlMapa.add(btnIng34, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 340, 50, -1));

        btnIng35.setText("11");
        pnlMapa.add(btnIng35, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 410, 50, -1));

        btnIng36.setText("12");
        pnlMapa.add(btnIng36, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 410, 50, -1));

        pngMapa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Mapa.png"))); // NOI18N
        pnlMapa.add(pngMapa, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        getContentPane().add(pnlMapa);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIng25;
    private javax.swing.JButton btnIng26;
    private javax.swing.JButton btnIng27;
    private javax.swing.JButton btnIng28;
    private javax.swing.JButton btnIng29;
    private javax.swing.JButton btnIng30;
    private javax.swing.JButton btnIng31;
    private javax.swing.JButton btnIng32;
    private javax.swing.JButton btnIng33;
    private javax.swing.JButton btnIng34;
    private javax.swing.JButton btnIng35;
    private javax.swing.JButton btnIng36;
    private javax.swing.JButton btnIpet13;
    private javax.swing.JButton btnIpet14;
    private javax.swing.JButton btnIpet15;
    private javax.swing.JButton btnIpet16;
    private javax.swing.JButton btnIpet17;
    private javax.swing.JButton btnIpet18;
    private javax.swing.JButton btnIpet19;
    private javax.swing.JButton btnIpet20;
    private javax.swing.JButton btnIpet21;
    private javax.swing.JButton btnIpet22;
    private javax.swing.JButton btnIpet23;
    private javax.swing.JButton btnIpet24;
    private javax.swing.JButton btnMotos1;
    private javax.swing.JButton btnMotos10;
    private javax.swing.JButton btnMotos11;
    private javax.swing.JButton btnMotos12;
    private javax.swing.JButton btnMotos2;
    private javax.swing.JButton btnMotos3;
    private javax.swing.JButton btnMotos4;
    private javax.swing.JButton btnMotos5;
    private javax.swing.JButton btnMotos6;
    private javax.swing.JButton btnMotos7;
    private javax.swing.JButton btnMotos8;
    private javax.swing.JButton btnMotos9;
    private javax.swing.JLabel pngMapa;
    private javax.swing.JPanel pnlMapa;
    // End of variables declaration//GEN-END:variables

}
