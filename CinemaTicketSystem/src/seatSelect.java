import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class seatSelect {

    private int counter = 0;
    
    JFrame frame;
    JPanel seatPanel1;
    JPanel seatPanel2;
    JPanel seatPanel3;
    JPanel topPanel;
    JPanel bottomPanel;
    myPanel bg;
    JButton confirm;
    int takenSeat[] = new int[234];
    HashMap<Integer, Integer> booking = new HashMap<Integer, Integer>();
    int seatCount = 0;
    
    public seatSelect(String db) throws FileNotFoundException {
        frame = new JFrame();
        frame.setVisible(true);
        frame.setSize(900, 900);
        frame.setTitle("Spark Cinema - Select Seat");
        frame.setLayout(new BorderLayout());
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JLabel topLabel = new JLabel("Please select your seat!");
        Font font1 = new Font("SansSerif", Font.ITALIC, 20);
        topLabel.setFont(font1);
        
        seatPanel1 = new JPanel();
        Dimension d = new Dimension(230, 740);
        seatPanel1.setPreferredSize(d);
        seatPanel1.setMaximumSize(d);
        seatPanel1.setMinimumSize(d);
        seatPanel1.setLayout(new FlowLayout());
        seatPanel1.setOpaque(false);
        seatPanel1.setBorder(BorderFactory.createEmptyBorder(0, 80, 0, 0));
        
        seatPanel2 = new JPanel();
        Dimension d2 = new Dimension(2, 2);
        seatPanel2.setPreferredSize(d2);
        seatPanel2.setMaximumSize(d2);
        seatPanel2.setMinimumSize(d2);
        seatPanel2.setLayout(new FlowLayout());
        seatPanel2.setOpaque(false);
        seatPanel2.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 30));
        
        seatPanel3 = new JPanel();
        seatPanel3.setPreferredSize(d);
        seatPanel3.setMaximumSize(d);
        seatPanel3.setMinimumSize(d);
        seatPanel3.setLayout(new FlowLayout());
        seatPanel3.setOpaque(false);
        seatPanel3.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 80));
        
        topPanel = new JPanel();
        //topPanel.setBackground(Color.ORANGE);
        topPanel.setOpaque(false);
        topPanel.setPreferredSize(new Dimension(768, 180));
        
        bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.ORANGE);
        bottomPanel.setLayout(new FlowLayout());
        bottomPanel.setOpaque(false);
        bottomPanel.setPreferredSize(new Dimension(768, 180));
        
        bg = new myPanel();
        bg.setLayout(new BorderLayout());
        bg.setOpaque(false);
        
        checkTakenSeat(db);
        
        generateSeat(0, 1, seatPanel1);
        generateSeatCenter(0, 5, seatPanel2);
        generateSeat(0, 15, seatPanel3);
        
        confirm = new JButton("Confirm");
        mouseLConfirm(confirm, db);
        bottomPanel.add(confirm);
        
        bg.add(topPanel, BorderLayout.PAGE_START);
        bg.add(seatPanel1, BorderLayout.WEST);
        bg.add(seatPanel2, BorderLayout.CENTER);
        bg.add(seatPanel3, BorderLayout.EAST);
        bg.add(bottomPanel, BorderLayout.PAGE_END);
        frame.add(bg);
        
        
    }
    
    public void checkTakenSeat(String db) throws FileNotFoundException {
        
        Scanner in = new Scanner(new File(db));
        
        for(int i=0; i<234; i++) {
            
            if((in.hasNext())) {
                String t = in.nextLine();
                takenSeat[i] = Integer.parseInt(t);
            }
        }
        
        in.close();
        
    }
    
    public void mouseLConfirm(JButton btn, String db) {
        btn.addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(MouseEvent arg0) {}           
            @Override
            public void mousePressed(MouseEvent arg0) {}      
            @Override
            public void mouseExited(MouseEvent arg0) {}           
            @Override
            public void mouseEntered(MouseEvent arg0) {}
            @Override
            public void mouseClicked(MouseEvent arg0) {
                try {
                    saveToFile(db);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
    }
    
    public void mouseL(JButton btn, int i) {
        btn.addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(MouseEvent arg0) {}           
            @Override
            public void mousePressed(MouseEvent arg0) {}      
            @Override
            public void mouseExited(MouseEvent arg0) {}           
            @Override
            public void mouseEntered(MouseEvent arg0) {}
            @Override
            public void mouseClicked(MouseEvent arg0) {
                
                if(btn.getBackground() == Color.RED)
                    JOptionPane.showMessageDialog(null, "THE SEAT HAS BEEN BOOKED!", "WARNING", JOptionPane.WARNING_MESSAGE);
                else if(btn.getBackground() == Color.GREEN) {
                    btn.setBackground(Color.WHITE);
                    booking.remove(i);
                }
                else {
                    btn.setBackground(Color.GREEN);
                    booking.put(i, 1);
                }
                
            }
        });
    }
    
    public void saveToFile(String db) throws IOException {
        
        if(booking.isEmpty())
            JOptionPane.showMessageDialog(null, "NO SEAT SELECTED!", "ERROR", JOptionPane.WARNING_MESSAGE);
        else {
            BufferedReader br = new BufferedReader(new FileReader(db));  
        
            PrintWriter pw = new PrintWriter(new FileWriter(db));
        
            for(int i = 0; i<234; i++) {            
                if(booking.containsKey(i)) {
                    pw.println(1);
                    seatCount++;
                } else
                    pw.println(takenSeat[i]);
            }
            pw.close();
            br.close();
            
            String byeMessage = (  "THANK YOU FOR PURCHASING!\n"
                                 + "TOTAL PRICE : RM " + seatCount*12 + "\n \n POPCORN AND SODA ARE AVAILABLE AT SNACK CORNER ");
            
            JOptionPane.showMessageDialog(null, byeMessage, "THANK YOU", JOptionPane.INFORMATION_MESSAGE);
            frame.dispose();
            new welcomeScreen();
        }
        
        
        
    }

    public void generateSeatCenter(int a, int b, JPanel seatPanel) {
        
        for(int i=a; i<13; i++)
        {
            for(int j=b; j<(b+10); j++) {
                String seat = Integer.toString(j);
                
                Font font2 = new Font("SansSerif", Font.BOLD, 9);

                JButton btn = new JButton(seat);
                
                btn.setMargin(new Insets(0,0,0,0));
                btn.setPreferredSize(new Dimension(30,30));
                btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
                btn.setFont(font2);
                
                if(takenSeat[counter]==1) {
                    btn.setBackground(Color.RED);
                    btn.setForeground(Color.WHITE);
                }
                seatPanel.add(btn);
                
                mouseL(btn, counter);
                
                counter++;
            }
        }
        
        frame.revalidate();
        frame.repaint();
        
    }
    
    public void generateSeat(int a, int b, JPanel seatPanel) {
        
        for(int i=a; i<13; i++)
        {
            for(int j=b; j<(b+4); j++) {
                String seat = Integer.toString(j);
                
                Font font2 = new Font("SansSerif", Font.BOLD, 9);
                
                JButton btn = new JButton(seat);
                
                btn.setMargin(new Insets(0,0,0,0));
                btn.setPreferredSize(new Dimension(30,30));
                btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
                btn.setFont(font2);
                
                if(takenSeat[counter]==1) {
                    btn.setBackground(Color.RED);
                    btn.setForeground(Color.WHITE);
                }
                seatPanel.add(btn);
                
                mouseL(btn, counter);
                
                counter++;
            }
        }
        
        frame.revalidate();
        frame.repaint();
        
    }
    
    private class myPanel extends JPanel{
        
        BufferedImage img;
        
        protected void paintComponent(Graphics g) {
            
            try {
                 img = ImageIO.read(new File("C:/Users/User/Desktop/CinemaTicketSystem/Images/wp2.jpg"));
            } catch(IOException e) {
                e.printStackTrace();
            }
            
            super.paintComponent(g);
            g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
        }
        
    }
    
}
