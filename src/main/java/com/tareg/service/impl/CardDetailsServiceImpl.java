package com.tareg.service.impl;

import com.tareg.cto.CardDetailsDto;
import com.tareg.entity.CardDetails;
import com.tareg.exception.CreditCardAlreadyExistException;
import com.tareg.exception.CreditCardNotFoundById;
import com.tareg.exception.CreditCardNumberNotValidException;
import com.tareg.helper.CreditCardHelper;
import com.tareg.repo.CardDetailsRepos;
import com.tareg.service.CardDetailsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CardDetailsServiceImpl implements CardDetailsService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CardDetailsRepos repos;


    @Override
    public CardDetails updatePost(String id, CardDetails cardDetails) {

        CardDetails cardDetails1 =repos.findById(id).get();

        String findCard=CreditCardHelper.maskCardNumber(cardDetails.getCredNumber());

        cardDetails1.setCredNumber(findCard);
        cardDetails1.setName(cardDetails.getName());
        cardDetails1.setExpire(cardDetails.getExpire());
        return repos.save(cardDetails1);
      /*  Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));

        post.setTitle(postRequest.getTitle());
        post.setDescription(postRequest.getDescription());
        post.setContent(postRequest.getContent());
        return postRepository.save(post);*/
    }

    /*  @Override
        public CardDetails createCard(CardDetails cardDetails) throws CreditCardAlreadyExistException, CreditCardNumberNotValidException{
            if (CreditCardHelper.isValid(cardDetails.getCredNumber())) {
                // convert DTO to entity
                CardDetails postRequest = modelMapper.map(cardDetails, CardDetails.class);

                CardDetails post = repos.save(postRequest);

                // convert entity to DTO
                CardDetailsDto postResponse = modelMapper.map(post, CardDetailsDto.class);
               return postRequest;


            } else {
                throw new CreditCardNumberNotValidException(cardDetails.getCredNumber());
            }
        }*/
/*
*
* @Override
	public Post getPostById(long id) {
		Optional<Post> result = postRepository.findById(id);
		if(result.isPresent()) {
			return result.get();
		}else {
			throw new ResourceNotFoundException("Post", "id", id);
		}
* */
    @Override
    public CardDetails getCardById(String id) {
        CardDetails cardDetails =repos.findById(id).get();

       String findCard=CreditCardHelper.maskCardNumber(cardDetails.getCredNumber());
       cardDetails.setCredNumber(findCard);
       return cardDetails;
       /* Optional<CardDetails> rseult= repos.findById(id);
        if(rseult.isPresent()) {

            return rseult.get();
        }else {
            throw new CreditCardNotFoundById("could not found id number"+id);
        }*/
    }

    @Override
    public ResponseEntity<CardDetailsDto> createCard(CardDetailsDto postDto) throws CreditCardAlreadyExistException, CreditCardNumberNotValidException {
        boolean valid = CreditCardHelper.isValidCreditCard(postDto.getCredNumber());
        if (valid) {
         // convert DTO to entity
        CardDetails postRequest = modelMapper.map(postDto, CardDetails.class);

        CardDetails post = repos.save(postRequest);

        // convert entity to DTO
        CardDetailsDto postResponse = modelMapper.map(post, CardDetailsDto.class);

        return new ResponseEntity<CardDetailsDto>(postResponse, HttpStatus.CREATED);

        } else {
            throw new CreditCardNumberNotValidException(postDto.getCredNumber());
     }

        /*
        * public Post updatePost(long id, Post postRequest) {
		Post post = postRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));

		post.setTitle(postRequest.getTitle());
		post.setDescription(postRequest.getDescription());
		post.setContent(postRequest.getContent());
		return postRepository.save(post);
	}
        *
        * */
    }
}
