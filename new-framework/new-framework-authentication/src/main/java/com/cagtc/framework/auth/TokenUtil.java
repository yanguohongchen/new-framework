package com.cagtc.framework.auth;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;

import com.cagtc.framework.exception.DeniedException;
import com.cagtc.framework.service.ITokenService;

public class TokenUtil
{

	//将token当做key，数据内容当做value
	
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

	protected static SessionData validateToken(String authToken) 
	{
		if("".equals(authToken)||"null".equals(authToken)){
			throw new DeniedException("请先登录！");
		}
		String[] parts = authToken.split(":");
		String username = parts[0];
		long expires = Long.parseLong(parts[1]);
		if (expires < System.currentTimeMillis())
		{
			throw new DeniedException("token 已过期！");
		}
		ITokenService tokenService = SpringUtils.getBean("tokenService");
		SessionData tokenRedis = tokenService.getSessionData(username);
		if (!authToken.equals(tokenRedis.getAccessToken()))
		{
			throw new DeniedException("token 不匹配！");
		}
		return tokenRedis;
	}

	public static void clientValidator(HttpServletRequest request)
	{
		String clientId = request.getParameter("client_id");
		String clientSecret = request.getParameter("client_secret");
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
