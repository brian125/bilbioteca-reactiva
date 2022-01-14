package co.com.sofka.biblioteca.useCases;

import co.com.sofka.biblioteca.dto.RecursoDTO;
import reactor.core.publisher.Mono;

@FunctionalInterface
public interface GuardarRecurso {

    public Mono<RecursoDTO> apply(RecursoDTO recursoDTO);
}