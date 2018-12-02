//package com.vaitkevicius.main.common.validation.validators.anotations;
//
//import com.vaitkevicius.main.common.validation.validators.NameValidator;
//import org.springframework.data.mongodb.core.mapping.Document;
//
//import javax.validation.Constraint;
//import javax.validation.Payload;
//import java.lang.annotation.ElementType;
//import java.lang.annotation.Retention;
//import java.lang.annotation.RetentionPolicy;
//import java.lang.annotation.Target;
//
//@Document
//@Constraint(validatedBy = NameValidator.class)
//@Target( {ElementType.FIELD})
//@Retention(RetentionPolicy.RUNTIME)
//public @interface Name {
//    String message() default "validation.not.userName";
//    Class<?>[] groups() default  {};
//    Class<? extends Payload>[] payload() default {};
//}
