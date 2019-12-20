import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerThread extends Thread {
	
	String[][] matriz = new String[100][100];
    Socket socket;
    PrintWriter pw;
   	TabelaPokemon tabela;
   	int total;
    ServerThread(Socket socket, TabelaPokemon tabelaS) throws IOException{
        this.socket = socket;
        this.pw = new PrintWriter(socket.getOutputStream(), true);
        this.tabela = tabelaS;
    }
    
    
    @Override
    public void run(){
        try {

            String message = null;
            ArrayList<String> pokemonsPlayer = new ArrayList<String>();
            
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while ((message = br.readLine()) != null){
                System.out.println("Message from Client: " +message);
                
                if(message.contains("LISTATODOSPOKEMONS")){
                	matriz = tabela.getMatriz();
                	this.printMatriz();
                }
                if(message.contains("LISTAPOKEMONSDISPONIVEIS")){
                	matriz = tabela.getMatrizGame();
                	this.printMatrizGame();
                }
                if (message.contains("PROCURARPOKEMON")){
                	String[] parts = message.split(" ");
                	int coordX = Integer.parseInt(parts[1]);
                	int coordY = Integer.parseInt(parts[2]);
                	coordX = coordX - 1;
                	coordY = coordY - 1;
                	matriz = tabela.getMatrizGame();
                    String resposta = tabela.searchPoke(coordX, coordY);
                    pw.println(resposta);
                    pw.println(resposta);
                    pokemonsPlayer = tabela.getPokemonPlayerHas();
                    this.total = tabela.getTotal();
                    pw.println(this.total);
                }
                
            }
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
	public void printMatriz(){
		for (int o = 0; o < 100; o++){
			for (int m = 0; m < 100; m++){
				if (this.matriz[o][m] != "0" ){
				pw.println(this.matriz[o][m]); }
			}
			pw.flush();
		}
		return;
	}
	public void printMatrizGame(){
		for (int o = 0; o < 100; o++){
			for (int m = 0; m < 100; m++){
				if (this.matriz[o][m] != "0" ){
				pw.println(this.matriz[o][m]); }
			}
			pw.flush();
		}
		return;
	}

    
}
