import java.rmi.*;
import java.io.*;

public class Client {

    /**
     * internal variable holds the Server
     */
    protected Server server;

    /**
     * internal variable holds the url String
     */	
    protected String url;
	
	/**
     * constructor
	 * Get url
	 *
	 * @param url the url String
     */
    public Client(String url){
        this.url = url;
    }

    /**
     *
     * main method
     *
     * Create a server instance, then handle request
     *
     */
    public static void main(String[] args){
        try{
            Client c = new Client("rmi://127.0.0.1/RMI");
            c.connect();

            System.err.println("Enter the string to be processed");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String input = br.readLine();
            c.process(input);

            c.printResult();
        } catch(Exception e){
            System.err.println( "Exception catched, message: " + e.toString());
        }
    }

    /**
     * Connect server by url
	 *
     */
    protected void connect() throws Exception {
        this.server = (Server) Naming.lookup(url);
    }

    /**
     * process information to msg
     *
     * @param input the String to be processed
	 *
     */
    protected void process(String input) throws Exception{
        this.server.setInput(input);
        this.server.setCounts();
    }

    /**
     * printResult function
	 *
	 * Receive count message from server and print the result out
     */
    protected void printResult() throws Exception{
        System.out.println("Result received: CharacterCount=" + this.server.getCharacterCount() + ", DigitCount=" + this.server.getDigitCount());
    }
}
