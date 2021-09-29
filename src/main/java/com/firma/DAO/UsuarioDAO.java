package com.firma.DAO;

import java.util.List;

import javax.persistence.Id;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.firma.modelo.Usuario;

@Repository
public interface UsuarioDAO extends JpaRepository<Usuario, Long>{
	public List<Usuario> findByOrderById();
}
