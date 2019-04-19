package com.training.controller.utill.interfaces;

import com.training.model.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public interface IExtractor <T> {
    public Optional<T> extract(HttpServletRequest req);
}
