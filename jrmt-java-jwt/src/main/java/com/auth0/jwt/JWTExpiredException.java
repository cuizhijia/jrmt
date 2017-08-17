package com.auth0.jwt;

public class JWTExpiredException extends JWTVerifyException {
    /**
	 * 
	 */
	private static final long serialVersionUID = -5394447784968825169L;
	private long expiration;

    public JWTExpiredException(long expiration) {
        this.expiration = expiration;
    }

    public JWTExpiredException(String message, long expiration) {
        super(message);
        this.expiration = expiration;
    }

    public long getExpiration() {
        return expiration;
    };
}
