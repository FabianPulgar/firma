package com.firma.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.firma.modelo.Usuario;

@Service
public interface UsuarioService {
	List<Usuario> findAll();
	Usuario save (Usuario usuario);
	void deleteById(Long id);
	Usuario findById(Long id);
}
