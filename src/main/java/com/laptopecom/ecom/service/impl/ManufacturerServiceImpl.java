package com.laptopecom.ecom.service.impl;

import com.laptopecom.ecom.model.Manufacturer;
import com.laptopecom.ecom.repository.ManufacturerRepository;
import com.laptopecom.ecom.service.ManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ManufacturerServiceImpl implements ManufacturerService {
    @Autowired
    ManufacturerRepository manufacturerRepository;
    public List<Manufacturer> getAllManufacturer(){
        return manufacturerRepository.findAll();
    }
    public void addManufacturer(Manufacturer manufacturer){
        manufacturerRepository.save(manufacturer);
    }
    public void deleteManufacturerById(int id){
        manufacturerRepository.deleteById(id);
    }
    public Optional<Manufacturer> checkManufacturerById(int id){
        return manufacturerRepository.findById(id);
    }
}
