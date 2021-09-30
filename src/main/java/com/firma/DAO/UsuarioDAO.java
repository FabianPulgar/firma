package com.firma.DAO;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.firma.modelo.Usuario;

@Repository
public interface UsuarioDAO extends CrudRepository<Usuario, Long>{
	public List<Usuario> findByOrderById();
}
