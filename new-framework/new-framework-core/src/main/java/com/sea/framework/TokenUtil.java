package com.sea.framework;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;

import com.sea.exception.DeniedException;

public class TokenUtil
{

	private static String MAGIC_KEY = "sdf8423@#$@^fsdf";

	public static String createToken(User user)
	{
		/* Expires in one hour */
		long expires = System.currentTimeMillis() + 1000L * 60 * 60;

		StringBuilder tokenBuilder = new StringBuilder();
		tokenBuilder.append(user.getUsername());
		tokenBuilder.append(":");
		tokenBuilder.append(expires);
		tokenBuilder.append(":");
		tokenBuilder.append(computeSignature(user, expires));
		return tokenBuilder.toString();
	}

	public static String computeSignature(User user, long expires)
	{
		StringBuilder signatureBuilder = new StringBuilder();
		signatureBuilder.append(user.getUsername());
		signatureBuilder.append(":");
		signatureBuilder.append(expires);
		signatureBuilder.append(":");
		signatureBuilder.append(MAGIC_KEY);
		return DigestUtils.md5Hex(signatureBuilder.toString());
	}

	public static void validateToken(String authToken, User userDetails)
	{
		String[] parts = authToken.split(":");
		long expires = Long.parseLong(parts[1]);
		String signature = parts[2];
		if (expires < System.currentTimeMillis())
		{
			throw new DeniedException("token 已过期！");
		}

		if (!signature.equals(computeSignature(userDetails, expires)))
		{
			throw new DeniedException("token 不匹配！");
		}
	}

	public static void clientValidator(HttpServletRequest request)
	{
		String clientId = request.getHeader("client_id");
		String clientSecret = request.getHeader("client_secret");
		if (!(clientId!=null&&clientId.equals("1")))
		{
			throw new DeniedException("客户端不存在！");
		}

		if (!(clientSecret!=null&&clientSecret.equals("2")))
		{
			// 私钥解密
			throw new DeniedException("密钥不正确");
		}
	}

}
