package service;

import app.model.Floor;
import app.model.Room;
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

public class RoomServiceTest {

    @Mock
    private Room room;

    @InjectMocks
    @Resource
    private RoomService roomService;

    private Room getRoom() { return Room.builder()
                                        .floorArea(getArea())
                                        .height(getHeight())
                                        .heatingPower(getHeating())
                                        .lightingPower(getLighting())
                                        .build(); }

    private BigDecimal getArea() {
        return BigDecimal.valueOf(2);
    }
    private BigDecimal getHeight() {
        return BigDecimal.valueOf(5);
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
        when(room.getFloorArea()).thenReturn(getArea());

        BigDecimal result = roomService.calculateArea(getRoom());

        assertThat("area", result, is(equalTo(BigDecimal.valueOf(2))));
    }


    @Test
    public void shouldCalculateVolume() {
        when(room.getFloorArea()).thenReturn(getArea());
        when(room.getHeight()).thenReturn(getHeight());

        BigDecimal result = roomService.calculateVolume(getRoom());

        assertThat("volume", result, is(equalTo(BigDecimal.valueOf(10))));

    }

    @Test
    public void shouldCalculateLighting() {
        when(room.getLightingPower()).thenReturn(getLighting());

        BigDecimal result = roomService.calculateLighting(getRoom());

        assertThat("lightning", result, is(equalTo(BigDecimal.valueOf(1))));
    }

    @Test
    public void shouldCalculateLightingPerArea() {
        when(room.getFloorArea()).thenReturn(getArea());
        when(room.getLightingPower()).thenReturn(getLighting());



        BigDecimal result = roomService.calculateLightingPerArea(getRoom());

        assertThat("lightningPerArea", result, is(equalTo(BigDecimal.valueOf(0.5))));
    }

    @Test
    public void shouldCalculateHeating() {
        when(room.getHeatingPower()).thenReturn(getHeating());

        BigDecimal result = roomService.calculateHeating(getRoom());

        assertThat("heating", result, is(equalTo(BigDecimal.valueOf(1))));
    }

    @Test
    public void shouldCalculateHeatingPerVolume() {
        when(room.getHeatingPower()).thenReturn(getHeating());
        when(room.getFloorArea()).thenReturn(getArea());
        when(room.getHeight()).thenReturn(getHeight());

        BigDecimal result = roomService.calculateHeatingPerVolume(getRoom());

        assertThat("heatingPerVolume", result, is(equalTo(BigDecimal.valueOf(0.1))));
    }

}
