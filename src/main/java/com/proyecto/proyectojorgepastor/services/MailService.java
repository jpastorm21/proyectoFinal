package com.proyecto.proyectojorgepastor.services;

import com.proyecto.proyectojorgepastor.bean.DataBolsaBean;
import com.proyecto.proyectojorgepastor.bean.WebScrapingBean;

public interface MailService {
  
  void sendMailUp(DataBolsaBean bean, WebScrapingBean bean2);
  
  void sendMailDown(DataBolsaBean bean, WebScrapingBean bean2);
  
}
