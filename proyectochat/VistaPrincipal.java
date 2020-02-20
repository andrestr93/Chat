package proyectochat;

import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.text.Document;

/**
 *
 * @author Andres
 */
public class VistaPrincipal extends javax.swing.JFrame {

    private boolean tecla = true;
    public static MulticastSocket ms = null; // creamos la instacia de clase MulticastSocket 
    public static MulticastSocket av = null; // creamos la instacia de clase MulticastSocket 
    public static byte[] buf = new byte[150000]; // array de bytes texto 
    public static byte[] mensajesegun = new byte[1000];
    public static InetAddress grupo = null; // instancia de clase InetAdress
    public static int port = 12345; // el puerto 
    AudioClip sonido;
    Calendar calendario = Calendar.getInstance();
    public static boolean repetir = true; // variable booleana
    public static String nick = ""; // el nick
    public static String texto = "";
    VentanaIcono icono;
    DatagramPacket mensaje;
    public static String aviso;
    DatagramPacket alerta;

    public VistaPrincipal() throws IOException {
        initComponents();

        this.setMinimumSize(new Dimension(1290, 755));
        icono = new VentanaIcono(this, true);
        sonido = java.applet.Applet.newAudioClip(getClass().getResource("/sonido/sonido.wav"));
        textoarea.setEditable(false);
        nick = JOptionPane.showInputDialog("Escriba su nick: "); // PRIMERO ESCRIBE EL NICK
        setTitle(nick + " esta conectado");
        av = new MulticastSocket(port);
        ms = new MulticastSocket(port); // INICIA EL OBJETO MULTICAST CON EL PUERTO 12345
        grupo = InetAddress.getByName("225.0.0.1"); //  nos devuelve la direccion ip 
        ms.joinGroup(grupo); // OBJETO MULTICASTSOCKET que le pasamos la ip 
        Hilo hilo = new Hilo(); // creamos e inicializamos el hilo 
        hilo.start(); // ejecutamos el hio 
        butenviar.setEnabled(true);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        textoarea = new javax.swing.JTextArea();
        butenviar = new javax.swing.JButton();
        txtenvio = new javax.swing.JTextField();
        buticonos = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(204, 255, 204));
        getContentPane().setLayout(null);

        textoarea.setColumns(20);
        textoarea.setRows(5);
        jScrollPane1.setViewportView(textoarea);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(50, 130, 840, 370);

        butenviar.setBackground(new java.awt.Color(255, 255, 255));
        butenviar.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        butenviar.setText("ENVIAR");
        butenviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butenviarActionPerformed(evt);
            }
        });
        getContentPane().add(butenviar);
        butenviar.setBounds(730, 610, 150, 80);

        txtenvio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtenvioKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtenvioKeyReleased(evt);
            }
        });
        getContentPane().add(txtenvio);
        txtenvio.setBounds(50, 590, 670, 120);

        buticonos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/emoji.png"))); // NOI18N
        buticonos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buticonosActionPerformed(evt);
            }
        });
        getContentPane().add(buticonos);
        buticonos.setBounds(140, 530, 50, 49);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/ventana-chat-mesn-messenger-1280x720.jpg"))); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 1260, 710);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtenvioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtenvioKeyPressed

        escritura();

    }//GEN-LAST:event_txtenvioKeyPressed

    private void butenviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butenviarActionPerformed

        envio();

    }//GEN-LAST:event_butenviarActionPerformed

    private void txtenvioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtenvioKeyReleased

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            envio();
        }



    }//GEN-LAST:event_txtenvioKeyReleased

    private void buticonosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buticonosActionPerformed

        icono.setVisible(true);


    }//GEN-LAST:event_buticonosActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VistaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new VistaPrincipal().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(VistaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton butenviar;
    private javax.swing.JButton buticonos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTextArea textoarea;
    public static javax.swing.JTextField txtenvio;
    // End of variables declaration//GEN-END:variables

    public void envio() {

        int hora, minuto, segundo;
        hora = calendario.get(Calendar.HOUR_OF_DAY);
        minuto = calendario.get(Calendar.MINUTE);
        segundo = calendario.get(Calendar.SECOND);

        String tiempo = "Hora :" + hora + ":" + minuto + "\n";

        if (!txtenvio.getText().equals("")) {
            texto = nick + " dice " + txtenvio.getText() + "\n ---------------------"; // recogemos el mensaje 
            txtenvio.setText("");
            try {
                DatagramPacket tiempomen = new DatagramPacket(tiempo.getBytes(), tiempo.length(), grupo, port);
                DatagramPacket paquete = new DatagramPacket(texto.getBytes(), texto.length(), grupo, port); // iniciamos y creamos el objeto datagrampacket  pasandole el 
                // mensaje , longitud del mensaje  , la ip , y el  num del puerto 
                ms.send(paquete); // aqui le pasamos el paquete con el mensaje la longitud , la direccion ip , y el numero de puerto 
                ms.send(tiempomen);
                sonido.play();
            } catch (IOException e) {

            } catch (NullPointerException o) {

            } catch (Exception o) {

            }

        } else {

            JOptionPane.showMessageDialog(this, "no hay ningún mensaje escrito");

        }

        tecla = true;

    }

    public void escritura() {

        if (tecla == true) {

            String mensenviado = nick + " esta enviando un mensaje";

            alerta = new DatagramPacket(mensenviado.getBytes(), mensenviado.length(), grupo, port);

            try {
                av.send(alerta);

            } catch (IOException ex) {
                Logger.getLogger(VistaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }

            tecla = false;

        }

    }
}
