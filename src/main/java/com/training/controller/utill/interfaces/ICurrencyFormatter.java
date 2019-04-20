package com.training.controller.utill.interfaces;

import java.util.Optional;

public interface ICurrencyFormatter {
    String format(Optional<Long> number);
    String format(Long number);
}
