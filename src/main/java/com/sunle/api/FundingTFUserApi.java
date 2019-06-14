package com.sunle.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sunle.entity.MogoUser;
import com.sunle.service.MongoDbUserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("tfuser/v1")
@Api("thymeleaf测试相关的api")
public class FundingTFUserApi {
	
	private static Logger logger = LoggerFactory.getLogger(FundingUserApi.class);

	@Autowired
	private MongoDbUserService mongoDbUserService;
	
	@ApiOperation(value = "查询所有", notes = "查询所有接口。。")
	@GetMapping("/mongo/findAll")
	public String queryAllUser(ModelMap map){
		List<MogoUser> userList = mongoDbUserService.findAll();
		map.addAttribute("userList", userList);
		return "user-view";
	}
	
}
