package com.example.demo.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@Entity
@RequiredArgsConstructor
@NoArgsConstructor
public class Menu {
  @Id
  private String id;

  @Column(nullable = false)
  @NonNull
  private String name;

  @Column(nullable = false)
  @NonNull
  private long price;

  @Column(nullable = false)
  private String date;

  @OneToOne(cascade = CascadeType.ALL, mappedBy = "menu")
  @NonNull
  private Tuition tuition;
}
