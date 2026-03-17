package thaithienthuan.lab02.service;

import thaithienthuan.lab02.model.Account;
import thaithienthuan.lab02.model.Role;
import thaithienthuan.lab02.repository.AccountRepository;
import thaithienthuan.lab02.repository.RoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {

        OAuth2User oauthUser = super.loadUser(userRequest);

        String email = oauthUser.getAttribute("email");

        Account account = accountRepository.findByEmail(email).orElse(null);

        // Nếu user chưa tồn tại trong DB → tạo mới
        if (account == null) {

            Account newUser = new Account();
            newUser.setUsername(email);
            newUser.setEmail(email);
            newUser.setPassword("{noop}oauth2user");

            Role role = roleRepository.findByName("ROLE_STUDENT");
            if (role == null) {
                // Nếu role không tồn tại, tạo role ROLE_STUDENT mới
                Role newRole = new Role();
                newRole.setName("ROLE_STUDENT");
                role = roleRepository.save(newRole);
            }
            newUser.setRoles(Set.of(role));

            account = accountRepository.save(newUser);
        }

        return new DefaultOAuth2User(
                account.getRoles()
                        .stream()
                        .map(role -> new SimpleGrantedAuthority(role.getName()))
                        .collect(Collectors.toList()),
                oauthUser.getAttributes(),
                "email"
        );
    }
}
