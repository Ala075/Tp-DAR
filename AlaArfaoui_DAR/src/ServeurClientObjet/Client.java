package ServeurClientObjet;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

	public class Client {
		

		    public static void main(String[] args) {
		        try {
		            System.out.println("Je suis un client");

		            Socket s = new Socket("localhost", 1234);
		            System.out.println("Je suis connecté au serveur");

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

		            Operation op1 = new Operation(nb1, nb2, op);

		            OutputStream os = s.getOutputStream();

		            InputStream is = s.getInputStream();

		            ObjectOutputStream oos = new ObjectOutputStream(os);
		            ObjectInputStream ois = new ObjectInputStream(is);

		            oos.writeObject(op1);

		            Operation op2= (Operation) ois.readObject();

		            System.out.println("Résultat : " + op2.getRes());

		            s.close();

		        } catch (IOException e) {
		            e.printStackTrace();
		        } catch (ClassNotFoundException e) {
		            e.printStackTrace();
		        }
		    }
		
	}

