package dev.rollberg.webservice;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import dev.rollberg.model.Pokemon;
import dev.rollberg.service.PokemonService;

public class PokemonWebService {
	
	private static ObjectMapper om = new ObjectMapper();

	public static void getPokemon(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));
		System.out.println(id);
		Pokemon p = PokemonService.getPokemon(id);
		
		//some code/ some logic;
		
		ObjectMapper om = new ObjectMapper();
		
		try {
			String pokemonJSON = om.writeValueAsString(p);
			response.getWriter().append(pokemonJSON);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void addPokemon(HttpServletRequest request, HttpServletResponse response) throws JsonParseException, JsonMappingException, IOException {
//		int id = Integer.parseInt(request.getParameter("id"));
//		String name = request.getParameter("name");
//		String type = request.getParameter("type");
//		System.out.println(id + " " + name + " " + type);
//		
//		Pokemon p = new Pokemon(id, name, type);
		Pokemon p = (Pokemon) om.readValue(request.getInputStream(), Pokemon.class);
		PokemonService.addPokemon(p);
		
		try {
			response.getWriter().append(om.writeValueAsString(p));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void getAllPokemon(HttpServletRequest request, HttpServletResponse response) {
		List<Pokemon> polkamans = PokemonService.getAllPokemon();
		
		try {
			response.getWriter().append(om.writeValueAsString(polkamans));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
