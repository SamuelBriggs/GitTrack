package com.jojo.GitTrack;

import com.nimbusds.jwt.*;

import java.text.ParseException;

public class JWTDecoder {
    public static void main(String[] args) {
        // Replace with your Google OAuth2 access token
        String accessToken = "ya29.a0AfB_byBTi4q4LvQe2sWLrJdTNAZQlfQxLFGjzCHTAjdxjgLsCb-Ol8YBPU_qcI5VJGu6h1ORn31mrJwOqYg2C786WR6ORuOjC2GyH2lmEIFYKhrA7YJi6w8tsSyTs5PR8nMlADJt_I6UGLs53N7GCU2lBwfxTKHibQfWaCgYKARISARASFQGOcNnCCbgqtmZA_Ix0zHrJhmv0vQ0171";

        try {
            // Parse the JWT token
            JWTClaimsSet claimsSet = JWTParser.parse(accessToken).getJWTClaimsSet();

            // Print the decoded token
            System.out.println("Decoded Access Token:");
            System.out.println(claimsSet.toJSONObject());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
