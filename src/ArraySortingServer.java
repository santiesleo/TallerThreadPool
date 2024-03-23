import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class ArraySortingServer {
    private static final int PORT = 12345;
    private ServerSocket serverSocket;
    private ExecutorService threadPool;

    public ArraySortingServer() {
        try {
            serverSocket = new ServerSocket(PORT);
            threadPool = Executors.newCachedThreadPool(); // Usamos un ThreadPool dinámico
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        System.out.println("Array Sorting Server started on port " + PORT);
        while (true) {
            try {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected: " + clientSocket);
                // Crear un nuevo hilo del ThreadPool para manejar la solicitud del cliente
                threadPool.execute(new ClientHandler(clientSocket));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ArraySortingServer server = new ArraySortingServer();
        server.start();
    }
}