package co.com.sofka.biblioteca.useCases;

import co.com.sofka.biblioteca.dto.RecursoDTO;
import co.com.sofka.biblioteca.mapper.RecursoMapper;
import co.com.sofka.biblioteca.repository.RepositorioRecurso;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;


@Service
@Validated
public class UseCaseObtenerPorId implements ObtenerPorId {

    private final RepositorioRecurso repositorio;
    private final RecursoMapper mapper;

    public UseCaseObtenerPorId(RepositorioRecurso repositorio, RecursoMapper mapper) {
        this.repositorio = repositorio;
        this.mapper = mapper;
    }

    @Override
    public Mono<RecursoDTO> get(String id) {
        return repositorio.findById(id).map(mapper.mapRecursoToDTO());
    }

}