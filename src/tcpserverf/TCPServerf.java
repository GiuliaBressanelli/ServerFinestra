
package tcpserverf;

import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;

public class TCPServerf {
    public static void main(String[] args) {
        Finestra f1=new Finestra();
        f1.setVisible(true);
    }
  }

class Finestra extends JFrame{
    JButton attiva=new JButton("Attiva");
    JPanel p2=new JPanel();
    JPanel p1=new JPanel();
    JPanel p3=new JPanel();
  
  
    
    Finestra(){
        setSize(300,200);
        setLocation(5,5);
        setTitle("TCP Server");
        setResizable(false);
        setLayout(new GridLayout(3,2));
        add(p1);
        p1.add(new Label("Server TCP"));
        
        add(p2);
        p2.add(new Label("Attivare il server:"));
        
        add(p3);
        p3.add(attiva);
        
        
        addWindowListener(new ListF()); 
        attiva.addActionListener(new ListB());
        
    }
    
}

 class ListF implements WindowListener{
    public void windowOpened(WindowEvent e) {}
    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }
    public void windowClosed(WindowEvent e) {}
    public void windowIconified(WindowEvent e) {}
    public void windowDeiconified(WindowEvent e) {}
    public void windowActivated(WindowEvent e) {}
    public void windowDeactivated(WindowEvent e) {}
    
}

class ListB implements ActionListener {
  
    ListB(){}
    

    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
       
        if(s.equals("Attiva")){
            try{
                 int port=2000;
                 ServerSocket ss=new ServerSocket(port);
            while(true){
                new EchoThread(ss.accept()).start();
            }
                
            }catch(IOException ec){
                System.err.println(ec);
            }
            
        
    }
}
}

class EchoThread extends Thread{
    Socket s;
    boolean maiuscole=false;
    public EchoThread(Socket s){
        this.s=s;
    }
    public void run(){
        try{
        BufferedReader in=new BufferedReader(new InputStreamReader(s.getInputStream()));
        PrintWriter out=new PrintWriter(new OutputStreamWriter (s.getOutputStream()),true);
        
            while(true){
                String stringa=in.readLine();
                if(stringa.equals("maiuscole: on")){
                    maiuscole=true;
                }
                if(maiuscole==true){
                    out.println(in.readLine().toUpperCase());
                }
                else{
                    out.println(stringa);
                }
            }
          
       
    }catch(IOException e){}
}
}