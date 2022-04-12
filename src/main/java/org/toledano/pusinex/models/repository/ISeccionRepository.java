package org.toledano.pusinex.models.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.toledano.pusinex.models.entity.Seccion;

import java.util.List;

public interface ISeccionRepository extends CrudRepository<Seccion, Integer> {
    @Query("SELECT s FROM Seccion s WHERE s.entidad.entidad = ?1 AND s.seccion = ?2")
    public Seccion findSeccion(Integer entidad, Integer seccion);
    public List<Seccion> findByEntidad_Entidad(Integer entidad);
    public List<Seccion> findByEntidad_EntidadAndDistritoFederal_DistritoFederal(Integer entidad, Integer distritoFederal);
    public List<Seccion> findByEntidad_EntidadAndMunicipio_Municipio(Integer entidad, Integer municipio);
}
