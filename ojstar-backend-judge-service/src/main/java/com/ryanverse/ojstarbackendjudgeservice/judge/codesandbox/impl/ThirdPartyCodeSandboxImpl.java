package com.ryanverse.ojstarbackendjudgeservice.judge.codesandbox.impl;

import com.ryanverse.ojstarbackendjudgeservice.judge.codesandbox.CodeSandbox;
import com.ryanverse.ojstarbackendmodel.codesandbox.ExecuteCodeRequest;
import com.ryanverse.ojstarbackendmodel.codesandbox.ExecuteCodeResponse;

/**
 * ClassName: ThirdPartyCodeSandboxImpl
 * Description: 第三方代码沙箱
 *
 * @Author Haoran
 * @Create 2024/7/11 20:00
 * @Version 1.0
 */
public class ThirdPartyCodeSandboxImpl implements CodeSandbox {
	@Override
	public ExecuteCodeResponse executeCode (ExecuteCodeRequest executeCodeRequest) {
		System.out.println("ThirdPartyCodeSandboxImpl");
		return null;
	}
}
