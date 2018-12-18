package service;

import app.model.Floor;
import app.model.Room;
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

public class FloorServiceTest {

    @Mock
    private RoomService roomService;

    @InjectMocks
    @Resource
    private FloorService floorService;

    private Floor getFloor() {
        List<Room> rooms = new ArrayList<>();
        for(int i = 0; i<5; ++i) {
            rooms.add(Room.builder().build());
        }
        return Floor.builder()
                .id(1L)
                .number(2L)
                .rooms(rooms)
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
        when(roomService.calculateArea(any())).thenReturn(getArea());

        BigDecimal result = floorService.calculateArea(getFloor());

        assertThat("area", result, is(equalTo(BigDecimal.valueOf(5))));
    }


    @Test
    public void shouldCalculateVolume() {
        when(roomService.calculateVolume(any())).thenReturn(getVolume());

        BigDecimal result = floorService.calculateVolume(getFloor());

        assertThat("volume", result, is(equalTo(BigDecimal.valueOf(5))));

    }

    @Test
    public void shouldCalculateLighting() {
        when(roomService.calculateLighting(any())).thenReturn(getLighting());

        BigDecimal result = floorService.calculateLighting(getFloor());

        assertThat("lightning", result, is(equalTo(BigDecimal.valueOf(5))));
    }

    @Test
    public void shouldCalculateLightingPerArea() {
        when(roomService.calculateArea(any())).thenReturn(getArea());
        when(roomService.calculateLighting(any())).thenReturn(getLighting());

        BigDecimal result = floorService.calculateLightingPerArea(getFloor());

        assertThat("lightningPerArea", result, is(equalTo(BigDecimal.valueOf(1))));
    }

    @Test
    public void shouldCalculateHeating() {
        when(roomService.calculateHeating(any())).thenReturn(getHeating());

        BigDecimal result = floorService.calculateHeating(getFloor());

        assertThat("heating", result, is(equalTo(BigDecimal.valueOf(5))));
    }

    @Test
    public void shouldCalculateHeatingPerVolume() {
        when(roomService.calculateHeating(any())).thenReturn(getHeating());
        when(roomService.calculateVolume(any())).thenReturn(getVolume());

        Floor floor = getFloor();
        BigDecimal heating = floorService.calculateHeating(floor);
        BigDecimal volume = floorService.calculateVolume(floor);
        BigDecimal result = heating.divide(volume, MathContext.DECIMAL32);

        assertThat("heatingPerVolume", result, is(equalTo(BigDecimal.valueOf(1))));
    }

}
