package com.training.controller.utill.impl;

import com.training.controller.utill.interfaces.ILocalization;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.Optional;

public class Localization implements ILocalization {
    public Locale extractLocale(HttpServletRequest req){
        return !Optional.ofNullable(req.getSession().getAttribute("lang")).isPresent() ?
                new Locale("en") :
                new Locale((String) req.getSession().getAttribute("lang"));
    }
}
