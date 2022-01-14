package co.com.sofka.biblioteca.router;

import co.com.sofka.biblioteca.dto.RecursoDTO;
import co.com.sofka.biblioteca.useCases.UseCaseBuscarPorTipo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class BuscarPorTipoRouter {
    @Bean
    public RouterFunction<ServerResponse> buscarPorArea(UseCaseBuscarPorTipo useCaseBuscarPorTipo) {
        return route(
                GET("/recursos/filtrarTipo/{tipo}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(useCaseBuscarPorTipo.get(request.pathVariable("tipo")), RecursoDTO.class))
        );
    }
}
