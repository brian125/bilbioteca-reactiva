package co.com.sofka.biblioteca.router;

import static org.junit.jupiter.api.Assertions.*;

import co.com.sofka.biblioteca.collections.Recurso;
import co.com.sofka.biblioteca.dto.RecursoDTO;
import co.com.sofka.biblioteca.mapper.RecursoMapper;
import co.com.sofka.biblioteca.repository.RepositorioRecurso;
import co.com.sofka.biblioteca.useCases.UseCaseBuscarPorAreaYTipo;
import co.com.sofka.biblioteca.useCases.UseCaseEliminarRecurso;
import co.com.sofka.biblioteca.utils.Area;
import co.com.sofka.biblioteca.utils.Tipo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@WebFluxTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes= {EliminarRecursoRouter.class, UseCaseEliminarRecurso.class, RecursoMapper.class})
class EliminarRecursoRouterTest {

    @MockBean
    RepositorioRecurso repositorio;

    @Autowired
    WebTestClient webTestClient;

    @Test
    public void eliminarRecursoTest(){
        Recurso recurso1 = new Recurso();
        recurso1.setId("xxx");

        when(repositorio.deleteById("xxx")).thenReturn(Mono.empty());

        webTestClient.delete()
                .uri("/recursos/eliminar/xxx")
                .exchange()
                .expectStatus().isOk()
                .expectBody().isEmpty();

        Mockito.verify(repositorio,Mockito.times(1)).deleteById("xxx");
    }
}