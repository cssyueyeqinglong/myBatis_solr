package com.cy.mybatis.convert;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConvertDate implements Converter<String, Date> {
    @Override
    public Date convert(String s) {
        try {
            Date data = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(s);
            return data;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
