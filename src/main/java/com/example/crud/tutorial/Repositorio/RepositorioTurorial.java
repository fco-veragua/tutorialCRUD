package com.example.crud.tutorial.Repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.crud.tutorial.modelo.Tutorial;

// @Repository
public interface RepositorioTurorial extends JpaRepository<Tutorial, Long> {
	List<Tutorial> findByPublished(boolean published);

	List<Tutorial> findByTitleContaining(String title);
}
