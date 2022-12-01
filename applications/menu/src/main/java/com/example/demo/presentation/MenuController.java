package com.example.demo.presentation;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.application.MenuService;
import com.example.demo.domain.Menu;

@RestController
@CrossOrigin
public class MenuController {

  @Autowired
  private MenuService menuService;

  @Autowired
  private ModelMapper modelMapper;

  @GetMapping("/v1/menus")
  public List<MenuDTO> listMenues() {
    List<Menu> menues = menuService.listMenus();
    List<MenuDTO> response = modelMapper.map(menues, new TypeToken<List<MenuDTO>>() {
    }.getType());
    return response;
  }

  @GetMapping(path = "/v1/menus/{menuId}")
  public MenuDTO getMenu(@PathVariable String menuId) throws Exception {
    Optional<Menu> menu = menuService.getMenu(menuId);
    if (menu.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    MenuDTO response = modelMapper.map(menu.get(), MenuDTO.class);
    return response;
  }
}
