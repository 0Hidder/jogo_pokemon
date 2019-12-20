import java.util.ArrayList;

public class Jogador {
	
	private String nomePlayer = "";
	private int quantPokemon = 0;
	private ArrayList<String> PokemonsPlayer = new ArrayList<String>();
	
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
