package org.toledano.pusinex.models.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.toledano.pusinex.models.entity.Localidad;

import java.util.List;

public interface ILocalidadRepository extends CrudRepository<Localidad, Integer> {
    @Query("SELECT l FROM Localidad l WHERE l.seccionId.entidadId.entidad = ?1 and l.seccionId.seccion = ?2 and l.localidad = ?3")
    public Localidad findLocalidad(int entidad, int seccion, int localidad);
    @Query("SELECT l FROM Localidad l WHERE l.seccionId.entidadId.entidad = ?1 and l.seccionId.municipioId.municipio = ?2")
    public List<Localidad> findLocalidadByMunicipio(int entidad, int municipio);
    @Query("SELECT l FROM Localidad l WHERE l.seccionId.entidadId.entidad = ?1")
    public List<Localidad> findAll(int entidad);

    @Query("SELECT l FROM Localidad l WHERE l.seccionId.entidadId.entidad = ?1 and l.seccionId.seccion = ?2 and l.localidad = ?3 and l.tipo = ?4")
    public Localidad findLocalidadAndTipo(int entidad, int seccion, int localidad, String tipo);
    @Query("SELECT l FROM Localidad l WHERE l.seccionId.entidadId.entidad = ?1 and l.seccionId.municipioId.municipio = ?2 and l.tipo = ?3")
    public List<Localidad> findLocalidadByMunicipioAndTipo(int entidad, int municipio, String tipo);
    @Query("SELECT l FROM Localidad l WHERE l.seccionId.entidadId.entidad = ?1 and l.tipo = ?2")
    public List<Localidad> findAllAndTipo(int entidad, String tipo);

}
