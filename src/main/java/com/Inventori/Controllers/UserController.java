package com.Inventori.Controllers;

import com.Inventori.Repository.UserRepository;
import com.Inventori.Models.Users;
import com.Inventori.Serivces.UserService;
import com.Inventori.Utils.JwtUtil;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequestMapping(value = "/api")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserService userService;


    @RequestMapping(value = "/user/list", method = RequestMethod.GET)
    public ResponseEntity<?> getUserList() {
        Map<String, Object> userList = new HashMap<>();
        try {
            List<Users> data = userRepository.findAll();
            userList.put("data", data);
            return ResponseEntity.ok(userList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @RequestMapping(value = "/user/detail/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getUserDetail(@PathVariable Long id) {
        Map<String, Object> userDetail = new HashMap<>();
        Map<String, Object> parent = new HashMap<>();

        try {
            Optional<Users> data = userRepository.findById(id);
            userDetail.put("username", data.get().username);
            userDetail.put("email", data.get().email);

            parent.put("data", userDetail);
            return ResponseEntity.ok(parent);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody Users authRequest) throws Exception {

        Map<String, Object> userDetail = new HashMap<>();

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
            final UserDetails userDetails = userService.loadUserByUsername(authRequest.getUsername());
            String token = jwtUtil.generateToken(userDetails);
            userDetail.put("username", authRequest.getUsername());
            userDetail.put("token", token);
            userDetail.put("messages", "Login Success");
            return ResponseEntity.ok(userDetail);
        } catch (JwtException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public ResponseEntity<?> getUserProfile() {
        Map<String, Object> userDetail = new HashMap<>();
        Authentication securityContextHolder = SecurityContextHolder.getContext().getAuthentication();
        userDetail.put("data", securityContextHolder.getName());
        return ResponseEntity.ok(userDetail);
    }
}
