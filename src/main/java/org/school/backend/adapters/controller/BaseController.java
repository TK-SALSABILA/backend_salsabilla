package org.school.backend.adapters.controller;


import org.school.backend.adapters.builder.ResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class BaseController {

    public long timeStamp;

    @Autowired
    protected ResponseBuilder responseDefault;

    public BaseController() {
        timeStamp = new Date().getTime();
    }
}
