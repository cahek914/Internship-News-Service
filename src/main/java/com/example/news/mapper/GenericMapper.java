package com.example.news.mapper;

import org.mapstruct.MappingTarget;

public interface GenericMapper<Entity, FullDto, UpdateDto> {

    FullDto mapEntityToFullDto(Entity entity);

    UpdateDto mapFullDtoToUpdateDto(FullDto fullDto);

    Entity mapForSaveEntity(UpdateDto updateDto);

    Entity mapForUpdateEntity(@MappingTarget Entity entity, UpdateDto updateDto);

}
