package server;

import java.io.IOException;
import java.util.Scanner;

public class ServerMain {
    public static final int PORT = 2308;

    public static void main(String[] args) {
        //FileManager fileManager = new FileManager();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите имя файла колллекции:");
        CollectionManager.path  = scanner.nextLine();
        CollectionManager cm = new CollectionManager();
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                cm.saveToFile();
            }
        });
        ServerRequestHandler requestManager = new ServerRequestHandler(cm);
        Server server = new Server(PORT, requestManager);
        server.run();
        cm.saveToFile();
    }
}