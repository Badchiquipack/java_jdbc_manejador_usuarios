package com.marriaga.mantenedor.usuarios.jdbc.repositorio;

import java.util.List;

public interface IRepositorio<T> {
    List<T> listar();

    T porId(Long id);

    void guardar(T t);

    void eliminar(Long id);
}
