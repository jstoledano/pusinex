package org.toledano.pusinex.models.service;

import org.toledano.pusinex.models.entity.Seccion;
import org.toledano.pusinex.models.repository.ISeccionService;

import java.util.List;
import java.util.Optional;

public class SeccionServiceImpl implements ISeccionService {

    @Override
    public List<Seccion> listarSecciones() {

        return null;
    }

    @Override
    public void guardarSeccion(Seccion seccion) {

    }

    @Override
    public void eliminarSeccion(Long seccion) {

    }

    @Override
    public Seccion buscarSeccion(Long seccion) {
        return null;
    }

    @Override
    public <S extends Seccion> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Seccion> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Seccion> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<Seccion> findAll() {
        return null;
    }

    @Override
    public Iterable<Seccion> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Seccion entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Seccion> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
