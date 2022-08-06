package com.tareg.controller;

import com.tareg.cto.CardDetailsDto;
import com.tareg.entity.CardDetails;
import com.tareg.service.CardDetailsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/card")
public class CardDetailsController {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CardDetailsService service;
    @PostMapping
    public ResponseEntity<CardDetailsDto> createPost(@RequestBody CardDetailsDto postDto) {
       return service.createCard(postDto);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CardDetailsDto> getPostById(@PathVariable(name = "id") String id) {
        CardDetails post = service.getCardById(id);

        // convert entity to DTO
        CardDetailsDto postResponse = modelMapper.map(post, CardDetailsDto.class);

        return ResponseEntity.ok().body(postResponse);
    }
    @PutMapping("/{id}")
    public ResponseEntity<CardDetailsDto> updatePost(@PathVariable String id, @RequestBody CardDetailsDto postDto) {

        // convert DTO to Entity
        CardDetails postRequest = modelMapper.map(postDto, CardDetails.class);

        CardDetails post = service.updatePost(id, postRequest);

        // entity to DTO
        CardDetailsDto postResponse = modelMapper.map(post, CardDetailsDto.class);

        return ResponseEntity.ok().body(postResponse);
    }
}
