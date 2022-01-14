package co.com.sofka.biblioteca.useCases;

import co.com.sofka.biblioteca.mapper.RecursoMapper;
import co.com.sofka.biblioteca.repository.RepositorioRecurso;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;


@Service
@Validated
public class UseCaseDisponibilidad implements ObtenerDisponibilidad{

    private final RepositorioRecurso repositorio;
    private final RecursoMapper mapper;

    public UseCaseDisponibilidad(RepositorioRecurso repositorio, RecursoMapper mapper) {
        this.repositorio = repositorio;
        this.mapper = mapper;
    }

    @Override
    public Mono<String> get(String id) {
        return repositorio.findById(id).map(r->
                r.isDisponible() ?
                        "El material está disponible"
                        : "El material no está disponible, fue prestado el: "+ r.getFecha()
        );
    }
}