package com.proyecto.proyectojorgepastor.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Clase MailBean
 *
 * Contiene información del email: correo electronico del emisor, el mensaje y el asunto.
 *
 * @author Jorge Pastor
 * @version 1.0
 */

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class MailBean {
  
  private String recipient;
  private String msgBody;
  private String subject;
  
}
