package com.proyecto.proyectojorgepastor.controller;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.proyectojorgepastor.bean.WebScrapingBean;
import com.proyecto.proyectojorgepastor.commons.mapper.WebScrapingMapper;
import com.proyecto.proyectojorgepastor.services.WebScrapingService;

import lombok.extern.slf4j.Slf4j;

/**
 * Clase WebScrapingController
 *
 * Contiene los metodos para hacer web scraping en la web de la bolsa y traernos la información necesaria.
 *
 * @author Jorge Pastor
 * @version 1.0
 */

@Slf4j
@RestController
@CrossOrigin(origins = "*", allowCredentials = "false", allowedHeaders = "*", maxAge = 3600)
public class WebScrapingController {
  
  private WebScrapingService serviceWebScrapingService;
  
  private WebScrapingMapper serviceWebScrapingMapper = Mappers.getMapper(WebScrapingMapper.class);
  
  public WebScrapingController(WebScrapingService serviceWebScrapingService) {
    this.serviceWebScrapingService = serviceWebScrapingService;
  }
  
  private ResponseEntity<Object> buildErrorResponse(Exception e) {
    ResponseEntity<Object> res = null;
    if (e instanceof EntityNotFoundException) {
      EntityNotFoundException ae = (EntityNotFoundException) e;
      res = ResponseEntity.status(HttpStatus.BAD_REQUEST).body( ae.getMessage());
    } else {
      res = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body( e.getMessage());
      log.error("Ops!", e);
    }
    return res;
  }
  
  @ResponseBody
  @GetMapping(value = "/bolsa", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> getBusiness() { 
    ResponseEntity<Object> response;
    log.info("");
    try {
      List<WebScrapingBean> listBeans = serviceWebScrapingService.getBussines();
      response = ResponseEntity.status(HttpStatus.OK).body(serviceWebScrapingMapper.mapDtoList(listBeans));
    } catch (Exception e) {
      response = buildErrorResponse(e);
      log.error("Error getBusiness:", e);
    }
    log.info("");
    return response;
  }
  
  @ResponseBody
  @GetMapping(value = "/bolsaDatos", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> getDataBusiness() { 
    ResponseEntity<Object> response;
    log.info("");
    try {
      List<WebScrapingBean> listBeans = serviceWebScrapingService.getDataBussines();
      response = ResponseEntity.status(HttpStatus.OK).body(serviceWebScrapingMapper.mapDtoList(listBeans));
    } catch (Exception e) {
      response = buildErrorResponse(e);
      log.error("Error getDataBusiness:", e);
    }
    log.info("");
    return response;
  }
  
}
