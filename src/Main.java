import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.*;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class Main {

	public static void main(String[] args) throws UnknownHostException, IOException {
		String op;
		int cont = 0;
		int cont2 = 0;
		int PORT;
		String ip;
		Scanner teclado = new Scanner(System.in);
		TabelaPokemon tabela = new TabelaPokemon();
		Jogador player = new Jogador();
		tabela.initializeMatriz();
		tabela.putPokeIntoMatriz();
		System.out.println("Please type the ip: ");
	    ip = teclado.nextLine();
	    System.out.println("Please type the Port number: ");
	    PORT = teclado.nextInt();
    	Socket socket = new Socket(ip, PORT);
    	BufferedReader brFromClient = new BufferedReader (new InputStreamReader(socket.getInputStream()));
    	PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	Scanner entrada = new Scanner(socket.getInputStream());
    	ArrayList<String> pokemonsPlayer = new ArrayList<String>();
		System.out.print("Please type your name: ");
    	String namePlayer = teclado.nextLine();
    	player.setName(namePlayer);
    	boolean running = true;
    	int totalNum = 150;
    	
    	do {
    		Main.clearScreen();
    		Main.Menu();
    		System.out.print("Chose one option: ");
    		op = br.readLine();
    		
    		switch (op){
    		case "1":
    			System.out.println("You chose the first option...");
    			System.out.println("The following are all the pokemon on the server at a random order...");
    			pw.println("LISTATODOSPOKEMONS");
    			while (entrada.hasNextLine()) {
					System.out.println(entrada.nextLine());
					cont += 1;

					if (cont == totalNum){
						cont = 0;
						break;
						
					}
				}
    			break;
				
    		case "2":
    			
    			System.out.println("You chose the seccond option...");
    			System.out.println("The following are the pokemon that are still available...");
    			pw.println("LISTAPOKEMONSDISPONIVEIS");
    			while (entrada.hasNextLine()) {
					System.out.println(entrada.nextLine());
					cont2 += 1;
					if (cont2 == 150){
						cont2 = 0;
						break;
						
					}
				
    		}
    		break;
    		
    		case "3":
    			System.out.println("You chose the third option...");
    			System.out.println("Please type the X coordinates: ");
    			int coordX = teclado.nextInt();
    			System.out.println("Please type the Y coordinates: ");
    			int coordY = teclado.nextInt();
    			String message = "PROCURARPOKEMON"+" "+coordX+" "+coordY;
    			System.out.println(message);
    			pw.println(message);
    			System.out.println("Searching for pokemon at that coordinates...");
    			System.out.println(brFromClient.readLine());
    			String answerFromServer = brFromClient.readLine();
    			if (answerFromServer.startsWith("ACHOU")){
    				String partsAnswer[] = answerFromServer.split(" ");
    				pokemonsPlayer.add(partsAnswer[1]);
    			}
    			String total = brFromClient.readLine();
    			totalNum = Integer.parseInt(total);
    			player.setPokemonsPlayerCaught(pokemonsPlayer);
    			break;
    		
    		case "4":
    			ArrayList<String> pokemonsPlayerFromClass = new ArrayList<String>();
    			pokemonsPlayerFromClass = player.getPokemonPlayerHas();
    			System.out.println("You chose the fourth option...");
    			System.out.println("The following are all the pokemon you own...");
    			int sizePokes = pokemonsPlayerFromClass.size();
    			for (int k = 0; k<sizePokes; k++){
    				System.out.println(pokemonsPlayerFromClass.get(k));
    			}
    			break;
    			
    		case "5":
    			pw.println(namePlayer + " Has disconected");
    			running = false;
    			break;
    	}
    	

	}while(running == true);
    	System.out.println("Connection Terminated... Thanks for playing");
    	teclado.close();
    	socket.close();
    	entrada.close();
}
	
	public static void Menu(){
		
		System.out.println("   ===   MENU - Find the pokemon   ===");
		System.out.println("      Select one of the following options.");
		System.out.println("      1 - List all pokemon on the server.");
		System.out.println("      2 - List all pokemon available.");
		System.out.println("      3 - Search for pokemon.");
		System.out.println("      4 - List all your pokemon");
		System.out.println("      5 - QUIT!");
	}
	
	public static void clearScreen(){
		for (int p = 0; p<3; p++){
			System.out.println();
		}
	}
	

}
