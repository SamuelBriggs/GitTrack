package com.jojo.GitTrack;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import java.io.IOException;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public class FilterGit extends AbstractAuthenticationProcessingFilter {


    protected FilterGit(String defaultFilterProcessesUrl) {
        super(defaultFilterProcessesUrl);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        return null;
    }


    @Override
    public void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult
    ) throws IOException {

      //  User user = userRepository.findUserByPhoneNumber(phoneNumber).get();
      //  String accessToken = jwtUtil.generateAccessToken(user,Role.SENDER);
        //String accessToken = generateAccessToken(authResult.getAuthorities(), request);
        response.setContentType(APPLICATION_JSON_VALUE);
        response.getOutputStream().write("samuel".getBytes());
    }

}
