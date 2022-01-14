package co.com.sofka.biblioteca.useCases;

import co.com.sofka.biblioteca.dto.RecursoDTO;
import reactor.core.publisher.Mono;

public interface ObtenerPorId {
    Mono<RecursoDTO> get(String id);
}
