package org.toledano.pusinex.models.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.RequestParam;
import org.toledano.pusinex.models.entity.Municipio;

import java.util.List;

public interface IMunicipioRepository extends CrudRepository<Municipio, Integer> {
    List<Municipio> findMunicipioByEntidadId_Entidad(Integer entidadId);
    @Query("SELECT m FROM Municipio m WHERE m.entidadId.entidad=:entidad AND m.municipio=:mpio")
    Municipio findMunicipio(@RequestParam("entidad") Integer entidad, @RequestParam("municipio") int mpio);
}
