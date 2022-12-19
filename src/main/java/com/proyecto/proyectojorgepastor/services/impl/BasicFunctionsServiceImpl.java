package com.proyecto.proyectojorgepastor.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.EntityNotFoundException;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import com.proyecto.proyectojorgepastor.bean.DataBolsaBean;
import com.proyecto.proyectojorgepastor.commons.mapper.DataBolsaMapper;
import com.proyecto.proyectojorgepastor.dao.domain.DataBolsa;
import com.proyecto.proyectojorgepastor.dao.repository.DataBolsaRepository;
import com.proyecto.proyectojorgepastor.services.BasicFunctionsService;

/**
 * Clase BasicFunctionsServiceImpl
 *
 * Contiene la logica para el funcionamiento del CRUD.
 *
 * @author Jorge Pastor
 * @version 1.0
 */
@Service
public class BasicFunctionsServiceImpl implements BasicFunctionsService{
  
  private DataBolsaRepository repository;

  private DataBolsaMapper dataBolsaMapper = Mappers.getMapper(DataBolsaMapper.class);
  
  public BasicFunctionsServiceImpl(DataBolsaRepository repository) {
    this.repository = repository;
  }
  
  /**
   * Este método devuelve un objeto que corresponde a una consulta realizada en la app en concreto
   * @author Jorge Pastor
   * @param id es un entero que corresponde a la clave de la consulta en la base de datos.
   * @return La consulta correspondiente al id introducido.
  */
  @Override
  public DataBolsaBean getOneBasicFunctionService(Long id) {
    Optional<DataBolsa> optAmazon = repository.findById(id);
    if (optAmazon.isPresent()) {
      return dataBolsaMapper.mapEntity(optAmazon.get());
    }
    throw new EntityNotFoundException();
  }
  
  /**
   * Este método devuelve una lista de consultas, las cuales hemos introducido a la base de datos.
   * @author Jorge Pastor
   * @return La lista con las consultas de la base de datos.
  */
  @Override
  public List<DataBolsaBean> getAllBasicFunctionService() {
    return dataBolsaMapper.mapEntityList(repository.findAll());
  }
  
  /**
   * Este método guarda una consulta realizada en la app.
   * @author Jorge Pastor
   * @param dataBasic corresponde a un objeto de la consulta relleno con los datos necesarios a introducir en la base de datos.
  */
  @Override
  public DataBolsaBean createBasicFunctionService(DataBolsaBean dataBasic) {
    //validate(dataBasic);
    dataBasic.setCreateDate(LocalDateTime.now());
    DataBolsa saved = repository.save(dataBolsaMapper.mapEntity(dataBasic));
    return dataBolsaMapper.mapEntity(saved);
  }
  
  private void validate(DataBolsaBean dataBasic) {
    //Llamar a los metodos de validación aqui.
    
    isMinor(dataBasic.getPrice(), dataBasic.getMin());
    isMayor(dataBasic.getPrice(), dataBasic.getMax());
    mailValidator(dataBasic.getEmail());
    
  }
  
/**
   * Este método actualiza una consulta realizada en la app.
   * @author Jorge Pastor
   * @param dataBasic corresponde a un objeto de la consulta relleno con los datos necesarios para actualizar en la base de datos.
  */
  @Override
  public DataBolsaBean updateBasicFunctionService(DataBolsaBean dataBasic) {
    DataBolsaBean updated = getOneBasicFunctionService(dataBasic.getId());
    updated.setName(dataBasic.getName());
    updated.setEmail(dataBasic.getEmail());
    updated.setPrice(dataBasic.getPrice());
    updated.setUpdateDate(LocalDateTime.now());
    updated.setNotify(dataBasic.getNotify());
    if (!dataBasic.getMax().equals(updated.getMax()) || !dataBasic.getMin().equals(updated.getMin() )) {
      updated.setNotify(Boolean.FALSE);
    }
    updated.setMax(dataBasic.getMax());
    updated.setMin(dataBasic.getMin());
    
    DataBolsa saved = repository.save(dataBolsaMapper.mapEntity(updated));
    
    return dataBolsaMapper.mapEntity(saved);
  }
  
  /**
   * Este método borra una consulta realizada en la app.
   * @author Jorge Pastor
   * @param id corresponde al identificador de la consulta (campo clave de la base de datos) que queremos borrar en la base de datos.
  */
  @Override
  public void deleteBasicFunctionService(Long id) {
    DataBolsaBean deleted = getOneBasicFunctionService(id);
    repository.delete(dataBolsaMapper.mapEntity(deleted));
  }
  
  public Boolean isMayor(Double price, Double min) {
    Boolean condition = false;
    if (Double.compare(min, price) < 0) { 
        condition = true; 
    } 
    return condition;
    
  }
  
  public Boolean isMinor(Double price, Double max) {
    Boolean condition = false;
    if (Double.compare(max, price) > 0) { 
        condition = true; 
    } 
    return condition;
    
  }
  
  public Boolean mailValidator(String email) {
      Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
      Matcher mather = pattern.matcher(email);
      return mather.find();
  }
  
}
