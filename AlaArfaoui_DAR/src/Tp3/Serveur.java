package Tp3;
import ServeurClientObjet.Operation;
	import java.io.*;
	import java.net.ServerSocket;
	import java.net.Socket;

public class Serveur {
	
   

	public class Server extends Thread {
	    public static void main(String[] args) {
	        new Serveur().start();
	    }

	    @Override
	    public void run() {
	        System.out.println("Je suis un serveur");
	        try {
	            ServerSocket ss = new ServerSocket(1235);
	            System.out.println("J'attends un client");
	            int index = 1;

	            while (true) {
	                Socket clientSocket = ss.accept();
	                new ClientProcess(clientSocket, index).start();
	                System.out.println("Le client est connecté");
	                index++;
	            }
	        } catch (IOException e) {
	            throw new RuntimeException(e);
	        }
	    }
	}

	class ClientProcess extends Thread {
	    private Socket socket;
	    private int index;

	    public ClientProcess(Socket socket, int index) {
	        this.socket = socket;
	        this.index = index;
	    }

	    @Override
	    public void run() {
	        try {
	            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	            PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);

	            String clientMessage = br.readLine();
	            System.out.println("Message du client " + index + ": " + clientMessage);

	            
	            String response = "Bienvenue, vous êtes le client numéro " + index + ". Votre @IP: " + socket.getRemoteSocketAddress();
	            pw.println(response);

	            OutputStream os = socket.getOutputStream(); 
	            InputStream is = socket.getInputStream(); 
	            ObjectOutputStream oos = new ObjectOutputStream(os);
	            ObjectInputStream ois = new ObjectInputStream(is);

	            ServeurClientObjet.Operation op1 = (Operation) ois.readObject();
	            int nb1 = op1.getNb1();
	            int nb2 = op1.getNb2();
	            String op = op1.getOp();
	            double res = 0;

	           
	            if (op.equals("+")) {
	                res = nb1 + nb2;

	            } else if (op.equals("-")) {
	                res = nb1 - nb2;

	            } else if (op.equals("*")) {
	                res = nb1 * nb2;

	            } else if (op.equals("/")) {
	                if (nb2 != 0) {
	                    res = (double) nb1 / nb2;

	                } else {
	                    System.out.println("Division par zéro impossible.");
	                }
	            }
	            op1.setRes(res);
	            oos.writeObject(op1);

	            socket.close();
	        } catch (IOException e) {
	            throw new RuntimeException(e);
	        } catch (ClassNotFoundException ex) {
	            throw new RuntimeException(ex);
	        }

	    }
	}

	public void start() {
		// TODO Auto-generated method stub
		
	}
}


