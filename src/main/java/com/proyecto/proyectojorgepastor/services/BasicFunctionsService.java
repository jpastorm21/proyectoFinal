package com.proyecto.proyectojorgepastor.services;

import java.util.List;

import com.proyecto.proyectojorgepastor.bean.DataBolsaBean;

public interface BasicFunctionsService {
  
  DataBolsaBean getOneBasicFunctionService(Long id);
  
  List<DataBolsaBean> getAllBasicFunctionService();
  
  DataBolsaBean createBasicFunctionService(DataBolsaBean dataBasic);
  
  DataBolsaBean updateBasicFunctionService(DataBolsaBean dataBasic);
  
  void deleteBasicFunctionService(Long id);
  
}
