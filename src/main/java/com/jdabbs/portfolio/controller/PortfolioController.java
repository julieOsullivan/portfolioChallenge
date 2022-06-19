package com.jdabbs.portfolio.controller;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.jdabbs.portfolio.exceptions.NotFoundException;
import com.jdabbs.portfolio.implementation.PortfolioServiceImpl;
import com.jdabbs.portfolio.model.ClientInstruction;
import com.jdabbs.portfolio.model.Portfolio;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/portfolio")
public class PortfolioController {

    @Autowired
    private PortfolioServiceImpl portFolioServiceImpl;

    @GetMapping(path = "/{id}")
    public Portfolio getPortfolio(@PathVariable Long id) {
        if((portFolioServiceImpl.findById(id)) == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found");
        }
        return portFolioServiceImpl.findById(id);
    }

    @GetMapping(path = "/")
    @JsonBackReference
    public List<Portfolio> getPortfolio() {
        return portFolioServiceImpl.findAll();
    }

    @PostMapping(path="/")
    public Portfolio createNewPortfolio(@RequestBody Portfolio portfolio){
        return portFolioServiceImpl.addNewPortfolio(portfolio);
    }

    @PutMapping(path="/")
    public Portfolio updatePortfolio(@RequestBody Portfolio portfolio){
        return portFolioServiceImpl.updatePortfolio(portfolio);
    }


    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFound() {
        log.error("Handling not found exception");

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("error");

        return modelAndView;
    }
}
