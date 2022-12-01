package com.example.demo.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Menu;

@Repository
public interface MenuRepository extends JpaRepository<Menu, String> {
}
