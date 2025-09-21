package com.gregoryleblond.giftcardrestapi.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.gregoryleblond.giftcardrestapi.dto.UserLoginDto;
import com.gregoryleblond.giftcardrestapi.models.User;
import com.gregoryleblond.giftcardrestapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    @Value("${jwt.secret}")
    private String JWT_SECRET;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.repository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repository.save(user);
    }

    public List<User> getAllUsers() {
        return repository.findAll();
    }

    public UserLoginDto loginUser(User user) {
        User userFind = repository.findByEmail(user.getEmail());
        if (userFind != null) {
            if (passwordEncoder.matches(user.getPassword(), userFind.getPassword())) {
                return new UserLoginDto("Connection réussie", generateJwtToken(userFind.getId().toString()));
            }
            else {
                return null;
            }
        }
        return null;
    };

    public String generateJwtToken(String userId) {
        Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET);
        long EXPIRATION_TIME = 60 * 60 * 1000;

        return JWT.create()
                .withSubject(userId) // identifiant principal
                .withIssuer("giftcard-api") // identifie ton application
                .withClaim("role", "USER") // tu peux ajouter des claims
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(algorithm);
    }
}
