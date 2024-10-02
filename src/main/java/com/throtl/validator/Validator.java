package com.throtl.validator;

import com.throtl.user.model.VerifyRegisteredUserRequest;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

@Component
public class Validator {


    public void validRegisteredPhoneNumber(Object target, Errors errors) {

        VerifyRegisteredUserRequest number = (VerifyRegisteredUserRequest) target;
        ValidationUtils.rejectIfEmpty(errors, "mobileNumber", "Please enter a value");
//        String decrptMobile = CansCrypt.decrypt(number.getPhoneNumber());
        if (!NumberUtils.isDigits(number.getMobileNumber()) || number.getMobileNumber().length() < 8 || number.getMobileNumber().length() > 15)
        {
            errors.rejectValue("phoneNumber", "Please enter a valid mobile number");
        }
    }

}
