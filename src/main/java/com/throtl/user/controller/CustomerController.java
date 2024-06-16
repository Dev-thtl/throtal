package com.throtl.user.controller;

import com.throtl.user.model.OtpVerificationRequest;
import com.throtl.user.model.UserRegistrationRequest;
import com.throtl.user.model.VerifyRegisteredUserRequest;
import com.throtl.user.service.CustomerService;
import com.throtl.user.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/th/customer")
public class CustomerController {

    @Autowired
    CommonUtil commonUtil;

    @Autowired
    CustomerService customerService;

    @PostMapping(value = "userValidation", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Object> validateRegisteredPhoneNumber(@RequestBody @Validated VerifyRegisteredUserRequest verifyRegisteredUserRequest,
                                                                @RequestHeader(name = "", required = false) String deviceId,
                                                                BindingResult result){

    try {
//        logger.info("Request body for /api/th/customer/validateRegisteredPhoneNumber" + "---"
//                + mapper.writeValueAsString(unregNumber));
//        customerValidator.validNumberV1(unregNumber,result );
//        if(result.hasErrors())
//        {
//            return new ResponseEntity<>(new ErrorResponse(ErrorType.VALIDATION, result), HttpStatus.BAD_REQUEST);
//        }
//        else
//        {
            return customerService.validateRegisterPhoneNumberV1(verifyRegisteredUserRequest,true);
//        }

    }catch (Exception e){
//        logger.error("@@@  Exception in [CustomerController][validatePhoneNumber]", e);

    }
    return new ResponseEntity<>(commonUtil.getInternalServerError(), HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @PostMapping(value = "verifyOtp", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Object> verifyOtp(@RequestBody @Validated OtpVerificationRequest otpVerificationRequest,
                                            @RequestHeader(name = "", required = false) String deviceId,
                                            BindingResult result){


        try{

            return customerService.verifyOtp(otpVerificationRequest,true);




        }catch (Exception e){



        }
        return new ResponseEntity<>(commonUtil.getInternalServerError(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping(value = "registration", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Object> userRegistration(@RequestBody @Validated UserRegistrationRequest userRegistrationRequest,
                                            @RequestHeader(name = "", required = false) String deviceId,
                                            BindingResult result){


        try{

            return customerService.userRegistration(userRegistrationRequest,true);




        }catch (Exception e){



        }
        return new ResponseEntity<>(commonUtil.getInternalServerError(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
