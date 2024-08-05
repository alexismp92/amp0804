package com.demo.rental.repository;

import com.demo.rental.entity.Tool;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IToolsRepo extends JpaRepository<Tool,String> {
}
