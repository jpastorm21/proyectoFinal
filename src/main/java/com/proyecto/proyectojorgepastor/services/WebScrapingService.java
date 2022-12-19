package com.proyecto.proyectojorgepastor.services;

import java.util.List;

import com.proyecto.proyectojorgepastor.bean.WebScrapingBean;

public interface WebScrapingService {
  
  List<WebScrapingBean> getBussines();
  
  List<WebScrapingBean> getDataBussines();

}
