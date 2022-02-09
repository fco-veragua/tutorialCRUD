package com.example.crud.tutorial.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.crud.tutorial.Repositorio.RepositorioTurorial;
import com.example.crud.tutorial.modelo.Tutorial;

@Service
public class ServicioTutorialImpl implements ServicioTutorial {

	// private RepositorioTutorial repositorioTutorial;
	private RepositorioTurorial repositorioTutorial;

	@Autowired
	public ServicioTutorialImpl(RepositorioTurorial repositorioTutorial) {
		this.repositorioTutorial = repositorioTutorial;
	}

	@Override
	public void guardar(Tutorial tutorial) {
		Tutorial tutorialAGuardar = new Tutorial(tutorial.getTitle(), tutorial.getDescription(),
				tutorial.getPublished());
		this.repositorioTutorial.saveAndFlush(tutorialAGuardar);
	}

	@Override
	public List<Tutorial> obtenerTodosTutoriales() {
		return this.repositorioTutorial.findAll();
	}

	@Override
	public Tutorial obtenerTutorialPorId(Long id) {
		return this.repositorioTutorial.findById(id).orElse(null);
	}

	@Override
	public void modificarTutorial(Long id, Tutorial tutorialNuevo) {

		Tutorial tutorialViejo = this.repositorioTutorial.getById(id);
		tutorialViejo.setTitle(tutorialNuevo.getTitle());
		this.repositorioTutorial.saveAndFlush(tutorialViejo);
	}

	@Override
	public void borrarTutorial(Long id) {
		this.repositorioTutorial.deleteById(id);
	}

	@Override
	public void borrarTutoriales() {
		this.repositorioTutorial.deleteAll();
	}

	@Override
	public List<Tutorial> obtenerPorPublicado(Boolean publicado) {
		return this.repositorioTutorial.findByPublished(false);
	}

	@Override
	public List<Tutorial> obtenerTutorialesPorTitulo(String titulo) {
		return this.repositorioTutorial.findByTitleContaining(titulo);
	}

}
