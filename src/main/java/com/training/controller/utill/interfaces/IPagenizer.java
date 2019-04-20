package com.training.controller.utill.interfaces;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IPagenizer {
    void pagenize(HttpServletRequest req, int counter, List list, int page);
}
