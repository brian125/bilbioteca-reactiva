package co.com.sofka.biblioteca.router;

import static org.junit.jupiter.api.Assertions.*;

import co.com.sofka.biblioteca.collections.Recurso;
import co.com.sofka.biblioteca.dto.RecursoDTO;
import co.com.sofka.biblioteca.mapper.RecursoMapper;
import co.com.sofka.biblioteca.repository.RepositorioRecurso;
import co.com.sofka.biblioteca.useCases.UseCaseObtenerPorId;
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
@ContextConfiguration(classes= {ObtenerPorIdRouter.class, UseCaseObtenerPorId.class, RecursoMapper.class})
class ObtenerPorIdRouterTest {

    @MockBean
    RepositorioRecurso repositorio;

    @Autowired
    WebTestClient webTestClient;

    @Test
    public void obtenerPorIdTest(){
        Recurso recurso1 = new Recurso();
        recurso1.setId("xxx");
        recurso1.setArea(Area.ARTES);
        recurso1.setDisponible(true);
        recurso1.setTipo(Tipo.DOCUMENTAL);
        recurso1.setNombre("Documental");
        recurso1.setFecha(LocalDate.now());

        Mono<Recurso> recursoMono = Mono.just(recurso1);

        when(repositorio.findById(recurso1.getId())).thenReturn(recursoMono);

        webTestClient.get()
                .uri("/recursos/consultar/xxx")
                .exchange()
                .expectStatus().isOk()
                .expectBody(RecursoDTO.class)
                .value(userResponse -> {
                            Assertions.assertThat(userResponse.getArea()).isEqualTo(recurso1.getArea());
                            Assertions.assertThat(userResponse.getTipo()).isEqualTo(recurso1.getTipo());
                            Assertions.assertThat(userResponse.getNombre()).isEqualTo(recurso1.getNombre());
                            Assertions.assertThat(userResponse.isDisponible()).isEqualTo(recurso1.isDisponible());
                            Assertions.assertThat(userResponse.getId()).isEqualTo(recurso1.getId());

                        }
                );
        Mockito.verify(repositorio, Mockito.times(1)).findById("xxx");
    }

}