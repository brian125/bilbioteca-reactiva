package co.com.sofka.biblioteca.router;

import co.com.sofka.biblioteca.collections.Recurso;
import co.com.sofka.biblioteca.dto.RecursoDTO;
import co.com.sofka.biblioteca.mapper.RecursoMapper;
import co.com.sofka.biblioteca.repository.RepositorioRecurso;
import co.com.sofka.biblioteca.useCases.UseCaseMostrarRecursos;
import co.com.sofka.biblioteca.utils.Area;
import co.com.sofka.biblioteca.utils.Tipo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@WebFluxTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {MostrarRecursosRouter.class, UseCaseMostrarRecursos.class, RecursoMapper.class})
class MostrarRecursosRouterTest {

    @MockBean
    private RepositorioRecurso repositorio;

    @Autowired
    private WebTestClient webTestClient;


    @Test
    public void testGetDatos() {

        Recurso recurso1 = new Recurso();
        recurso1.setId("xxx");
        recurso1.setArea(Area.ARTES);
        recurso1.setDisponible(true);
        recurso1.setTipo(Tipo.DOCUMENTAL);
        recurso1.setNombre("Documental");
        recurso1.setFecha(LocalDate.now());

        Recurso recurso2 = new Recurso();
        recurso2.setId("yyy");
        recurso2.setArea(Area.CIENCIAS);
        recurso2.setDisponible(true);
        recurso2.setTipo(Tipo.LIBRO);
        recurso2.setNombre("Libro");
        recurso2.setFecha(LocalDate.now());

        when(repositorio.findAll()).thenReturn(Flux.just(recurso1, recurso2));

        webTestClient.get()
                .uri("/recursos")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(RecursoDTO.class)
                .value(userResponse -> {
                            Assertions.assertThat(userResponse.get(0).getNombre()).isEqualTo(recurso1.getNombre());
                            Assertions.assertThat(userResponse.get(1).getNombre()).isEqualTo(recurso2.getNombre());
                        }
                );
    }

}