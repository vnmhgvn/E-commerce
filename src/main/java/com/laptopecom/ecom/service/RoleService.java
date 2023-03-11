package com.laptopecom.ecom.service;

import com.laptopecom.ecom.model.Role;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface RoleService {
    List<Role> getAllRole();
    Optional<Role> findRoleById(int id);
}
