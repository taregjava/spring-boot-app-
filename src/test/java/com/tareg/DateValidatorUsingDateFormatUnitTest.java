package com.tareg;

import com.tareg.service.DateValidator;
import com.tareg.service.impl.DateValidatorUsingDateFormat;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;


public class DateValidatorUsingDateFormatUnitTest {

    @Test
    public void givenValidator_whenValidDatePassed_ThenTrue() {
        DateValidator validator = new DateValidatorUsingDateFormat("MM/dd/yyyy");

        assertTrue(validator.isValid("02/28/2019"));
    }

    @Test
    public void givenValidator_whenInvalidDatePassed_ThenFalse() {
        DateValidator validator = new DateValidatorUsingDateFormat("MM/dd/yyyy");

        assertFalse(validator.isValid("02/30/2019"));
    }
}
