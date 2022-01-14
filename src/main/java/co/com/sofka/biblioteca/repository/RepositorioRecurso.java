package co.com.sofka.biblioteca.repository;

import co.com.sofka.biblioteca.collections.Recurso;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface RepositorioRecurso extends ReactiveMongoRepository<Recurso, String> {
}