package com.fbs.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.fbs.entity.Flight;

import jakarta.transaction.Transactional;

public interface FlightRepository extends CrudRepository<Flight, Integer>{
	
	// Query to find flight details by source, destination, and date with available seats
    @Query("SELECT f FROM Flight f WHERE f.source = :source AND f.destination = :dest AND f.flightAvailableDate = :jdate AND f.seatCount > 0")
    public List<Flight> findFlightDetails(@Param("source") String source,@Param("dest") String destination,@Param("jdate") LocalDate date);
    
    // Update seat count in the database (modifies the entity)
    @Modifying(clearAutomatically = true)  // Ensure the persistence context is cleared to avoid caching issues
    @Transactional
    @Query("UPDATE Flight f SET f.seatCount = f.seatCount - :noOfSeats WHERE f.flightId = :flightId")
    public void updateSeatsDetails(@Param("flightId") String flightId,@Param("noOfSeats") int noOfSeats);

    // Find a flight by its ID
    @Query("SELECT f FROM Flight f WHERE f.flightId = :flightId")
    public Flight findFlight(@Param("flightId") String flightId);

    // Find distinct sources (for source dropdown or search purposes)
    @Query("SELECT DISTINCT f.source FROM Flight f")
    public List<String> findFlightSources();

    // Find distinct destinations (for destination dropdown or search purposes)
    @Query("SELECT DISTINCT f.destination FROM Flight f")
    public List<String> findFlightDestinations();
    
}

