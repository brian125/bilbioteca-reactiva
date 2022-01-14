package co.com.sofka.biblioteca.useCases;

import co.com.sofka.biblioteca.collections.Recurso;
import co.com.sofka.biblioteca.dto.RecursoDTO;
import co.com.sofka.biblioteca.mapper.RecursoMapper;
import co.com.sofka.biblioteca.repository.RepositorioRecurso;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Service
@Validated
public class UseCasePrestarRecurso implements ObtenerDisponibilidad {

    private final RepositorioRecurso repositorio;
    private final RecursoMapper mapper;


    public UseCasePrestarRecurso(RepositorioRecurso repositorio, RecursoMapper mapper) {
        this.repositorio = repositorio;
        this.mapper = mapper;
    }

    @Override
    public Mono<String> get(String id) {
        Mono<Recurso> recursoMono = repositorio.findById(id);
        return recursoMono.flatMap(r -> {
            if (r.isDisponible()) {
                r.setDisponible(false);
                r.setFecha(LocalDate.now());
                return repositorio.save(r).thenReturn("El recurso fue prestado con exito");
            }
            return Mono.just("El recurso no está disponible");
        });
    }
}
