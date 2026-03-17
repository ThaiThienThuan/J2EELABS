package thaithienthuan.lab02.service;

import thaithienthuan.lab02.model.Account;
import thaithienthuan.lab02.model.Role;
import thaithienthuan.lab02.repository.AccountRepository;
import thaithienthuan.lab02.repository.RoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AccountService implements UserDetailsService {

@Autowired
private AccountRepository accountRepository;

@Autowired
private RoleRepository roleRepository;

@Autowired
private PasswordEncoder passwordEncoder;

@Override
public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    Account account = accountRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));

    Set<SimpleGrantedAuthority> authorities = account.getRoles()
            .stream()
            .map(role -> new SimpleGrantedAuthority(role.getName()))
            .collect(Collectors.toSet());

    return new org.springframework.security.core.userdetails.User(
            account.getUsername(),
            account.getPassword(),
            authorities
    );
}

public void registerStudent(Account account) {

    account.setPassword(passwordEncoder.encode(account.getPassword()));

    Role studentRole = roleRepository.findByName("ROLE_STUDENT");

    if (studentRole == null) {
        // Create ROLE_STUDENT role if it doesn't exist
        studentRole = new Role();
        studentRole.setName("ROLE_STUDENT");
        studentRole = roleRepository.save(studentRole);
    }

    Set<Role> roles = new java.util.HashSet<>();
    roles.add(studentRole);
    account.setRoles(roles);

    accountRepository.save(account);
}

}
