package com.proyecto.proyectojorgepastor.controller.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Clase WebScrapingDto
 *
 * Contiene .
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DataBolsaDto {
  
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
