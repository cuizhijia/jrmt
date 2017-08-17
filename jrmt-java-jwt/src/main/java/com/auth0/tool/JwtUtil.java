package com.auth0.tool;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.auth0.jwt.JWTAudienceException;
import com.auth0.jwt.JWTExpiredException;
import com.auth0.jwt.JWTIssuerException;
import com.auth0.jwt.JWTSigner;
import com.auth0.jwt.JWTVerifier;
import com.swift.jrmt.common.constants.StatusCode;
import com.swift.jrmt.common.exception.JrmtRuntimeException;

/**
 * @author  phil E-mail: s@m1c.cn
 * @date 2016年5月20日
 * @version 
 * @since y:2016
 * @description 使用jwt方式来替代传统的token
 */
public class JwtUtil {
	private static Logger logger = LoggerFactory.getLogger(JwtUtil.class);
	private static final byte[] BSECRET = new byte[]{73, 83, 121, 104, 81, 85, 65, 70, 94, 99, 101, 82, 105, 118, 100, 76, 99, 69, 120, 36, 72, 99, 35, 67, 64, 73, 42, 82, 69, 72, 38, 118, 105, 67, 101, 82, 72, 111, 71, 112, 98, 120, 89, 86, 90, 69, 111, 120, 64, 112, 122, 81, 66, 89, 110, 97, 122, 103, 33, 105, 75, 114, 33, 71};
    
	 private static JWTSigner signer = new JWTSigner(BSECRET);
	 private static JWTVerifier verifier = new JWTVerifier(BSECRET);
	 /**
	  * @param object  入参为user对象就可以
	  * @return 签名之后的数据
	  */
	 public static String sign(Object object){
		 HashMap<String, Object> claims = new HashMap<String, Object>();
	        claims.put("user", object);
	        String token = signer.sign(claims,new JWTSigner.Options().setJwtId(true).setExpirySeconds(12*60*60).setNotValidBeforeLeeway(2).setIssuedAt(true));
	       return token;
	 }
	 
	public static Object unSign(String token) {
		Map<String, Object> decoded = new HashMap<String, Object>();
		try {
			decoded = verifier.verify(token);
		} catch (InvalidKeyException e) {
			logger.error("无效key",e);
			throw new JrmtRuntimeException(StatusCode.common_error_illegal_error,"");
		} catch (NoSuchAlgorithmException e) {
			logger.error("参数错误",e);
			throw new JrmtRuntimeException(StatusCode.common_error_illegal_error,"");
		} catch (IllegalStateException e) {
			logger.error("不合适的状态",e);
			throw new JrmtRuntimeException(StatusCode.common_error_illegal_error,"");
		} catch (SignatureException e) {
			logger.error("签名错误",e);
			throw new JrmtRuntimeException(StatusCode.common_error_illegal_error,"");
		} catch (IOException e) {
			logger.error("io异常",e);
			throw new JrmtRuntimeException(StatusCode.common_error_illegal_error,"");
		} catch (JWTExpiredException e) {
			logger.error("token过期",e);
			throw new JrmtRuntimeException(StatusCode.person_login_overtime_error,"");
		} catch (JWTIssuerException e) {
			logger.error("issuser无效",e);
			throw new JrmtRuntimeException(StatusCode.common_error_illegal_error,"");
		} catch (JWTAudienceException e) {
			logger.error("用户无效",e);
			throw new JrmtRuntimeException(StatusCode.common_error_illegal_error,"");
		}  
		return decoded.get("user");
	}
	
}
