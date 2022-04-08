package org.toledano.pusinex.models.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.RequestParam;
import org.toledano.pusinex.models.entity.DistritoFederal;

import java.util.List;

public interface IDistritoFederalRepository extends CrudRepository<DistritoFederal, Integer> {
    List<DistritoFederal> findByEntidadId_entidad(int entidad);
    @Query("SELECT d FROM DistritoFederal d WHERE d.entidadId.entidad=:entidadId AND d.distritoFederal=:distrito")
    DistritoFederal findByEntidadIdAndDistrito(@RequestParam("entidadId") int entidadId, @RequestParam("distrito") int distrito);
}
