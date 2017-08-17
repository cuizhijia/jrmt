package com.auth0.jwt;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

/**
 * Test things that are difficult using signer or verifier alone. In particular, setting
 * claims via Options produces output dependent on current time.
 *
 */
public class RoundtripTest {
    private static final String SECRET;
    private static final byte[] BSECRET = new byte[]{73, 83, 121, 104, 81, 85, 65, 70, 94, 99, 101, 82, 105, 118, 100, 76, 99, 69, 120, 36, 72, 99, 35, 67, 64, 73, 42, 82, 69, 72, 38, 118, 105, 67, 101, 82, 72, 111, 71, 112, 98, 120, 89, 86, 90, 69, 111, 120, 64, 112, 122, 81, 66, 89, 110, 97, 122, 103, 33, 105, 75, 114, 33, 71};
    static {
        SECRET = "ISyhQUAF^ceRivdLcEx$Hc#C@I*REH&viCeRHoGpbxYVZEox@pzQBYnazg!iKr!G";
    }
    private static JWTSigner signer = new JWTSigner(SECRET);
    private static JWTVerifier verifier = new JWTVerifier(SECRET);
    /*
     * Roundtrip of different datatypes.
     */
    @Test
    public void shouldEmpty() throws Exception {
        HashMap<String, Object> claims = new HashMap<String, Object>();
        String token = signer.sign(claims);
        System.out.println(token);
        Map<String, Object> decoded = verifier.verify(token);
        System.out.println(decoded.toString());
        assertEquals(claims, decoded);
    }
    
    @Test
    public void shouldString() throws Exception {
        HashMap<String, Object> claims = new HashMap<String, Object>();
        claims.put("foo", "bar");
        String token = signer.sign(claims);
        System.out.println(token);
        Map<String, Object> decoded = verifier.verify(token);
        System.out.println(decoded);
        assertEquals(claims, decoded);
    }

    @Test
    public void shouldShort() throws Exception {
        HashMap<String, Object> claims = new HashMap<String, Object>();
        claims.put("foo", (short) -10);
        String token = signer.sign(claims);
        System.out.println(token);
        Map<String, Object> decoded = verifier.verify(token);
        Number fooValue = (Number) decoded.get("foo");
        decoded.put("foo", fooValue.shortValue());
        System.out.println(decoded.toString());
        assertEquals(claims, decoded);
    }
    
    @Test
    public void shouldLong() throws Exception {
        HashMap<String, Object> claims = new HashMap<String, Object>();
        claims.put("foo", Long.MAX_VALUE);
        String token = signer.sign(claims);
        System.out.println(token);
        Map<String, Object> decoded = verifier.verify(token);
        System.out.println(decoded);
        assertEquals(claims, decoded);
    }
    
    @Test
    public void shouldObject() throws Exception {
        HashMap<String, Object> claims = new HashMap<String, Object>();
        Long delay = new Date().getTime();
        User user = new User();
        user.setUsername("foo");
        user.setPassword("bar");
        user.setDelay(delay);
        claims.put("user", user);
        String token = signer.sign(claims,new JWTSigner.Options().setJwtId(true).setExpirySeconds(10).setNotValidBeforeLeeway(2).setIssuedAt(true));
        TimeUnit.SECONDS.sleep(15);
        String token2 = signer.sign(claims,new JWTSigner.Options().setJwtId(true));
        System.out.println(token);
        System.out.println(token2);
        Map<String, Object> decoded = new HashMap<String, Object>();
        try {
        	decoded = verifier.verify(token);
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        HashMap<String, Object> expectedUser = new HashMap<String, Object>();
        expectedUser.put("username", "foo");
        expectedUser.put("password", "bar");
        expectedUser.put("delay", delay);
        HashMap<String, Object> expected = new HashMap<String, Object>();
        expected.put("user", expectedUser);
        assertEquals(expected, decoded);
    }

    @Test
    public void shouldBoolean() throws Exception {
        HashMap<String, Object> claims = new HashMap<String, Object>();
        claims.put("foo", true);
        claims.put("bar", false);
        String token = signer.sign(claims);
        System.out.println(token);
        Map<String, Object> decoded = verifier.verify(token);
        System.out.println(decoded);
        assertEquals(claims, decoded);
    }

    /*
     * Setting claims via Options
     */
    
    @Test
    public void shouldOptionsIat() throws Exception {
        HashMap<String, Object> claims = new HashMap<String, Object>();
        long before = System.currentTimeMillis();
        String token = signer.sign(claims, new JWTSigner.Options().setIssuedAt(true));
        long after = System.currentTimeMillis();
        Map<String, Object> decoded = verifier.verify(token);

        assertEquals(decoded.size(), 1);
        long iat = ((Number) decoded.get("iat")).longValue();
        assertTrue(iat >= before / 1000l);
        assertTrue(iat <= after / 1000l);
    }

    @Test
    public void shouldOptionsTimestamps() throws Exception {
        HashMap<String, Object> claims = new HashMap<String, Object>();
        String token = signer.sign(claims,
                new JWTSigner.Options()
        .setExpirySeconds(50).setNotValidBeforeLeeway(10).setIssuedAt(true));
        Map<String, Object> decoded = verifier.verify(token);
        assertEquals(decoded.size(), 3);
        long iat = ((Number) decoded.get("iat")).longValue();
        long exp = ((Number) decoded.get("exp")).longValue();
        long nbf = ((Number) decoded.get("nbf")).longValue();
        assertEquals(exp, iat + 50);
        assertEquals(nbf, iat - 10);
    }
    
    @Test
    public void shouldOptionsJti() throws Exception {
        HashMap<String, Object> claims = new HashMap<String, Object>();
        String token = signer.sign(claims,
                new JWTSigner.Options().setJwtId(true));
        Map<String, Object> decoded = verifier.verify(token);
        assertEquals(decoded.size(), 1);
        assertEquals(((String) decoded.get("jti")).length(), 36);
    }
}



