package org.acme;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

import java.util.List;
import java.util.stream.Collectors;


@Path("/hello")
public class GreetingResource {

    @GET
    @Transactional
    @Produces(MediaType.TEXT_PLAIN)
    public String hello(@QueryParam("name") String name) {
        Greeting greeting = new Greeting();
        greeting.name = name;
        greeting.persist();
        return "Teste " + name;
    }

    @GET
    @Path("names")
    @Produces(MediaType.TEXT_PLAIN)
    public String name() {
        List<Greeting> greetings = Greeting.listAll();
        String names = greetings.stream().map(
                g -> g.name
        )
                .collect(Collectors.joining(", "));
        return "Lista de nomes " + names;
    }
}
