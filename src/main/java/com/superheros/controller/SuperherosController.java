package com.superheros.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.superheros.common.ExecutionTime;
import com.superheros.entity.Superhero;
import com.superheros.service.SuperherosService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Rest controller for superheros management.
 * @author Victor
 *
 */
@RestController
@RequestMapping("/superheros-management")
@Tag(name = "Superheros API", description = "Superheros management")
public class SuperherosController {

	@Autowired
	private SuperherosService superherosService;
	
	/**
	 * Find a list with all the superheroes.
	 * @return List of {@link Superhero}.
	 */
	@GetMapping("/superheros")
	@Operation(summary = "Find a list with all the superheroes")
	@ExecutionTime
	public ResponseEntity<List<Superhero>> findAllSuperheos() {
		return ResponseEntity.ok(superherosService.findAllSuperheos());
	}
	
	/**
	 * Find a superhero by id.
	 * @param id Superhero ID.
	 * @return {@link Superhero}.
	 */
	@GetMapping("/superheros/{id}")
	@Operation(summary = "Find a superhero by id")
	@ExecutionTime
	public ResponseEntity<Superhero> findSuperheroById(@PathVariable("id") Integer id) {
		return ResponseEntity.ok(superherosService.findSuperheroById(id));
	}
	
	/**
	 * Find a list with all the superheroes that contain a value in their name.
	 * @param name Parameter value.
	 * @return List of {@link Superhero}.
	 */
	@GetMapping("/superherosByName/{name}")
	@Operation(summary = "Find a list with all the superheroes that contain a value in their name")
	@ExecutionTime
	public ResponseEntity<List<Superhero>> findSuperherosByName(@PathVariable("name") String name) {
		return ResponseEntity.ok(superherosService.findSuperherosByName(name));
	}
	
	/**
	 * Create a new superhero.
	 * @param superhero Superhero data.
	 * @return {@link Superhero} created.
	 */
	@PostMapping(path = "/superheros", consumes = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Create a new superhero")
	@ExecutionTime
	public ResponseEntity<Superhero> createSuperhero(@RequestBody Superhero superhero) {
		return ResponseEntity.ok(superherosService.createSuperhero(superhero));
	}
	
	/**
	 * Update a superhero by id.
	 * @param id Superhero ID.
	 * @param superhero Superhero data.
	 * @return {@link Superhero} updated.
	 */
	@PutMapping(path = "/superheros/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Update a superhero by id")
	@ExecutionTime
	public ResponseEntity<Superhero> updateSuperhero(@PathVariable("id") Integer id, @RequestBody Superhero superhero) {
		return ResponseEntity.ok(superherosService.updateSuperhero(id, superhero));
	}
	
	/**
	 * Delete a superhero by id.
	 * @param id Superhero ID.
	 * @return
	 */
	@DeleteMapping("/superheros/{id}")
	@Operation(summary = "Delete a superhero by id")
	@ExecutionTime
	public ResponseEntity<HttpStatus> deleteSuperhero(@PathVariable("id") Integer id) {
		superherosService.deleteSuperhero(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
}
