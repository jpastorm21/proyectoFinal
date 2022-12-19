package com.proyecto.proyectojorgepastor.controller;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.proyectojorgepastor.services.MailService;

import lombok.extern.slf4j.Slf4j;

/**
 * Clase MailController
 *
 * Contiene el metodo para dar funcionalidad al envio de emails en la aplicación.
 *
 * @author Jorge Pastor
 * @version 1.0
 */

@Slf4j
@RestController
public class MailController {
  
  private MailService mailService;
  
  public MailController (MailService mailService) {
    this.mailService = mailService;
  }
  
//  @PostMapping("/sendMail")
//  public ResponseEntity sendMail(@RequestBody MailBean bean) {
//    ResponseEntity<Object> response;
//    log.info("");
//    try {
//      String result = mailService.sendMail(bean);
//      response = ResponseEntity.status(HttpStatus.OK).body(result);
//    } catch (Exception e) {
//      response = buildErrorResponse(e);
//      log.error("Error sendMail:", e);
//    }
//    log.info("");
//    return response;
//    }
  
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
  
}
