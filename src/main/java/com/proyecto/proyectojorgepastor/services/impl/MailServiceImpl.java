package com.proyecto.proyectojorgepastor.services.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.proyecto.proyectojorgepastor.bean.DataBolsaBean;
import com.proyecto.proyectojorgepastor.bean.MailBean;
import com.proyecto.proyectojorgepastor.bean.WebScrapingBean;
import com.proyecto.proyectojorgepastor.services.MailService;

import lombok.extern.slf4j.Slf4j;

/**
 * Clase MailServiceImpl
 *
 * Contiene los metodos para los envíos de los email de aviso a los usuarios.
 *
 * @author Jorge Pastor
 * @version 1.0
 */

@Slf4j
@Service
public class MailServiceImpl implements MailService {
  
  private JavaMailSender javaMailSender;
  
  @Value("${spring.mail.username}") 
  private String sender;
  
  public MailServiceImpl (JavaMailSender javaMailSender){
    this.javaMailSender = javaMailSender;
  }
  
  /**
   * Este método envía un email de notificación.
   * @author Jorge Pastor
   * @param MailBean contiene los datos necesarios para poder envias un mensaje al usuario. 
  */
  private void sendMail(MailBean bean) {
    try {
      log.info("Init sending mail " + bean.getRecipient());
      SimpleMailMessage mailMessage = new SimpleMailMessage();
      mailMessage.setFrom(sender);
      mailMessage.setTo(bean.getRecipient());
      mailMessage.setText(bean.getMsgBody());
      mailMessage.setSubject(bean.getSubject());
      
      javaMailSender.send(mailMessage);
      log.info("End sending mail " + bean.getRecipient());
    }
    catch (Exception e) {
      log.error("Error sending mail");
    }
  }
  
  /**
   * Este método crea el mailBean cuando sube de precio la accion para enviar el mensaje.
   * @author Jorge Pastor
   * @param DatabolsaBean contiene información de la consulta en la base de datos. 
   * WebScrapingBean contiene la información recogida de la página web mediante web scraping.
  */
  @Override
  public void sendMailUp (DataBolsaBean bean, WebScrapingBean bean2) {
    
    MailBean mailBean = MailBean.builder().recipient(bean.getEmail()).msgBody("Aviso: El precio de la acción en la empresa " + bean.getName() 
    + " ha subido, se encuentra actualmente en : " + bean2.getPrice()).subject("Subida en el precio de la bolsa").build();
    
    sendMail(mailBean);
  }
  
  /**
   * Este método crea el mailBean cuando baja de precio la accion para enviar el mensaje.
   * @author Jorge Pastor
   * @param DatabolsaBean contiene información de la consulta en la base de datos. 
   * WebScrapingBean contiene la información recogida de la página web mediante web scraping.
  */
  @Override
  public void sendMailDown (DataBolsaBean bean, WebScrapingBean bean2) {
    
    MailBean mailBean = MailBean.builder().recipient(bean.getEmail()).msgBody("Aviso: El precio de la acción en la empresa " 
    + bean.getName() + " ha bajado, se encuentra actualmente en : " + bean2.getPrice()).subject("Bajada en el precio de la bolsa").build();
    
    sendMail(mailBean);
  }
}
