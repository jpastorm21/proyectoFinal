package com.proyecto.proyectojorgepastor.controller;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.proyectojorgepastor.services.WebScrapingService;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

/**
 * Clase HelloController
 *
 * Contiene un controlador de pruebas, no usado en la app.
 *
 * @author Jorge Pastor
 * @version 1.0
 */

@Slf4j
@RestController
@Api(value = "microservice", tags = { "Microservice example" })
@CrossOrigin(origins = "*", allowCredentials = "false", allowedHeaders = "*", maxAge = 3600)
public class HelloController {
  
  private WebScrapingService serviceWebScrapingService;
  
  public HelloController(WebScrapingService microserviceAmazonService) {
    this.serviceWebScrapingService = microserviceAmazonService;
  }
  @ResponseBody
  @GetMapping(value = "/hello", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> Hello() {
    log.info("Start: " + LocalDateTime.now());
    ResponseEntity objeto =	ResponseEntity.status(HttpStatus.OK).body("Hola Mundo");
    log.info("Finish: " + LocalDateTime.now());
    return objeto;	
  }
 
  //RequestParam QueryParam para fitrar por un campo 
  @ResponseBody
  @GetMapping(value = "/helloName", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> HelloName(@RequestParam (required = true) String name){
    ResponseEntity objeto =	ResponseEntity.status(HttpStatus.OK).body("Hola " + name);
    return objeto;
  }
  
  //PathParam es obligatorio pasar parametro se usa para devolver, actualizar, borrar ids
  @ResponseBody
  @GetMapping(value = "/helloName2/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> HelloName2(@PathVariable String name){
    ResponseEntity objeto =	ResponseEntity.status(HttpStatus.OK).body("Hola " + name);
    return objeto;
  }
  
}
