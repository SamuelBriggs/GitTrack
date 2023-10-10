package com.jojo.GitTrack;

import com.nimbusds.oauth2.sdk.AuthorizationRequest;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
@Component
public class SuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
   //     System.out.println(request.getAuthType());

        response.setContentType(APPLICATION_JSON_VALUE);
     //  response.getOutputStream().write("samuelIresi".getBytes());
        response.sendRedirect("http://localhost:3000/loginRedirect?access_token=samuel");
     //   System.out.println(authentication.getPrincipal().toString());

    }
}
