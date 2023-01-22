package com.crud.tasks.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;


@AllArgsConstructor
@Getter
public class Mail {
    public  String mailTo;
    private   String subject;
    private   String message;
    private String toCc;
public Mail  (   String mailTo, String subject, String message){}

}