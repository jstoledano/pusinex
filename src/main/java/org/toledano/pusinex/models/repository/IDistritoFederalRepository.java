package org.toledano.pusinex.models.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.RequestParam;
import org.toledano.pusinex.models.entity.DistritoFederal;

import java.util.List;

public interface IDistritoFederalRepository extends CrudRepository<DistritoFederal, Integer> {
    @Query("SELECT d FROM DistritoFederal d WHERE d.entidadId.entidad=:entidadId")
    List<DistritoFederal> findByEntidadId(@RequestParam("entidadId") int entidadId);
    @Query("SELECT d FROM DistritoFederal d WHERE d.entidadId.entidad=:entidadId AND d.distritoFederal=:distrito")
    DistritoFederal findByEntidadIdAndDistrito(@RequestParam("entidadId") int entidadId, @RequestParam("distrito") int distrito);
}
