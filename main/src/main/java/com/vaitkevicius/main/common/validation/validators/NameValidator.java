//package com.vaitkevicius.main.common.validation.validators;
//
//import com.vaitkevicius.main.common.validation.validators.anotations.Name;
//
//import javax.validation.ConstraintValidator;
//import javax.validation.ConstraintValidatorContext;
//
//public class NameValidator implements ConstraintValidator<Name, String> {
//
//    @Override
//    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
//        if(value.length() == 4) {
//            return false;
//        }
//        return true;
//    }
//}
