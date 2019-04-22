package com.training.view;

import java.util.Optional;

public interface ICurrencyFormatter {
    String format(Optional<Long> number);
    String format(Long number);
    String formatDouble(double number);
}
