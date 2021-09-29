package com.firma.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.firma.modelo.Usuario;
import com.firma.service.UsuarioService;

@Controller
public class HomeController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping(value = {"", "/"})
	public String inicio() {
		return "inicio";
	}
	
	//falta hacer el handler de home para que podamos con ese tomar los datos del usuario que quiere ingresar
	
	@GetMapping(value="/registrar")
	public String registrar(Model model) {
		model.addAttribute("titulo", "A registrarse de ha dicho");
		Usuario usuario = new Usuario();
		model.addAttribute("usuario", usuario);
		return "registrar";
	}
	
	@PostMapping(value="/registrarUsuario")
	public String registrarUsuario(@ModelAttribute Usuario usuario, BindingResult result, 
			Model model, RedirectAttributes flash, SessionStatus status) {
		
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Agregar Usuario");
			return "redirect:/registrar";
		}
		
		// acá tenemos que ver que ambas contraseñas sean iguales, que no exista un username igual 
		// y que el correo exista (ni idea de como hacer eso xd)
		
		this.usuarioService.save(usuario);
		System.out.println("Acá estamos");
		status.setComplete();
		flash.addFlashAttribute("success", "Usuario Creado");
		return "redirect:/";
	}
	
	@GetMapping(value = "restablecerContrasenia")
	public String restablecerContrasenia() {
		return "restablecerContraseniaForm";
	}
}
