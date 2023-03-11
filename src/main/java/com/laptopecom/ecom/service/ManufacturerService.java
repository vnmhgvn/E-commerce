package com.laptopecom.ecom.service;

import com.laptopecom.ecom.model.Manufacturer;
import com.laptopecom.ecom.repository.ManufacturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ManufacturerService {

    public List<Manufacturer> getAllManufacturer();
    public void addManufacturer(Manufacturer manufacturer);
    public void deleteManufacturerById(int id);
    public Optional<Manufacturer> checkManufacturerById(int id);
}
