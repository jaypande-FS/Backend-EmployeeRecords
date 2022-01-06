package com.project.demo.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Configurable;

import lombok.Value;

@Configurable
public class PhoneNumberValidator implements ConstraintValidator<Phone, String> {

  
   @Override
   public boolean isValid(String str, ConstraintValidatorContext constraintValidatorContext) {
       if (str.matches("[0-9]+")) return true;
       return false;
   }


}
