package com.dhrmarques.ermdemo.controller;

import com.dhrmarques.ermdemo.model.TBUser;
import com.dhrmarques.ermdemo.service.TBUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
public class TBUserController {

    private final TBUserService userService;

    @Autowired
    public TBUserController(TBUserService userService) {this.userService = userService;}

    @GetMapping
    public ResponseEntity<List<TBUser>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<TBUser>> findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(userService.findById(id));
    }

    @PostMapping
    public ResponseEntity<TBUser> create(@RequestBody TBUser user){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
    }

    @PutMapping
    public ResponseEntity<TBUser> update(@RequestBody TBUser user){
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
