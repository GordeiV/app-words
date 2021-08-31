package net;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleServerSocket {
    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(32112);

        while (true) {
            Socket client = socket.accept();
            new SimpleServer(client).start();
        }
    }
}

class SimpleServer extends Thread {
    private Socket client;

    public SimpleServer(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));

            StringBuilder sb = new StringBuilder("Hello, ");
            String request = br.readLine();
            String[] lines = request.split("\\s+");
            String command = lines[0];
            String userName = lines[1];
            System.out.println("Server got String 1: " + command);
            System.out.println("Server got String 2: " + userName);
            sb.append(userName);

            bw.write(sb.toString());
            bw.newLine();
            bw.flush();

            br.close();
            bw.close();

            client.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
