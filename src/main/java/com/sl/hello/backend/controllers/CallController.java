/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sl.hello.backend.controllers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sl.hello.backend.entities.Call;
import com.sl.callcenter.backend.jdbc.dao.CallDAO;
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
import java.util.List;
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
            CallDAO callDAO = new CallDAO();
            return Response.ok(callDAO.list()).build();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CallController.class.getCanonicalName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)        
    @Path("{id}/")
    public Response get(@PathParam("id") long id) {
        try {
            CallDAO callDAO = new CallDAO();
            return Response.ok(callDAO.select(id)).build();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(CallController.class.getCanonicalName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)        
    @Path("/")
    public Response create(Call call) {
        try {
            call.setStatus(Status.NEW);
            CallDAO callDAO = new CallDAO();
            callDAO.insert(call);
            return Response.status(Response.Status.CREATED).build();
        } catch (SQLException | ClassNotFoundException ex) {
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
            CallDAO callDAO = new CallDAO();
            callDAO.update(call);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(CallController.class.getCanonicalName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }
    
    @DELETE
    @Path("{id}/")
    public Response delete(@PathParam("id") long id) {
        try {
            CallDAO callDAO = new CallDAO();
            callDAO.delete(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(CallController.class.getCanonicalName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)        
    @Path("{id}/")
    public Response closed(@PathParam("id") long id) throws SQLException {
        try {
            CallDAO callDAO = new CallDAO();
            
            Call c = callDAO.select(id);
            c.setStatus(Status.CLOSED);
            
            callDAO.update(c);
            
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CallController.class.getCanonicalName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }
    
    
}
