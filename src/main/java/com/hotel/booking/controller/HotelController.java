package com.hotel.booking.controller;

import com.hotel.booking.dto.CreateHotelRequestDto;
import com.hotel.booking.dto.HotelDto;
import com.hotel.booking.service.HotelService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController // Indica que es un controlador REST (devuelve JSON)
@RequestMapping("/api/hotels")
@RequiredArgsConstructor
public class HotelController {

    private final HotelService hotelService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HotelDto createHotel(@Valid @RequestBody CreateHotelRequestDto requestDto){

        // @RequestBody: Convierte el cuerpo JSON de la petición a un objeto CreateHotelRequest
        // @Valid: Activa las validaciones que definimos en el DTO (@NotBlank, etc.)
        return hotelService.createHotel(requestDto);
    }




}
