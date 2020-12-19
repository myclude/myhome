package me.myclude.calculator.members.service;

import lombok.RequiredArgsConstructor;
import me.myclude.calculator.members.entity.Role;
import me.myclude.calculator.members.repository.RoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RoleService {

    private final RoleRepository roleRepository;

    @Transactional
    public Role save(Role role) {
        return roleRepository.save(role);
    }
}
