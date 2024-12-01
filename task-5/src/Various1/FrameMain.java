package Various1;

import javax.swing.*;
import java.awt.*;


public class FrameMain extends JFrame {
    private final GUI mapPanel;
    private final JButton findPathButton;
    private final JButton resetButton;

    public FrameMain() {
        setTitle("Дипломная работа по СиАКОДУ");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        mapPanel = new GUI(10, 10);
        add(mapPanel, BorderLayout.CENTER);


        JPanel controlPanel = new JPanel();
        findPathButton = new JButton("Найти путь");
        resetButton = new JButton("Сброс");
        controlPanel.add(findPathButton);
        controlPanel.add(resetButton);

        add(controlPanel, BorderLayout.SOUTH);


        findPathButton.addActionListener(e -> mapPanel.findPath());
        resetButton.addActionListener(e -> mapPanel.resetMap());
    }
}