package com.ms.mscreditappraiser.mscreditappraiser.exception;

import lombok.Getter;


public class ComunicationErrorEx extends Exception{

    @Getter
    private Integer status;

    public ComunicationErrorEx(String msg, Integer status) {
        super(msg);
        this.status = status;
    }
}
