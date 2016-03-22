package com.cagtc.framework.service;

public interface ITokenService
{
	String createToken(String username);

	String getToken(String username);
}
