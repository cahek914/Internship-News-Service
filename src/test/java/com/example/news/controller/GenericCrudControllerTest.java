package com.example.news.controller;

import com.example.news.exception.GenericServiceException;
import com.example.news.mapper.GenericMapper;
import com.example.news.model.DtoId;
import com.example.news.model.TextDto;
import com.example.news.service.GenericCrudService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.assertj.core.internal.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public abstract class GenericCrudControllerTest<Entity, FullDto extends DtoId, UpdateDto extends TextDto> {

    protected abstract Class<FullDto> getFullDtoClass();

    protected abstract Class<UpdateDto> getUpdateDtoClass();

    protected abstract String getRootUrl();

    protected abstract String getUrlWithId();

    protected abstract FullDto getFullDto();

    protected abstract GenericMapper<Entity, FullDto, UpdateDto> getMapper();

    protected abstract GenericCrudService<Entity, FullDto, UpdateDto> getService();

    @Autowired
    private WebApplicationContext webApplicationContext;

    private ObjectMapper objectMapper;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
    }

    @Test
    public void getByIdIsOk() throws Exception {

        FullDto fullDto = getFullDto();

        when(getService().getById(anyLong())).thenReturn(fullDto);

        mockMvc.perform(MockMvcRequestBuilders.get(getUrlWithId(), anyLong()))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(getService()).getById(anyLong());
    }

    @Test
    void getByNotExistIdReturnBadRequest() throws Exception {

        when(getService().getById(anyLong())).thenThrow(GenericServiceException.NotFound.class);

        mockMvc.perform(MockMvcRequestBuilders.get(getUrlWithId(), anyLong()))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        verify(getService()).getById(anyLong());
    }

    @Test
    void postValidData() throws Exception {

        FullDto fullDto = getFullDto();

        String jsonBody = objectMapper.writeValueAsString(getMapper().mapFullDtoToUpdateDto(fullDto));

        when(getService().save(any(getUpdateDtoClass()))).thenReturn(fullDto);

        String response = mockMvc.perform(MockMvcRequestBuilders
                        .post(getRootUrl())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn().getResponse().getContentAsString();

        verify(getService()).save(any(getUpdateDtoClass()));

        FullDto fullDtoDb = objectMapper.readValue(response, getFullDtoClass());
        assertThat(fullDtoDb).isEqualTo(fullDto);
    }

    @Test
    void postInvalidDataReturnBadRequest() throws Exception {

        UpdateDto updateDto = getMapper().mapFullDtoToUpdateDto(getFullDto());
        updateDto.setText(RandomString.make(3000));
        String jsonBody = objectMapper.writeValueAsString(updateDto);

        mockMvc.perform(MockMvcRequestBuilders
                        .post(getRootUrl())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        verify(getService(), never()).save(any(getUpdateDtoClass()));
    }

    @Test
    void updateValidData() throws Exception {

        FullDto fullDto = getFullDto();
        UpdateDto updateDto = getMapper().mapFullDtoToUpdateDto(fullDto);

        String jsonBody = objectMapper.writeValueAsString(updateDto);

        when(getService().update(anyLong(), any(getUpdateDtoClass()))).thenReturn(fullDto);

        String response = mockMvc.perform(MockMvcRequestBuilders
                        .put(getUrlWithId(), eq(anyLong()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();

        verify(getService()).update(anyLong(), any(getUpdateDtoClass()));

        FullDto fullDtoDb = objectMapper.readValue(response, getFullDtoClass());
        assertThat(fullDtoDb).isEqualTo(fullDto);
    }

    @Test
    void updateInvalidDataReturnBadRequest() throws Exception {

        FullDto fullDto = getFullDto();
        UpdateDto updateDto = getMapper().mapFullDtoToUpdateDto(fullDto);
        updateDto.setText(RandomString.make(3000));

        String jsonBody = objectMapper.writeValueAsString(updateDto);

        mockMvc.perform(MockMvcRequestBuilders
                        .put(getUrlWithId(), fullDto.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        verify(getService(), never()).update(anyLong(), any(getUpdateDtoClass()));
    }

    @Test
    void deleteData() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                        .delete(getUrlWithId(), anyLong()))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

        verify(getService()).delete(anyLong());
    }

}