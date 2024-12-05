/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sl.callcenter.backend.controllers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sl.callcenter.backend.business.CallBus;
import com.sl.callcenter.backend.entities.Call;
import com.sl.callcenter.backend.enumerados.call.Status;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sicemal
 */
@JsonIgnoreProperties
@Path("calls")
public class CallController {
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)        
    @Path("/")
    public Response list() throws SQLException {
        try {
            CallBus callBus = new CallBus();
            return Response.ok(callBus.list()).build();
        } catch (Exception ex) {
            Logger.getLogger(CallController.class.getCanonicalName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)        
    @Path("{id}/")
    public Response get(@PathParam("id") long id) {
        try {
            CallBus callBus = new CallBus();
            return Response.ok(callBus.select(id)).build();
        } catch (Exception ex) {
            Logger.getLogger(CallController.class.getCanonicalName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)        
    @Path("/")
    public Response create(Call call) {
        try {
            CallBus callBus = new CallBus();
            callBus.insert(call);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception ex) {
            Logger.getLogger(CallController.class.getCanonicalName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)        
    @Path("/")
    public Response update(Call call) {
        try {
            call.setStatus(Status.PENDING);
            CallBus callBus = new CallBus();
            callBus.update(call);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (Exception ex) {
            Logger.getLogger(CallController.class.getCanonicalName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }
    
    @DELETE
    @Path("{id}/")
    public Response delete(@PathParam("id") long id) {
        try {
            CallBus callBus = new CallBus();
            callBus.delete(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (Exception ex) {
            Logger.getLogger(CallController.class.getCanonicalName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)        
    @Path("{id}/")
    public Response closed(@PathParam("id") long id) throws SQLException {
        try {
            CallBus callBus = new CallBus();
            
            Call c = callBus.select(id);
            c.setStatus(Status.CLOSED);
            
            callBus.update(c);
            
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (Exception ex) {
            Logger.getLogger(CallController.class.getCanonicalName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }
    
    
}
