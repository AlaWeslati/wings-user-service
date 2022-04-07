package com.wings.wingsuserservice.security.services;

import com.wings.wingsuserservice.models.Route;
import com.wings.wingsuserservice.models.User;
import com.wings.wingsuserservice.payload.request.RouteDTO;
import com.wings.wingsuserservice.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class Routeservice {
    @Autowired
    RouteRepository routeRepository;



    /*public Optional<Route> getRouteByFilter(RouteDTO routeDTO){
        return routeRepository.findByDepar_govOrAdr_departureOrArri_govOrAdr_arrival(routeDTO);

    }*/

    public Route newRoute(Long id, RouteDTO routeDTO){
        User user = new User();
        user.setId(id);
        Route route = new Route(routeDTO.getDepar_gov(),
                routeDTO.getAdr_departure(),
                routeDTO.getArri_gov(),
                routeDTO.getAdr_arrival(),
                routeDTO.getLongitude(),
                routeDTO.getLatitude(),
                routeDTO.getDescription(),
                user);
        return   routeRepository.save(route);
    }

    public Route Update(long id, RouteDTO routeDTO){
        Optional<Route> route = routeRepository.findById(id);
        if (route.isPresent()) {
            Route _route = route.get();
            _route.setDepar_gov(routeDTO.getDepar_gov());
            _route.setAdr_departure(routeDTO.getAdr_departure());
            _route.setArri_gov(routeDTO.getArri_gov());
            _route.setAdr_arrival(routeDTO.getAdr_arrival());
            _route.setLongitude(routeDTO.getLongitude());
            _route.setLatitude(routeDTO.getLatitude());
            _route.setDescription(routeDTO.getDescription());
            return routeRepository.save(_route);
        }else {
            return null;
        }
    }

}
