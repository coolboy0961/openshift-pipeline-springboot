package com.example.demo.application;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Menu;
import com.example.demo.infrastructure.MenuRepository;

@Service
public class MenuService {

  @Autowired
  private MenuRepository menuRepository;

  public Optional<Menu> getMenu(String menuId) {
    return menuRepository.findById(menuId);
  }

  public List<Menu> listMenus() {
    return menuRepository.findAll();
  }
}
