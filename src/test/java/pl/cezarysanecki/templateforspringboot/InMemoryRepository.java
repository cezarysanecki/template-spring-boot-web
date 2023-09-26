package pl.cezarysanecki.templateforspringboot;

import org.springframework.data.repository.CrudRepository;
import pl.cezarysanecki.templateforspringboot.util.StreamWrapper;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public abstract class InMemoryRepository<T> implements CrudRepository<T, Long> {

  protected final Map<Long, T> database = new ConcurrentHashMap<>();

  @Override
  public <S extends T> Iterable<S> saveAll(Iterable<S> entities) {
    return StreamWrapper.of(entities)
        .stream()
        .map(this::save)
        .collect(Collectors.toList());
  }

  @Override
  public Optional<T> findById(Long id) {
    return Optional.ofNullable(database.get(id));
  }

  @Override
  public boolean existsById(Long id) {
    return findById(id).isPresent();
  }

  @Override
  public Iterable<T> findAll() {
    return database.values();
  }

  @Override
  public Iterable<T> findAllById(Iterable<Long> ids) {
    return StreamWrapper.of(ids)
        .stream()
        .map(database::get)
        .filter(Objects::nonNull)
        .collect(Collectors.toList());
  }

  @Override
  public long count() {
    return database.values().size();
  }

  @Override
  public void deleteById(Long id) {
    database.keySet()
        .removeIf(id::equals);
  }

  @Override
  public void delete(T entity) {
    database.values()
        .removeIf(entity::equals);
  }

  @Override
  public void deleteAllById(Iterable<? extends Long> ids) {
    ids.forEach(database::remove);
  }

  @Override
  public void deleteAll(Iterable<? extends T> entities) {
    entities.forEach(this::delete);
  }

  @Override
  public void deleteAll() {
    database.clear();
  }

}
