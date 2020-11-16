
package Client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.net.Socket;

/**
 *class extended by st used in order to write data
 *@author Alessio
 */
public class Write extends Thread
{
   public Socket sc;                  //socket client
   public BufferedReader inKeyboard;  //in from keyboard
   public BufferedReader in;          //in 
   public DataOutputStream out;       //out
   public Read read;                  //object used for Read's class methods
    
    /**
     * constructor method
     * @param inKeyboard varible used to read keyboard's input
     * @param in    variable used to read input data
     * @param out   variable used to write output data
     * @param sc    socket (ip,port)
     * @param read  object used to call methods from the Read class
     */
    public Write(BufferedReader inKeyboard, BufferedReader in, DataOutputStream out, Socket sc, Read read) 
    {
        this.inKeyboard = inKeyboard;
        this.in = in;
        this.out = out;
        this.sc= sc;
        this.read=read;
    }

        /**
         * override
         * method which overrides the run method in the Thread class used to write data
         */
        public void run()
        {
            String msg;
            try
            {
                for(;;)
                {
                    msg=inKeyboard.readLine();
                    if(msg.equals("stop"))
                    {
                        out.writeBytes(msg+ "\n");
                        read.ending();
                        inKeyboard.close();
                        in.close();
                        out.close();
                        sc.close();
                        this.stop();
                    }
                    else
                    {
                        out.writeBytes(msg+ "\n");
                    }
                }
            }
            catch(Exception e)
            {
                System.out.println(e.getMessage());
            }
        } 
}