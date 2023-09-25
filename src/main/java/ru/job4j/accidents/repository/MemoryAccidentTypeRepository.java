package ru.job4j.accidents.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.AccidentType;
import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class MemoryAccidentTypeRepository implements AccidentTypeRepository {

    private AtomicInteger nextId = new AtomicInteger(1);
    private final ConcurrentHashMap<Integer, AccidentType> types = new ConcurrentHashMap<>();

    @Override
    public Collection<AccidentType> findAll() {
        return types.values();
    }

    @Override
    public Optional<AccidentType> findById(int id) {
        return Optional.ofNullable(types.get(id));
    }
}
