package service;

import app.model.Building;
import app.model.Floor;
import app.service.BuildingService;
import app.service.FloorService;
import app.service.RoomService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class BuildingServiceTest {

    @Mock
    private FloorService floorService;

    @InjectMocks
    @Resource
    private BuildingService buildingService;

    private Building getBuilding() {
        List<Floor> floors = new ArrayList<>();
        for(int i = 0; i<5; ++i) {
            floors.add(Floor.builder().build());
        }
        return Building.builder()
                .id(1L)
                .address("Średnia 45")
                .name("Gimnazjum im. Jana Węglarza")
                .floors(floors)
                .build();
    }

    private BigDecimal getArea() {
        return BigDecimal.ONE;
    }
    private BigDecimal getVolume() {
        return BigDecimal.ONE;
    }
    private BigDecimal getLighting() {
        return BigDecimal.ONE;
    }
    private BigDecimal getHeating() {
        return BigDecimal.ONE;
    }


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldCalculateArea() {
        when(floorService.calculateArea(any())).thenReturn(getArea());

        BigDecimal result = buildingService.calculateArea(getBuilding());

        assertThat("area", result, is(equalTo(BigDecimal.valueOf(5))));
    }


    @Test
    public void shouldCalculateVolume() {
        when(floorService.calculateVolume(any())).thenReturn(getVolume());

        BigDecimal result = buildingService.calculateVolume(getBuilding());

        assertThat("volume", result, is(equalTo(BigDecimal.valueOf(5))));

    }

    @Test
    public void shouldCalculateLighting() {
        when(floorService.calculateLighting(any())).thenReturn(getLighting());

        BigDecimal result = buildingService.calculateLighting(getBuilding());

        assertThat("lightning", result, is(equalTo(BigDecimal.valueOf(5))));
    }

    @Test
    public void shouldCalculateLightingPerArea() {
        when(floorService.calculateArea(any())).thenReturn(getArea());
        when(floorService.calculateLighting(any())).thenReturn(getLighting());

        BigDecimal result = buildingService.calculateLightingPerArea(getBuilding());

        assertThat("lightningPerArea", result, is(equalTo(BigDecimal.valueOf(1))));
    }

    @Test
    public void shouldCalculateHeating() {
        when(floorService.calculateHeating(any())).thenReturn(getHeating());

        BigDecimal result = buildingService.calculateHeating(getBuilding());

        assertThat("heating", result, is(equalTo(BigDecimal.valueOf(5))));
    }
}
