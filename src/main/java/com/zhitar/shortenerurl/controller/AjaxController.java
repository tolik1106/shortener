package com.zhitar.shortenerurl.controller;

import com.zhitar.shortenerurl.domain.Link;
import com.zhitar.shortenerurl.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alllinks")
public class AjaxController {

    @Autowired
    private LinkService service;

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Link get(@PathVariable long id) {
        return service.getById(id);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Link> getAll() {
        return service.getAll();
    }

    @PostMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void enable(@PathVariable long id, @RequestParam boolean active) {
        service.enable(id, active);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id) {
        service.delete(id);
    }

    @PostMapping
    public ResponseEntity<String> update(Link link) {
        service.update(link);
        return ResponseEntity.ok().build();
    }
}
