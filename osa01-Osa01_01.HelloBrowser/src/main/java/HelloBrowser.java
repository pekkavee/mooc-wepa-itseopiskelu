
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class HelloBrowser {

    public static void main(String[] args) throws Exception {

        int portti = 80;
        Scanner input = new Scanner(System.in);
        System.out.println("================");
        System.out.println("THE INTERNETS!");
        System.out.println("================");
        System.out.println("Where to?");
        String uri = input.nextLine();
        System.out.println("==========");
        System.out.println("RESPONSE");
        System.out.println("==========");
        input.close();

        Socket socket = new Socket(uri, portti);

        PrintWriter kirjoittaja = new PrintWriter(socket.getOutputStream());
        kirjoittaja.println("GET / HTTP/1.1");
        kirjoittaja.println("Host: " + uri);
        kirjoittaja.println();
        kirjoittaja.flush();

        Scanner lukija = new Scanner(socket.getInputStream());
        while (lukija.hasNextLine()) {
            System.out.println(lukija.nextLine());
        }

        kirjoittaja.close();
        socket.close();
        lukija.close();
    }
}
