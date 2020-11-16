
package Server;

import java.io.*;
import java.net.*;

/**
 *class used in order to manage sts server side
 * @author Alessio
 */
public class ServerThread extends Thread
{
       public BufferedReader in;          //input data
       public DataOutputStream out;       //output data
       public Socket s;                   //socket
       public String username;            //client username
       public String msg;                 //client message
       public int protocol;               //variable used as a counter of the position of the character used as a protocol for whispers, in this case the chosen character is @
       public String whisper;
       public MainServer server;          //Main server object used in order to run methods from the class
        
        /**
         * constructior method
         * @param s Socket (ip,port)
         * @param server object from the MainServer class
         */
        public ServerThread(Socket s, MainServer server)
        {
            this.s=s;
            this.server=server;
        }
        
        /**
         *override
         *run method which overrides the run method in the Thread class
         */
        @Override
        public void run()
        {
            try
            {
                in= new BufferedReader(new InputStreamReader(s.getInputStream()));
                out= new DataOutputStream(s.getOutputStream());

                out.writeBytes("Enter your username. \n");
                username=in.readLine();                                             //username variable that equals to the input data username coming from the client
                server.sendMessage(username+" connected", "Server");
                for(;;)                                                             //infinite loop
                {
                    msg=in.readLine();                                              //msg variable that equals to the input data coming from the client
                    protocol=msg.indexOf("@");                                      //index of the character chosen as the protocol one
                    if(msg.equalsIgnoreCase("exit"))                                //if the msg coming equals the word exit (in any case type) the user disconnects from the chat
                    {
                        server.sendMessage(username+" disconnected.", "Server");
                        out.writeBytes("End of service. \n");
                        break;
                    }
                    else if(protocol == 0 && !msg.equalsIgnoreCase("@ls"))          //if the index of @ equals 0 and the msg is not @ls then the client will send a whisper to the username written right next of the command                                    //if the index equals 0 then it means that the client is trying to whisper someone else, in this case the message will only be sent to the correct client
                    {
                        protocol=msg.indexOf(" ");
                        whisper=msg.substring(1, protocol);
                        System.out.println(whisper);
                        server.Whispers(msg, whisper, this.username);
                    }
                    else if(msg.equals("@ls"))                                      //if the command equals @ls then the server will print all the usernames connected in that moment
                    {
                        server.showList(username);
                    }
                    else
                    {
                        server.sendMessage(msg, username);                        //if none of the previous cases is correct, the message will be printed to all the clients connected in the server
                    }
                }
                server.Remove(this.username);
                this.stop();
                System.exit(0);
            }
            catch(IOException e)
            {
                System.out.println("Error sending the message. \n");
            }
        }
        
        /**
         * method used in order to get the username attribute 
         * @return returns the username
         */
        public String getUsername()
        {
            return this.username;
        }
}