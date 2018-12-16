package app.service;

import app.model.Floor;
import app.repository.FloorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;

@Service
public class FloorService implements GenericService<Floor> {

    @Autowired
    private FloorRepository floorRepository;

    @Autowired
    private RoomService roomService;

    public FloorRepository getRepository() {
        return floorRepository;
    }

    public BigDecimal calculateArea(Floor floor) {
        return floor.getRooms().stream().map(r -> roomService.calculateArea(r)).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal calculateVolume(Floor floor) {
        return floor.getRooms().stream().map(r -> roomService.calculateVolume(r)).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal calculateLighting(Floor floor) {
        return floor.getRooms().stream().map(r -> roomService.calculateLighting(r)).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal calculateLightingPerArea(Floor floor) {
        return calculateLighting(floor).divide(calculateArea(floor), MathContext.DECIMAL32);
    }

    public BigDecimal calculateHeating(Floor floor) {
        return floor.getRooms().stream().map(r -> roomService.calculateHeating(r)).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal calculateHeatingPerVolume(Floor floor) {
        return calculateHeating(floor).divide(calculateVolume(floor), MathContext.DECIMAL32);
    }
}
