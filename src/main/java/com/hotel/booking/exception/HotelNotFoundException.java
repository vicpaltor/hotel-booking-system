package com.hotel.booking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
// Anotación clave: le dice a Spring que cuando esta excepción llegue al controlador,
// debe transformarse automáticamente en una respuesta HTTP 404 Not Found.
@ResponseStatus(HttpStatus.NOT_FOUND)
public class HotelNotFoundException extends RuntimeException{

    public HotelNotFoundException(String message) {
       super(message);// <-- Llama al constructor padre para establecer el mensaje
    }
    
}
