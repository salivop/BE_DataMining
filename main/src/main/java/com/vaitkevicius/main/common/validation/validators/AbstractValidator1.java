//package com.vaitkevicius.main.common.validation.validators;
//
//import org.springframework.validation.Errors;
//import org.springframework.validation.Validator;
//
//import java.lang.reflect.ParameterizedType;
//
//public abstract class AbstractValidator<T> implements Validator {
//
//    private Class<T> target;
//
//    public AbstractValidator() {
//        target = (Class<T>)((ParameterizedType)this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
//    }
//    @Override
//    public boolean supports(Class<?> clazz) {
//        return clazz.isAssignableFrom(target);
//    }
//
//    @Override
//    public void validate(Object target, Errors errors) {
//        doValidate((T)target, errors);
//    }
//
//    public abstract void doValidate(T target, Errors errors);
//}
