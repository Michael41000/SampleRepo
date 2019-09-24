package dev.rollberg.service;

import java.util.ArrayList;
import java.util.List;

import dev.rollberg.db.FakeDB;
import dev.rollberg.model.Pokemon;

public class PokemonService {

	public static Pokemon getPokemon(int id) {
		return FakeDB.team.get(id);
	}
	
	public static void addPokemon(Pokemon p) {
		FakeDB.team.put(p.getId(), p);
	}

	public static List<Pokemon> getAllPokemon() {
		return new ArrayList<Pokemon>(FakeDB.team.values());
	}
	
}
