import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static final int PORT = 8765;
    public static void main(String[] args) throws IOException {
        new Server().runServer();
    }
    
    public void runServer() throws IOException{
    	TabelaPokemon tabela = new TabelaPokemon();
	   	tabela.initializeMatriz();
	   	tabela.putPokeIntoMatriz();
        ServerSocket server = new ServerSocket(PORT);
        System.out.println("Server up, waiting for connections...");
        while(true){
            Socket socket = server.accept();
            new ServerThread(socket,tabela).start();
        }
       
    }
    
}
