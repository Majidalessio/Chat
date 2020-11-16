
package Client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;

/**
 * class used in order to manage the client 
 *@author Alessio
 */
public class Client 
{
   public Socket sc;                  //socket client
   public BufferedReader inKeyboard;  //in from keyboard
   public BufferedReader in;          //in 
   public DataOutputStream out;       //out
   public Write write;                //object used for Write's class methods
   public Read read;                  //object used for Read's class methods
    
    /**
     * main method 
     * @param args main variable
     */
    public static void main(String[] args) 
    {
            Client c= new Client();
            c.connect();
            //new Graphics.Graphic();
    }
    
    /**
     * method used in order to manage the connect with the server
     */
    public void connect()
    {
        try
        {
            InetAddress ip= InetAddress.getByName("localhost");
            sc= new Socket(ip, 7230);
            inKeyboard = new BufferedReader(new InputStreamReader(System.in));
            out= new DataOutputStream(sc.getOutputStream());
            in= new BufferedReader(new InputStreamReader(sc.getInputStream()));            
            read= new Read(in);
            write = new Write(inKeyboard, in, out, sc, read);
            write.start();      //starting run method, for reference look at the Write class
            read.start();       //starting run method, for reference look at the Read class
        }
        catch(Exception e)
        {
            System.out.println("Error connecting. \n");
        }
    }   
}

