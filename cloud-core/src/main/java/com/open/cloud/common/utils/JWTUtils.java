package com.open.cloud.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
/**
 * @author Leijian
 * @date   2020/2/2
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public abstract class JWTUtils {

	public static final String UID = "uid";
	private static final String SECRET = "WgtqaT1HNTZPZNMDJu3k";
	private static final long EXPIRE = 604800 * 1000;

	/**
	 * 生成token
	 *
	 * @param uid
	 * @return
	 */
	public static String generate(String uid) {
		Date nowDate = new Date();
		// 过期时间
		Date expireDate = new Date(nowDate.getTime() + EXPIRE);
		Map<String, Object> claims = new HashMap<>(1);
		claims.put(UID, uid);
		return Jwts.builder().setClaims(claims).setIssuedAt(nowDate).setExpiration(expireDate)
				.signWith(SignatureAlgorithm.HS512, SECRET).compact();
	}

	/**
	 * 解析Claims
	 *
	 * @param token
	 * @return
	 */
	public static Claims getClaim(String token) {
		Claims claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
		return claims;
	}



	/**
	 * 获取UID
	 */
	public static String getUid(String token) {
		return String.valueOf(getClaim(token).get(UID));
	}

	/**
	 * 获取jwt失效时间
	 */
	public static Date getExpiration(String token) {
		return getClaim(token).getExpiration();
	}

	/**
	 * 验证token是否失效
	 *
	 * @param token
	 * @return true:过期 false:没过期
	 */
	public static boolean isExpired(String token) {
		try {
			final Date expiration = getExpiration(token);
			return expiration.before(new Date());
		} catch (Exception e) {
			return true;
		}
	}

}
