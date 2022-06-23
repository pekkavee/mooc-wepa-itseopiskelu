

import java.io.File;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class HelloServer {

    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(8080);
        int laskuri = 1;

        while (true) {
            Socket socket = server.accept();
            System.out.println("Laskuri: " + laskuri);
            laskuri++;

            Scanner lukija = new Scanner(socket.getInputStream());
            if (lukija.hasNextLine() && lukija.nextLine().contains("quit")) {
                break;
            }

            PrintWriter kirjoittaja = new PrintWriter(socket.getOutputStream());

            kirjoittaja.println("HTTP/1.1 200 OK");
            kirjoittaja.println("");
            Files.lines(Paths.get("index.html")).forEach(line -> kirjoittaja.println(line));
            kirjoittaja.flush();

            kirjoittaja.close();
            socket.close();
            lukija.close();
        }

    }
}
