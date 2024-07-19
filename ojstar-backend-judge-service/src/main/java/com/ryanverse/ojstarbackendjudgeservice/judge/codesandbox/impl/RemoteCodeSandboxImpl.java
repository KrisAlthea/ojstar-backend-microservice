package com.ryanverse.ojstarbackendjudgeservice.judge.codesandbox.impl;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.ryanverse.ojstarbackendcommon.common.ErrorCode;
import com.ryanverse.ojstarbackendcommon.exception.BusinessException;
import com.ryanverse.ojstarbackendjudgeservice.judge.codesandbox.CodeSandbox;
import com.ryanverse.ojstarbackendmodel.codesandbox.ExecuteCodeRequest;
import com.ryanverse.ojstarbackendmodel.codesandbox.ExecuteCodeResponse;
import org.apache.commons.lang3.StringUtils;

/**
 * ClassName: RemoteCodeSandboxImpl
 * Description: 远程代码沙箱(实际调用)
 *
 * @Author Haoran
 * @Create 2024/7/11 19:59
 * @Version 1.0
 */
public class RemoteCodeSandboxImpl implements CodeSandbox {

	// 定义鉴权请求头和密钥
	private static final String AUTH_REQUEST_HEADER = "auth";

	private static final String AUTH_REQUEST_SECRET = "secretKey";

	@Override
	public ExecuteCodeResponse executeCode (ExecuteCodeRequest executeCodeRequest) {
		System.out.println("远程代码沙箱");
		String url = "http://localhost:8090/executeCode";
		String json = JSONUtil.toJsonStr(executeCodeRequest);
		String response = HttpUtil.createPost(url)
				.header(AUTH_REQUEST_HEADER, AUTH_REQUEST_SECRET)
				.body(json)
				.execute()
				.body();
		if (StringUtils.isBlank(response)) {
			throw new BusinessException(ErrorCode.API_REQUEST_ERROR, "远程代码沙箱调用失败, " + response);
		}
		return JSONUtil.toBean(response, ExecuteCodeResponse.class);
	}
}
