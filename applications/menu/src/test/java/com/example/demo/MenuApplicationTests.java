package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.demo.presentation.MenuController;

@SpringBootTest
public class MenuApplicationTests {
  @Autowired
  private MenuController menuController;

  private MockMvc mockMvc;

  @BeforeEach
  void setup() {
    this.mockMvc = MockMvcBuilders.standaloneSetup(menuController).addFilter(((request, response, chain) -> {
      response.setCharacterEncoding("UTF-8");
      chain.doFilter(request, response);
    })).build();
  }

  @Test
  public void getMenu_200() throws Exception {
    // Arrange
    int expectedStatus = 200;
    String expectedJsonResponse = "{\"id\":\"4iX3dG27\",\"name\":\"牛丼\",\"date\":\"2022/2/15\",\"price\":300,\"tuition\":{\"cal\":829.0,\"protein\":78.4,\"fat\":15.5,\"carbo\":3.3,\"salt\":9.0}}";

    // Act
    MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/v1/menus/4iX3dG27")).andReturn();
    int actualStatus = mvcResult.getResponse().getStatus();
    String actualResponse = mvcResult.getResponse().getContentAsString();

    // Assert
    assertEquals(expectedStatus, actualStatus);
    assertEquals(expectedJsonResponse, actualResponse);
  }

  @Test
  public void listMenu_200() throws Exception {
    // Arrange
    int expectedStatus = 200;
    String expectedJsonResponse = "[{\"id\":\"4iX3dG27\",\"name\":\"牛丼\",\"date\":\"2022/2/15\",\"price\":300,\"tuition\":{\"cal\":829.0,\"protein\":78.4,\"fat\":15.5,\"carbo\":3.3,\"salt\":9.0}},{\"id\":\"XvSEjhPC\",\"name\":\"きつねそば\",\"date\":\"2002/2/15\",\"price\":300,\"tuition\":{\"cal\":394.0,\"protein\":49.0,\"fat\":1.2,\"carbo\":10.3,\"salt\":4.0}},{\"id\":\"t5CIZc1v\",\"name\":\"豚の生姜焼き定食\",\"date\":\"2002/2/15\",\"price\":300,\"tuition\":{\"cal\":983.0,\"protein\":78.0,\"fat\":15.5,\"carbo\":3.3,\"salt\":9.0}},{\"id\":\"vpthNhwj\",\"name\":\"秋の味覚スペシャルランチ\",\"date\":\"2022/2/16\",\"price\":300,\"tuition\":{\"cal\":789.0,\"protein\":98.2,\"fat\":19.2,\"carbo\":23.0,\"salt\":12.0}},{\"id\":\"JEvgfchH\",\"name\":\"特製エビチャーハン\",\"date\":\"2022/2/16\",\"price\":300,\"tuition\":{\"cal\":829.0,\"protein\":98.3,\"fat\":20.2,\"carbo\":11.4,\"salt\":12.0}},{\"id\":\"19MFgjgc\",\"name\":\"月見うどん\",\"date\":\"2022/2/16\",\"price\":300,\"tuition\":{\"cal\":387.0,\"protein\":68.7,\"fat\":6.4,\"carbo\":17.5,\"salt\":4.9}},{\"id\":\"6dVjArJ1\",\"name\":\"焼きカレー\",\"date\":\"2022/2/17\",\"price\":300,\"tuition\":{\"cal\":631.0,\"protein\":69.0,\"fat\":14.4,\"carbo\":13.2,\"salt\":8.0}},{\"id\":\"EdoOwysi\",\"name\":\"ナスと豚のオイスター炒め\",\"date\":\"2022/2/17\",\"price\":300,\"tuition\":{\"cal\":739.0,\"protein\":55.0,\"fat\":13.2,\"carbo\":16.0,\"salt\":10.0}},{\"id\":\"TaScdcgW\",\"name\":\"鳥の唐揚げ定食\",\"date\":\"2022/2/19\",\"price\":300,\"tuition\":{\"cal\":580.0,\"protein\":58.0,\"fat\":13.2,\"carbo\":12.5,\"salt\":7.5}},{\"id\":\"znD78jTA\",\"name\":\"塩サバ焼き定食\",\"date\":\"2022/2/19\",\"price\":300,\"tuition\":{\"cal\":300.0,\"protein\":51.0,\"fat\":11.4,\"carbo\":20.0,\"salt\":8.1}},{\"id\":\"KDTdAyId\",\"name\":\"牛サガリの味噌焼き\",\"date\":\"2022/2/19\",\"price\":300,\"tuition\":{\"cal\":650.0,\"protein\":22.0,\"fat\":5.4,\"carbo\":15.5,\"salt\":7.0}}]";

    // Act
    MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/v1/menus/")).andReturn();
    int actualStatus = mvcResult.getResponse().getStatus();
    String actualJsonResponse = mvcResult.getResponse().getContentAsString();

    // Assert
    assertEquals(actualStatus, expectedStatus);
    assertEquals(expectedJsonResponse, actualJsonResponse);
  }

  @Test
  public void getMenu_404() throws Exception {
    MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/v1/menus/1111111")).andReturn();
    int status = mvcResult.getResponse().getStatus();
    assertEquals(404, status);
  }

}
