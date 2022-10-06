package com.odev.myestateagencyapi.sec;

public class JWTUtils {
    public static final String SECRET = "mysecret";
    public static final String ROLES = "roles";
    public static final String ERROR_MESSAGE = "error-message";
    public static final String AUTHORIZATION = "Authorization";
    public static final String TOKEN_LABEL = "accessToken";
    public static final String BEARER_ = "Bearer ";
    public static final String AUTH_HEADER = "Authorization";
    public static final long EXPIRE_ACCESS_TOKEN= 360L *24*60*60*1000;
    public static final long EXPIRE_REFRESH_TOKEN= 15*60*1000;

}
