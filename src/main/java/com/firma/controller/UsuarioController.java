package com.firma.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.firma.modelo.Usuario;
import com.firma.service.UsuarioService;

@Controller
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@RequestMapping("/perfil")
	public String perfil(Model model) {
		Usuario usuario = new Usuario();
		usuario.setNombre("andres");
		usuario.setApellido("martinez");
		usuario.setEmail("andrea@gmail.com");
		
		model.addAttribute("usuario", usuario);
		model.addAttribute("titulo", "Perfil del Usuario: ".concat(usuario.getNombre()));
		return "perfil";
	}
	
	@RequestMapping("/listar")
	private String listar (Model model) {
		List<Usuario> usuarios = new ArrayList<>();
		usuarios.add(new Usuario("antonio","aja","adsa@live.cl"));
		model.addAttribute("titulo","lista de usuarios");
		model.addAttribute("usuarios", usuarios);
		
		return "listar";
	}
	
	
	
	
}
