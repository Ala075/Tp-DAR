package ServeurClientObjet;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Serveur {
	

		    public static void main(String[] args) {
		        System.out.println("Je suis un serveur");

		        try (ServerSocket serverSocket = new ServerSocket(1234)) {
		            System.out.println("J'attends un client");

		            Socket clientSocket = serverSocket.accept();
		            System.out.println("Un client est connecté");

		            try (OutputStream os = clientSocket.getOutputStream(); 
		                 InputStream is = clientSocket.getInputStream()) { 
		                ObjectOutputStream oos = new ObjectOutputStream(os);
		                ObjectInputStream ois = new ObjectInputStream(is);

		              Operation op1 = (Operation) ois.readObject();
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
		            } catch (ClassNotFoundException e) {
		                e.printStackTrace();
		            } catch (IOException e) {
		                throw new RuntimeException(e);
		            }
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		    }

}
