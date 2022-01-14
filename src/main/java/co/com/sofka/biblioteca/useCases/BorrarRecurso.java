package co.com.sofka.biblioteca.useCases;

import reactor.core.publisher.Mono;

public interface BorrarRecurso {
    Mono<Void> deleteById(String id);
}
