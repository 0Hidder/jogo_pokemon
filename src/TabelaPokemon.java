import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class TabelaPokemon {
	
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
		

		for (int i = 0; i<150; i++){
			havePut = false;
			while(havePut != true){
				int randomX = randX.nextInt((max - min) + 1) + min;
				int randomY = randY.nextInt((max - min) + 1) + min;
				if(matriz[randomX][randomY] == "0"){
					this.matriz[randomX][randomY] = listArr[i];
					this.matrizCopy[randomX][randomY] = listArr[i];
					havePut = true;
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
		
		if (this.matriz[coordiX][coordiY] == "0"){
			answer = "NAOACHOU";
		}else{
			answer = "ACHOU " + this.matriz[coordiX][coordiY];
			this.pokeFound.add(this.matriz[coordiX][coordiY]);
			this.matriz[coordiX][coordiY] = "0";
			this.total = this.total - 1;
		}
		
		
		return answer;
	}
	
	public int getTotal(){
		return this.total;
	}
	
}
