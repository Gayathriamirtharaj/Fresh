package com.example.service;
import com.example.exception.ServiceException;;
import com.example.model.UserEntity;
import com.example.repository.UserRepository;
import com.example.security.JWTProvider;
import com.example.service.UserService;
import com.example.vo.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    protected UserRepository userRepository;

    @Autowired
    protected PasswordEncoder passwordEncoder;

    @Autowired
    protected JWTProvider jwtProvider;

    @Autowired
    protected AuthenticationManager authenticationManager;

    @Autowired
    protected ModelMapper modelMapper;

    @Autowired
    protected RestTemplate restTemplate;


    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        try {
            String username = loginRequest.getUsername();
            String password = loginRequest.getPassword();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            String token = jwtProvider.createToken(username, userRepository.findByUsername(username).getRoles());
            return new LoginResponse(token);
        } catch (AuthenticationException e) {
            throw new ServiceException("Invalid username/password", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override
    public RegistrationResponse register(User user) {

        if (!userRepository.existsByUsername(user.getUsername())) {

            // Save in Repository
            UserEntity userEntity = modelMapper.map(user, UserEntity.class);
            userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
            UserEntity savedUserEntity = userRepository.save(userEntity);
            System.out.println("Saved User: " + savedUserEntity);



            // Create Token
            String token = jwtProvider.createToken(userEntity.getUsername(), userEntity.getRoles());

            return new RegistrationResponse(token);

        } else {
            throw new ServiceException("Username already exists", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override
    public User search(String username) {
        UserEntity userEntity = userRepository.findByUsername(username);
        if (userEntity == null) {
            throw new ServiceException("User Not Found", HttpStatus.NOT_FOUND);
        }
        return modelMapper.map(userEntity, User.class);
    }

    @Override
    public String refresh(String username) {
        return jwtProvider.createToken(username, userRepository.findByUsername(username).getRoles());
    }

}