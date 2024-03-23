import java.io.*;
import java.net.*;
import java.util.Arrays;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        final String SERVER_ADDRESS = "localhost";
        final int SERVER_PORT = 12345;
        boolean flag = true;

        try {
            // Conexión al servidor
            Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            Scanner scanner = new Scanner(System.in);

            // Lectura del arreglo del usuario
            System.out.println("Ingrese los elementos del arreglo separados por espacios:");
            String input = scanner.nextLine();
            out.println(input);
            // Lectura del resultado del servidor
            String sortedArray = in.readLine();
            System.out.println("Arreglo ordenado recibido del servidor: " + sortedArray);

            // Cierre de recursos
            // scanner.close();
            // socket.close();

            while (true) {
                socket.close();

                System.out.println("\nQuieres conectarte otra vez? 1: Si | 0: No");
                String res = scanner.nextLine();

                if (res.equalsIgnoreCase("1")) {
                    socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
                    in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    out = new PrintWriter(socket.getOutputStream(), true);

                    // Lectura del arreglo del usuario
                    System.out.println("Ingrese los elementos del arreglo separados por espacios:");
                    input = scanner.nextLine();
                    out.println(input);
                    // Lectura del resultado del servidor
                    sortedArray = in.readLine();
                    System.out.println("Arreglo ordenado recibido del servidor: " + sortedArray);
                } else {
                    scanner.close();
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}