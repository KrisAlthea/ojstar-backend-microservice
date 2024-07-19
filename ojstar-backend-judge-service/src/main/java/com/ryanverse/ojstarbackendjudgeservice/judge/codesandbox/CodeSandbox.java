package com.ryanverse.ojstarbackendjudgeservice.judge.codesandbox;

import com.ryanverse.ojstarbackendmodel.codesandbox.ExecuteCodeRequest;
import com.ryanverse.ojstarbackendmodel.codesandbox.ExecuteCodeResponse;

/**
 * ClassName: CodeSandbox
 * Description: 代码沙箱接口
 *
 * @Author Haoran
 * @Create 2024/7/11 19:48
 * @Version 1.0
 */
public interface CodeSandbox {
	ExecuteCodeResponse executeCode (ExecuteCodeRequest executeCodeRequest);
}
