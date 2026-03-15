package com.example.logistics_api.repository;

import com.example.logistics_api.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {

    List<Route> findByStartPointIgnoreCase(String startPoint);

    List<Route> findByEndPointIgnoreCase(String endPoint);

    List<Route> findByStartPointIgnoreCaseAndEndPointIgnoreCase(String startPoint, String endPoint);
}