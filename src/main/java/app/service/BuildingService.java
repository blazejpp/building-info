package app.service;

import app.dto.RoomHeatingDto;
import app.model.Building;
import app.model.Room;
import app.repository.BuildingRepository;
import com.sun.java.swing.plaf.windows.WindowsTreeUI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BuildingService {

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private FloorService floorService;

    @Autowired
    private RoomService roomService;

    public BuildingRepository getRepository() {
        return buildingRepository;
    }

    public BigDecimal calculateArea(Building building) {
        return building.getFloors().stream().map(f -> floorService.calculateArea(f)).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal calculateVolume(Building building) {
        return building.getFloors().stream().map(f -> floorService.calculateVolume(f)).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal calculateLighting(Building building) {
        return building.getFloors().stream().map(f -> floorService.calculateLighting(f)).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal calculateLightingPerArea(Building building) {
        return calculateLighting(building).divide(calculateArea(building), MathContext.DECIMAL32);
    }

    public BigDecimal calculateHeating(Building building) {
        return building.getFloors().stream().map(f -> floorService.calculateHeating(f)).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal calculateHeatingPerVolume(Building building) {
        return calculateHeating(building).divide(calculateVolume(building), MathContext.DECIMAL32);
    }

    public List<RoomHeatingDto> getRoomsExceedingHeating(Building building, BigDecimal heating) {
        return building.getFloors().stream()
                .map(f -> f.getRooms()).flatMap(List::stream)
                .map(r -> RoomHeatingDto.builder()
                        .id(r.getId())
                        .number(r.getNumber())
                        .floorNumber(r.getFloor().getNumber())
                        .heatingPerArea(roomService.calculateHeatingPerVolume(r))
                        .build())
                .filter(r -> r.getHeatingPerArea().compareTo(heating) > 0)
                .collect(Collectors.toList());
    }
}
