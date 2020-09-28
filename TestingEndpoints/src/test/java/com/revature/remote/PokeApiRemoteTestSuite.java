package com.revature.remote;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.revature.models.PokeApiPokemonResponse;


public class PokeApiRemoteTestSuite {
	
	
	private RestTemplate rtMock = mock(RestTemplate.class);

	private RestTemplateBuilder rtBMock = mock(RestTemplateBuilder.class);
	
	private PokeApiRemote pkr;
	
	
	@BeforeEach
	public void initTests() {
		when(rtBMock.build()).thenReturn(rtMock);
		this.pkr = new PokeApiRemote(rtBMock);
	}
	
	@Test
	public void getPokemonSuccessfully() throws Exception{
		when(rtMock.getForEntity("https://pokeapi.co/api/v2/pokemon/pikachu", PokeApiPokemonResponse.class))
			.thenReturn(new ResponseEntity<PokeApiPokemonResponse>(new PokeApiPokemonResponse(), HttpStatus.OK));
		
		assertThat(pkr.getPokemonById("pikachu"), isA(PokeApiPokemonResponse.class));
	}
	
	@Test()
	public void pokemonNotFound() {
		Exception exception = assertThrows(PokemonNotFoundException.class, ()->{
			when(rtMock.getForEntity("https://pokeapi.co/api/v2/pokemon/-1", PokeApiPokemonResponse.class))
				.thenReturn(new ResponseEntity<PokeApiPokemonResponse>(new PokeApiPokemonResponse(), HttpStatus.NOT_FOUND));//this means pokeapi is down
			pkr.getPokemonById("-1");//throw generic exception
		});
		System.out.println(exception);
		String expectedMessage = "Name not found";
		assertThat(exception.getMessage(), is(equalTo(expectedMessage)));
	}
	
	
	@Test
	public void UnhandledError() {
		Exception exception = assertThrows(Exception.class, ()->{
			when(rtMock.getForEntity("https://pokeapi.co/api/v2/pokemon/pikachu", PokeApiPokemonResponse.class))
				.thenReturn(new ResponseEntity<PokeApiPokemonResponse>(new PokeApiPokemonResponse(), HttpStatus.INTERNAL_SERVER_ERROR));//this means pokeapi is down
			pkr.getPokemonById("pikachu");//throw generic exception
		});
		System.out.println(exception);
		String expectedMessage = "Something Went Wrong";
		assertThat(exception.getMessage(), is(equalTo(expectedMessage)));
				
	}
	

}
