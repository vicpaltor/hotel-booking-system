package com.hotel.booking.service;

import com.hotel.booking.mapper.HotelMapper;
import com.hotel.booking.repository.HotelRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

// Le decimos a JUnit 5 que use la extensión de Mockito para gestionar los mocks
@ExtendWith(MockitoExtension.class)
public class HotelServiceTest {

    // @Mock: Crea un mock (un doble de prueba) de esta dependencia.
    // No usaremos el repositorio real, sino uno falso que controlamos.
    @Mock
    private HotelRepository hotelRepository;

    @Mock
    private HotelMapper hotelMapper;

    // @InjectMocks: Crea una instancia de HotelService e intenta inyectar
    // los mocks declarados en esta clase (hotelRepository y hotelMapper).
    @InjectMocks
    private HotelService hotelService;

    @Test
    void shouldCreateHotelSuccessfully(){
        // GIVE
        // WHEN
        // THEN


    }

}
