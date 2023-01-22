package com.crud.tasks.domain;


import lombok.Data;


@Data
public class Mail {
    public  String mailTo;
    private   String subject;
    private   String message;
    private String toCc;
public Mail  (   String mailTo, String subject, String message){}

}