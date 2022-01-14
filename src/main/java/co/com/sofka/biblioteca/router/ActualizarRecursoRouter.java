package co.com.sofka.biblioteca.router;

import co.com.sofka.biblioteca.dto.RecursoDTO;
import co.com.sofka.biblioteca.useCases.UseCaseActualizarRecurso;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class ActualizarRecursoRouter {
    @Bean
    public RouterFunction<ServerResponse> actualizarRecurso(UseCaseActualizarRecurso useCaseActualizarRecurso) {
        return route(RequestPredicates.PUT("/recursos/actualizar").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(RecursoDTO.class)
                        .flatMap(recursoDTO -> useCaseActualizarRecurso.apply(recursoDTO)
                                .flatMap(result -> ServerResponse.ok()
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(result))
                        )
        );
    }
}