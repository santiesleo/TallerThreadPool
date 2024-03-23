import java.io.*;
import java.net.Socket;
import java.util.Arrays;

class ClientHandler implements Runnable {
    private Socket clientSocket;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        try (
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        ) {
            // Leer el arreglo enviado por el cliente desde el flujo de entrada
            String input = in.readLine();
            String[] strArray = input.split(" ");
            int[] array = new int[strArray.length];
            for (int i = 0; i < strArray.length; i++) {
                array[i] = Integer.parseInt(strArray[i]);
            }
            System.out.println(Arrays.toString(array));

            // Ordenar el arreglo usando merge sort
            selectionSort(array);

            // Enviar el resultado de vuelta al cliente a través del flujo de salida
            out.println(Arrays.toString(array));
        } catch (IOException e) {
            e.printStackTrace();
        }
//        finally {
//            try {
//                // Cerrar los flujos de entrada y salida y el socket del cliente
//                clientSocket.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
    }

    public static void selectionSort(int[] numbers){
        int n = numbers.length;

        for(int i = 0; i < n - 1; i++){
            for(int j = i + 1; j < n; j++){
                if(numbers[j] < numbers[i]){
                    int min = numbers[j];
                    int max = numbers[i];
                    numbers[j] = max;
                    numbers[i] = min;
                }
            }
        }
        System.out.println(Arrays.toString(numbers));
    }
}