package com.firma.controller;

import java.util.List;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

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
	
	@GetMapping(value = "/home")
	public String inicioSesion(Model model, String username, String password) {
		List<Usuario> usuarios = this.usuarioService.findAll();
		for (int i = 0; i < usuarios.size(); i++) {
			if (usuarios.get(i).getUsername().equals(username) && usuarios.get(i).getContrasenia().equals(password)) {
				model.addAttribute("titulo", "Estas dentro, "+ username);
				model.addAttribute("usuario", usuarios.get(i));
				return "home";
			}
		}
		return "redirect:/";
	}
	
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
		
		//hacer que funcione el flash
		if (!usuario.getContrasenia().equals(usuario.getConfirmContrasenia())) {
			flash.addFlashAttribute("error", "Las contraseñas no coinciden");
			return "redirect:/registrar";
		}
		
		// verificar que el correo exista (ni idea demo  cohacer eso xd)
		
		this.usuarioService.save(usuario);
		status.setComplete();
		flash.addFlashAttribute("success", "Usuario Creado");
		return "redirect:/";
	}
	
	@GetMapping(value = "restablecerContrasenia")
	public String restablecerContraseni() {
		return "restablecerContraseniaForm";
	}

}
