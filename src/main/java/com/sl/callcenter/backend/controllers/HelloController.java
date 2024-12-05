/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sl.callcenter.backend.controllers;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

/**
 *
 * @author sicemal
 */
@Path("hello")
public class HelloController {
    

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getMessage() {
        return "Welcome !";
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("users")
    public String getMessage(@QueryParam("user") String user) {
        return "Welcome: " + user;
    }
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("users/{id}")
    public String getUser(@PathParam("id") long id) {
        return "Recuperando usuario com ID: " + id;
    }
}
