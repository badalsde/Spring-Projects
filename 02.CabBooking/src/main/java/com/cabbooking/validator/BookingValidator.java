package com.cabbooking.validator;

import com.cabbooking.dto.BookingDTO;
import com.cabbooking.exception.CabBookingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class BookingValidator {

    @Autowired
    private Environment environment;

    public void validate(BookingDTO bookingDTO) throws CabBookingException{
        if(!validatePhoneNo(bookingDTO.getPhoneNo())){
            throw new CabBookingException(environment.getProperty("Validator.INVALID_PHONE_NO"));
        }
    }

    public boolean validatePhoneNo(Long phoneNo) throws CabBookingException{
        String regex="[6-9]{1}[0-9]{9}";
        return String.valueOf(phoneNo).matches(regex);
    }
}
