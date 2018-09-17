package ru.vsk.services;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface EmployeeService {
    @GET
    @Path("/string/{name}/")
    String getString(@PathParam("name") String name);

    @GET
    @Path("/factorial/{id}/")
    String methods(@PathParam("id") Integer id);
}
