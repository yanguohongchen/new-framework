package com.sea.framework;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.AbstractJsonpResponseBodyAdvice;

/**
 * 添加jsonp支持
 * 
 * @author sea
 *
 */
@ControllerAdvice
public class JsonpAdvice extends AbstractJsonpResponseBodyAdvice
{
	public JsonpAdvice()
	{
		super("callback");
	}
}
