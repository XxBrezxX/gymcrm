package com.example.gymcrm.configuration;

import java.text.SimpleDateFormat;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

@ControllerAdvice
public class GlobalInitBinder {

    @InitBinder
    public void bindSqlDate(WebDataBinder binder) {
        // Establece el formato de fecha para java.sql.Date
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        // Establece que se debe permitir un valor de fecha vacío (null)
        dateFormatter.setLenient(true);
        CustomDateEditor editor = new CustomDateEditor(dateFormatter, true);
        binder.registerCustomEditor(java.sql.Date.class, editor);
    }
}