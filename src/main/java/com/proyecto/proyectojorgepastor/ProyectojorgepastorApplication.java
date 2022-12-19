package com.proyecto.proyectojorgepastor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ProyectojorgepastorApplication {
  
  /**
   * Este método se encarga de iniciar la ejecución del programar
   * Éste es el método principal del proyecto
   * @author jorgepastor
   * @param args[] es un arreglo con los parámetros que el reciba por consola
   * @return void
  */
  public static void main(String[] args) {
    SpringApplication.run(ProyectojorgepastorApplication.class, args);
  }
  
}
