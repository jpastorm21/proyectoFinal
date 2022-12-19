package com.proyecto.proyectojorgepastor.dao.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * Clase DataBolsa
 *
 * Contiene .
 *
 * @author Jorge Pastor
 * @version 1.0
 */

@Data
@Entity
@Table(name = "data_bolsa")
public class DataBolsa {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  @Column(nullable = false)
  private String name;
  
  @Column(nullable = false)
  private double price;
  
  @Column(nullable = false)
  private String email;
  
  @Column(nullable = false)
  private boolean notify;
  
  @Column(nullable = false)
  private double min;
  
  @Column(nullable = false)
  private double max;
  
  @Column(nullable = true)
  private LocalDateTime updateDate;
  
  @Column(nullable = false)
  private LocalDateTime createDate;
  
}
