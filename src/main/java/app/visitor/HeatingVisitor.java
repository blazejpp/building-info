package app.visitor;

import app.model.Building;
import app.model.Floor;
import app.model.Room;
import app.service.BuildingService;
import app.service.FloorService;
import app.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class HeatingVisitor implements Visitor {

    @Autowired
    private BuildingService buildingService;

    @Autowired
    private FloorService floorService;

    @Autowired
    private RoomService roomService;

    @Override
    public BigDecimal calculate(Building building) {
        return buildingService.calculateHeatingPerVolume(building);
    }

    @Override
    public BigDecimal calculate(Floor floor) {
        return floorService.calculateHeatingPerVolume(floor);
    }

    @Override
    public BigDecimal calculate(Room room) {
        return roomService.calculateHeatingPerVolume(room);
    }
}
