package com.insanwalat.modcalc.ductsizer.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
public @interface EquationDescription {

    String name();

    String description();

    String output();
}
