package com.superheros.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.superheros.entity.Superhero;

/**
 * Repository for superheros management.
 * @author Victor
 *
 */
public interface ISuperherosRepository extends JpaRepository<Superhero, Integer> {

	/**
	 * Find a list with all the superheroes that contain a value in their name.
	 * @param name Parameter value.
	 * @return List of {@link Superhero}.
	 */
	List<Superhero> findByNameContaining(String name);
}
