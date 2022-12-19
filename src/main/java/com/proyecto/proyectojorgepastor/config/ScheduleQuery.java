package com.proyecto.proyectojorgepastor.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.proyecto.proyectojorgepastor.services.IntegrationService;

/**
 * Clase ScheduleQuery
 *
 * Contiene el cron que relanza la petición del método que extrae la información de la pagina web.
 *
 * @author Jorge Pastor
 * @version 1.0
 */

@Component
public class ScheduleQuery {
  
  @Autowired
  @Lazy
  public IntegrationService integrationService;
  
  @Scheduled(cron = "0 0/1 * * * ?")
  public void scheduleQuery() {
    
    integrationService.checkBolsaData();
    
  }
  
}
