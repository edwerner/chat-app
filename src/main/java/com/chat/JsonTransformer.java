package com.chat;

import spark.ResponseTransformer;

public class JsonTransformer implements ResponseTransformer {
	
	/**
	 * Renders json objects for
	 * get/post requests
	 *
	 * @return json object
	 */
	@Override
	public String render(Object obj) throws Exception {
		return JsonUtils.toJson(obj);
	}
}
