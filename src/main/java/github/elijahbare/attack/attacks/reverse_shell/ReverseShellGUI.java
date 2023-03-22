package github.elijahbare.attack.attacks.reverse_shell;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import static github.elijahbare.attack.attacks.reverse_shell.ReverseShell.input;
import static github.elijahbare.attack.attacks.reverse_shell.ReverseShell.output;

/**
 * @author Elijah Bare
 * @since 3/22/23
 */


public class ReverseShellGUI {

    private final JFrame frame;
    private final JTextArea textArea;
    private final JTextField commandField;
    private final JButton submitButton;

    /**
     * Gui for the reverse shell module
     * credit:
     * https://stackoverflow.com/questions/1627028/how-to-set-auto-scrolling-of-jtextarea-in-java-gui
     * https://stackoverflow.com/questions/13731710/allowing-the-enter-key-to-press-the-submit-button-as-opposed-to-only-using-mo
     */

    public ReverseShellGUI() {
        frame = new JFrame("Reverse Shell");
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());

        JLabel label = new JLabel("Enter Command: ");
        topPanel.add(label, BorderLayout.WEST);

        commandField = new JTextField();
        topPanel.add(commandField, BorderLayout.CENTER);

        submitButton = new JButton("Submit");

        ActionListener submitListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String command = commandField.getText();

                //clear command
                if (command.trim().equalsIgnoreCase("clear")|| command.trim().equalsIgnoreCase("cls")){
                    commandField.setText("");
                    textArea.setText("");
                    return;
                }

                //exit command
                if (command.trim().equals("exit") || command.trim().equals("q") || command.trim().equals("quit")) {
                    frame.dispose();
                }

                try {
                    //write command to be parsed
                    output.write(command.getBytes());
                    byte[] buffer = new byte[1024];
                    int bytesRead = input.read(buffer);
                    textArea.append(new String(buffer, 0, bytesRead));


                    //clear input
                    commandField.setText("");
                } catch (IOException ex) {
                    ex.printStackTrace();
                    System.exit(1);
                }
            }
        };


        submitButton.addActionListener(submitListener);
        commandField.addActionListener(submitListener);


        topPanel.add(submitButton, BorderLayout.EAST);

        frame.add(topPanel, BorderLayout.NORTH);

        textArea = new JTextArea();
        //alwasy show bottom and scroll for user
        DefaultCaret caret = (DefaultCaret)textArea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

        frame.add(textArea, BorderLayout.CENTER);

        frame.setVisible(true);
    }
}