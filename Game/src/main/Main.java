package src.main;

import javax.swing.JFrame;

public class Main{
        public static void main(String[] args){
            
            JFrame window = new JFrame();//makes window
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// Close on x
            window.setResizable(false);//cant resize
            window.setTitle("Kitty Kat Adventures");
            
            GamePanel gamePanel = new GamePanel();
            window.add(gamePanel);//adds game panel to window
            window.pack();//layout control

            window.setLocationRelativeTo(null);//center screen
            window.setVisible(true);//shows 

            gamePanel.startGameThread();//start
        }
}
