package com.superheros.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.superheros.entity.Superhero;
import com.superheros.exception.ResourceNotFoundException;
import com.superheros.repository.ISuperherosRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SuperherosServiceIntegrationTest {

	@Autowired
	private ISuperherosRepository superherosRepository;
	
	@Test
	public void findAllSuperheosTest() {
		List<Superhero> superheros = superherosRepository.findAll();
		
		assertNotNull(superheros);
		assertFalse(superheros.isEmpty());
		assertEquals(10, superheros.size());
	}
	
	@Test
	public void findSuperheroByIdTest() {
		Superhero superhero = superherosRepository.findById(5).get();
		
		assertNotNull(superhero);
		assertEquals("Thor", superhero.getName());
	}
	
	@Test(expected = ResourceNotFoundException.class)
	public void findSuperheroByIdNotFoundTest() {
		superherosRepository.findById(20).orElseThrow(() -> new ResourceNotFoundException(20));
	}
	
	@Test
	public void findSuperherosByNameTest() {
		List<Superhero> superheros = superherosRepository.findByNameContaining("man");
		
		assertFalse(superheros.isEmpty());
		assertEquals(5, superheros.size());
	}
	
	@Test
	public void updateSuperheroTest() {
		Superhero superheroToUpdate = new Superhero(10, "Spiderman");
		superherosRepository.save(superheroToUpdate);
		Superhero actual = superherosRepository.findById(10).get();
		
		assertNotNull(actual);
		assertEquals(superheroToUpdate.getName(), actual.getName());
	}
	
	@Test(expected = ResourceNotFoundException.class)
	public void deleteSuperheroTest() {
		superherosRepository.deleteById(3);
		superherosRepository.findById(3).orElseThrow(() -> new ResourceNotFoundException(3));
	}
}
