package com.example.demo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Tuition {

  @Id
  @JsonIgnore
  private String id;

  @JsonIgnore
  @OneToOne
  @JoinColumn(name = "menuId", nullable = false)
  private Menu menu;

  @Column(nullable = false)
  private double cal;

  @Column(nullable = false)
  private double protein;

  @Column(nullable = false)
  private double fat;

  @Column(nullable = false)
  private double carbo;

  @Column(nullable = false)
  private double salt;
}
