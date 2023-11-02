package Tp3;
import ServeurClientObjet.Operation;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

	public class Client {
	    public static void main(String[] args) {
	        try {
	            Socket socket = new Socket("localhost", 1235);
	            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	            PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
	            OutputStream os = socket.getOutputStream();

	            InputStream is = socket.getInputStream();
	           
	            pw.println("Bonjour je suis un client dans ce serveur");

	            String serverResponse = br.readLine();
	            System.out.println(serverResponse);
	            int nb1;
	            int nb2;
	            String op;
	            double res = 0;

	            
	            Scanner scanner = new Scanner(System.in);
	            System.out.print("Donnez un nombre1 : ");
	            nb1 = scanner.nextInt();
	            System.out.print("Donnez un nombre2 : ");
	            nb2 = scanner.nextInt();
	            scanner.nextLine();
	            System.out.print("Donnez un opérateur (+, -, *, /) : ");
	            op = scanner.nextLine();
	           ServeurClientObjet.Operation op1 = new Operation(nb1, nb2, op);
	            ObjectOutputStream oos = new ObjectOutputStream(os);
	            ObjectInputStream ois = new ObjectInputStream(is);
	            oos.writeObject(op1);
	            Operation op2= (Operation) ois.readObject();
	            System.out.println("Résultat : " + op2.getRes());

	            socket.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } catch (ClassNotFoundException e) {
	            throw new RuntimeException(e);
	        }
	    }


}


