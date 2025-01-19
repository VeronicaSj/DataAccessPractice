package com.example.selfWeb;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.selfWeb.Model.Post;
import com.example.selfWeb.Repository.BlogPostRepository;

@SpringBootApplication
public class SelfWebApplication implements  CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SelfWebApplication.class, args);
	}

	@Autowired
	BlogPostRepository postRepo;

	@Override
	public void run(String... args) throws Exception {
		postRepo.save(new Post(1, "prueba post 1", 
				"oy Estudiante de Formación Profesional de " +
				"Desarrollo de Aplicaciones Multiplataforma" +
				" con experiencia laboral en Administracion" +
				" de Sistemas. Actualmente me encuentro en " +
				"búsqueda activa de empleo. Formación Profes Formación Profes Formación Profes ",
				new Date()));
		postRepo.save(new Post(2, "prueba Estudiante de Formación Profesionalpost 1", 
				"oy Estudiante de Formación Profesional de " +
				"Desarrollo de Aplicaciones Multiplataforma" +
				" con experiencia laboral en Administracion" +
				" de Sistemas. Actualmente me encuentro en " +
				"búsqueda activa de empleo.",
				new Date()));
		postRepo.save(new Post(3, "prueba post 1", 
				"oy Estudiante de Formación Profesional de " +
				"Desarrollo de Aplicaciones Multiplataforma" +
				" con experiencia laboral en Administracion" +
				" de Sistemas. Actualmente me encuentro en " +
				"búsqueda activa de empleo.",
				null));
				postRepo.save(new Post(4, "prueba post 1", 
				"oy Estudiante de Formación Profesional de " +
				"Desarrollo de Apli",
				new Date()));
				postRepo.save(new Post(5, "prueEstsionalba post 1", 
				"oy Estudiante de Formación Profesional de " +
				"Desarrollo de Aplicaciones Multiplataforma" +
				" con experiencia laboral en Administracion" +
				" de Sistemas. Actualmente me encuentro en " +
				"búsqueda activa de empleo.",
				null));
				postRepo.save(new Post(6, "prueba post 1 Estudiante de Formación Profesional", 
				"oy Estudiante de Formación Profesional de " +
				"Desarrollo de Aplicaciones Multiplataforma" +
				" con experiencia laboral en Administracion" +
				" de Sistemas. Actualmente me encuentro err" +
				"ollo de Aplicaciones Multiplatrrollo de Ap" +
				"Desarrollo de Aplicaciones Multiplataforma" +
				" con experiencia laboral en Administracion" +
				"Desarrollo de Aplicaciones Multiplataforma" +
				" con experiencia laboral en Administracion" +
				" de Sistemas. Actualmente me encuentro err" +
				"ollo de Aplicaciones Multiplatrrollo de Ap" +
				" de Sistemas. Actualmente me encuentro err" +
				"ollo de Aplicaciones Multiplatrrollo de Ap" +
				"licaciones Multiplatrrollo de Aplicaciones M"+ 
				"ultiplatrrollo de Aplicaciones Multiplatrr" +
				"ollo de Aplicaciones Multiplatrrollo de Ap" +
				"Desarrollo de Aplicacio\nnes Multiplataforma" +
				" con experiencia laboral en Administracion" +
				" de Sistemas. Actualmente me encuentro err" +
				"ollo de Aplicaciones Multiplatrrollo de Ap" +
				"Desarrollo de Aplicaciones Multiplataforma" +
				" con experiencia laboral en Administracion" +
				"Desarrollo de Aplicaciones Multiplataforma" +
				" con experiencia laboral en Administracion" +
				" de Sistemas. Actualmente me encuentro err" +
				"ollo de Aplicaciones Mult\niplatrrollo de Ap" +
				"búsqueda activa de empleo.",
				new Date()));
	}


}
