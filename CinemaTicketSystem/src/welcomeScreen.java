import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class welcomeScreen{
	
	JFrame frame;
	JButton bookNow = new JButton(new ImageIcon("C:/Users/User/Desktop/CinemaTicketSystem/Images/BookNow.png"));;
	myPanel bg;
	
	public welcomeScreen(){
		frame = new JFrame();
		
		frame.setVisible(true);
		frame.setSize(1024, 768);
		frame.setTitle("Spark Cinema");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLayout(new BorderLayout());

		bookNow.setBorder(BorderFactory.createEmptyBorder());
		bookNow.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		bg = new myPanel();
		bg.setLayout(new GridBagLayout());
		bg.add(bookNow, new GridBagConstraints());
		
		frame.add(bg);
		
		changeImage("C:/Users/User/Desktop/CinemaTicketSystem/Images/BookNow.png", "C:/Users/User/Desktop/CinemaTicketSystem/Images/BookNowHovered.png", bookNow);
		
		theHandler handler = new theHandler();
		bookNow.addActionListener(handler);
		
	}
	
	private class theHandler implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource()==bookNow) {
				new MovieScreen();
				frame.dispose();
			}
			
		}
		
	}
	
	public void changeImage(String a, String b, JButton btn) {
		btn.addMouseListener(new MouseListener() {
			@Override
            public void mouseReleased(MouseEvent arg0) {}           
            @Override
            public void mousePressed(MouseEvent arg0) {}      
			@Override
            public void mouseExited(MouseEvent arg0) { 
				btn.setIcon(new ImageIcon(a));
            }           
            @Override
            public void mouseEntered(MouseEvent arg0) {
            	btn.setIcon(new ImageIcon(b));
            }
            @Override
            public void mouseClicked(MouseEvent arg0) {}
		});
	}
	
	private class myPanel extends JPanel{
		
		BufferedImage img;
		
		protected void paintComponent(Graphics g) {
			
			try {
	             img = ImageIO.read(new File("C:/Users/User/Desktop/CinemaTicketSystem/Images/welcome.jpg"));
	        } catch(IOException e) {
	            e.printStackTrace();
	        }
			
            super.paintComponent(g);
            g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
        }
		
	}
	
}
