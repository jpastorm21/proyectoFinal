package com.proyecto.proyectojorgepastor.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.proyecto.proyectojorgepastor.bean.DataBolsaBean;
import com.proyecto.proyectojorgepastor.services.BasicFunctionsService;
import com.proyecto.proyectojorgepastor.services.WebScrapingService;

import lombok.extern.slf4j.Slf4j;

/**
 * Clase FrontController
 *
 * Contiene los metodos para dar funcionalidad al front. Saca los listados de empresas y edita, crea y borra consultas.
 *
 * @author Jorge Pastor
 * @version 1.0
 */

@Slf4j
@RestController
public class FrontController {
  
  private WebScrapingService webScrapingService;
  private BasicFunctionsService basicFunctionsService;
  
  public FrontController(WebScrapingService serviceWebScrapingService, BasicFunctionsService basicFunctionsService ) {
    this.webScrapingService = serviceWebScrapingService;
    this.basicFunctionsService = basicFunctionsService;
  }
  
  @GetMapping(value = "/index")
  public ModelAndView index() {
      ModelAndView mav = new ModelAndView();
      mav.addObject("listBasicFunctions", this.basicFunctionsService.getAllBasicFunctionService());
      mav.addObject("listWebScraping", this.webScrapingService.getDataBussines());
      mav.setViewName("index");
      return mav;
  }
  
  @GetMapping(value = "/")
  public ModelAndView localIndex() {
      return index();
  }
  
  @GetMapping(value = "/edit-data/{id}")
  public ModelAndView updateBasicFunctions(@PathVariable("id")Long id, String name, String email, double min, double max) { 
      DataBolsaBean dataBolsaBean =  DataBolsaBean.builder().id(id).name(name).email(email).min(min).max(max).build();
      basicFunctionsService.updateBasicFunctionService(dataBolsaBean);
      return index();
  }
  
  @GetMapping(value = "/edit/{id}") //this
  public ModelAndView editBasicFunctions(@PathVariable("id") Long id) {
      ModelAndView mav = new ModelAndView();
      DataBolsaBean bean = basicFunctionsService.getOneBasicFunctionService(id);
      bean.setPrice(webScrapingService.getDataBussines().stream().filter(w-> w.getName().equals(bean.getName())).findAny().get().getPrice());
      mav.addObject("bean", bean);
      mav.setViewName("edit");
      return mav;
  }
  
  @GetMapping(value = "/create-data")
  public ModelAndView listUpdateBasicFunctions(String name, String email, double min, double max) { //Introducir los objetos del dto
      ModelAndView mav = new ModelAndView();
      DataBolsaBean dataBolsaBean =  DataBolsaBean.builder().name(name).email(email).min(min).max(max).build();//aqui tambien añadir los campos del dto
      basicFunctionsService.createBasicFunctionService(dataBolsaBean);
      return index();
  }
  
  @GetMapping(value = "/create")
  public ModelAndView createBasicFunctions() {
      ModelAndView mav = new ModelAndView();
      mav.addObject("listWebScraping", this.webScrapingService.getDataBussines());
      mav.addObject("addForm", DataBolsaBean.builder().build());
      mav.setViewName("create");
      return mav;
  }
  
  @GetMapping(value = "/delete/{id}")
  public ModelAndView deleteBasicFunctions(@PathVariable("id") Long id) {
      basicFunctionsService.deleteBasicFunctionService(id);
      return index();
  }
  
}
