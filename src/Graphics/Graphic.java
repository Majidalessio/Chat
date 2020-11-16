
package Graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Alessio
 */
public class Graphic implements ActionListener
{

    private JFrame frame;       //init frame       
    private JPanel pb;          //button panel
    private JLabel imglabel;
    private JButton start;      //start button
    private JButton quit;       //quit button (ends the program)
    
    /**
     * constructor method
     */
    public Graphic()
    {
        initialize();
    }
    
    /**
     * method used to initialize all the graphic components
     */
    public void initialize()
    {
        //frame creation
        frame=new JFrame();                                     //frame created
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   //if you close the frame, the program ends.
        frame.setVisible(true);                                 //the frame interface is visible
        frame.setTitle(" Chat Launcher ");                      //title
        frame.setBounds(600, 600, 600, 600);                    
        frame.setResizable(false);                              //the user cannot resize the frame
        
         //buttons 
       start=new JButton("Start");
       start.addActionListener(this);
       start.setFont(new Font("Tahoma", Font.BOLD, 15));
       start.setBounds(250,250,100,30);                         //set the position of the button
       
       quit=new JButton("Quit");
       quit.addActionListener(this);
       quit.setFont(new Font("Tahoma", Font.BOLD, 15));
       quit.setBounds(250,300,100,30);                         //set the position of the button
       
       //Button panel
       pb=new JPanel();
       pb.setBackground(Color.white);
       pb.setPreferredSize(new Dimension(600,600));
       pb.setLayout(null);
       
       //graphic hierarchy  
       frame.add(pb);
       pb.add(start);
       pb.add(quit);  
    }
    
    /**
     * Method used in order to manage the graphic buttons and labels
     * @param e variable used to call ActionEvent class methods
     */
    public void actionPerformed(ActionEvent e) 
    {
        if(e.getSource()==quit)
        {
            frame.dispose();                //the program ends when clicking the quit button
        }
    }
    
}
