package com.tareg.entity.error;

import com.tareg.constant.ConstantValues;
import com.tareg.cto.RequestWrapperDTO;
import com.tareg.cto.StatusResponse;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ControllerAdvice
@RestController
public class ValidationHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<RequestWrapperDTO> handleConstraintViolationException(ConstraintViolationException ex) {
      RequestWrapperDTO reqWDTO = new RequestWrapperDTO();

        List<StatusResponse> errors;
 /*
        for (ConstraintViolation violation : ex.getConstraintViolations()) {
            error.getViolations().add(new Violation(violation.getPropertyPath().toString(), violation.getMessage()));
        }*/


      /*
      1
      *  ValidationErrorResponse error = new ValidationErrorResponse();
        for (ConstraintViolation violation : e.getConstraintViolations()) {
            error.getViolations().add(new Violation(violation.getPropertyPath().toString(), violation.getMessage()));
        }
        return error;

        2
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) ->{

            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });
        return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
      * */
       // ValidationErrorResponse error = new ValidationErrorResponse();
        /*for (ConstraintViolation violation : ex.getConstraintViolations()){
            error.getViolations().add(new Violation(violation.getPropertyPath().toString(), violation.getMessage()));

        }*/
     //  String fieldName = ((FieldError) ex).getField();
        List<Violation> errorsList = ex.getConstraintViolations().stream()
                .map(constraintViolation -> new
                        Violation(Error.ERROR_GENERAL_MSG.getCode(), constraintViolation.getConstraintDescriptor().getMessageTemplate()))
                .collect(Collectors.toList());
       /* Optional<String> optional = ex.getConstraintViolations().stream()
                .findAny()
                .map(constraintViolation -> constraintViolation.getConstraintDescriptor().getMessageTemplate());
           *//* List<StatusResponse>*//* errors = Stream.of(new
                 StatusResponse(Error.ERROR_GENERAL_MSG.getCode(),optional.get()
                 //Error.ERROR_GENERAL_MSG.getDescription())) .collect(Collectors.toList());
              )) .collect(Collectors.toList());*/
      reqWDTO.setErrors(errorsList); reqWDTO.setStatus(Stream.of(new
                 StatusResponse(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()),
                 HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())).collect(Collectors.
                 toList())); reqWDTO.setTimeStamp(LocalDateTime.now());

      //if (log.isErrorEnabled()) log.error(ex.getMessage(), ex.getCause());

      return new ResponseEntity<>(reqWDTO, HttpStatus.INTERNAL_SERVER_ERROR); }




/*
*
*    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<RequestWrapperDTO> handleConstraintViolationException(ConstraintViolationException ex) {
      RequestWrapperDTO reqWDTO = new RequestWrapperDTO();

        List<StatusResponse> errors;
 /*
        for (ConstraintViolation violation : ex.getConstraintViolations()) {
            error.getViolations().add(new Violation(violation.getPropertyPath().toString(), violation.getMessage()));
        }*/


    /*
    1
    *  ValidationErrorResponse error = new ValidationErrorResponse();
      for (ConstraintViolation violation : e.getConstraintViolations()) {
          error.getViolations().add(new Violation(violation.getPropertyPath().toString(), violation.getMessage()));
      }
      return error;

      2
      Map<String, String> errors = new HashMap<>();
      ex.getBindingResult().getAllErrors().forEach((error) ->{

          String fieldName = ((FieldError) error).getField();
          String message = error.getDefaultMessage();
          errors.put(fieldName, message);
      });
      return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
    * */
    // ValidationErrorResponse error = new ValidationErrorResponse();
        /*for (ConstraintViolation violation : ex.getConstraintViolations()){
            error.getViolations().add(new Violation(violation.getPropertyPath().toString(), violation.getMessage()));

        }*/
  /*  List<StatusResponse> errorsList = ex.getConstraintViolations().stream()
            .map(constraintViolation -> new
                    StatusResponse(Error.ERROR_GENERAL_MSG.getCode(), constraintViolation.getConstraintDescriptor().getMessageTemplate()))
            .collect(Collectors.toList());
   */    /* Optional<String> optional = ex.getConstraintViolations().stream()
                .findAny()
                .map(constraintViolation -> constraintViolation.getConstraintDescriptor().getMessageTemplate());
           *//* List<StatusResponse>*//* errors = Stream.of(new
                 StatusResponse(Error.ERROR_GENERAL_MSG.getCode(),optional.get()
                 //Error.ERROR_GENERAL_MSG.getDescription())) .collect(Collectors.toList());
              )) .collect(Collectors.toList());*/
   /*   reqWDTO.setErrors(errorsList); reqWDTO.setStatus(Stream.of(new
    StatusResponse(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()),
            HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())).collect(Collectors.
                                                                                 toList())); reqWDTO.setTimeStamp(LocalDateTime.now());
*/
    //if (log.isErrorEnabled()) log.error(ex.getMessage(), ex.getCause());

    /*  return new ResponseEntity<>(reqWDTO, HttpStatus.INTERNAL_SERVER_ERROR); }

*/
/*

* */

  @Override
  public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
                                                             HttpStatus status, WebRequest request) {
      RequestWrapperDTO reqWDTO = new RequestWrapperDTO();
      List<StatusResponse> errors;

      if (CollectionUtils.isNotEmpty(ex.getBindingResult().getFieldErrors())) {
          errors = Stream
                  .of(new StatusResponse(Error.ERROR_INVALID_REQ.getCode(),
                          StringUtils.trim(ex.getBindingResult().getFieldErrors().stream()
                                  .map(e -> e.getDefaultMessage().concat(" ")).collect(Collectors.joining()))))
                  .collect(Collectors.toList());
      } else {
          errors = Stream
                  .of(new StatusResponse(Error.ERROR_GENERAL_MSG.getCode(), Error.ERROR_GENERAL_MSG.getDescription()))
                  .collect(Collectors.toList());
      }
      reqWDTO.setErrors(errors);
      reqWDTO.setStatus(Stream.of(new StatusResponse(ConstantValues.SUCCESS_CODE, ConstantValues.SUCCESS_CODE_DESC))
              .collect(Collectors.toList()));
      reqWDTO.setTimeStamp(LocalDateTime.now());

    /*  if (log.isErrorEnabled())
          log.error(ex.getMessage(), ex.getCause());*/

      return new ResponseEntity<>(reqWDTO, HttpStatus.OK);
  }
   /* @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
      *//*  ErrorDetails exceptionResponse = new ErrorDetails(new Date(), "Validation Failed",
                ex.getBindingResult().toString());
        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);*//*

        List<FieldError>fieldErrors=ex.getBindingResult().getFieldErrors();
        fieldErrors.stream().map(err -> err.getField() +":"+err.getDefaultMessage())
                .collect(Collectors.toList());

        ErrorDetails apierr=  new ErrorDetails();
        apierr.setStatus(HttpStatus.BAD_REQUEST);
        apierr.setTimeStamp(LocalDateTime.now());
        return new ResponseEntity<>(apierr,headers,apierr.getStatus());
    }*/
  /* @Override
   public ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
                                                                     HttpHeaders headers, HttpStatus status, WebRequest request) {
       RequestWrapperDTO reqWDTO = new RequestWrapperDTO();
       List<StatusResponse> errors = Stream
               .of(new StatusResponse(java.lang.Error.ERROR_GENERAL_MSG.getCode(), java.lang.Error.ERROR_GENERAL_MSG.getDescription()))
               .collect(Collectors.toList());
       reqWDTO.setErrors(errors);
       reqWDTO.setStatus(Stream.of(new StatusResponse(MasterConstants.SUCCESS_CODE, MasterConstants.SUCCESS_CODE_DESC))
               .collect(Collectors.toList()));
       reqWDTO.setTimeStamp(LocalDateTime.now());

       if (log.isErrorEnabled())
           log.error(ex.getMessage(), ex.getCause());

       return new ResponseEntity<>(reqWDTO, new HttpHeaders(), HttpStatus.OK);
   }*/
    private HttpStatus getHttpStatus(String code) {
        if (code.equals(ConstantValues.CREATE_ERROR_CODE) || code.equals(ConstantValues.DELETE_ERROR_CODE)
                || code.equals(ConstantValues.INVALID_REQUEST_CODE)) {
            return HttpStatus.BAD_REQUEST;
        } else {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }


}