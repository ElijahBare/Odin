package github.elijahbare;


import github.elijahbare.attack.Attack;
import github.elijahbare.attack.AttackType;
import github.elijahbare.setting.Setting;

import javax.swing.*;
import java.awt.*;

import static github.elijahbare.Main.attackManager;

/**
 * @author Elijah Bare 3/10/23
 */

public class UI extends JFrame {


    public UI() {
        JPanel mainPanel = new JPanel(new GridLayout(0, 1)); // create new panel to hold all categories

        // creates a panel for each attack type category
        for (AttackType attackType : AttackType.values()) {
            JPanel attackCategoriesPanel = new JPanel(new BorderLayout());
            attackCategoriesPanel.setBorder(BorderFactory.createTitledBorder(attackType.name()));

            // create a panel for each attack in the category
            JPanel attackListPanel = new JPanel(new GridLayout(0, 1));
            for (Attack attack : attackManager.getAttacksByCategory(attackType)) {
                JPanel attackPanel = new JPanel(new BorderLayout());
                attackPanel.setBorder(BorderFactory.createTitledBorder(attackManager.getAttackName(attack)));
                JButton runButton = new JButton("Run");
                attackPanel.add(runButton);
                for (Setting setting : attack.getSettings()) {
                    JPanel settingPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                    JLabel settingLabel = new JLabel(setting.getName());
                    JTextField settingField = new JTextField(10);
                    JButton setButton = new JButton("Set");
                    settingField.setText(setting.getValue().toString());
                    setButton.addActionListener(e -> {
                        String value = settingField.getText();
                        setting.setValue(value);
                        settingField.setText(setting.getValue().toString());
                    });
                    settingPanel.add(settingLabel);
                    settingPanel.add(settingField);
                    settingPanel.add(setButton);
                    attackPanel.add(settingPanel, BorderLayout.NORTH);
                }

                // Add additional setting panels for each setting
                for (Setting setting : attack.getSettings()) {
                    JPanel settingPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                    JLabel settingLabel = new JLabel(setting.getName());
                    JTextField settingField = new JTextField(10);
                    JButton setButton = new JButton("Set");
                    settingField.setText(setting.getValue().toString());
                    setButton.addActionListener(e -> {
                        String value = settingField.getText();
                        setting.setValue(value);
                        settingField.setText(setting.getValue().toString());
                    });
                    settingPanel.add(settingLabel);
                    settingPanel.add(settingField);
                    settingPanel.add(setButton);
                    attackPanel.add(settingPanel, BorderLayout.NORTH);
                }


                runButton.addActionListener(e -> {
                    Thread thread = new Thread(() -> {
                        System.out.println(attack.run(null));
                    });
                    thread.start();
                });

                attackListPanel.add(attackPanel);
            }

            attackCategoriesPanel.add(attackListPanel, BorderLayout.NORTH);
            mainPanel.add(attackCategoriesPanel); // add the category panel to the main panel
        }

        this.add(mainPanel); // add the main panel to the UI

        // Set up the window
        setTitle("SHREK - UI");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }


    public static void init() {

        Thread sync = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {

                try {
                    Thread.sleep(50);
                } catch (InterruptedException ignored) {
                }
            }
        }, "Sync-Thread");

        new UI();

    }

}
