package controller;

import app.utils.JsonUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import integration.IntegrationTest;
import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AreaControllerTest extends IntegrationTest {

    private static final String AREA_ENDPOINT_PATH = "/area/";

    @Test
    public void shouldCorrectlyCalculateBuildingArea() throws Exception {

        MvcResult mvcResult = mockMvc.perform(get(AREA_ENDPOINT_PATH + "building/1"))
                .andExpect(status().isOk())
                .andReturn();

        BigDecimal result = JsonUtils.fromJson(mvcResult.getResponse().getContentAsString(), new TypeReference<BigDecimal>(){});

        assertThat("building area calculated", result, is(equalTo(BigDecimal.valueOf(90))));
    }

    @Test
    public void shouldCorrectlyCaculateFloorArea() throws Exception

    {
        String building_id = "1";
        String floor_number = "1";
        MvcResult mvcResult = mockMvc.perform(get(AREA_ENDPOINT_PATH + "building/" + building_id + "/floor/" + floor_number))
                .andExpect(status().isOk())
                .andReturn();

        BigDecimal result = JsonUtils.fromJson(mvcResult.getResponse().getContentAsString(), new TypeReference<BigDecimal>() {
        });

        assertThat("floor area calculated", result, is(equalTo(BigDecimal.valueOf(30))));
    }

    @Test
    public void shouldCorrectlyCaculateRoomArea() throws Exception

    {
        String building_id = "1";
        String room_number = "1";
        MvcResult mvcResult = mockMvc.perform(get(AREA_ENDPOINT_PATH + "building/" + building_id + "/room/" + room_number))
                .andExpect(status().isOk())
                .andReturn();

        BigDecimal result = JsonUtils.fromJson(mvcResult.getResponse().getContentAsString(), new TypeReference<BigDecimal>() {
        });

        assertThat("room area calculated", result, is(equalTo(BigDecimal.valueOf(10))));
    }


}