package mkcoding.services.sockets;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * Created by mx on 16/4/4.
 */
public class ServiceTest {
    public final static int PROT = 4300;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PROT)) {
            System.out.println("Server is started!");
            while (true) {
                try (Socket connection = serverSocket.accept()) {

                    System.out.println("Accepted from " + connection.getInetAddress());

                    Writer out = new OutputStreamWriter(connection.getOutputStream());
                    Date now = new Date();
                    out.write(now.toString() + "\r\n");
                    out.flush();
                    connection.close();
                } catch (IOException e) {
                }
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
}
