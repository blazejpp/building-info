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
    public void shouldCalculateArea() throws Exception {

        MvcResult mvcResult = mockMvc.perform(get(AREA_ENDPOINT_PATH + "building/1"))
                .andExpect(status().isOk())
                .andReturn();

        BigDecimal result = JsonUtils.fromJson(mvcResult.getResponse().getContentAsString(), new TypeReference<BigDecimal>(){});

        assertThat("area calculated", result, is(equalTo(BigDecimal.valueOf(90))));
    }

}