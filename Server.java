import java.io.*;
import java.net.*;

/**
 * Created by andrey_prvt on 19.10.15.
 */
public class Server {

    private ServerSocket serverSocket;
    private int port;

    public void setPort(int port) {
        this.port = port;
    }

    public void initServer(){

        try{
            this.serverSocket = new ServerSocket(this.port);
            System.out.println("Wait for Client");
            Socket socket = serverSocket.accept(); // waiting for client
            System.out.println("Finally got a client");


            InputStream sin = socket.getInputStream();
            OutputStream sout = socket.getOutputStream();

            DataInputStream in = new DataInputStream(sin);
            DataOutputStream out = new DataOutputStream(sout);

            String line = null;
            while(true) {
                line = in.readUTF(); // whait text from client
                System.out.println("The dumb client just sent me this line : " + line);
                System.out.println("I'm sending it back...");
                out.writeUTF(line); // send text back to Client
                out.flush(); // make stream finish send data
                System.out.println("Waiting for the next line...");
                System.out.println();
            }



        }catch (Exception exp){
           exp.printStackTrace();
        }

    }


    public static void main(String[] args) {
        Server server = new Server();
        server.setPort(6666);
        server.initServer();
    }

}
