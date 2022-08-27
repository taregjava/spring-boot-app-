package com.tareg.service;

import com.tareg.cto.CardDetailsDto;
import com.tareg.cto.RequestWrapperDTO;
import com.tareg.entity.CardDetails;
import com.tareg.exception.CreditCardAlreadyExistException;
import com.tareg.exception.CreditCardNumberNotValidException;
import org.springframework.http.ResponseEntity;

import java.text.ParseException;
import java.util.List;

public interface CardDetailsService {

  //  CardDetails createCard(CardDetails cardDetails);
/*
* List<Post> getAllPosts();

	Post createPost(Post post);

	C updatePost(long id, Post post);

	void deletePost(long id);

	Post getPostById(long id);
*
* */
  RequestWrapperDTO cardById(String id);
  RequestWrapperDTO saveDto(CardDetailsDto dto) throws ParseException;
  List<CardDetails> findAllCredit();
  CardDetails updatePost(String id, CardDetails cardDetails);
    CardDetails getCardById(String id);
    ResponseEntity<CardDetailsDto> createCard(CardDetailsDto postDto) throws CreditCardAlreadyExistException, CreditCardNumberNotValidException;

  boolean isValid(String dateStr);

}
