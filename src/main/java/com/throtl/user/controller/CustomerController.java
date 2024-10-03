package com.throtl.user.controller;

import com.throtl.clientModel.RSAPurchasedDataRequest;
import com.throtl.user.model.*;
import com.throtl.user.service.CustomerService;
import com.throtl.user.util.CommonUtil;
import com.throtl.validator.Validator;
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

    @Autowired
    Validator validator;

    @PostMapping(value = "userValidation", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Object> validateRegisteredPhoneNumber(@RequestBody @Validated VerifyRegisteredUserRequest verifyRegisteredUserRequest,
                                                                @RequestHeader(name = "DEVICE_ID", required = false) String deviceId,
                                                                BindingResult result){

    try {
//        logger.info("Request body for /api/th/customer/validateRegisteredPhoneNumber" + "---"
//                + mapper.writeValueAsString(unregNumber));
        validator.validRegisteredPhoneNumber(verifyRegisteredUserRequest,result );
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


    @PostMapping(value = "rsaUserDetails", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Object> userRSAUserDEtails(@RequestBody @Validated UserRSADetailsRequest userRSADetailsRequest,
                                                     @RequestHeader(name = "", required = false) String deviceId,
                                                     BindingResult result){


        try{

            return customerService.getUserRSADetails(userRSADetailsRequest,true);




        }catch (Exception e){



        }
        return new ResponseEntity<>(commonUtil.getInternalServerError(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @PostMapping(value = "rsaStateList", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Object> userRSAStateList(@RequestBody @Validated UserRSADetailsRequest userRSADetailsRequest,
                                                     @RequestHeader(name = "", required = false) String deviceId,
                                                     BindingResult result){


        try{

            return customerService.getRsaStateList(userRSADetailsRequest,true);




        }catch (Exception e){



        }
        return new ResponseEntity<>(commonUtil.getInternalServerError(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping(value = "rsaMembershipList", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Object> userRSAMembershipList(@RequestBody @Validated UserRSADetailsRequest userRSADetailsRequest,
                                                   @RequestHeader(name = "", required = false) String deviceId,
                                                   BindingResult result){


        try{

            return customerService.getRsaMembershipList(userRSADetailsRequest,true);




        }catch (Exception e){



        }
        return new ResponseEntity<>(commonUtil.getInternalServerError(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

 @PostMapping(value = "rsaBrandList", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Object> userRSABrandList(@RequestBody @Validated UserRSADetailsRequest userRSADetailsRequest,
                                                   @RequestHeader(name = "", required = false) String deviceId,
                                                   BindingResult result){


        try{

            return customerService.getRsaBrandList(userRSADetailsRequest,true);




        }catch (Exception e){



        }
        return new ResponseEntity<>(commonUtil.getInternalServerError(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @PostMapping(value = "rsaModelList", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Object> userRSAModelList(@RequestBody @Validated UserRSADetailsRequest userRSADetailsRequest,
                                                   @RequestHeader(name = "", required = false) String deviceId,
                                                   BindingResult result){


        try{

            return customerService.getRsaModelList(userRSADetailsRequest,true);




        }catch (Exception e){



        }
        return new ResponseEntity<>(commonUtil.getInternalServerError(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @PostMapping(value = "rsaPurchaseData", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Object> userRSAModelList(@RequestBody RSAPurchasedDataRequest rsaPurchasedDataRequest,
                                                   @RequestHeader(name = "", required = false) String deviceId,
                                                   BindingResult result){


        try{

            return customerService.rsaPurchase(rsaPurchasedDataRequest,true);




        }catch (Exception e){



        }
        return new ResponseEntity<>(commonUtil.getInternalServerError(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PostMapping(value = "saveTransactionDetails", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Object> saveTransactionDetails(@RequestBody @Validated TransactionData transactionData,
                                                         @RequestHeader(name = "", required = false) String deviceId,
                                                         BindingResult result){
        try{
            return customerService.saveTransactionData(transactionData,true);
        }catch (Exception e){

        }
        return new ResponseEntity<>(commonUtil.getInternalServerError(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @PostMapping(value = "getUserTransactionDetails", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Object> getUserTransactionDetails(@RequestBody @Validated GetUserTransactionDetailsRequest getUserTransactionDetailsRequest,
                                                            @RequestHeader(name = "", required = false) String deviceId,
                                                            BindingResult result){
        try{
            return customerService.getUserTransactionDetails(getUserTransactionDetailsRequest,true);
        }catch (Exception e){

        }
        return new ResponseEntity<>(commonUtil.getInternalServerError(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping(value = "getUserProfile", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Object> getUserProfile(@RequestBody @Validated ProfileDetailsRequest profileDetailsRequest,
                                                 @RequestHeader(name = "", required = false) String deviceId,
                                                 BindingResult result){
        try{
            return customerService.getUserProfileDetails(profileDetailsRequest,true);
        }catch (Exception e){

        }
        return new ResponseEntity<>(commonUtil.getInternalServerError(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping(value = "deleteUserProfile", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Object> deleteUserProfile(@RequestBody @Validated ProfileDetailsRequest profileDetailsRequest,
                                                 @RequestHeader(name = "", required = false) String deviceId,
                                                 BindingResult result){
        try{
            return customerService.deleteUserProfile(profileDetailsRequest,true);
        }catch (Exception e){

        }
        return new ResponseEntity<>(commonUtil.getInternalServerError(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
