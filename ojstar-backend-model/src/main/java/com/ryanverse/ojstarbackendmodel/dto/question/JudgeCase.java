package com.ryanverse.ojstarbackendmodel.dto.question;

import lombok.Data;

/**
 * ClassName: JudgeCase
 * Description:
 *
 * @Author Haoran
 * @Create 2024/7/7 1:29
 * @Version 1.0
 */
@Data
public class JudgeCase {
	/**
	 * 输入用例
	 */
	private String input;

	/**
	 * 输出用例
	 */
	private String output;
}
