package com.example.crud.tutorial.servicio;

import java.util.List;

import com.example.crud.tutorial.modelo.Tutorial;

public interface ServicioTutorial {
	public void guardar(Tutorial tutorial);

	public List<Tutorial> obtenerTodosTutoriales();

	public Tutorial obtenerTutorialPorId(Long id);

	public void modificarTutorial(Long id, Tutorial tutorial);

	public void borrarTutorial(Long id);

	public void borrarTutoriales();

	public List<Tutorial> obtenerPorPublicado(Boolean publicado);

	public List<Tutorial> obtenerTutorialesPorTitulo(String titulo);
}
