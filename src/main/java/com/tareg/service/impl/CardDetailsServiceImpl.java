package com.tareg.service.impl;

import com.tareg.builder.CardDetailsBuilder;
import com.tareg.constant.ConstantValues;
import com.tareg.cto.CardDetailsDto;
import com.tareg.cto.RequestWrapperDTO;
import com.tareg.entity.CardDetails;
import com.tareg.exception.CreditCardAlreadyExistException;
import com.tareg.exception.CreditCardDateNotValidException;
import com.tareg.exception.CreditCardNotFoundById;
import com.tareg.exception.CreditCardNumberNotValidException;
import com.tareg.helper.CreditCardHelper;
import com.tareg.repo.CardDetailsRepos;
import com.tareg.service.CardDetailsService;
import com.tareg.util.CommonUtils;
import com.tareg.util.CreditCardBuilderUtils;
import com.tareg.util.FopMapperUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CardDetailsServiceImpl implements CardDetailsService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CardDetailsRepos repos;


    @Override
    public RequestWrapperDTO cardById(String id) {
        RequestWrapperDTO requestWrapperDTO = new RequestWrapperDTO();
        Optional<CardDetails> findById= repos.findById(id);

        if (findById.isPresent()){
            List<CardDetailsBuilder> builderUtils= new ArrayList<>();
            builderUtils.add(CreditCardBuilderUtils.getRegistrationBuilder(findById.get()));
            requestWrapperDTO.setResponse(CreditCardBuilderUtils.buildForObjectMapper(ConstantValues.registration,builderUtils.stream().collect(Collectors.toList())));
            return requestWrapperDTO;
        }
        return null;
    }

    /*
    *  FopEntity addFob = fopRepository.save(FopMapperUtils.mapToFopEntity(fop));
            if (ObjectUtils.isNotEmpty(addFob)) {
                return CommonUtils.mapToWrapper(
                        fop, CommonUtils.buildForObjectMapper(Domain.FOP.getDescription(),
                                FopMapperUtils.mapToFopBuilder(addFob)));
    * */
    @Override
    public RequestWrapperDTO saveDto(@Valid CardDetailsDto dto) throws ParseException {
        RequestWrapperDTO requestWrapperDTO = new RequestWrapperDTO();
      //  boolean valid = CreditCardHelper.isValidCreditCard(dto.getCredNumber());
      boolean valid = CreditCardHelper.validate2(dto.getCredNumber());
    // boolean validDate= CreditCardHelper.isDateValid(String.valueOf(dto.getExpire()));
      //  List<CardDetailsBuilder> builderUtils= new ArrayList<>();
       /* SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/yy");
        simpleDateFormat.setLenient(false);
        Date expiry = simpleDateFormat.parse(dto.getExpire().toString());*/
       // boolean expired = expiry.before(new Date());
        if (valid) {
            //if (validDate) {


                CardDetails cardDetails = repos.save(CreditCardBuilderUtils.mapToCardDetailsEntity(dto));
                //  requestWrapperDTO.setResponse(CreditCardBuilderUtils.buildForObjectMapper(ConstantValues.registration,builderUtils.stream().collect(Collectors.toList())));
                if (ObjectUtils.isNotEmpty(cardDetails)) {
                    return CommonUtils.mapToWrapper(
                            dto, CommonUtils.buildForObjectMapper(ConstantValues.registration,
                                    FopMapperUtils.mapToFopBuilder(cardDetails))
                    );
                }
            /*
            *  if (ObjectUtils.isNotEmpty(addFob)) {
                return CommonUtils.mapToWrapper(
                        fop, CommonUtils.buildForObjectMapper(Domain.FOP.getDescription(),
                                FopMapperUtils.mapToFopBuilder(addFob)));
            * */

                return requestWrapperDTO;
            } else {
                throw new CreditCardNumberNotValidException(dto.getCredNumber());
            }
       // }
       // throw new CreditCardDateNotValidException(dto.getCredNumber());
    }

    @Override
    public List<CardDetails> findAllCredit() {
        return repos.findAll();
    }

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

    /*
    *  @Override
    public RequestWrapperDTO getS3ById(String id) {
        RequestWrapperDTO requestWrapperDTO = new RequestWrapperDTO();
        Optional<S3ConfigurationEntity> findById= repo.findById(id);
        if (findById.isPresent()){
            List<S3ConfigBuilder> builderUtils= new ArrayList<>();
            builderUtils.add(S3ConfigBuilderUtil.getRegistrationBuilder(findById.get()));
            requestWrapperDTO.setResponse(S3ConfigBuilderUtil.buildForObjectMapper(ConstantValues.registration,builderUtils.stream().collect(Collectors.toList())));
           return requestWrapperDTO;
        }
        return null;
    }
    *
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

    @Override
    public boolean isValid(String dateStr) {
        return false;
    }
}
