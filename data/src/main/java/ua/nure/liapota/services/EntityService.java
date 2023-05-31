package ua.nure.liapota.services;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public abstract class EntityService<E, V, T extends CrudRepository<E, V>> {
    protected T repository;

    public List<E> getAll() {
        return (List<E>) repository.findAll();
    }

    public E getById(V id) {
        return repository.findById(id).get();
    }

    public E create(E e) {
        return repository.save(e);
    }

    public void delete(V id) {
        repository.delete(repository.findById(id).get());
    }
}
