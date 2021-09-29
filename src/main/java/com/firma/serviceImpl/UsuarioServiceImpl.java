package com.firma.serviceImpl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.firma.DAO.UsuarioDAO;
import com.firma.modelo.Usuario;
import com.firma.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioDAO usuarioDAO;
	
	@Override
	@Transactional(readOnly = true)
	public Usuario findById(Long id) {
		return this.usuarioDAO.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Usuario> findAll() {
		return this.usuarioDAO.findByOrderById();
	}

	@Override
	@Transactional
	public Usuario save(Usuario usuarioModelo) {
		return this.usuarioDAO.save(usuarioModelo);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		this.usuarioDAO.deleteById(id);
	}
}
