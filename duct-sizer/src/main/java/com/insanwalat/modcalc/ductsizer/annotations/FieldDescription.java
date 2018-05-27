package com.insanwalat.modcalc.ductsizer.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
public @interface FieldDescription {

    String uiField();

    String note();
}
