package com.proyecto.proyectojorgepastor.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.proyecto.proyectojorgepastor.bean.DataBolsaBean;
import com.proyecto.proyectojorgepastor.bean.WebScrapingBean;
import com.proyecto.proyectojorgepastor.services.BasicFunctionsService;
import com.proyecto.proyectojorgepastor.services.IntegrationService;
import com.proyecto.proyectojorgepastor.services.MailService;
import com.proyecto.proyectojorgepastor.services.WebScrapingService;

/**
 * Clase IntegrationServiceImpl
 *
 * Contiene parte de la lógica del programa, encargado de ver si es necesario notificar al usuario y cambiarlo en base de datos.
 *
 * @author Jorge Pastor
 * @version 1.0
 */

@Service
public class IntegrationServiceImpl implements IntegrationService {
  
  public BasicFunctionsService basicFunctionsService;
  public WebScrapingService webScrapingService;
  public MailService mailService;
  
  public IntegrationServiceImpl (BasicFunctionsService basicFunctionsService, WebScrapingService webScrapingService, MailService mailService) {
    this.basicFunctionsService = basicFunctionsService;
    this.webScrapingService = webScrapingService;
    this.mailService = mailService;
  }
  
  /**
   * Este método revisa las consultas de la base de datos, verifica quien no ha sido notificado 
   * y si se cumple la condición de que el precio mínimo o máximo introducido es mayor o menor respectivamente, se envía un mensaje.
   * @author Jorge Pastor
  */
  @Override
  public void checkBolsaData() {
    List<DataBolsaBean> listBasicFunctions = basicFunctionsService.getAllBasicFunctionService();
    listBasicFunctions.removeIf(b-> Boolean.TRUE.equals(b.getNotify()));
    List<WebScrapingBean> listWebScraping = webScrapingService.getDataBussines();
    for (DataBolsaBean bean : listBasicFunctions) {
      for (WebScrapingBean bean2 : listWebScraping) {
        if (bean.getName().equals(bean2.getName())) {
          if (bean.getMin() >= bean2.getPrice()) {
            mailService.sendMailDown(bean, bean2);
            updateNotification(bean);
          }
          if (bean.getMax() <= bean2.getPrice()) {
            mailService.sendMailUp(bean, bean2);
            updateNotification(bean);
          }
        }
      }
    }
  }
  
  /**
   * Este método actualiza en la base de datos cambiando el campo notify a true.
   * @author Jorge Pastor
   * @param bean es un objeto de tipo DataBolsaBean necesario para poder actualizar en la base de datos.
  */
  private void updateNotification(DataBolsaBean bean) {
    bean.setNotify(Boolean.TRUE);
    basicFunctionsService.updateBasicFunctionService(bean);
  }
  
}
