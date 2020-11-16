
package Client;

import java.io.BufferedReader;

/**
 *class extended by st used in order to read data
 *@author Alessio
 */
public class Read extends Thread
{     
    public BufferedReader in;
        
        /**
         * constructor method
         * @param in variable used to read input data 
         */
        public Read(BufferedReader in)
        {
            this.in=in;
        }
        
        /**
         * override
         * method which overrides the run method in the Thread class used to read data
         */
        public void run()
        {
            String s;
            try
            {
                for(;;)     //infinite loop
                {
                    s=in.readLine();
                    System.out.println(s);
                }
            }
            catch (Exception e)
            {
                System.out.println("Error reading data. \n");
            }
        }
        
        /**
         * method used to end the reading process
         */
        public void ending()
        {
            this.stop();
        }
    }