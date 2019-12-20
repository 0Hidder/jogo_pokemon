import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class TabelaPokemon { 													// Tabela contendo os pokemons em jogo
	
	public String[][] matriz = new String[100][100];
	public String[][] matrizCopy = new String[100][100];
	public ArrayList<String> pokeFound = new ArrayList<String>();
	public int total = 150;
	
	public void initializeMatriz(){
		for (int i = 0; i <100; i++){
			for (int j = 0; j<100; j++){
				matriz[i][j] = "0";
				matrizCopy[i][j] = "0";
			}
		}
	}
	
	public String[][] getMatriz(){
		return matrizCopy;
	}
	
	public String[][] getMatrizGame(){
		return matriz;
	}
	
	public ArrayList<String> getPokemonPlayerHas(){
		return pokeFound;
	}

	public void putPokeIntoMatriz() throws FileNotFoundException{
		int linesCount = 0;
		Scanner s = new Scanner(new File("pokemons.txt"));
		ArrayList<String> list = new ArrayList<String>();
		while (s.hasNextLine()){
		    list.add(s.nextLine());
		    linesCount += 1;
		}
		String[] listArr = new String[list.size()];
		listArr = list.toArray(listArr);
		int max = 99;
		int min = 0;
		
		Random randX = new Random();
		Random randY = new Random();
		
		boolean havePut = false;
		

		for (int i = 0; i<150; i++){									// Realiza o loop 150 vezes (número de pokemons disponíveis)
			havePut = false;										
			while(havePut != true){
				int randomX = randX.nextInt((max - min) + 1) + min;		// Gera valores aleatórios para X e Y
				int randomY = randY.nextInt((max - min) + 1) + min;
				if(matriz[randomX][randomY] == "0"){					// Se aquele local estiver com um "0", pode colocar um pokemon ali
					this.matriz[randomX][randomY] = listArr[i];
					this.matrizCopy[randomX][randomY] = listArr[i];
					havePut = true;										// Atualiza que colocou um pokemon e segue para o próximo no loop
		//			System.out.println(matriz[randomX][randomY]);
				}
			}	
		}
		
		s.close();
		
	}
	
	public void printMatriz(){
		for (int o = 0; o < 100; o++){
			for (int m = 0; m < 100; m++){
				if (this.matriz[o][m] != "0" ){
				System.out.println(this.matriz[o][m]); }
			}
		}
		return;
	}
	
	public void printAllPokeOnServer(){
		for (int a = 0; a < 100; a++){
			for (int s = 0; s < 100; s++){
				if (this.matriz[a][s] != "0" ){
				System.out.println(this.matrizCopy[a][s]); }
			}
		}
		return;
	}
	
	public String searchPoke(int coordX, int coordY){
		
		String answer = "";
	
		int coordiX = coordX;
		int coordiY = coordY;
		
		if (this.matriz[coordiX][coordiY] == "0"){					// Caso aquele local possua um "0" o jogador não achou um pokemon
			answer = "NAOACHOU";
		}else{
			answer = "ACHOU " + this.matriz[coordiX][coordiY];		// Caso contrário ele achou
			this.pokeFound.add(this.matriz[coordiX][coordiY]);		// O valor é substituído por "0", pois ali não existe mais um pokemon
			this.matriz[coordiX][coordiY] = "0";
			this.total = this.total - 1;							// O total de pokemons em jogo agora é menor
		}
		
		
		return answer;												// Devolve a resposta
	}
	
	public int getTotal(){
		return this.total;
	}
	
}
