package co.com.sofka.biblioteca.useCases;

import co.com.sofka.biblioteca.dto.RecursoDTO;
import reactor.core.publisher.Flux;

public interface ObtenerPorString {
    Flux<RecursoDTO> get(String string);
}
