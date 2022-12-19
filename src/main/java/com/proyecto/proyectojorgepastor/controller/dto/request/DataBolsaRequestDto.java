package com.proyecto.proyectojorgepastor.controller.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Clase DataBolsaRequestDto
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
public class DataBolsaRequestDto {
  
  private String name;
  private double price;
  private String email;
  private double min;
  private double max;
  
}