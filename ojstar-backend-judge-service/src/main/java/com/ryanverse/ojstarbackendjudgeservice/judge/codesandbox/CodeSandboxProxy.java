package com.ryanverse.ojstarbackendjudgeservice.judge.codesandbox;

import com.ryanverse.ojstarbackendmodel.codesandbox.ExecuteCodeRequest;
import com.ryanverse.ojstarbackendmodel.codesandbox.ExecuteCodeResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * ClassName: CodeSandboxProxy
 * Description:
 *
 * @Author Haoran
 * @Create 2024/7/11 20:47
 * @Version 1.0
 */
@Slf4j
public class CodeSandboxProxy implements CodeSandbox {

	private final CodeSandbox codeSandbox;

	public CodeSandboxProxy (CodeSandbox codeSandbox) {
		this.codeSandbox = codeSandbox;
	}

	@Override
	public ExecuteCodeResponse executeCode (ExecuteCodeRequest executeCodeRequest) {
		log.info("代码沙箱请求信息{}", executeCodeRequest.toString());
		ExecuteCodeResponse executeCodeResponse = codeSandbox.executeCode(executeCodeRequest);
		log.info("代码沙箱响应信息{}", executeCodeResponse.toString());
		return executeCodeResponse;
	}
}
