package com.wings.wingsuserservice.controllers;

import com.wings.wingsuserservice.models.Route;
import com.wings.wingsuserservice.payload.request.RouteDTO;
import com.wings.wingsuserservice.repository.RouteRepository;
import com.wings.wingsuserservice.security.services.Routeservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class RouteController {
    @Autowired
    RouteRepository routeRepository;

    @Autowired
    Routeservice routeservice;

    @GetMapping("/GetRouteById/{id}")
    public Optional<Route> getRouteById(@PathVariable("id") long id) {
        return  routeRepository.findById(id);
    }

    /*@GetMapping("/GetRouteByFilter/{id}")
    public Optional<Route> getRouteByFilter(@RequestBody RouteDTO routeDTO) {
        return  routeservice.getRouteByFilter(routeDTO);
    }*/

    @PostMapping("/Add-Route/{id}")
    public Route AddRoute(@PathVariable("id") long id, @RequestBody RouteDTO routeDTO) {
          return   routeservice.newRoute(id, routeDTO);
    }

    @PutMapping("/UpdateRouteById/{id}")
    public Route updateRoute(@PathVariable("id") long id, @RequestBody RouteDTO routeDTO) {
        try{
            return routeservice.Update(id,routeDTO);
        }catch (Exception e) {
            return null;
        }
    }

    @DeleteMapping("/DeleteRoute/{id}")
    public void DeleteRoute(@PathVariable("id") long id) {
            routeRepository.deleteById(id);
    }

    @DeleteMapping("/DeleteAllRoute")
    public ResponseEntity<HttpStatus> DeleteAllRoute() {
        try {
            routeRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
