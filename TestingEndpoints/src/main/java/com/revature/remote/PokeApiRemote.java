package com.revature.remote;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.revature.models.PokeApiPokemonResponse;

@Component
public class PokeApiRemote {

	private final RestTemplate rt;
	
	public PokeApiRemote(RestTemplateBuilder restTemplateBuilder) {
		this.rt = restTemplateBuilder.build();
	}
	
	
	//get a single pokemon
	public PokeApiPokemonResponse getPokemonById(String name) throws Exception {
		String url = "https://pokeapi.co/api/v2/pokemon/" + name;
		//to get the data from the api
		ResponseEntity<PokeApiPokemonResponse> response = rt.getForEntity(url, PokeApiPokemonResponse.class);
		//you might have to some to do some data parsing on your own
		
		//you should check status code and either return or throw an exception as appropriate
		if(response.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
			throw new PokemonNotFoundException("Name not found");//specific case we are ready for
		} else if(response.getStatusCode().equals(HttpStatus.OK)){
			return response.getBody();//our success case
		} else {
			throw new Exception("Something Went Wrong");//anything else went wrong
		}
	}
	
}
