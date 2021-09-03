package net;

import java.io.*;
import java.net.Socket;

public class SimpleClientSocket {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 8080);

        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        String command = "GET / HTTP/1.1\r\nHost:java-course.ru\r\n\r\n";
        bw.write(command);
        bw.flush();
        String answer = br.readLine();

        while (answer != null) {
            System.out.println(answer);
            answer = br.readLine();
        }

        br.close();
        bw.close();

        socket.close();

//        for (int i = 0; i < 5; i++) {
//            SimpleClient simpleClient = new SimpleClient(i);
//            simpleClient.start();
//        }
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
            Socket socket = new Socket("localhost", 8080);

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

