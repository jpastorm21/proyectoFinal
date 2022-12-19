package com.proyecto.proyectojorgepastor;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.apache.commons.collections4.IterableUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import com.proyecto.proyectojorgepastor.bean.DataBolsaBean;
import com.proyecto.proyectojorgepastor.commons.mapper.DataBolsaMapper;
import com.proyecto.proyectojorgepastor.dao.domain.DataBolsa;
import com.proyecto.proyectojorgepastor.dao.repository.DataBolsaRepository;
import com.proyecto.proyectojorgepastor.services.BasicFunctionsService;
import com.proyecto.proyectojorgepastor.services.impl.BasicFunctionsServiceImpl;


class BasicFunctionsServiceImplTests {

    private DataBolsaRepository repository;
    private BasicFunctionsService service;

    private DataBolsaMapper dataBolsaMapper = Mappers.getMapper(DataBolsaMapper.class);

    @BeforeEach
    public void setUpBeforeClass(){
        repository = mock(DataBolsaRepository.class);
        service = new BasicFunctionsServiceImpl(repository);
    }

    @DisplayName("getOneBasicFunctionsServiceTest")
    @Test
    void getOneBasicFunctionsServiceTest() {
        DataBolsa entity = new DataBolsa();
        LocalDateTime date = LocalDateTime.now();
        entity.setCreateDate(date);
        entity.setId(1L);
        entity.setName("testName");
        entity.setPrice(1111111.11);
        entity.setEmail("testEmail");
        entity.setMin(1111111.11);
        entity.setMax(1111111.11);

        when(repository.findById(anyLong())).thenReturn(Optional.of(entity));

        DataBolsaBean bean = service.getOneBasicFunctionService(1L);

        assertEquals(Long.valueOf(1), bean.getId());
        assertEquals(date, bean.getCreateDate());
        assertEquals("testName", bean.getName());
        assertEquals(1111111.11, bean.getPrice(), 0);
        assertEquals("testEmail", bean.getEmail());
        assertEquals(1111111.11, bean.getPrice(), 0);
        assertEquals(1111111.11, bean.getPrice(), 0);
    }

    @Test
    @DisplayName("getOneBasicFunctionsServiceTestCatch")
    void getOneBasicFunctionsServiceWithExceptionTest() {

        assertThrows(EntityNotFoundException.class, () -> {
          service.getOneBasicFunctionService(anyLong());
    });
    }

    @Test
    @DisplayName("getAllBasicFunctionsServiceTest")
    void getAllBasicFunctionsTest(){
        LocalDateTime date = LocalDateTime.now();

        DataBolsa entity = new DataBolsa();
        entity.setCreateDate(date);
        entity.setId(1L);
        entity.setName("testName");
        entity.setPrice(1111111.11);
        entity.setEmail("testEmail");
        entity.setMin(1111111.11);
        entity.setMax(1111111.11);

        DataBolsa entity2 = new DataBolsa();
        entity2.setCreateDate(date);
        entity2.setId(2L);
        entity2.setName("testName2");
        entity2.setPrice(1111111.11);
        entity2.setEmail("testEmail2");
        entity2.setMin(1111111.11);
        entity2.setMax(1111111.11);

        List<DataBolsa> list = new ArrayList();
        list.add(entity);
        list.add(entity2);

        when(repository.findAll()).thenReturn(list);

        Iterable<DataBolsaBean> it = service.getAllBasicFunctionService();

        assertEquals(list.size(), IterableUtils.size(it));
    }
    @Test
    @DisplayName("createBasicFunctionsServiceTest")
    void createBasicFunctionsServiceTest() {
        LocalDateTime date = LocalDateTime.now();

        DataBolsaBean input = new DataBolsaBean();
        input.setCreateDate(date);
        input.setId(1L);
        input.setName("testName");
        input.setPrice(1111111.11);
        input.setEmail("testEmail");
        input.setMin(1111111.11);
        input.setMax(1111111.11);

        when(repository.save(any())).thenReturn(dataBolsaMapper.mapEntity(input));
        
        DataBolsaBean result = service.createBasicFunctionService(input);
        assertEquals(date, result.getCreateDate());
        assertEquals(Long.valueOf(1), result.getId());
        assertEquals("testName", result.getName());
        assertEquals(1111111.11, result.getPrice(), 0);
        assertEquals("testEmail", result.getEmail());
        assertEquals(1111111.11, result.getPrice(), 0);
        assertEquals(1111111.11, result.getPrice(), 0);
    }

    @Test
    @DisplayName("UpdateOneBasicFunctionsServiceTest")
    void UpdateOneBasicFunctionsServiceTest() {
        DataBolsa entity = new DataBolsa();
        LocalDateTime date = LocalDateTime.now();
        entity.setCreateDate(date);
        entity.setId(1L);
        entity.setName("testName");
        entity.setPrice(1111111.11);
        entity.setEmail("testEmail");
        entity.setMin(1111111.11);
        entity.setMax(1111111.11);

        when(repository.findById(anyLong())).thenReturn(Optional.of(entity));

        DataBolsaBean input = new DataBolsaBean();
        input.setCreateDate(date);
        input.setId(1L);
        input.setName("testName");
        input.setPrice(1111111.11);
        input.setEmail("testEmail");
        entity.setMin(1111111.11);
        entity.setMax(1111111.11);

        when(repository.save(any())).thenReturn(dataBolsaMapper.mapEntity(input));

        /*DataBolsaBean bean = service.updateBasicFunctionService(input);
        
        assertEquals(date, bean.getCreateDate());
        assertEquals(Long.valueOf(1), bean.getId());
        assertEquals("testName", bean.getName());
        assertEquals(1111111.11, bean.getPrice(), 0);
        assertEquals("testEmail", bean.getEmail());
        assertEquals(1111111.11, bean.getPrice(), 0);
        assertEquals(1111111.11, bean.getPrice(), 0);*/
    }

    @Test
    @DisplayName("deleteBasicFunctionsServiceTest")
    void deleteBasicFunctionsServiceTest() {
        DataBolsa entity = new DataBolsa();
        entity.setId(1L);

        when(repository.findById(anyLong())).thenReturn(Optional.of(entity));
        service.deleteBasicFunctionService(anyLong());
    }

}
