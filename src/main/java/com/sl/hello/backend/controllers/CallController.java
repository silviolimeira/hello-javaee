/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sl.hello.backend.controllers;

import com.sl.hello.backend.entities.Call;
import com.sl.hello.backend.entities.Status;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

/**
 *
 * @author sicemal
 */
@Path("calls")
public class CallController {
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)        
    @Path("/")
    public Response list() {

        List<Call> list = List.of(
            new Call(1,"Assunto1", "Mensagem 1", Status.NEW),
            new Call(2,"Assunto2", "Mensagem 2", Status.NEW),
            new Call(3,"Assunto3", "Mensagem 4", Status.CLOSED),
            new Call(4,"Assunto4", "Mensagem 4", Status.PENDING),
            new Call(5,"Assunto5", "Mensagem 5", Status.PENDING)
        );
        return  Response.ok(list).build();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)        
    @Path("{id}/")
    public Response get(@PathParam("id") long id) {

        Call call = new Call(1,"Assunto " + id, "Mensagem " + id, Status.NEW);
        return  Response.ok(call).build();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)        
    @Path("/")
    public Response create(Call call) {
        System.out.println(call);
        return Response.status(Response.Status.CREATED).build();
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)        
    @Path("/")
    public Response update(Call call) {
        System.out.println(call);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
    
    @DELETE
    @Path("{id}/")
    public Response delete(@PathParam("id") long id) {
        System.out.println("Deletando ID: " + id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
    
    
}
