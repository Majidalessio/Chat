
package Server;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alessio
 */
public class MainServer 
{
   public ServerSocket socket;
   public ArrayList <ServerThread> list= new ArrayList <>();
   public BufferedReader read=new BufferedReader(new InputStreamReader(System.in));
    
    /**
     * Main method
     * @param args variable 
     */
    public static void main(String[] args) 
    {
        MainServer s= new MainServer();
        s.connect();
    }
    
    /**
     * method used in order to manage the connection client-server, max N clients allowed is 64, once it reaches the limit the socket will close
     */
    public void connect()
    {
        try
        {
            socket= new ServerSocket(7230);             //port used server side
            System.out.println("server started. \n");
            for(int i=0; i<64; i++)                     //hard cap at 64 members 
            {
                Socket accept = socket.accept();
                ServerThread st =new ServerThread(accept, this);
                list.add(st);
                System.out.println("client connected, spaces left "+(64-(i+1))+" \n");
                list.get(i).start();
            }
            socket.close();                             //closing the connection after 64 members joined the chat       
        }
        catch(Exception e)
        {
            System.out.println("Error connecting with the client. \n");
        }
    }
    
    /**
     * Method used in order to manage the send message part of the chat. it requires the srting message and the username of the client
     * @param msg string variable
     * @param username string variable
     */
    public void sendMessage(String msg, String username)
    {
        for(ServerThread s: list)
        {
            try 
            {
                s.out.writeBytes(username +": "+ msg +"\n");
            } 
            catch (IOException e) 
            {
                System.out.println("Error sending the message. \n");
            }
        }
    }
    
    /**
     * Method used to manage whispers between 2 clients, it requires the string message and the username of the client to whisper
     * @param msg string variable
     * @param username string variable
     * @param sender string variable
     */
    public void Whispers(String msg, String username, String sender)
    {
        for(ServerThread s: list)
        {
            if(username.equalsIgnoreCase(s.getUsername()))
            {
                try 
                {
                    s.out.writeBytes("(Whisper)"+sender+": "+msg+"\n");
                    break;
                } 
                catch (IOException e) 
                {
                    System.out.println("Error sending the whisper. \n");
                }
            }
        }
    }
    
    /**
     * Method used to manage the list of the clients currently connected
     * @param username string variable
     */
    public void showList(String username)
    {
        ArrayList <String> usernames=new ArrayList<>();
        for(ServerThread s: list)
        {
            if(username.equalsIgnoreCase(s.getUsername()))
            {
                try 
                {
                    for(ServerThread x: list)
                    {
                        usernames.add(x.getUsername());
                    }
                    System.out.println("The list of members is: \n");
                    for(int i=0;i<list.size();i++)
                    {
                        s.out.writeBytes(usernames.get(i)+"\n");
                    }
                } 
                catch (IOException e) 
                {
                    System.out.println("Error showing the list of members. \n");
                }
            }
        }
    }
    
    /**
     * method used in order to remove the client from the list of members
     * @param username string variable
     */
    public void Remove(String username)
    {
        for(ServerThread s : list)
        {
            if(username.equalsIgnoreCase(s.getUsername()))
            {
                try
                {
                    for(ServerThread x: list)
                    {
                        list.remove(x.getUsername());
                    }
                }
                catch(Exception e)
                {
                    System.out.println("Error deleting the client from the member's list. \n");
                }
            }
        }
    }
}