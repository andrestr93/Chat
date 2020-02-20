package proyectochat;

import java.awt.Color;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.SocketException;
import javax.swing.JOptionPane;
import static proyectochat.VistaPrincipal.nick;
import static proyectochat.VistaPrincipal.textoarea;

public class Hilo extends Thread {

    public void run() {
        while (VistaPrincipal.repetir) {
            

            try {
                DatagramPacket p = new DatagramPacket(VistaPrincipal.buf, VistaPrincipal.buf.length); // iniciamos un datagranpacket pasandole el numero de caracteres permitidos y su longitud
                //  DatagramPacket s = new DatagramPacket(VistaPrincipal.mensajesegun, VistaPrincipal.mensajesegun.length);

                VistaPrincipal.ms.receive(p); // obtenemos el mensaje
                String texto = new String(p.getData(), 0, p.getLength()); // lo guardamos en una variable string pasandole parametros 
                VistaPrincipal.textoarea.append(texto + "\n");
                if (texto.equals("adios")){
                    VistaPrincipal.repetir = false;
                    break;
                   
                }
            } catch (SocketException e) {

            } catch (IOException e) {

            }
        }

    }
}
