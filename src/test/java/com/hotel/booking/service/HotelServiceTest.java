package com.hotel.booking.service;

import com.hotel.booking.domain.hotel.Hotel;
import com.hotel.booking.dto.CreateHotelRequestDto;
import com.hotel.booking.dto.HotelDto;
import com.hotel.booking.exception.HotelNotFoundException;
import com.hotel.booking.mapper.HotelMapper;
import com.hotel.booking.repository.HotelRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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
    void createHotel_shouldCreateHotelSuccessfully(){
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
        // Simula la conversión del request a entidad
        Hotel hotelEntity = Hotel.builder()
                .name(request.getName())
                .address(request.getAddress())
                .city(request.getCity())
                .country(request.getCountry())
                .stars(request.getStars())
                .description(request.getDescription())
                .build();

        // Simula la entidad guardada con ID
        Hotel savedHotelEntity = Hotel.builder()
                .id(1L)
                .name(request.getName())
                .address(request.getAddress())
                .city(request.getCity())
                .country(request.getCountry())
                .stars(request.getStars())
                .description(request.getDescription())
                .build();

        // DTO de respuesta esperado
        HotelDto expectedDto = HotelDto.builder()
                .id(1L)
                .name(request.getName())
                .address(request.getAddress())
                .city(request.getCity())
                .country(request.getCountry())
                .stars(request.getStars())
                .description(request.getDescription())
                .build();

        /** Configuramos los mocks:**/
        // 1. Cuando se llame a hotelMapper.toEntity con CUALQUIER request...
        when(hotelMapper.toEntity(request)).thenReturn(hotelEntity);

        // 2. Cuando se llame a hotelRepository.save con CUALQUIER entidad Hotel...
        when(hotelRepository.save(hotelEntity)).thenReturn(savedHotelEntity);

        // 3. Cuando se llame a hotelMapper.toDto con la entidad guardada...
        when(hotelMapper.toDto(savedHotelEntity)).thenReturn(expectedDto);

        // WHEN (Cuando ejecutamos el método que queremos probar)
        // Esta línea dará un error de compilación porque el método no existe aún.
        // ¡Eso es TDD!
        HotelDto result = hotelService.createHotel(request);

        // THEN (Entonces verificamos que el resultado es el esperado)
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getName()).isEqualTo("Hotel Económico");
        assertThat(result.getCity()).isEqualTo("Shelbyville");
        assertThat(result.getStars()).isEqualTo(3);
    }

    @Test
    void getHotelById_shouldReturnHotelWhenFound(){
         // GIVE (Dados unos datos de entrada)
        Hotel request = Hotel.builder()
                .name("Hotel Económico")
                .address("Avenida Siempre Viva 742")
                .city("Shelbyville")
                .country("USA")
                .stars(3)
                .description("Bueno, bonito y barato.")
                .build();
        HotelDto requestDto = HotelDto.builder()
                .id(1L)
                .name("Hotel Económico")
                .address("Avenida Siempre Viva 742")
                .city("Shelbyville")
                .country("USA")
                .stars(3)
                .description("Bueno, bonito y barato.")
                .build();

        when(hotelRepository.findById(1L)).thenReturn(Optional.of(request));
        when(hotelMapper.toDto(request)).thenReturn(requestDto);

         // WHEN (Cuando ejecutamos el método que queremos probar)
        HotelDto result = hotelService.getHotelById(1L);

         // THEN (Entonces verificamos que el resultado es el esperado)
        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo("Hotel Económico");
        assertThat(result.getCity()).isEqualTo("Shelbyville");
        assertThat(result.getCountry()).isEqualTo("USA");
        assertThat(result.getStars()).isEqualTo(3);
        assertThat(result.getDescription()).isEqualTo("Bueno, bonito y barato.");

    }

    @Test
    void getHotelById_shouldThrowNotFoundExceptionWhenHotelDoesNotExist(){
        // GIVE (Dados unos datos de entrada)
        final Long nonExistentId = 99L;

        when(hotelRepository.findById(nonExistentId)).thenReturn(Optional.empty());

        // WHEN & THEN (Cuando llamamos al método y verificamos que lanza la excepción)

        // assertThrows espera dos argumentos:
        // 1. La clase de la excepción que esperamos que se lance (HotelNotFoundException.class).
        // 2. Una expresión lambda que contiene el código que DEBE lanzar la excepción.
//        HotelNotFoundException exception =
        assertThrows(
                HotelNotFoundException.class,
                () -> {hotelService.getHotelById(nonExistentId);
        });

        // Verificamos que el mensaje de la excepción capturada es el correcto.
//        assertThat(exception.getMessage()).isEqualTo("Hotel no encontrado con id: " + nonExistentId);

        // Verificamos que el método toDto del mapper NUNCA fue llamado,
        // porque la lógica debería haber terminado antes al lanzar la excepción.
        verify(hotelMapper, never()).toDto(any(Hotel.class));
    }

}
