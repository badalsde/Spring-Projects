package com.cabbooking.repository;

import com.cabbooking.entity.Cab;
import org.springframework.data.repository.CrudRepository;

public interface CabRepository extends CrudRepository<Cab,Integer> {
    public Cab findByCabNo(Integer cabNo);
}
