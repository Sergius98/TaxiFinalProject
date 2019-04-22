package com.training.controller.utill.impl;

import com.training.controller.IServletConstants;
import com.training.controller.utill.interfaces.IPagenizer;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

public class Pagenizer implements IPagenizer {

    @Override
    public void pagenize(HttpServletRequest req, int counter, List list) {
        int begin;
        int end;
        int max_page = (list.size() - 1)/counter + 1;
        int page;

        Optional<Integer> optionalPage = Optional.ofNullable(
                (Integer)req.getSession()
                        .getAttribute(IServletConstants.PAGE_NUMBER_KEY_WORD));
        Optional<String> newPage = Optional.ofNullable(
                req.getParameter(IServletConstants.PAGE_NUMBER_KEY_WORD));

        if (newPage.isPresent()){
            try{
                optionalPage = Optional.of(Integer.valueOf(newPage.get()));
            } catch (NumberFormatException e){
                optionalPage = Optional.empty();
            }
        }

        page = optionalPage.orElse(1);

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
