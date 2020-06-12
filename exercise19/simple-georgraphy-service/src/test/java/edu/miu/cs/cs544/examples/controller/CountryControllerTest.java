package edu.miu.cs.cs544.examples.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.miu.cs.cs544.examples.service.CityServiceImpl;
import edu.miu.cs.cs544.examples.service.CountryServiceImpl;
import edu.miu.cs.cs544.examples.service.response.CityResponse;
import edu.miu.cs.cs544.examples.service.response.CountryResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;

@WebMvcTest(CountryController.class)
class CountryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CityServiceImpl cityService;

    @MockBean
    private CountryServiceImpl countryService;

    @Test
    void getAllCountries() throws Exception {
        CountryResponse country1 = buildCountryResponse1();
        CountryResponse country2 = buildCountryResponse2();
        Mockito.when(countryService.getAllCountries()).thenReturn(Arrays.asList(country1, country2));
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/countries"))
                .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        String jsonResult = mvcResult.getResponse().getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();
        CountryResponse[] countryResponses = objectMapper.readValue(jsonResult, CountryResponse[].class);
        Assertions.assertTrue(countryResponses.length == 2);
        Assertions.assertEquals(countryResponses[0].getName(), country1.getName());
        Assertions.assertEquals(countryResponses[1].getName(), country2.getName());
    }

    @Test
    void getAllCountriesEmpty() throws Exception {
        Mockito.when(countryService.getAllCountries()).thenReturn(null);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/countries"))
                .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        String jsonResult = mvcResult.getResponse().getContentAsString();
        Assertions.assertEquals(jsonResult, "");
    }

    @Test
    void getCountryById() throws Exception {
        CountryResponse country1 = this.buildCountryResponse1();
        Mockito.when(countryService.getCountryById(1)).thenReturn(country1);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/countries/1"))
                .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        String jsonResult = mvcResult.getResponse().getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();
        CountryResponse countryResponse = objectMapper.readValue(jsonResult, CountryResponse.class);
        Assertions.assertEquals(countryResponse.getName(), country1.getName());
        Assertions.assertEquals(countryResponse.getId(), country1.getId());
    }

    @Test
    void getCountryByIdNotFound() throws Exception {
        Mockito.when(countryService.getCountryById(1)).thenReturn(null);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/countries/1"))
                .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        String jsonResult = mvcResult.getResponse().getContentAsString();
        Assertions.assertEquals(jsonResult, "");
    }

    @Test
    void testGetCountryByName() throws Exception {
        CountryResponse country1 = buildCountryResponse1();
        Mockito.when(countryService.getCountryByName(country1.getName())).thenReturn(country1);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/countries?name=country 1"))
                .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        String jsonResult = mvcResult.getResponse().getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();
        CountryResponse countryResponse = objectMapper.readValue(jsonResult, CountryResponse.class);
        Assertions.assertEquals(countryResponse.getName(), country1.getName());
        Assertions.assertEquals(countryResponse.getId(), country1.getId());
    }

    @Test
    void getAllCitiesByCountryId() throws Exception {
        CityResponse cityResponse1 = new CityResponse(1, "test city 1", buildCountryResponse1());
        CityResponse cityResponse2 = new CityResponse(2, "test city 2", buildCountryResponse1());
        Mockito.when(cityService.getAllCitiesByCountryId(1)).thenReturn(Arrays.asList(cityResponse1, cityResponse2));
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/countries/1/cities"))
                .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        ObjectMapper objectMapper = new ObjectMapper();
        CityResponse[] cityResponses = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), CityResponse[].class);
        Assertions.assertTrue(cityResponses.length == 2);
        Assertions.assertEquals(cityResponses[0].getName(), cityResponse1.getName());
        Assertions.assertEquals(cityResponses[1].getName(), cityResponse2.getName());
    }

    private CountryResponse buildCountryResponse1() {
        return new CountryResponse(1, "country 1");
    }

    private CountryResponse buildCountryResponse2() {
        return new CountryResponse(2, "country 2");
    }
}