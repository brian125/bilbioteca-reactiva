package co.com.sofka.biblioteca.useCases;

import co.com.sofka.biblioteca.dto.RecursoDTO;
import reactor.core.publisher.Flux;

public interface ObtenerPorAreaYTipo {
    Flux<RecursoDTO> get(String area, String tipo);
}