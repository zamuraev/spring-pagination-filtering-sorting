package com.zamuraev.controller;

import com.zamuraev.model.Customer;
import com.zamuraev.dto.responce.Response;
import com.zamuraev.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/customers")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class Controller {

    private final CustomerRepository customerRepository;

    @GetMapping("/pageable")
    public Page<Customer> retrieveCustomerWithPaging(@Param(value = "page") int page,
                                                     @Param(value = "size") int size) {
        Pageable requestedPage = PageRequest.of(page, size);
        Page<Customer> customers = customerRepository.findAll(requestedPage);
        return customers;
    }

    @GetMapping("/custom/pageable")
    public Response retrieveCustomer(@Param(value = "page") int page,
                                     @Param(value = "size") int size) {
        Pageable requestedPage = PageRequest.of(page, size);
        Page<Customer> customers = customerRepository.findAll(requestedPage);
        Response res = new Response(customers.getContent(), customers.getTotalPages(),
                customers.getNumber(), customers.getSize());

        return res;
    }

    @GetMapping("/pageable/list")
    public List<Customer> retrieveCustomerListWithPaging(@Param(value = "page") int page,
                                                         @Param(value = "size") int size) {
        Pageable requestedPage = PageRequest.of(page, size);
        Page<Customer> customers = customerRepository.findAll(requestedPage);
        return customers.toList();
    }

    @GetMapping("/pageablebysalary")
    public Slice<Customer> retrieveCustomerBySalaryWithPaging(@Param(value = "salary") double salary,
                                                              @Param(value = "page") int page,
                                                              @Param(value = "size") int size) {
        Pageable requestedPage = PageRequest.of(page, size);
        Slice<Customer> customers = customerRepository.findAllBySalary(salary, requestedPage);
        return customers;
    }

    @GetMapping("/pageable/byagegreaterthan")
    public Slice<Customer> retrieveCustomerByAgeGreaterThan(@Param(value = "age") int age,
                                                            @Param(value = "page") int page,
                                                            @Param(value = "size") int size) {
        Pageable requestedPage = PageRequest.of(page, size);
        Page<Customer> customers = customerRepository.findAllByAgeGreaterThan(age, requestedPage);
        return customers;
    }


    @GetMapping("/pagingandsorting")
    public Page<Customer> pagingAndSortingCustomers(@Param(value = "page") int page,
                                                    @Param(value = "size") int size) {
        Pageable requestedPage = PageRequest.of(page, size, Sort.by("salary")
                .descending()
                .and(Sort.by("age"))
                .and(Sort.by("firstname")));
        Page<Customer> customers = customerRepository.findAll(requestedPage);
        return customers;
    }

    @GetMapping("/pagingfilteringandsorting")
    public Page<Customer> pagingFilteringAndSortingCustomersByAge(@Param(value = "salary") int salary,
                                                                  @Param(value = "age") int age,
                                                                  @Param(value = "page") int page,
                                                                  @Param(value = "size") int size) {
        Pageable requestedPage = PageRequest.of(page, size, Sort.by("salary")
                .descending()
                .and(Sort.by("age"))
                .and(Sort.by("firstname")));

        Page<Customer> customers = customerRepository.findAllByAgeGreaterThan(age, requestedPage);
        return customers;
    }


}
