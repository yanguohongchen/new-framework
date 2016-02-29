package com.sea.framework;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sea.exception.DeniedException;

@RestController
@RequestMapping(value = "/auth")
public class AuthController
{
	@RequestMapping("/asscessToken")
	@ResponseBody
	public MsgResult asscessToken(HttpServletRequest request, User user)
	{
		TokenUtil.clientValidator(request);
		userValidator(user);
		String token = TokenUtil.createToken(user);
		return new MsgResult(token);
	}

	private void userValidator(User user)
	{
		if (!(user.getUsername().equals("username") && user.getPassword().equals("password")))
		{
			throw new DeniedException("用户名或者密码错误！");
		}
	}

}
