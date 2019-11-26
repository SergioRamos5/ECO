package eco;

import java.io.*;
import java.net.*;

public class EcoServidor {

    public static final int PORT = 4444;

    public static void main(String[] args) throws IOException {
        // Establece el puerto en el que escucha peticiones
        ServerSocket socketServidor = null;
        try {
            socketServidor = new ServerSocket(PORT);
        } catch (IOException e) {
            System.out.println("No puede escuchar en el puerto: " + PORT);
            System.exit(-1);
        }

        Socket socketCliente = null;

        System.out.println("Escuchando: " + socketServidor);

        try {
            while (true) {
                socketCliente = socketServidor.accept();
                Thread t = new MultipleClientes(socketCliente);
                t.start();                
            }
        } catch (Exception e) {
        }

        socketServidor.close();
    }
}
