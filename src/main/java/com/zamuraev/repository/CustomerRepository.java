package com.zamuraev.repository;

import com.zamuraev.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Slice<Customer> findAllBySalary (double salary, Pageable pageable);
    Page<Customer> findAllByAgeGreaterThan(int age, Pageable pageable);

}
