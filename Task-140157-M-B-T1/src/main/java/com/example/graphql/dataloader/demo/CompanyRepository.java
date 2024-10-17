package com.example.graphql.dataloader.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    List<Company> findByIdIn(List<Long> ids);
}
