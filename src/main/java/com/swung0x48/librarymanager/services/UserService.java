package com.swung0x48.librarymanager.services;

import com.swung0x48.librarymanager.RuntimeConstants;
import com.swung0x48.librarymanager.domain.LibUser;
import com.swung0x48.librarymanager.exception.AuthException;
import com.swung0x48.librarymanager.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
@Transactional
public class UserService {
    @Autowired
    UserRepository userRepository;

    public List<LibUser> getAllUsers() {
        return userRepository.selectAllUser();
    }

    public LibUser validateUser(String username, String password) throws AuthException {
        LibUser user = userRepository.selectUserByName(username);
        if (user == null || !user.getPassword().equals(password)) return null;
        if (user.getRole().equals("banned")) throw new AuthException("User has been banned");

        return user;
    }

    public LibUser registerUser(String username, String password, String role) throws AuthException {
        boolean userExists = userRepository.selectUserByName(username) != null;
        if (userExists)
            throw new AuthException("Username already in use.");

        LibUser user = new LibUser(username, password, role);
        userRepository.addUser(user);
        return userRepository.selectUserByName(username);
    }

    public String GenerateJwtToken(LibUser user) {
        long now = System.currentTimeMillis();

        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, RuntimeConstants.API_SECRET_KEY)
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + RuntimeConstants.TOKEN_VALIDITY))
                .claim("userId", user.getUserId())
                .claim("username", user.getUserName())
                .claim("role", user.getRole())
                .compact();
    }

    public LibUser getLoginUser(HttpServletRequest httpRequest) {
        Integer userId=(Integer) httpRequest.getAttribute("userId");
        return userRepository.selectUserByID(userId);
    }

    public Integer deleteUser(Integer id) {
        userRepository.deleteUser(id);
        return id;
    }

    public LibUser addUser(LibUser libUser) {
        userRepository.addUser(libUser);
        return libUser;
    }

    public LibUser banUser(Integer userId) {
        userRepository.banUser(userId);
        return userRepository.selectUserByID(userId);
    }

    public LibUser unbanUser(Integer userID) {
        userRepository.unbanUser(userID);
        return userRepository.selectUserByID(userID);
    }
}
