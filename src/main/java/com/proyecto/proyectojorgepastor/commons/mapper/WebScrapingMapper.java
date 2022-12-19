package com.proyecto.proyectojorgepastor.commons.mapper;

import java.util.List;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import com.proyecto.proyectojorgepastor.bean.WebScrapingBean;
import com.proyecto.proyectojorgepastor.controller.dto.WebScrapingDto;


@Mapper
public interface WebScrapingMapper {

  @Named("mapDto")
  WebScrapingDto map(WebScrapingBean entity);

  @Named("map")
  WebScrapingBean map(WebScrapingDto entity);

  @Named("mapDtoList")
  @IterableMapping(qualifiedByName = { "mapDto" })
  List<WebScrapingDto> mapDtoList(List<WebScrapingBean> content);


}
