package com.sunle.service;

import java.util.List;

import com.sunle.entity.MogoUser;

public interface MongoDbUserService {

	
	/**
     * 保存对象
     *
     * @param MogoUser
     * @return
     */
    public String saveObj(MogoUser MogoUser);


    /**
     * 查询所有
     *
     * @return
     */
    public List<MogoUser> findAll();

    /***
     * 根据id查询
     * @param id
     * @return
     */
    public MogoUser getUserById(String id);

    /**
     * 根据名称查询
     *
     * @param name
     * @return
     */
    public MogoUser getUserByName(String name);

    /**
     * 更新对象
     *
     * @param MogoUser
     * @return
     */
    public String updateUser(MogoUser MogoUser);

    /***
     * 删除对象
     * @param MogoUser
     * @return
     */
    public String deleteUser(MogoUser MogoUser);

    /**
     * 根据id删除
     *
     * @param id
     * @return
     */
    public String deleteUserById(String id);
}
