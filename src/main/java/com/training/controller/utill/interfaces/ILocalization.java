package com.training.controller.utill.interfaces;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

public interface ILocalization {
    Locale extractLocale(HttpServletRequest req);
}
