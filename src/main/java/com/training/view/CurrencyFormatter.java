package com.training.view;

import org.apache.log4j.Logger;

import java.util.MissingResourceException;
import java.util.Optional;
import java.util.ResourceBundle;

public class CurrencyFormatter implements ICurrencyFormatter {
    private static final Logger log = Logger.getLogger(CurrencyFormatter.class);
    private int multiplier;

    public CurrencyFormatter(String currency){
        try{
            multiplier = Integer.valueOf(ResourceBundle.getBundle("currency").getString(currency));
        } catch (NumberFormatException | MissingResourceException e){
            log.warn("wrong currency");
            multiplier = 1;
        }
    }

    @Override
    public String format(Optional<Long> number) {

        return format(number.orElse((long) 0));
    }

    @Override
    public String format(Long number) {
        long value = number * multiplier;

        return (value / 100) + "." + String.format("%02d", value % 100);
    }

    @Override
    public String formatDouble(double number) {
        return String.format("%.2f", number);
    }
}
