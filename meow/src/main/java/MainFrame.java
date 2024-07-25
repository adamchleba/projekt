import javax.swing.*;

public class MainFrame extends JFrame {
    public Menu menu;
    public Game game;

    public MainFrame(){
        setTitle("angry mice menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1080,720);
        setLocationRelativeTo(null);
        menu = new Menu(this, "menu.jpg");
        add(menu);

        setVisible(true);
    }
    public void startGame(){
        remove(menu);
        game = new Game();
        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run(){
                new MainFrame();
            }
        });
    }


}
