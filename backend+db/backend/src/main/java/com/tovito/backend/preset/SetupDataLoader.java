package com.tovito.backend.preset;

import com.tovito.backend.entity.PrivilegeEntity;
import com.tovito.backend.entity.RoleEntity;
import com.tovito.backend.entity.UserEntity;
import com.tovito.backend.repository.PrivilegeRepo;
import com.tovito.backend.repository.RoleRepo;
import com.tovito.backend.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {
    boolean alreadySetup = false;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private PrivilegeRepo privilegeRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (alreadySetup) return;

        PrivilegeEntity viewPrivilege = createPrivilegeIfNotFound("VIEW_PRODUCT_PRIVILEGE");
        PrivilegeEntity createPrivilege = createPrivilegeIfNotFound("CREATE_PRODUCT_PRIVILEGE");
        PrivilegeEntity hidePrivilege = createPrivilegeIfNotFound("HIDE_COMMENT_PRIVILEGE");
        PrivilegeEntity deleteProductPrivilege = createPrivilegeIfNotFound("DELETE_PRODUCT_PRIVILEGE");
        PrivilegeEntity deleteUserPrivilege = createPrivilegeIfNotFound("DELETE_USER_PRIVILEGE");

        List<PrivilegeEntity> adminPrivileges = Arrays.asList(viewPrivilege, createPrivilege, hidePrivilege, deleteProductPrivilege, deleteUserPrivilege);
        List<PrivilegeEntity> staffPrivileges = Arrays.asList(viewPrivilege, createPrivilege, hidePrivilege, deleteProductPrivilege);
        List<PrivilegeEntity> userPrivileges = Arrays.asList(viewPrivilege, createPrivilege);

        createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
        createRoleIfNotFound("ROLE_STAFF", staffPrivileges);
        createRoleIfNotFound("ROLE_USER", userPrivileges);

        RoleEntity adminRole = roleRepo.findByName("ROLE_ADMIN");
        UserEntity user = new UserEntity();
        user.setName("Pavel");
        user.setPassword(passwordEncoder.encode("12345678"));
        user.setBalance(0.0);
        user.setFrozen_balance(0.0);
        user.setEmail("baka@gmail.com");
        user.setWallet_id("abcdefu");
        user.setRoles(Arrays.asList(adminRole));
        userRepo.save(user);

        alreadySetup = true;
    }

    @Transactional
    PrivilegeEntity createPrivilegeIfNotFound(String name) {
        PrivilegeEntity privilege = privilegeRepo.findByName(name);
        if (privilege == null) {
            privilege = new PrivilegeEntity(name);
            privilegeRepo.save(privilege);
        }
        return privilege;
    }

    @Transactional
    RoleEntity createRoleIfNotFound(String name, Collection<PrivilegeEntity> privileges) {
        RoleEntity role = roleRepo.findByName(name);
        if (role == null) {
            role = new RoleEntity(name);
            role.setPrivileges(privileges);
            roleRepo.save(role);
        }
        return role;
    }
}
