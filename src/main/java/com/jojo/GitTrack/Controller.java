package com.jojo.GitTrack;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ResolvableType;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RestController
public class Controller {

    @Autowired

    private OAuth2AuthorizedClientService auth2AuthorizedClientService;

    private static String authorizationBaseUri = "oauth2/authorization";

    @Autowired
    private ClientRegistrationRepository clientRegistrationRepository;

    Map<String, String> oauth2AuthenticationUrls = new HashMap<>();
    @GetMapping("/help")
    public String help(){
        return "Help";
    }

    @GetMapping("/loginSuccess")
    public byte[] getLoginInfo(OAuth2AuthenticationToken auth2AuthenticationToken){
        OAuth2AuthorizedClient client = auth2AuthorizedClientService.
                loadAuthorizedClient(auth2AuthenticationToken.getAuthorizedClientRegistrationId(),
                        auth2AuthenticationToken.getName());
     //   System.out.println(client.getClientRegistration().getRedirectUri());
        String userInfo = client.getClientRegistration().getProviderDetails()
                .getUserInfoEndpoint()
                .getUri();
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

      //  System.out.println(client.getAccessToken().getTokenValue());
       // System.out.println(Arrays.toString(client.getAccessToken().getScopes().toArray()));
        //System.out.println(client.getAccessToken().getTokenType());
        headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + client.getAccessToken().getTokenValue());
        HttpEntity entity = new HttpEntity<>("", headers);
        ResponseEntity<?> response = restTemplate.exchange(userInfo, HttpMethod.GET, entity, Map.class);
    //    System.out.println(response);
        Map userAttributes = (Map) response.getBody();
     //   System.out.println(userAttributes);
        return client.getAccessToken().getTokenValue().getBytes(StandardCharsets.UTF_8);


    }
    @GetMapping("/oauth")
    public void getLoginPage(){
        Iterable<ClientRegistration> clientRegistrations = null;
        ResolvableType type = ResolvableType.forInstance(clientRegistrationRepository).as(Iterable.class);
        if(type != ResolvableType.NONE && ClientRegistration.class.isAssignableFrom(type.resolveGenerics()[0])){
            clientRegistrations = (Iterable<ClientRegistration>) clientRegistrationRepository;
        }
        assert clientRegistrations != null;
        clientRegistrations.forEach(registration -> oauth2AuthenticationUrls.put(registration.getClientName(),
                authorizationBaseUri + "/" + registration.getRegistrationId()));
        System.out.println(oauth2AuthenticationUrls);

    }
        @GetMapping("/oauth2/authorization/google")
        public void oauthCallback(HttpServletResponse response) throws IOException {
            // Assume you obtained the access token
            String accessToken = "YOUR_ACCESS_TOKEN";

            // Encode the access token for HTML
           String encodedAccessToken = URLEncoder.encode(accessToken, StandardCharsets.UTF_8);

            // Construct the HTML form with the access token
           String htmlForm = "<html><body><form id='tokenForm' method='post' action='http://frontend.com/receive-token'>" +
                   "<input type='hidden' name='access_token' value='" + encodedAccessToken + "'>" +
                    "</form><script>document.getElementById('tokenForm').submit();</script></body></html>";

            // Set the response content type and write the HTML form to the response
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.print(htmlForm);
            out.flush();
        }
    }












