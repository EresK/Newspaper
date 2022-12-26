package com.newspaper.backend.mapper;

public interface DefaultMapper<D, E> {
    public E dtoToEntity(D dto);

    public void updateEntity(E entity, D dto);
}
