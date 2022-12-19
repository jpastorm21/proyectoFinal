package com.proyecto.proyectojorgepastor.bean;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Clase WebScrapingBean
 *
 * Contiene Contiene información que recogeremos en la web.
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
public class WebScrapingBean {
  
  private Long id;
  private String name;
  private Double price;
  private String email;
  private Boolean notify;
  private Double min;
  private Double max;
  private LocalDateTime updateDate;
  private LocalDateTime createDate;
  
}
