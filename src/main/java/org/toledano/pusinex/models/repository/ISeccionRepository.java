package org.toledano.pusinex.models.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.toledano.pusinex.models.entity.Seccion;

import java.util.List;

public interface ISeccionRepository extends CrudRepository<Seccion, Integer> {
    @Query("SELECT s FROM Seccion s WHERE s.entidadId.entidad = ?1 AND s.seccion = ?2")
    Seccion findSeccion(Integer entidad, Integer seccion);
    List<Seccion> findByEntidadId_Entidad(Integer entidad);
    List<Seccion> findByEntidadId_EntidadAndDistritoFederalId_DistritoFederal(Integer entidad, Integer distritoFederal);
    List<Seccion> findByEntidadId_EntidadAndMunicipioId_Municipio(Integer entidad, Integer municipio);
}
