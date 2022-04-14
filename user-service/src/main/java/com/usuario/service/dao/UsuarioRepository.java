package com.usuario.service.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.usuario.service.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

}
