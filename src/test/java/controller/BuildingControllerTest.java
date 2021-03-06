package controller;

import app.model.Building;
import app.service.BuildingService;
import app.utils.JsonUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import integration.IntegrationTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MvcResult;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.http.MediaType;


public class BuildingControllerTest extends IntegrationTest {

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static final String BUILDINGS_ENDPOINT_PATH = "/buildings";


    @Autowired
    private BuildingService buildingService;


    @Test
    public void shouldReturnAllBuildings() throws Exception {

        MvcResult mvcResult = mockMvc.perform(get(BUILDINGS_ENDPOINT_PATH))
                .andExpect(status().isOk())
                .andReturn();

        String expectedJsonResponse = "[{\"id\":1,\"address\":\"Średnia 45\",\"name\":\"Gimnazjum im. Jana Węglarza\",\"floors\":[{\"id\":1,\"number\":1,\"rooms\":[{\"id\":1,\"number\":1,\"floorArea\":10,\"height\":3,\"lightingPower\":11.4,\"heatingPower\":2801.8},{\"id\":2,\"number\":2,\"floorArea\":10,\"height\":3,\"lightingPower\":12.1,\"heatingPower\":2802.7},{\"id\":3,\"number\":3,\"floorArea\":10,\"height\":3,\"lightingPower\":12.8,\"heatingPower\":2803.6}]},{\"id\":2,\"number\":2,\"rooms\":[{\"id\":4,\"number\":101,\"floorArea\":10,\"height\":3,\"lightingPower\":82.1,\"heatingPower\":3592.7},{\"id\":5,\"number\":102,\"floorArea\":10,\"height\":3,\"lightingPower\":82.8,\"heatingPower\":3593.6},{\"id\":6,\"number\":103,\"floorArea\":10,\"height\":3,\"lightingPower\":83.5,\"heatingPower\":3594.5}]},{\"id\":3,\"number\":3,\"rooms\":[{\"id\":7,\"number\":201,\"floorArea\":10,\"height\":3,\"lightingPower\":152.8,\"heatingPower\":4383.6},{\"id\":8,\"number\":202,\"floorArea\":10,\"height\":3,\"lightingPower\":153.5,\"heatingPower\":4384.5},{\"id\":9,\"number\":203,\"floorArea\":10,\"height\":3,\"lightingPower\":154.2,\"heatingPower\":4385.4}]}]},{\"id\":2,\"address\":\"Berdychowo 21\",\"name\":\"Sklep monopolowy Radość\",\"floors\":[{\"id\":4,\"number\":1,\"rooms\":[{\"id\":10,\"number\":1,\"floorArea\":40,\"height\":3,\"lightingPower\":43.5,\"heatingPower\":19604.5},{\"id\":11,\"number\":2,\"floorArea\":50,\"height\":3,\"lightingPower\":54.2,\"heatingPower\":24505.4}]}]},{\"id\":3,\"address\":\"Krótka 37\",\"name\":\"Synagoga\",\"floors\":[{\"id\":5,\"number\":1,\"rooms\":[{\"id\":12,\"number\":0,\"floorArea\":200,\"height\":12,\"lightingPower\":203.5,\"heatingPower\":238004.5}]},{\"id\":6,\"number\":2,\"rooms\":[{\"id\":13,\"number\":1,\"floorArea\":30,\"height\":3,\"lightingPower\":34.9,\"heatingPower\":18906.3},{\"id\":14,\"number\":2,\"floorArea\":45,\"height\":3,\"lightingPower\":50.6,\"heatingPower\":28357.2},{\"id\":15,\"number\":3,\"floorArea\":20,\"height\":3,\"lightingPower\":26.3,\"heatingPower\":12608.1}]},{\"id\":7,\"number\":3,\"rooms\":[{\"id\":16,\"number\":4,\"floorArea\":40,\"height\":3,\"lightingPower\":47.7,\"heatingPower\":28009.9}]},{\"id\":8,\"number\":4,\"rooms\":[{\"id\":17,\"number\":5,\"floorArea\":30,\"height\":3,\"lightingPower\":39.1,\"heatingPower\":23111.7}]},{\"id\":9,\"number\":5,\"rooms\":[{\"id\":18,\"number\":6,\"floorArea\":20,\"height\":3,\"lightingPower\":30.5,\"heatingPower\":16813.5}]}]}]";
        String result = mvcResult.getResponse().getContentAsString();


        assertThat("all buildings are returned", result, is(equalTo(expectedJsonResponse)));
    }

    @Test
    public void shouldReturnSpecificBuilding() throws Exception {

        String building_id = "2";
        MvcResult mvcResult = mockMvc.perform(get(BUILDINGS_ENDPOINT_PATH + "/"+ building_id))
                .andExpect(status().isOk())
                .andReturn();

        String expectedJsonResponse = "{\"id\":2,\"address\":\"Berdychowo 21\",\"name\":\"Sklep monopolowy Radość\",\"floors\":[{\"id\":4,\"number\":1,\"rooms\":[{\"id\":10,\"number\":1,\"floorArea\":40,\"height\":3,\"lightingPower\":43.5,\"heatingPower\":19604.5},{\"id\":11,\"number\":2,\"floorArea\":50,\"height\":3,\"lightingPower\":54.2,\"heatingPower\":24505.4}]}]}";
        String result = mvcResult.getResponse().getContentAsString();

        assertThat("building is returned", result, is(equalTo(expectedJsonResponse)));
    }

    @Test
    public void shouldCreateNewBuilding() throws Exception {

        int buildingsCount = buildingService.getRepository().findAll().size();;

        Building building = new Building();
        building.setName("Building");
        building.setAddress("address");
        MvcResult mvcResult = mockMvc.perform(post(BUILDINGS_ENDPOINT_PATH)
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(asJsonString(building)))
                .andExpect(status().isOk())
                .andReturn();

        int buildingsCountAfterCreate = buildingService.getRepository().findAll().size();

        assertThat("new building is added", buildingsCount + 1, is(equalTo(buildingsCountAfterCreate)));
    }



    @Test
    public void shouldDeleteSpecificBuilding() throws Exception {

        int buildingsCount = buildingService.getRepository().findAll().size();;
        String building_id = "2";
        MvcResult mvcResult = mockMvc.perform(delete(BUILDINGS_ENDPOINT_PATH + "/"+ building_id))
                .andExpect(status().isOk())
                .andReturn();

        int buildingsCountAfterDelete = buildingService.getRepository().findAll().size();

        assertThat("building is deleted from db", buildingsCount - 1, is(equalTo(buildingsCountAfterDelete)));
    }

    @Test
    public void shouldModifySpecificBuilding() throws Exception {

        String building_id = "2";

        Building building = new Building();
        String newAddress = "newaddress";
        building.setAddress(newAddress);
        MvcResult mvcResult = mockMvc.perform(put(BUILDINGS_ENDPOINT_PATH + "/"+ building_id)
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(asJsonString(building)))
                .andExpect(status().isOk())
                .andReturn();

        Building editedBuilding = buildingService.getRepository().getOne(new Long(2));


        assertThat("building is edited", editedBuilding.getAddress(), is(equalTo(newAddress)));
    }



}