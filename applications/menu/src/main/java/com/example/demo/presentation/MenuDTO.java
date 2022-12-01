package com.example.demo.presentation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuDTO {
  private String id;

  private String name;

  private String date;

  private long price;

  private Tuition tuition;

  @Data
  private static class Tuition {
    private double cal;

    private double protein;

    private double fat;

    private double carbo;

    private double salt;
  }
}
