package com.proyecto.proyectojorgepastor.commons.mapper;

import java.util.List;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import com.proyecto.proyectojorgepastor.bean.DataBolsaBean;
import com.proyecto.proyectojorgepastor.controller.dto.DataBolsaDto;
import com.proyecto.proyectojorgepastor.controller.dto.request.DataBolsaRequestDto;
import com.proyecto.proyectojorgepastor.dao.domain.DataBolsa;

@Mapper
public interface DataBolsaMapper {
  
  @Named("mapDto")
  DataBolsaDto map(DataBolsaBean entity);
  
  @Named("map")
  DataBolsaBean map(DataBolsaDto entity);
  
  @Named("mapDtoList")
  @IterableMapping(qualifiedByName = { "mapDto" })
  List<DataBolsaDto> mapDtoList(List<DataBolsaBean> content);
  
  @Named("mapEntity")
  DataBolsa mapEntity(DataBolsaBean bean);
  
  @Named("mapBean")
  DataBolsaBean mapEntity(DataBolsa entity);
  
  @Named("mapBeanList")
  @IterableMapping(qualifiedByName = { "mapBean" })
  List<DataBolsaBean> mapEntityList(List<DataBolsa> content);
  
  @Named("mapRDto")
  DataBolsaBean mapRequestDto(DataBolsaRequestDto rdto);
  
}