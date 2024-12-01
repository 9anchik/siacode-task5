package Various1;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FrameMain frame = new FrameMain();
            frame.setVisible(true);
        });
    }
}
