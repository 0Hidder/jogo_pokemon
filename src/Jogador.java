import java.util.ArrayList;

public class Jogador {
	
	private String nomePlayer = "";										  // Nome do jogador
	private int quantPokemon = 0;   									 // Quantidade de Pokemons que o jogador possui
	private ArrayList<String> PokemonsPlayer = new ArrayList<String>(); // Array com os nomes dos pokemons que o jogador possui
	
	public void setNewPokemon(String nomepoke) {
		this.PokemonsPlayer.add(nomepoke);
	}
	
	public void setName(String name){
		this.nomePlayer = name;
	}
	
	public void setPokemonsPlayerCaught(ArrayList<String> pokemonsPlayer){
		this.PokemonsPlayer = pokemonsPlayer;
	}
	
	public ArrayList<String> getPokemonPlayerHas(){
		return PokemonsPlayer;
	}
}
