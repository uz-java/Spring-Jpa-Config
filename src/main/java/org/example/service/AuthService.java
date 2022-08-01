package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.domains.AuthUserEntity;
import org.example.dto.auth.UserCreateDto;
import org.example.dto.auth.UserLoginDTO;
import org.example.exceptions.MethodNotAllowedException;
import org.example.exceptions.NotFoundException;
import org.example.exceptions.UnAuthorizedException;
import org.example.repository.AuthRepository;
import org.example.util.Utils;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author "Tojaliyev Asliddin"
 * @since 01/08/22 20:14 (Monday)
 * SpringMvcJpa/IntelliJ IDEA
 */
@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthRepository repository;

    public Long login(UserLoginDTO userLoginDTO) {
        Optional<AuthUserEntity> byEmail = repository.findByEmail(userLoginDTO.getEmail());
        AuthUserEntity user = byEmail.orElseThrow(() -> new NotFoundException("User not found by email %s".formatted(userLoginDTO.getEmail())));
        if (!Utils.matchPassword(userLoginDTO.getPassword(), user.getPassword())) {
            throw new UnAuthorizedException("Password do not match");
        }
        return user.getId();
    }

    public Long create(UserCreateDto userCreateDto) {
        Optional<AuthUserEntity> byEmail = repository.findByEmail(userCreateDto.getEmail());
        if (byEmail.isPresent()) {
            throw new MethodNotAllowedException("Email already taken %s".formatted(userCreateDto.getEmail()));
        }
        if (!userCreateDto.getPassword().equals(userCreateDto.getConfirmPassword())) {
            throw new MethodNotAllowedException("Password do not mach");
        }
        AuthUserEntity user = AuthUserEntity.builder()
                .email(userCreateDto.getEmail())
                .password(Utils.encode(userCreateDto.getPassword()))
                .build();

        AuthUserEntity userEntity = repository.save(user);
        return userEntity.getId();
    }
}
