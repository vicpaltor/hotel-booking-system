package com.hotel.booking.service;

import com.hotel.booking.domain.hotel.Hotel;
import com.hotel.booking.dto.CreateHotelRequestDto;
import com.hotel.booking.dto.HotelDto;
import com.hotel.booking.mapper.HotelMapper;
import com.hotel.booking.repository.HotelRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

// Le decimos a JUnit 5 que use la extensión de Mockito para gestionar los mocks
@ExtendWith(MockitoExtension.class)
public class HotelServiceTest {

    // @Mock: Crea un mock (un doble de prueba) de esta dependencia.
    // No usaremos el repositorio real, sino uno falso que controlamos.
    @Mock
    private HotelRepository hotelRepositoryMock;

    @Mock
    private HotelMapper hotelMapperMock;

    // @InjectMocks: Crea una instancia de HotelService e intenta inyectar
    // los mocks declarados en esta clase (hotelRepository y hotelMapper).
    @InjectMocks
    private HotelService hotelService;

    @Test
    void shouldCreateHotelSuccessfully(){
        // GIVE (Dados unos datos de entrada y el comportamiento de los mocks)

        CreateHotelRequestDto request = CreateHotelRequestDto.builder()
                .name("Hotel Económico")
                .address("Avenida Siempre Viva 742")
                .city("Shelbyville")
                .country("USA")
                .stars(3)
                .description("Bueno, bonito y barato.")
                .build();

        // Entidad que simula la conversión
        Hotel hotelEntity = Hotel.builder().build();

        // Entidad que simula el guardado en BD
        Hotel savedHotelEntity = Hotel.builder().build();

        // Le ponemos un ID para simular que se ha guardado
        savedHotelEntity.setId(1L);

        HotelDto expectedDto = HotelDto.builder()
                .id(1L)
                .name("Hotel Test")
                .city("Ciudad Test")
                .build();

        /** Configuramos los mocks:**/
        // 1. Cuando se llame a hotelMapper.toEntity con CUALQUIER request...
        when(hotelMapperMock.toEntity(any(CreateHotelRequestDto.class))).thenReturn(hotelEntity);

        // 2. Cuando se llame a hotelRepository.save con CUALQUIER entidad Hotel...
        when(hotelRepositoryMock.save(any(Hotel.class))).thenReturn(savedHotelEntity);

        // 3. Cuando se llame a hotelMapper.toDto con la entidad guardada...
        when(hotelMapperMock.toDto(savedHotelEntity)).thenReturn(expectedDto);


        // WHEN (Cuando ejecutamos el método que queremos probar)
        // Esta línea dará un error de compilación porque el método no existe aún.
        // ¡Eso es TDD!
        HotelDto result = hotelService.createHotel(request);


        // THEN (Entonces verificamos que el resultado es el esperado)
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getName()).isEqualTo("Hotel Test");


    }

}
