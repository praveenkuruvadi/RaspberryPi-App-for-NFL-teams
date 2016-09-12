package raspberryPiFinal;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.io.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.*;
/**
 * Write a description of class mygui here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class mygui
{
    // instance variables - replace the example below with your own
    private int x;
    public static String s , s1 , s2 , s3;
    public static boolean authorization = false;
    public static boolean server = false;
    public static boolean client = false;
    public Message instmsg;
	private static ServerSocket server_socket = null;
	private static JFrame window1;
	private static JFrame window2;
	private static JFrame window3;
	private static JFrame window4;
	private static JFrame window5;
	private static JFrame window6;
	public static volatile PriorityQueue<Message> MessageQueue = new PriorityQueue<Message>();
	public static int port;
    private static class firstgui extends JPanel
    {
            public void paintComponent(Graphics g)
            {
                    super.paintComponent(g);
            }
            
    }
    
    private static class ButtonHandler implements ActionListener
    {
            public void actionPerformed(ActionEvent e)
            {
                JButton butt = (JButton) e.getSource();
                s = butt.getActionCommand();
              if(server == true)
              {
                if(s.equals("Cancel"))
                {
                	window4.dispose();
                	window1.setVisible(true);
                    //System.exit(0);
                }
                else if(s.equals("Confirm")){
                	window4.dispose();
                    Message msg = new Message("qbackMsg",s1,s2,s3);
                    System.out.println(s1+s2+s3);
            		ServerThread s = new ServerThread(msg);
            		Thread t = new Thread(s);
            		t.start();
            		firstgui displayPanel = new firstgui();
                    JButton so = new JButton("Start Over");
                    ButtonHandler listener = new ButtonHandler();
                    so.addActionListener(listener);
                    
                    JLabel label = new JLabel(s1 + s2 + s3 + " was sent" , JLabel.CENTER);
                    JPanel content = new JPanel();
                    content.setLayout(new BorderLayout());
                    content.add(label);
                    content.add(so, BorderLayout.SOUTH);
                    window6 = new JFrame("Confirmation");
                    window6.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    window6.setContentPane(content);
                    window6.setSize(250,250);
                    window6.setLocation(100,100);
                    window6.setVisible(true);
                }
                else if(s.equals("Start Over"))
                {
                	window1.setVisible(true);
                	window6.dispose();
                }
                else if(s.equals("A") || s.equals("B") || s.equals("C") || s.equals("D") || s.equals("E") || s.equals("F") || s.equals("G") || s.equals("H") || s.equals("I"))
                {
                      JButton but = (JButton) e.getSource();
                      s1 = but.getActionCommand(); 
                      window1.setVisible(false);
                      firstgui displayPanel = new firstgui();
                      JButton n1 = new JButton("1");
                      JButton n2 = new JButton("2");
                      JButton n3 = new JButton("3");
                      JButton n4 = new JButton("4");
                      JButton n5 = new JButton("5");
                      JButton n6 = new JButton("6");
                      JButton n7 = new JButton("7");
                      JButton n8 = new JButton("8");
                      JButton n9 = new JButton("9");
                      ButtonHandler listener = new ButtonHandler();
                      n1.addActionListener(listener);
                      n2.addActionListener(listener);
                      n3.addActionListener(listener);
                      n4.addActionListener(listener);
                      n5.addActionListener(listener);
                      n6.addActionListener(listener);
                      n7.addActionListener(listener);
                      n8.addActionListener(listener);
                      n9.addActionListener(listener);
                      //num = true;
                      
                      JPanel content = new JPanel();
                      GridLayout layout = new GridLayout(3,3);
                      content.setLayout(new GridLayout(3,3));
                      //content.add(displayPanel, BorderLayout.CENTER);
                      content.add(n1);
                      content.add(n2);
                      content.add(n3);
                      content.add(n4);
                      content.add(n5);
                      content.add(n6);
                      content.add(n7);
                      content.add(n8);
                      content.add(n9);
           
                      window2 = new JFrame("Select a Number");
                      window2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                      window2.setContentPane(content);
                      window2.setSize(250,250);
                      window2.setLocation(100,100);
                      window2.setVisible(true);
                }
                else if(s.equals("1") || s.equals("2") || s.equals("3") || s.equals("4") || s.equals("5") || s.equals("6") || s.equals("7") || s.equals("8") || s.equals("9"))
                {
                        JButton but = (JButton) e.getSource();
                        s2 = but.getActionCommand();
                        window2.dispose();
                        firstgui displayPanel = new firstgui();
                        JButton red = new JButton("RED");
                        red.setBackground(Color.RED);
                        JButton blue = new JButton("BLUE");
                        blue.setBackground(Color.BLUE);
                        JButton green = new JButton("GREEN");
                        green.setBackground(Color.GREEN);
                        ButtonHandler listener = new ButtonHandler();
                        red.addActionListener(listener);
                        blue.addActionListener(listener);
                        green.addActionListener(listener);
                        
                        JPanel content = new JPanel();
                        GridLayout layout = new GridLayout(3,1);
                        content.setLayout(new GridLayout(3,1));
                        content.add(red);
                        content.add(blue);
                        content.add(green);
                        
                        window3 = new JFrame("Select a Number");
                        window3.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        window3.setContentPane(content);
                        window3.setSize(250,250);
                        window3.setLocation(100,100);
                        window3.setVisible(true);
                }
                else if(s.equals("RED") || s.equals("BLUE") || s.equals("GREEN"))
                {
                        JButton but = (JButton) e.getSource();
                        s3 = but.getActionCommand();
                        window3.dispose();
                        JButton confirm = new JButton("Confirm");
                        JButton cancel = new JButton("Cancel");
                        ButtonHandler listener = new ButtonHandler();
                        confirm.addActionListener(listener);
                        cancel.addActionListener(listener);
                        
                        JLabel label = new JLabel("Send " + s1 + s2 + s3 + " Confirm?" , JLabel.CENTER);
                        JPanel content = new JPanel();
                        content.setLayout(new BorderLayout());
                        content.add(label);
                        content.add(confirm, BorderLayout.NORTH);
                        content.add(cancel, BorderLayout.SOUTH);
                        window4 = new JFrame("Confirmation");
                        window4.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        window4.setContentPane(content);
                        window4.setSize(250,250);
                        window4.setLocation(100,100);
                        window4.setVisible(true);

                }
              }
              else if(client == true)
              {
            	  if(s.equals("Show Description") || s.equals("OK"))
            	  {
            		  window5.dispose();
            		  window1.setVisible(true);
            	  }
                  System.exit(0);
              }
            }
    }
    
	private static void runServer(int port) {

		try {
			server_socket = new ServerSocket(port);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ListenerThread s = new ListenerThread(server_socket);
        Thread t = new Thread(s);
        t.start();
	}
    
    public static void main(String[] args)
    {
            //window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            String loginid = JOptionPane.showInputDialog(null, "Login Id");
        try
        {
            FileInputStream fis = new FileInputStream("C:/Users/PRAVEEN/Desktop/utd/RaspberryPi/players.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            String line = null;
            while((line = br.readLine()) != null)
            {
                String t[] = line.split(" ");
                //System.out.println(t[0] +" " + t[1] +" " + t[2]);
                if(t[0].equals(loginid))
                {
                        String password = JOptionPane.showInputDialog(null, "Password");
                        while(!t[1].equals(password))
                        {
                            password = JOptionPane.showInputDialog(null, "Password");
                        }
                        if(t[1].equals(password))
                        {
                                authorization = true;
                                if(t[2].equals("Server"))
                                {
                                        server = true;
                                }
                                else
                                    client = true;
                                port= Integer.parseInt(t[3]);
                        }
                        
                }
            }
            fis.close();
        }catch(Exception e)
        {
                e.printStackTrace();
        }
            //String password = JOptionPane.showInputDialog(null, "Password");
        if(authorization == true)
        {
          if(server == true)
          {
            firstgui displayPanel = new firstgui();
            JButton a1 = new JButton("A");
            JButton a2 = new JButton("B");
            JButton a3 = new JButton("C");
            JButton a4 = new JButton("D");
            JButton a5 = new JButton("E");
            JButton a6 = new JButton("F");
            JButton a7 = new JButton("G");
            JButton a8 = new JButton("H");
            JButton a9 = new JButton("I");
            ButtonHandler listener = new ButtonHandler();
            a1.addActionListener(listener);
            a2.addActionListener(listener);
            a3.addActionListener(listener);
            a4.addActionListener(listener);
            a5.addActionListener(listener);
            a6.addActionListener(listener);
            a7.addActionListener(listener);
            a8.addActionListener(listener);
            a9.addActionListener(listener);
            JPanel content = new JPanel();
            GridLayout layout = new GridLayout(3,3);
            content.setLayout(new GridLayout(3,3));
            //content.add(displayPanel, BorderLayout.CENTER);
            content.add(a1);
            content.add(a2);
            content.add(a3);
            content.add(a4);
            content.add(a5);
            content.add(a6);
            content.add(a7);
            content.add(a8);
            content.add(a9);
            
            window1 = new JFrame("Select an Alphabet");
            window1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            window1.setContentPane(content);
            window1.setSize(250,250);
            window1.setLocation(100,100);
            window1.setVisible(true);
          }
          else if(client == true)
          {
        	  	  runServer(port);
        	  	  
        	  	  waitingDisplay();


          }
        }
        else
        {
            System.out.println("user does not exist");
            System.exit(0);
        }
    }

    public static void waitingDisplay(){
        String msg;
        msg = "Waiting for message";
        firstgui displayPanel = new firstgui();
        JLabel label = new JLabel(msg , JLabel.CENTER);
        JPanel content = new JPanel();
        content.setLayout(new BorderLayout());
        content.add(label);
        window1 = new JFrame("Confirmation");
        window1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window1.setContentPane(content);
        window1.setSize(250,250);
        window1.setLocation(100,100);
        window1.setVisible(true);
        while(MessageQueue.peek()== null){
        	
        }
        messageDisplay();
    	
    }
    public static void messageDisplay(){
    	window1.setVisible(false);
    	Message msgrcv = MessageQueue.poll();
        String msg;
        msg = " Message is: "+ msgrcv.getLetter()+msgrcv.getNumber()+msgrcv.getColor();
        System.out.println("message: "+ msgrcv.getLetter()+msgrcv.getNumber()+msgrcv.getColor());
        firstgui displayPanel = new firstgui();
        JButton SD = new JButton("Show Description");
        JButton ok = new JButton("OK");
        ButtonHandler listener = new ButtonHandler();
        SD.addActionListener(listener);
        ok.addActionListener(listener);
        JLabel label = new JLabel(msg , JLabel.CENTER);
        JPanel content = new JPanel();
        content.setLayout(new BorderLayout());
        content.add(label);
        content.add(SD, BorderLayout.NORTH);
        content.add(ok, BorderLayout.SOUTH);
        window5 = new JFrame("Confirmation");
        window5.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window5.setContentPane(content);
        window5.setSize(250,250);
        window5.setLocation(100,100);
        window5.setVisible(true);
        waitingDisplay();
    }
    
}
