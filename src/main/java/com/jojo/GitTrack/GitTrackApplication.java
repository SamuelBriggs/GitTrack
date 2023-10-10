package com.jojo.GitTrack;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.IOException;

@SpringBootApplication
public class GitTrackApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(GitTrackApplication.class, args);
	//	System.out.println(response.getOutputStream().toString());

		//System.out.println(SecurityContextHolder.getContextHolderStrategy().getContext().getAuthentication().getDetails());

	}

}
