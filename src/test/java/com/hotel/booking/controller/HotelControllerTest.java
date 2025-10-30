package com.hotel.booking.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hotel.booking.dto.CreateHotelRequestDto;
import com.hotel.booking.dto.HotelDto;
import com.hotel.booking.service.HotelService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// @WebMvcTest: Es un "test de porción" (slice test). Carga solo la capa web (el controller)
// y no la aplicación completa. Es mucho más rápido. Le decimos qué controller queremos probar.
@WebMvcTest(HotelController.class)
@Import(HotelControllerTest.TestConfig.class) // 1. Importar la configuración de test
public class HotelControllerTest {
    // @Autowired MockMvc: Spring nos inyecta un objeto para simular peticiones HTTP.
    @Autowired
    private MockMvc mockMvc;

    // 2. Ya no se necesita @MockBean aquí
    // @MockBean: Como @WebMvcTest no carga los servicios, tenemos que proporcionar un mock
    // del servicio que el controlador necesita. Es la versión de @Mock para tests de Spring.

    @Autowired // Ahora el mock se inyecta como un bean normal
    private HotelService hotelService;

    // ObjectMapper: una utilidad para convertir objetos Java a JSON y viceversa.
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreateHotelAndReturn201() throws Exception {

        CreateHotelRequestDto requestDto = CreateHotelRequestDto.builder()
                .name("Hotel API Test")
                .city("Testville")
                .country("Testland")
                .address("123 Test Street")
                .stars(4)
                .build();

        HotelDto responseDto = HotelDto.builder()
                .id(1L)
                .name("Hotel API Test")
                .city("Testville")
                .build();

        // Configuramos el mock del servicio. Cuando se llame a createHotel...

        when(hotelService.createHotel(any(CreateHotelRequestDto.class)))
                .thenReturn(responseDto);

        // WHEN & THEN (Realizar la petición y verificar la respuesta)

        mockMvc.perform(post("/api/hotels")// Simula un POST a /api/hotels
                .contentType(MediaType.APPLICATION_JSON)// Le decimos que el cuerpo es JSON
                .content(objectMapper.writeValueAsString(requestDto))) // Convertimos el request a JSON

                .andExpect(status().isCreated()) // Esperamos un estado HTTP 201 (Created)
                .andExpect(jsonPath("$.id", is(1))) // Verificamos el JSON de respuesta
                .andExpect(jsonPath("$.name", is("Hotel API Test")));
    }

    // 3. Clase interna estática para definir los mocks
    @TestConfiguration
    static class TestConfig {
        @Bean
        public HotelService hotelService() {
            // Se usa Mockito.mock() para crear el mock
            return Mockito.mock(HotelService.class);
        }
    }

}
