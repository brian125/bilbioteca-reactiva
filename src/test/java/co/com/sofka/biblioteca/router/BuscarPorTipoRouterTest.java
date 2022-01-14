package co.com.sofka.biblioteca.router;

import co.com.sofka.biblioteca.collections.Recurso;
import co.com.sofka.biblioteca.dto.RecursoDTO;
import co.com.sofka.biblioteca.mapper.RecursoMapper;
import co.com.sofka.biblioteca.repository.RepositorioRecurso;
import co.com.sofka.biblioteca.useCases.UseCaseBuscarPorArea;
import co.com.sofka.biblioteca.useCases.UseCaseBuscarPorTipo;
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
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@WebFluxTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes= {BuscarPorTipoRouter.class, UseCaseBuscarPorTipo.class, RecursoMapper.class})
class BuscarPorTipoRouterTest {

    @MockBean
    RepositorioRecurso repositorio;

    @Autowired
    WebTestClient webTestClient;

    @Test
    public void buscarPorAreaTest(){
        Recurso recurso1 = new Recurso();
        recurso1.setId("xxx");
        recurso1.setArea(Area.CIENCIAS);
        recurso1.setDisponible(true);
        recurso1.setTipo(Tipo.DOCUMENTAL);
        recurso1.setNombre("Documental");
        recurso1.setFecha(LocalDate.now());

        Recurso recurso2 = new Recurso();
        recurso2.setId("yyy");
        recurso2.setArea(Area.ARTES);
        recurso2.setDisponible(true);
        recurso2.setTipo(Tipo.DOCUMENTAL);
        recurso2.setNombre("Libro");
        recurso2.setFecha(LocalDate.now());

        Mono<Recurso> recursoMono = Mono.just(recurso1);

        when(repositorio.findByTipo(recurso1.getTipo().toString())).thenReturn(Flux.just(recurso1, recurso2));

        webTestClient.get()
                .uri("/recursos/filtrarTipo/DOCUMENTAL")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(RecursoDTO.class)
                .value(userResponse -> {
                            Assertions.assertThat(userResponse.get(0).getTipo()).isEqualTo(recurso1.getTipo());
                            Assertions.assertThat(userResponse.get(1).getTipo()).isEqualTo(recurso2.getTipo());
                        }
                );

        Mockito.verify(repositorio,Mockito.times(1)).findByTipo("DOCUMENTAL");
    }

}