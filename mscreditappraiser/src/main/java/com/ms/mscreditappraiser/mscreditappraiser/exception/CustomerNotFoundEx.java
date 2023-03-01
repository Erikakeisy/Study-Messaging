package com.ms.mscreditappraiser.mscreditappraiser.exception;

public class CustomerNotFoundEx extends Exception{

     public CustomerNotFoundEx(){
         super("Data customer not found for this cpf");
     }

}
