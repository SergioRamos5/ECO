package eco;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MultipleClientes extends Thread {

    Socket cs;

    public MultipleClientes(Socket cs) {
        this.cs = cs;
    }

    public void run() {

        try {
            BufferedReader entrada = null;
            PrintWriter salida = null;
            entrada = new BufferedReader(new InputStreamReader(cs.getInputStream()));
            // Establece canal de salida
            salida = new PrintWriter(new BufferedWriter(new OutputStreamWriter(cs.getOutputStream())), true);
            while (true) {
                String str = entrada.readLine();
                System.out.println("Cliente: " + str);
                salida.println(str);
                if (str.equals("Adios")) {
                    break;
                }
            }
            salida.close();
            entrada.close();
            cs.close();
        } catch (IOException ex) {
            Logger.getLogger(MultipleClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
