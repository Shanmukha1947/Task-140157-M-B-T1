package com.example.graphql.dataloader.demo;

import org.dataloader.DataLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class DataLoaderService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CompanyRepository companyRepository;

    private DataLoader<Long, User> userDataLoader;
    private DataLoader<Long, Company> companyDataLoader;

    @PostConstruct
    public void init() {
        userDataLoader = new DataLoader<>(keys -> CompletableFuture.supplyAsync(() -> userRepository.findByIdIn(keys)));
        companyDataLoader = new DataLoader<>(keys -> CompletableFuture.supplyAsync(() -> companyRepository.findByIdIn(keys)));
    }

    public DataLoader<Long, User> getUserDataLoader() {
        return userDataLoader;
    }

    public DataLoader<Long, Company> getCompanyDataLoader() {
        return companyDataLoader;
    }
}
