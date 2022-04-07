package com.wings.wingsuserservice.repository;

import com.wings.wingsuserservice.models.Route;
import com.wings.wingsuserservice.models.User;
import com.wings.wingsuserservice.payload.request.RouteDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {
    //Optional<Route> findByDepar_govOrAdr_departureOrArri_govOrAdr_arrival(RouteDTO routeDTO);

}
