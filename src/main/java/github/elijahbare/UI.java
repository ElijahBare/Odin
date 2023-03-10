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
        // creates a panel for each attack type category
        for (AttackType attackType : AttackType.values()) {
            JPanel attackCategoriesPanel = new JPanel(new BorderLayout());
            attackCategoriesPanel.setBorder(BorderFactory.createTitledBorder(attackType.name()));

            if (attackManager.getAttacksByCategory(attackType) == null){
                JPanel attackPanel = new JPanel(new BorderLayout());
                attackPanel.add(attackPanel, BorderLayout.NORTH);
            }

            for (Attack attack : attackManager.getAttacksByCategory(attackType)) {
                JPanel attackPanel = new JPanel(new BorderLayout());
                attackPanel.setBorder(BorderFactory.createTitledBorder(attackManager.getAttackName(attack)));

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

                attackCategoriesPanel.add(attackPanel, BorderLayout.NORTH);
            }

            add(attackCategoriesPanel);
        }

        // Set up the window
        setTitle("ODIN - UI");
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
