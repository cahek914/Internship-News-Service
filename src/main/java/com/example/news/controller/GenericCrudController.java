package com.example.news.controller;

import com.example.news.service.GenericCrudService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public abstract class GenericCrudController<Entity, FullDto, UpdateDto> {

    public abstract GenericCrudService<Entity, FullDto, UpdateDto> getService();

    @GetMapping("/{id}")
    public ResponseEntity<FullDto> getById(@PathVariable @NotNull Long id) {
        return ResponseEntity.ok(getService().getById(id));
    }

    @PostMapping
    public ResponseEntity<FullDto> save(@Valid @RequestBody UpdateDto dtoUpdate) {
        return ResponseEntity.status(HttpStatus.CREATED).body(getService().save(dtoUpdate));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FullDto> update(@PathVariable @NotNull Long id,
                                          @Valid @RequestBody UpdateDto dtoUpdate) {
        return ResponseEntity.ok(getService().update(id, dtoUpdate));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable @NotNull Long id) {
        getService().delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
