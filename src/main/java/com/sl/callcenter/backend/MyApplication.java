/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sl.callcenter.backend;

import jakarta.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;

/**
 *
 * @author sicemal
 */
@ApplicationPath("rest")
public class MyApplication extends ResourceConfig {

    public MyApplication() {
        packages(true, "com.sl.help.backend.controllers");
    }
}