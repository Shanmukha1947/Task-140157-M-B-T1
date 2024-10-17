package com.example.graphql.dataloader.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.dataloader.DataLoader;
import graphql.kickstart.tools.GraphQLQueryResolver;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
public class UserResolver implements GraphQLQueryResolver {

    @Autowired
    private DataLoaderService dataLoaderService;

    @Autowired
    private UserRepository userRepository;

    // Fetch a single user using DataLoader
    public CompletableFuture<User> user(Long id) {
        DataLoader<Long, User> userLoader = dataLoaderService.getUserDataLoader();
        return userLoader.load(id);
    }

    // Fetch all users using the repository directly
    public List<User> users() {
        return userRepository.findAll();
    }
}
