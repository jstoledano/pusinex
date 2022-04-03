package org.toledano.pusinex.models.repository;

import org.springframework.data.repository.CrudRepository;
import org.toledano.pusinex.models.entity.Seccion;

import java.util.List;

public interface ISeccionService extends CrudRepository<Seccion, Long> {
    public List<Seccion> listarSecciones();
    public void guardarSeccion(Seccion seccion);
    public void eliminarSeccion(Long seccion);
    public Seccion buscarSeccion(Long seccion);
}
