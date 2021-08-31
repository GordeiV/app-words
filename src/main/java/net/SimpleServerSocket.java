package net;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class SimpleServerSocket {
    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(32112);
        Map<String, Greetable> handlers = loadHandlers();

        while (true) {
            Socket client = socket.accept();

            new SimpleServer(client, handlers).start();
        }
    }

    private static Map<String, Greetable> loadHandlers() {
        Map<String, Greetable> result = new HashMap<>();

        try (InputStream is = SimpleServerSocket.class.getClassLoader().getResourceAsStream("server.properties")){

            Properties properties = new Properties();
            properties.load(is);

            for (Object command : properties.keySet()) {
                String className = properties.getProperty(command.toString());
                Class<Greetable> cl = (Class<Greetable>) Class.forName(className);

                Greetable handler = cl.getConstructor().newInstance();
                result.put(command.toString(), handler);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }

        return result;
    }
}

class SimpleServer extends Thread {
    private Socket client;
    private Map<String, Greetable> handlers;

    public SimpleServer(Socket client, Map<String, Greetable> handlers) {
        this.client = client;
        this.handlers = handlers;
    }

    @Override
    public void run() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));


            String request = br.readLine();
            String[] lines = request.split("\\s+");
            String command = lines[0];
            String userName = lines[1];
            System.out.println("Server got String 1: " + command);
            System.out.println("Server got String 2: " + userName);

            String response = buildResponse(command, userName);
            bw.write(response.toString());
            bw.newLine();
            bw.flush();

            br.close();
            bw.close();

            client.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String buildResponse(String command, String userName) {
        Greetable handler = handlers.get(command);
        if(handler != null) {
            return handler.buildResponse(userName);
        }
        return "Hello, " + userName;
    }
}
