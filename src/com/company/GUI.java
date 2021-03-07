package com.company;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GUI {
    protected GUI() {
        frame = new JFrame();
        mainPanel = new JPanel();
        panel = new JPanel();
        panel1 = new JPanel();
        buttons = new JButton[4];
        label = new JLabel();
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        press = false;
    }

    protected void generation() {
        frame.setSize(screenSize.width, screenSize.height);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainPanel.setLayout(new GridLayout(2, 1));
        panel.add(label);
        for (short i = 0; i < 4; i++) {
            buttons[i] = new JButton();
            buttons[i].setForeground(Color.WHITE);
            panel1.add(buttons[i]);
        }
        mainPanel.add(panel);
        mainPanel.add(panel1);
        frame.add(mainPanel);
        frame.setVisible(true);
    }

    protected void setQuestFrame(String quest, String[] textsAns) {
        label.setText(quest);
        for (short i = 0; i < 4; i++) {
            for (ActionListener al : buttons[i].getActionListeners()) {
                buttons[i].removeActionListener(al);
            }
            buttons[i].setBackground(Color.BLUE);
            buttons[i].setText(textsAns[i]);
            short finalI = i;
            buttons[finalI].addActionListener(e -> {
                numPressButt = finalI;
                press = true;
            });
        }
    }

    protected final short getResponse() {
        while (!press) Thread.onSpinWait();
        press = false;
        return numPressButt;
    }

    protected void wrongAns() {
        buttons[numPressButt].setBackground(Color.RED);
    }

    protected void endOfQuiz() {
        label.setText("Все раунды пройдены");
        for (JButton butt : buttons) {
            butt.removeActionListener(butt.getActionListeners()[0]);
        }
    }

    protected void createErrorWindow(ArrayList<String> errorFiles, boolean fatalError) {
        short halfOfScreenWidth = (short) (screenSize.width / 2);
        short halfOfScreenHeight = (short) (screenSize.height / 2);
        frame.setSize(halfOfScreenWidth / 3, halfOfScreenHeight / 4);
        frame.setLocation(5 * halfOfScreenWidth / 6, 7 * halfOfScreenHeight / 8);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        StringBuilder text = new StringBuilder();
        if (fatalError) text.append("<html><p>Не удалось создать файлы:</p>");
        else text.append("<html><p>В этих файлах не достаёт данных:</p>");
        for (String emptyFile : errorFiles) {
            text.append("<p>").append(emptyFile).append("</p>");
        }
        text.append("</html>");
        label.setText(text.toString());
        label.setForeground(Color.RED);
        mainPanel.add(label);
        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private final JFrame frame;
    private final JPanel mainPanel;
    private final JPanel panel;
    private final JPanel panel1;
    private final JLabel label;
    private final JButton[] buttons;
    private final Dimension screenSize;
    private short numPressButt;
    private volatile boolean press;
}