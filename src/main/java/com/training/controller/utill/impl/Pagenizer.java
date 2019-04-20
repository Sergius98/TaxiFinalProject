package com.training.controller.utill.impl;

import com.training.controller.IServletConstants;
import com.training.controller.utill.interfaces.IPagenizer;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class Pagenizer implements IPagenizer {

    @Override
    public void pagenize(HttpServletRequest req, int counter, List list, int page) {
        int begin;
        int end;
        int max_page = (list.size() - 1)/counter + 1;

        if (page <= 0){
            page = 1;
        }
        if (page > max_page){
            page = max_page;
        }
        begin = counter * (page - 1);
        end = counter * page - 1;

        if (end >= list.size()){
            end = (list.size() - 1);
        }

        req.setAttribute(IServletConstants.FIRST_ELEMENT_KEY_WORD, begin);
        req.setAttribute(IServletConstants.LAST_ELEMENT_KEY_WORD, end);
        req.setAttribute(IServletConstants.PAGE_NUMBER_KEY_WORD, end/counter + 1);
        req.getSession().setAttribute(IServletConstants.PAGE_NUMBER_KEY_WORD, end/counter + 1);
    }
}
