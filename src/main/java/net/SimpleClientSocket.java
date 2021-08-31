package net;

import java.io.*;
import java.net.Socket;

public class SimpleClientSocket {

    public static void main(String[] args) throws IOException {
        for (int i = 0; i < 5; i++) {
            SimpleClient simpleClient = new SimpleClient(i);
            simpleClient.start();
        }
    }


}

class SimpleClient extends Thread {
    private static String[] COMMAND = {"HELLO", "MORNING", "DAY", "EVENING"};
    private int cmdNumber;

    public SimpleClient(int cmdNumber) {
        this.cmdNumber = cmdNumber;
    }

    @Override
    public void run() {
        try {
            Socket socket = new Socket("127.0.0.1", 32112);

            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            String command = COMMAND[cmdNumber % COMMAND.length];
            String sb = command + " " + "Anton";
            bw.write(sb);
            bw.newLine();
            bw.flush();

            String answer = br.readLine();
            System.out.println("Client got string: " + answer);

            br.close();
            bw.close();

            socket.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

