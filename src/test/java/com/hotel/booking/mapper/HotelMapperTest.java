package com.hotel.booking.mapper;

import com.hotel.booking.domain.hotel.Hotel;
import com.hotel.booking.dto.CreateHotelRequestDto;
import com.hotel.booking.dto.HotelDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest
public class HotelMapperTest {

    @Autowired
    private HotelMapper hotelMapper;

    @Test
    void shouldMapHotelEntityToHotelDto() {
        // GIVEN (Dado un objeto de origen)
        Hotel hotelEntity = Hotel.builder()
                .id(1L)
                .name("Grand Hotel")
                .address("Calle Falsa 123")
                .city("Springfield")
                .country("USA")
                .stars(5)
                .description("Un hotel de lujo.")
                .build();

        // WHEN (Cuando llamamos al método del mapper)
        HotelDto hotelDto = hotelMapper.toDto(hotelEntity);

        // THEN (Entonces verificamos que el resultado es el esperado)
        assertThat(hotelDto).isNotNull();
        assertThat(hotelDto.getId()).isEqualTo(hotelEntity.getId());
        assertThat(hotelDto.getName()).isEqualTo(hotelEntity.getName());
        assertThat(hotelDto.getCity()).isEqualTo(hotelEntity.getCity());
        assertThat(hotelDto.getStars()).isEqualTo(hotelEntity.getStars());

    }

    @Test
    void shouldMapCreateHotelRequestToHotelEntity() {
        // GIVEN
        CreateHotelRequestDto request = CreateHotelRequestDto.builder()
                .name("Hotel Económico")
                .address("Avenida Siempre Viva 742")
                .city("Shelbyville")
                .country("USA")
                .stars(3)
                .description("Bueno, bonito y barato.")
                .build();

        // WHEN
        Hotel hotelEntity = hotelMapper.toEntity(request);

        // THEN
        assertThat(hotelEntity).isNotNull();
        // El ID debe ser nulo porque el request no lo tiene
        assertThat(hotelEntity.getId()).isNull();
        assertThat(hotelEntity.getName()).isEqualTo(request.getName());
        assertThat(hotelEntity.getCity()).isEqualTo(request.getCity());
        assertThat(hotelEntity.getStars()).isEqualTo(request.getStars());
    }
}
