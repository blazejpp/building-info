package controller;

import app.Application;
import app.utils.JsonUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author visix
 */

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = Application.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-test.properties")
public class AreaControllerTest {

    private static final String AREA_ENDPOINT_PATH = "/area/";

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldCalculateArea() throws Exception {
        //WHEN
        MvcResult mvcResult = mockMvc.perform(get(AREA_ENDPOINT_PATH + "building/1"))
                .andExpect(status().isOk())
                .andReturn();

        BigDecimal result = JsonUtils.fromJson(mvcResult.getResponse().getContentAsString(), new TypeReference<BigDecimal>(){});

        assertThat("area calculated", result, is(equalTo(BigDecimal.valueOf(90))));
    }

}