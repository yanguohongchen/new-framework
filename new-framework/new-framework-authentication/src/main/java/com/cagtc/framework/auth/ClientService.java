package com.cagtc.framework.auth;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ClientService implements IClientService
{

	private ConcurrentHashMap<String, String> clients = new ConcurrentHashMap<String, String>();

	// 直接预定义
	ClientService()
	{
		clients.put("PCCLIENT", "!sdfioaflksdxcijvn");
		clients.put("PHONECLIENT", "!sdfioaflksdxcijvn");
		clients.put("OTHERCLIENT", "!sdfioaflksdxcijvn");
	}

	@Autowired
	private RedisClusterUtil redisClusterUtil;

	@Override
	public Client getClient(String clientId)
	{
		String clientSecret = clients.get(clientId);
		Client client = new Client();
		client.setClientId(clientId);
		client.setClientSecret(clientSecret);
		return client;
	}
}
