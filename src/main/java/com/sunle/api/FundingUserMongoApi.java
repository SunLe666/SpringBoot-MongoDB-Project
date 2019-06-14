package com.sunle.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sunle.entity.MogoUser;
import com.sunle.service.MongoDbUserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/mguser/v1")
@Api("swaggerFundingUserMongoApi相关的api")
public class FundingUserMongoApi {
	@Autowired
	private MongoDbUserService mongoDbUserService;
    
	@ApiOperation(value = "保存对象", notes = "保存对象接口。。")
	@PostMapping("/mongo/save")
	public String saveObj(@RequestBody MogoUser MogoUser) {
		return mongoDbUserService.saveObj(MogoUser);
	}
	
	@ApiOperation(value = "查询所有", notes = "查询所有接口。。")
	@GetMapping("/mongo/findAll")
	public List<MogoUser> findAll() {
		return mongoDbUserService.findAll();
	}
	
	@ApiOperation(value = "根据id查询", notes = "根据id查询接口。。")
	@GetMapping("/mongo/findOne")
	public MogoUser findOne(@RequestParam String id) {
		return mongoDbUserService.getUserById(id);
	}
	
	@ApiOperation(value = "根据name查询", notes = "根据name查询接口。。")
	@GetMapping("/mongo/findOneByName")
	public MogoUser findOneByName(@RequestParam String name) {
		return mongoDbUserService.getUserByName(name);
	}

	@ApiOperation(value = "修改对象", notes = "修改对象接口。。")
	@PostMapping("/mongo/update")
	public String update(@RequestBody MogoUser MogoUser) {
		return mongoDbUserService.updateUser(MogoUser);
	}
	
	
	@ApiOperation(value = "删除对象", notes = "修改对象接口。。")
	@PostMapping("/mongo/delOne")
	public String delOne(@RequestBody MogoUser MogoUser) {
		return mongoDbUserService.deleteUser(MogoUser);
	}

	@ApiOperation(value = "根据id删除", notes = "根据id删除接口。。")
	@GetMapping("/mongo/delById")
	public String delById(@RequestParam String id) {
		return mongoDbUserService.deleteUserById(id);
	}
}
