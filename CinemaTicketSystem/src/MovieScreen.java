import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MovieScreen {
	
	JButton movie1 = new JButton(new ImageIcon("C:/Users/User/Desktop/CinemaTicketSystem/Images/1.jpg"));
	JButton movie2 = new JButton(new ImageIcon("C:/Users/User/Desktop/CinemaTicketSystem/Images/2.jpg"));
	JButton movie3 = new JButton(new ImageIcon("C:/Users/User/Desktop/CinemaTicketSystem/Images/3.jpg"));
	JButton movie4 = new JButton(new ImageIcon("C:/Users/User/Desktop/CinemaTicketSystem/Images/4.jpg"));
	JButton movie5 = new JButton(new ImageIcon("C:/Users/User/Desktop/CinemaTicketSystem/Images/5.jpg"));
	JButton movie6 = new JButton(new ImageIcon("C:/Users/User/Desktop/CinemaTicketSystem/Images/6.jpg"));
	
	JFrame frame;
	
	public MovieScreen() {
			
		frame = new JFrame();
		frame.setVisible(true);
		frame.setSize(1024, 768);
		frame.setTitle("Spark Cinema - Select Movies");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		movie1.setBorder(BorderFactory.createEmptyBorder());
		movie2.setBorder(BorderFactory.createEmptyBorder());
		movie3.setBorder(BorderFactory.createEmptyBorder());
		movie4.setBorder(BorderFactory.createEmptyBorder());
		movie5.setBorder(BorderFactory.createEmptyBorder());
		movie6.setBorder(BorderFactory.createEmptyBorder());
		
		movie1.setCursor(new Cursor(Cursor.HAND_CURSOR));
		movie2.setCursor(new Cursor(Cursor.HAND_CURSOR));
		movie3.setCursor(new Cursor(Cursor.HAND_CURSOR));
		movie4.setCursor(new Cursor(Cursor.HAND_CURSOR));
		movie5.setCursor(new Cursor(Cursor.HAND_CURSOR));
		movie6.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		//Adding stuff to the panels
		//----------------------------------------------------
		myPanel bg = new myPanel();
		
		//HeaderPanel
		JPanel header = new JPanel();
		JLabel big = new JLabel("WELCOME, PLEASE SELECT YOUR MOVIE DOWN BELOW");
		Font font1 = new Font("Consolas Regular", Font.PLAIN, 32);
		big.setFont(font1);
		big.setForeground(Color.white);
		header.add(big);
		header.setOpaque(false);
		
		//MoviesPanel
		JPanel moviesPanel = new JPanel();
		Dimension d = new Dimension(650, 740);
		moviesPanel.setPreferredSize(d);
		moviesPanel.setMaximumSize(d);
		moviesPanel.setMinimumSize(d);
		moviesPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 10, 10));

		moviesPanel.add(movie1);
		moviesPanel.add(movie2);
		moviesPanel.add(movie3);
		moviesPanel.add(movie4);
		moviesPanel.add(movie5);
		moviesPanel.add(movie6);
		moviesPanel.setOpaque(false);
		
		Box box = new Box(BoxLayout.Y_AXIS);

        box.add(Box.createVerticalGlue());
        box.add(moviesPanel);     
        box.add(Box.createVerticalGlue());
       
        bg.add(header);
		bg.add(box);
		
        frame.add(bg);
		
		//----------------------------------------------------
		
		changeImage("C:/Users/User/Desktop/CinemaTicketSystem/Images/1.jpg", "C:/Users/User/Desktop/CinemaTicketSystem/Images/11.jpg", movie1);
		changeImage("C:/Users/User/Desktop/CinemaTicketSystem/Images/2.jpg", "C:/Users/User/Desktop/CinemaTicketSystem/Images/22.jpg", movie2);
		changeImage("C:/Users/User/Desktop/CinemaTicketSystem/Images/3.jpg", "C:/Users/User/Desktop/CinemaTicketSystem/Images/33.jpg", movie3);
		changeImage("C:/Users/User/Desktop/CinemaTicketSystem/Images/4.jpg", "C:/Users/User/Desktop/CinemaTicketSystem/Images/44.jpg", movie4);
		changeImage("C:/Users/User/Desktop/CinemaTicketSystem/Images/5.jpg", "C:/Users/User/Desktop/CinemaTicketSystem/Images/55.jpg", movie5);
		changeImage("C:/Users/User/Desktop/CinemaTicketSystem/Images/6.jpg", "C:/Users/User/Desktop/CinemaTicketSystem/Images/66.jpg", movie6);
		
		//Adding actionlistener
		
		handlerClass handler = new handlerClass();
		
		movie1.addActionListener(handler);
		movie2.addActionListener(handler);
		movie3.addActionListener(handler);
		movie4.addActionListener(handler);
		movie5.addActionListener(handler);
		movie6.addActionListener(handler);

		
	}
	
	private class handlerClass implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			
			try
			{
				if(e.getSource()==movie1) {
					new seatSelect("C:/Users/User/Desktop/CinemaTicketSystem/SeatDatabase/movie1.txt");
					frame.dispose();
				} else if(e.getSource()==movie2) {
					new seatSelect("C:/Users/User/Desktop/CinemaTicketSystem/SeatDatabase/movie2.txt");
					frame.dispose();
				} else if(e.getSource()==movie3) {
					new seatSelect("C:/Users/User/Desktop/CinemaTicketSystem/SeatDatabase/movie3.txt");
					frame.dispose();
				} else if(e.getSource()==movie4) {
					new seatSelect("C:/Users/User/Desktop/CinemaTicketSystem/SeatDatabase/movie4.txt");
					frame.dispose();
				} else if(e.getSource()==movie5) {
					new seatSelect("C:/Users/User/Desktop/CinemaTicketSystem/SeatDatabase/movie5.txt");
					frame.dispose();
				} else if(e.getSource()==movie6) {
					new seatSelect("C:/Users/User/Desktop/CinemaTicketSystem/SeatDatabase/movie6.txt");
					frame.dispose();
				}
			} catch(IOException e1) {
				e1.printStackTrace();
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
		private static final long serialVersionUID = 1L;
		BufferedImage img;
		
		protected void paintComponent(Graphics g) {
			
			try {
	             img = ImageIO.read(new File("C:/Users/User/Desktop/CinemaTicketSystem/Images/wp.jpeg"));
	        } catch(IOException e) {
	            e.printStackTrace();
	        }
			
            super.paintComponent(g);
            g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
        }
		
	}

}
