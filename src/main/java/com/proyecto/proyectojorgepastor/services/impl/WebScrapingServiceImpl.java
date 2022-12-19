package com.proyecto.proyectojorgepastor.services.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import com.proyecto.proyectojorgepastor.bean.WebScrapingBean;
import com.proyecto.proyectojorgepastor.services.WebScrapingService;

/**
 * Clase WebScrapingServiceImpl
 *
 * Contiene los metodos para sacar la información de la web "https://www.infobolsa.es/acciones/ibex35" mediante web scraping.
 *
 * @author Jorge Pastor
 * @version 1.0
 */

@Service
public class WebScrapingServiceImpl implements WebScrapingService{
  
  public WebScrapingServiceImpl() {
  
  }
  
  /**
   * Este método devuelve una lista datos de las empresas del IBEX 35 .
   * @author Jorge Pastor
   * @return Una lista con el nombre de las empresas del IBEX 35.
  */
  @Override
  public List<WebScrapingBean> getBussines() {
    List<WebScrapingBean> listBeans = new ArrayList<>();
    try {
      Document document = Jsoup.connect(
              "https://www.infobolsa.es/acciones/ibex35")
              .get();
      Element element = document.getElementById("instrumentTable");
      List<Element> documentoProcesado = element.getElementsByClass("normal");
      for (Element str : documentoProcesado) {
        String a = str.getElementsByClass("name").text();
        String b = str.getElementsByClass("price").text().replace(",", ".");
        if (StringUtils.isNotBlank(b)) {
          double bDouble = Double.parseDouble(b);
          WebScrapingBean bean = new WebScrapingBean();
          bean.setName(a);
          listBeans.add(bean);
        }
      }
    } catch (IOException e) {
    }
    return listBeans;
  }
  
  /**
   * Este método devuelve una lista datos de las empresas del IBEX 35 .
   * @author Jorge Pastor
   * @return Una lista con el nombre y el precio de las empresas del IBEX 35.
  */
  @Override
  public List<WebScrapingBean> getDataBussines() {
    List<WebScrapingBean> listBeans = new ArrayList<>();
    try {
      Document document = Jsoup.connect(
          "https://www.infobolsa.es/acciones/ibex35")
              .get();
      Element element = document.getElementById("instrumentTable");
      List<Element> documentoProcesado = element.getElementsByClass("normal");
      for (Element str : documentoProcesado) {
        String a = str.getElementsByClass("name").text();
        String b = str.getElementsByClass("price").text().replace(",", ".");
        if (StringUtils.isNotBlank(b)) {
          double bDouble = Double.parseDouble(b);
          WebScrapingBean bean = new WebScrapingBean();
          bean.setName(a);
          bean.setPrice(bDouble);
          listBeans.add(bean);
        }
      }
    } catch (IOException e) {
    }
    return listBeans;
  }
  
}
