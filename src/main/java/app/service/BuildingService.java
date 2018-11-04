package app.service;

import app.model.Building;
import app.repository.BuildingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;

@Service
public class BuildingService {

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private FloorService floorService;

    public BuildingRepository getRepository() {
        return buildingRepository;
    }

    public BigDecimal calculateArea(Building building) {
        return building.floors.stream().map(f -> floorService.calculateArea(f)).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal calculateVolume(Building building) {
        return building.floors.stream().map(f -> floorService.calculateVolume(f)).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
