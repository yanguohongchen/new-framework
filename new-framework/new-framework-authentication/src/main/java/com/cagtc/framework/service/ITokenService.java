package com.cagtc.framework.service;

import com.cagtc.framework.auth.SessionData;


public interface ITokenService
{
	SessionData createSessionData(String username);

	SessionData getSessionData(String username);
}
