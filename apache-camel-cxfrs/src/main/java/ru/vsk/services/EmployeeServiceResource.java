package ru.vsk.services;

import ru.vsk.exchange.Exchanger;
import ru.vsk.exchange.ExchangerActiveMQ;
import ru.vsk.exchange.StaticData;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/")
public class EmployeeServiceResource {
    private Exchanger exchanger = new ExchangerActiveMQ(StaticData.getCamelContext());

    public EmployeeServiceResource() {
    }

    @GET
    @Path("/employees/{name}/")
    public String getCustomer(@PathParam("name") String name) {
        return "Welcome " + name;
    }

    @GET
    @Path("/methods/{id}/")
    public String methods(@PathParam("id") Integer id) {
        return "you write in this: " + id + "<br>" +
                "Factorial of this number is: " + exchanger.getFactorial(id);
    }

}