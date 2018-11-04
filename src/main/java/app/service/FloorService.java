package app.service;

import app.model.Floor;
import app.repository.FloorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class FloorService {

    @Autowired
    private FloorRepository floorRepository;

    @Autowired
    private RoomService roomService;

    public BigDecimal calculateArea(Floor floor) {
        return floor.rooms.stream().map(r -> roomService.calculateArea(r)).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
