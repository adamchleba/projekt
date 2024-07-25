import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JPanel {
    private JButton startButton;
    private JButton endButton;
    private MainFrame mainFrame;
    private Image backgroundImage;

    public Menu(MainFrame mainFrame, String imagePath) {
        this.mainFrame = mainFrame;
        this.backgroundImage = new ImageIcon(this.getClass().getResource(imagePath)).getImage();

        // Transparent panel for buttons
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        startButton = new JButton("Start Game");
        endButton = new JButton("End Game");

        // Customize buttons
        startButton.setFont(new Font("Arial", Font.BOLD, 20));
        startButton.setContentAreaFilled(false);  // No background color
        startButton.setFocusPainted(false);
        startButton.setBorderPainted(false);  // No border
        startButton.setForeground(Color.WHITE);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.startGame();
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(startButton, gbc);

        endButton.setFont(new Font("Arial", Font.BOLD, 20));
        endButton.setContentAreaFilled(false);  // No background color
        endButton.setFocusPainted(false);
        endButton.setBorderPainted(false);  // No border
        endButton.setForeground(Color.WHITE);
        endButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(endButton, gbc);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the background image, scaled to fill the panel
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}
