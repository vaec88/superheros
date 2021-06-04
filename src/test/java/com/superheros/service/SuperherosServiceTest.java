package com.superheros.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.superheros.entity.Superhero;
import com.superheros.repository.ISuperherosRepository;

@RunWith(MockitoJUnitRunner.class)
public class SuperherosServiceTest {

	@InjectMocks
	private SuperherosService superherosService;
	
	@Mock
	private ISuperherosRepository superherosRepository;
	
	private List<Superhero> superheros = new ArrayList<>();
	
	@Before
	public void setup() {
		MockitoAnnotations.openMocks(this);
		superheros.add(new Superhero(1, "Batman"));
		superheros.add(new Superhero(2, "Hulk"));
		superheros.add(new Superhero(3, "Superman"));
		superheros.add(new Superhero(4, "Thor"));
		superheros.add(new Superhero(5, "Spiderman"));
	}
	
	@Test
	public void findAllSuperheosTest() {
		// Arrange
		when(superherosRepository.findAll()).thenReturn(superheros);

		// Act
		superherosService.findAllSuperheos();
		
		// Assert
		assertNotNull(superheros);
		assertFalse(superheros.isEmpty());
		assertEquals(5, superheros.size());
		verify(superherosRepository, times(1)).findAll();
	}
	
	@Test
	public void findSuperheroByIdTest() {
		// Arrange
		Superhero expected = superheros.get(2);
		when(superherosRepository.findById(3)).thenReturn(Optional.of(expected));
		
		// Act
		Superhero actual = superherosService.findSuperheroById(3);
		
		// Assert
		assertNotNull(actual);
		assertEquals(expected.getId(), actual.getId());
		verify(superherosRepository, times(1)).findById(3);
	}
	
	@Test
	public void findSuperherosByNameTest() {
		// Arrange
		String param = "man";
		List<Superhero> expected = superheros.stream().filter(superhero -> superhero.getName().contains(param)).collect(Collectors.toList());
		when(superherosRepository.findByNameContaining(param)).thenReturn(expected);
		
		// Act
		List<Superhero> actual = superherosService.findSuperherosByName(param);
		
		// Assert
		assertNotNull(actual);
		assertEquals(3, actual.size());
		assertEquals(expected, actual);
		verify(superherosRepository, times(1)).findByNameContaining(param);
	}
	
	@Test
	public void updateSuperheroTest() {
		// Arrange
		Superhero superhero = new Superhero(5, "Wolverine");
		when(superherosRepository.findById(5)).thenReturn(Optional.of(superheros.get(4)));
		when(superherosRepository.save(superhero)).thenReturn(superhero);
		
		// Act
		superherosService.updateSuperhero(superhero.getId(), superhero);
		
		// Assert
		verify(superherosRepository, times(1)).findById(5);
		verify(superherosRepository, times(1)).save(ArgumentMatchers.refEq(superhero) );
	}
	
	@Test
	public void deleteSuperheroTest() {
		// Act
		superherosService.deleteSuperhero(2);
		
		// Assert
		verify(superherosRepository, times(1)).deleteById(2);
	}
}
