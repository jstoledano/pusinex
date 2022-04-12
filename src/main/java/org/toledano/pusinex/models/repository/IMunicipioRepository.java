package org.toledano.pusinex.models.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.RequestParam;
import org.toledano.pusinex.models.entity.Municipio;

import java.util.List;

public interface IMunicipioRepository extends CrudRepository<Municipio, Integer> {
    List<Municipio> findMunicipioByEntidad_Entidad(Integer entidad);
    @Query("SELECT m FROM Municipio m WHERE m.entidad.entidad=:entidad AND m.municipio=:mpio")
    Municipio findMunicipio(@RequestParam("entidad") Integer entidad, @RequestParam("municipio") int mpio);
}
