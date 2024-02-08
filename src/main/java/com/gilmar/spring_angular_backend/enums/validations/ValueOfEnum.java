package com.gilmar.spring_angular_backend.enums.validations;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

//anotação para qual essa validação servirá.
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
//Esta validação está disponível durante toda a execução do programa
@Retention(RUNTIME)
//será  incluídos na documentação Javadoc;
@Documented
//qual classe será a validadora
@Constraint(validatedBy = ValueOfEnumValidator.class)
public @interface ValueOfEnum {

    //declaração de um Enum generico para o ValueOfEnumValidator consumir
    // o sinal ? será substituido pela classe passada na validaçaõ.
     Class<? extends Enum<?>> enumClass();
    //abaixo há uma mensagem padrão caso a mensagem não seja passada.
    String message() default "must be any of enum {enumClass}";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

