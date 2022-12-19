package com.proyecto.proyectojorgepastor.bean;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Clase DataBolsaBean
 *
 * Contiene los datos de las empresas nombre y precio, y los valores ingresados por el usuario para la notificación del servicio email, precio mínimo y máximo.
 * También continene las fechas de creación y actualización de la consulta y si ha sido notificado el usuario.
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
public class DataBolsaBean {
  
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
