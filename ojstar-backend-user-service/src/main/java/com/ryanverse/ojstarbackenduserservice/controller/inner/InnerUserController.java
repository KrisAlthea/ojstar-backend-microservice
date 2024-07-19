package com.ryanverse.ojstarbackenduserservice.controller.inner;

import com.ryanverse.ojstarbackendmodel.entity.User;
import com.ryanverse.ojstarbackendserviceclient.service.UserFeignClient;
import com.ryanverse.ojstarbackenduserservice.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * ClassName: InnerUserController
 * Description: 仅供内部调用的用户控制器
 *
 * @Author Haoran
 * @Create 2024/7/18 16:11
 * @Version 1.0
 */
@RestController
@RequestMapping("/inner")
public class InnerUserController implements UserFeignClient {

	@Resource
	private UserService userService;

	/**
	 * 根据 id 获取用户
	 *
	 * @param userId
	 * @return
	 */
	@Override
	@GetMapping("/get/id")
	public User getById (@RequestParam("userId") long userId){
		return userService.getById(userId);
	}

	/**
	 * 根据 id 获取用户列表
	 *
	 * @param idList
	 * @return
	 */
	@Override
	@GetMapping("/get/ids")
	public List<User> listByIds (@RequestParam("idList") Collection<Long> idList){
		return userService.listByIds(idList);
	}

}
