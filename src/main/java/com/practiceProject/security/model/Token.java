package com.practiceProject.security.model;

import lombok.Builder;
import lombok.Data;


@Data
public class Token {
    
	private String id;
	private String access;
	private String refresh;
	
	@Data
    public static final class Request {
        private String id;
        private String password;
    }

    @Data
    @Builder
    public static final class Response {
        private String accToken;
        private String refToken;
    }
    
    @Data
    public static final class RefreshRequest{
    	private String refToken;
    }
    
}