package pooh;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class PoohServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     InputStream input = socket.getInputStream()) {
                    byte[] buff = new byte[1_000_000];
                    int total = input.read(buff);
                    String text = new String(Arrays.copyOfRange(buff, 0, total), StandardCharsets.UTF_8);
                    System.out.println(input.toString());
                   // System.out.println(text);
                   // System.out.println(text.getBytes());
                    out.write("HTTP/1.1 200 OK\r\n".getBytes());
                    out.write(text.getBytes());
                }
            }
        }
    }
}