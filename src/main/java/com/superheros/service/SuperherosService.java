package com.superheros.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import com.superheros.entity.Superhero;
import com.superheros.exception.ResourceNotFoundException;
import com.superheros.repository.ISuperherosRepository;

/**
 * Services for superheros management.
 * @author Victor
 *
 */
@Service
public class SuperherosService {

	@Autowired
	private ISuperherosRepository superherosRepository;
	
	/**
	 * Find a list with all the superheroes.
	 * @return List of {@link Superhero}.
	 */
	@Cacheable("superheros")
	public List<Superhero> findAllSuperheos() {
		return superherosRepository.findAll();
	}
	
	/**
	 * Find a superhero by id.
	 * @param id Superhero ID.
	 * @return {@link Superhero}.
	 */
	@Cacheable(value = "superhero", key = "{#id}")
	public Superhero findSuperheroById(Integer id) {
		return superherosRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	/**
	 * Find a list with all the superheroes that contain a value in their name.
	 * @param name Parameter value.
	 * @return List of {@link Superhero}.
	 */
	@Cacheable(value = "superheros")
	public List<Superhero> findSuperherosByName(String name) {
		return superherosRepository.findByNameContaining(name);
	}
	
	/**
	 * Create a new superhero.
	 * @param superhero Superhero data.
	 * @return {@link Superhero} created.
	 */
	@CacheEvict(value = "superheros", allEntries = true)
	public Superhero createSuperhero(Superhero superhero) {
		return superherosRepository.save(new Superhero(superhero.getId(), superhero.getName()));
	}
	
	/**
	 * Update a superhero by id.
	 * @param id Superhero ID.
	 * @param superhero Superhero data.
	 * @return {@link Superhero} updated.
	 */
	@Caching(evict = {
			@CacheEvict(value = "superheros", allEntries = true),
			@CacheEvict(value = "superhero", key = "{#id}")})
	public Superhero updateSuperhero(Integer id, Superhero superhero) {
		Superhero superheroToUpdate = superherosRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
		superheroToUpdate.setName(superhero.getName());
		return superherosRepository.save(superheroToUpdate);
	}
	
	/**
	 * Delete a superhero by id.
	 * @param id Superhero ID.
	 * @return
	 */
	@Caching(evict = {
			@CacheEvict(value = "superheros", allEntries = true),
			@CacheEvict(value = "superhero", key = "{#id}")})
	public void deleteSuperhero(Integer id) {
		superherosRepository.deleteById(id);
	}
}
