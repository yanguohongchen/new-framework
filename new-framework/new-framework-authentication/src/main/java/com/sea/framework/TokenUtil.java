package com.sea.framework;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;

import com.sea.exception.DeniedException;
import com.sea.user.entity.Client;
import com.sea.user.service.IClientService;
import com.sea.user.service.ITokenService;

public class TokenUtil
{

	protected static String MAGIC_KEY = "sdf8423@#$@^fsdf";

	public static String computeSignature(String username, long expires)
	{
		StringBuilder signatureBuilder = new StringBuilder();
		signatureBuilder.append(username);
		signatureBuilder.append(":");
		signatureBuilder.append(expires);
		signatureBuilder.append(":");
		signatureBuilder.append(Math.random());
		signatureBuilder.append(":");
		signatureBuilder.append(TokenUtil.MAGIC_KEY);
		return DigestUtils.md5Hex(signatureBuilder.toString());
	}

	protected static void validateToken(String authToken)
	{
		String[] parts = authToken.split(":");
		String username = parts[0];
		long expires = Long.parseLong(parts[1]);
		if (expires < System.currentTimeMillis())
		{
			throw new DeniedException("token 已过期！");
		}
		ITokenService tokenService = SpringUtils.getBean("tokenService");
		String tokenRedis = tokenService.getToken(username);
		if (!authToken.equals(tokenRedis))
		{
			throw new DeniedException("token 不匹配！");
		}
	}

	public static void clientValidator(HttpServletRequest request)
	{
		String clientId = request.getHeader("client_id");
		String clientSecret = request.getHeader("client_secret");
		if (clientId == null)
		{
			throw new DeniedException("客户端不存在！");
		}
		IClientService clientService = SpringUtils.getBean("clientService");
		Client clientCertificate = clientService.getClient(clientId);
		if (!clientCertificate.getClientSecret().equals(clientSecret))
		{
			throw new DeniedException("密钥不正确");
		}
	}

}