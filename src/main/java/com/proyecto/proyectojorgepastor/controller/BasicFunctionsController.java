package com.proyecto.proyectojorgepastor.controller;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.proyectojorgepastor.bean.DataBolsaBean;
import com.proyecto.proyectojorgepastor.commons.mapper.DataBolsaMapper;
import com.proyecto.proyectojorgepastor.controller.dto.request.DataBolsaRequestDto;
import com.proyecto.proyectojorgepastor.services.BasicFunctionsService;

import lombok.extern.slf4j.Slf4j;

/**
 * Clase BasicFunctionsController
 *
 * Contiene los metodos CRUD para la base de datos.
 *
 * @author Jorge Pastor
 * @version 1.0
 */

@Slf4j
@RestController
@CrossOrigin(origins = "*", allowCredentials = "false", allowedHeaders = "*", maxAge = 3600)
public class BasicFunctionsController {

  private BasicFunctionsService basicFunctionsService;
  private DataBolsaMapper dataBolsaMapper = Mappers.getMapper(DataBolsaMapper.class);

  public BasicFunctionsController(BasicFunctionsService basicFunctionsService) {
    this.basicFunctionsService = basicFunctionsService;
  }

  @ResponseBody
  @GetMapping(value = "/get-one/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> getOneBasicFunctions(@PathVariable("id") Long id) {
    ResponseEntity<Object> response;
    log.info("");
    try {
      DataBolsaBean bean = basicFunctionsService.getOneBasicFunctionService(id);
      response = ResponseEntity.status(HttpStatus.OK).body(dataBolsaMapper.map(bean));
    } catch (Exception e) {
      response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
              .body(e.getMessage());
      log.error("Error getOneBasicFunctions:", e);
    }
    log.info("");
    return response;
  }
  @ResponseBody
  @GetMapping(value = "/get-all", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> getAllBasicFunctions() {
    ResponseEntity<Object> response;
    log.info("");
    try {
      List<DataBolsaBean> result = basicFunctionsService.getAllBasicFunctionService();
      
      response = ResponseEntity.status(HttpStatus.OK).body(dataBolsaMapper.mapDtoList(result));
    } catch (Exception e) {
      response = buildErrorResponse(e);
      log.error("Error getAllBasicFunctions:", e);
    }
    log.info("");
    return response;
  }

  @ResponseBody
  @PostMapping(value = "/create-basic-functions", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> createBasicFunctions(@Valid @RequestBody DataBolsaRequestDto example) {
    ResponseEntity<Object> response;
    log.info("");
    try {
      DataBolsaBean result = basicFunctionsService
          .createBasicFunctionService(dataBolsaMapper.mapRequestDto(example));
      response = ResponseEntity.status(HttpStatus.OK).body(dataBolsaMapper.map(result));
    } catch (Exception e) {
      response = buildErrorResponse(e);
      log.error("Error createBasicFunctions:", e);
    }
    log.info("");
    return response;
  }

  @ResponseBody
  @DeleteMapping(value = "/delete-basic-functions/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> deleteBasicFunctions(@PathVariable("id") Long id) {
    ResponseEntity<Object> response;
    log.info("");
    try {
      basicFunctionsService.deleteBasicFunctionService(id);
      response = ResponseEntity.status(HttpStatus.OK).build();
    } catch (Exception e) {
      response = buildErrorResponse(e);
      log.error("Error deleteMBasicFunctions:", e);
    }
    log.info("");
    return response;
  }

  @ResponseBody
  @PutMapping(value = "/update-basicFunctions/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> updateBasicFunctions(@PathVariable("id") Long id,
       @RequestBody DataBolsaRequestDto example) {
    ResponseEntity<Object> response;
    log.info("");
    try {
      DataBolsaBean exampleBean = dataBolsaMapper.mapRequestDto(example);
      exampleBean.setId(id);
      DataBolsaBean result = basicFunctionsService.updateBasicFunctionService(exampleBean);
      response = ResponseEntity.status(HttpStatus.OK).body(dataBolsaMapper.map(result));
    } catch (Exception e) {
      response = buildErrorResponse(e);
      log.error("Error updateBasicFunctions:", e);
    }
    log.info("");
    return response;
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

}
