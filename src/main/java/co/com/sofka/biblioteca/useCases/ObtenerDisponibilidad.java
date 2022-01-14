package co.com.sofka.biblioteca.useCases;

import co.com.sofka.biblioteca.dto.RecursoDTO;
import reactor.core.publisher.Mono;

public interface ObtenerDisponibilidad {
    Mono<String> get(String id);
}