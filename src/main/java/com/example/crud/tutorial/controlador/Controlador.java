package com.example.crud.tutorial.controlador;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.crud.tutorial.modelo.Tutorial;
import com.example.crud.tutorial.servicio.ServicioTutorial;

@RestController
@RequestMapping("/api")

public class Controlador {
	@Autowired
	private ServicioTutorial servicioTutorial;

	@GetMapping("/tutorials")
	public ResponseEntity<List<Tutorial>> getAllTutorials(@RequestParam(required = false) String title) {
		try {
			List<Tutorial> tutorials = new ArrayList<Tutorial>();
			if (title == null)
				servicioTutorial.obtenerTodosTutoriales().forEach(tutorials::add);
			else
				servicioTutorial.obtenerTutorialesPorTitulo(title).forEach(tutorials::add);
			if (tutorials.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(tutorials, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/tutorials/{id}")
	public ResponseEntity<Tutorial> getTutorialById(@PathVariable("id") long id) {
		Tutorial tutorialData = servicioTutorial.obtenerTutorialPorId(id);
		if (tutorialData != null) {
			return new ResponseEntity<>(tutorialData, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/tutorials")
	public ResponseEntity<?> guardaUsuario(@RequestBody Tutorial tutorial) {
		Map<Integer, Object> errores = new HashMap<Integer, Object>();
		HttpStatus estado = HttpStatus.OK;

		try {
			this.servicioTutorial.guardar(tutorial);
		} catch (Exception e) {
			estado = HttpStatus.PRECONDITION_FAILED;
			// errores.put(0, e.getErrores());
		}

		return new ResponseEntity<>(errores, estado);
	}

}