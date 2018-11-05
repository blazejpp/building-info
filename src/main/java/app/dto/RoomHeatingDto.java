package app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data
public class RoomHeatingDto {

    private Long id;
    private Long number;
    private Long floorNumber;
    private BigDecimal heatingPerArea;
}
