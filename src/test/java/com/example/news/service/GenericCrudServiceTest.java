package com.example.news.service;

import com.example.news.data.DatabaseCleaner;
import com.example.news.data.TestDataProvider;
import com.example.news.exception.GenericServiceException;
import com.example.news.mapper.GenericMapper;
import com.example.news.model.DtoId;
import com.example.news.model.DtoText;
import org.assertj.core.internal.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ValidationException;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public abstract class GenericCrudServiceTest<Entity, FullDto extends DtoText & DtoId, UpdateDto extends DtoText> {

    protected static final int MINIMUM_ARRAY_SIZE = 5;
    protected static final int ARRAY_SIZE_DELTA = 10;

    @Autowired
    protected TestDataProvider dataProvider;

    @Autowired
    private DatabaseCleaner databaseCleaner;

    protected abstract UpdateDto getUpdateDto();

    protected abstract GenericCrudService<Entity, FullDto, UpdateDto> getService();

    protected abstract GenericMapper<Entity, FullDto, UpdateDto> getMapper();

    @AfterEach
    public void tearDown() {
        databaseCleaner.clean();
    }

    @Test
    public void createdEntityShouldEqualToRetrieved() {

        UpdateDto updateDto = getUpdateDto();
        UpdateDto savedUpdateDto = getMapper().mapFullDtoToUpdateDto(
                getService().save(updateDto)
        );
        assertThat(updateDto).isEqualTo(savedUpdateDto);

    }

    @Test
    public void createdEntityListSizeShouldEqualToRetrieved() {

        List<FullDto> fullDtos = fillDatabaseBySimpleEntities();
        assertThat(fullDtos.size()).isGreaterThanOrEqualTo(MINIMUM_ARRAY_SIZE);

        List<FullDto> fullDtosDb = getService().getList();
        assertThat(fullDtosDb.size()).isEqualTo(fullDtos.size());

    }

    @Test
    public void updatedEntityTextShouldBeStoredToDatabase() {

        UpdateDto dtoUpdate = getUpdateDto();
        Long savedId = getService().save(dtoUpdate).getId();

        dtoUpdate.setText("some new description");

        FullDto fullDto = getService().update(savedId, dtoUpdate);
        assertThat(fullDto.getText()).isEqualTo(dtoUpdate.getText());

    }

    @Test
    public void deletedEntityShouldNotExistInDatabase() {

        List<FullDto> fullDtos = fillDatabaseBySimpleEntities();
        assertThat(fullDtos.size()).isGreaterThanOrEqualTo(MINIMUM_ARRAY_SIZE);

        Long idToDelete = fullDtos.get(fullDtos.size() / 2).getId();
        getService().delete(idToDelete);

        List<FullDto> fullDtosDb = getService().getList();

        assertThat(fullDtosDb.size()).isEqualTo(fullDtos.size() - 1);
        assertThat(fullDtosDb.stream().anyMatch(dto -> dto.getId().equals(idToDelete))).isFalse();

    }

    @Test
    public void shouldThrownByValidationExceptionIfEntityTextIncreaseMaxSize() {

        UpdateDto updateDto = getUpdateDto();
        updateDto.setText(RandomString.make(3000));
        assertThatThrownBy(() -> getService().save(updateDto))
                .isInstanceOf(ValidationException.class);

    }

    @Test
    public void shouldThrownExceptionIfEntityToGetByIdNotExist() {

        assertThatThrownBy(() -> getService().getById(100000L))
                .isInstanceOf(GenericServiceException.NotFound.class);

    }

    @Test
    public void shouldThrownExceptionIfEntityToDeleteByIdNotExist() {

        assertThatThrownBy(() -> getService().delete(100000L))
                .isInstanceOf(GenericServiceException.NotFound.class);

    }

    private List<FullDto> fillDatabaseBySimpleEntities() {
        return dataProvider.getRandomListOf(this::getUpdateDto, MINIMUM_ARRAY_SIZE, ARRAY_SIZE_DELTA)
                .stream()
                .map(getService()::save)
                .collect(Collectors.toList());
    }

}