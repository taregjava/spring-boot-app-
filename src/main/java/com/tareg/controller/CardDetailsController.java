package com.tareg.controller;

import com.tareg.cto.CardDetailsDto;
import com.tareg.cto.RequestWrapperDTO;
import com.tareg.entity.CardDetails;
import com.tareg.service.CardDetailsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping("dto/{id}")
    public ResponseEntity<RequestWrapperDTO> getRegistrationById(@PathVariable String id){
        return new ResponseEntity<>(service.cardById(id), HttpStatus.OK);
    }

    @PostMapping("/dto")
    // @ApiOperation(value = "Add Fops",consumes = "application/json",produces = "application/json")
    public ResponseEntity<RequestWrapperDTO> addCard(@Valid @RequestBody CardDetailsDto cardDetails) throws ParseException {
        return new ResponseEntity<>(service.saveDto(cardDetails), HttpStatus.OK);
    }

    @GetMapping
    public List<CardDetailsDto> getAllCard() {

        return service.findAllCredit().stream().map(credit -> modelMapper.map(credit, CardDetailsDto.class))
                .collect(Collectors.toList());
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
