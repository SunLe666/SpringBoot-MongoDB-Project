package com.sunle.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.sunle.entity.MogoUser;
import com.sunle.service.MongoDbUserService;

@Service
@Component("mongoDbUserService")
public class MongoDbUserServiceImpl implements MongoDbUserService{
    private static final Logger logger = LoggerFactory.getLogger(MongoDbUserServiceImpl.class);

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 保存对象
     *
     * @param MogoUser
     * @return
     */
    public String saveObj(MogoUser MogoUser) {
        logger.info("--------------------->[MongoDB save start]");
        mongoTemplate.save(MogoUser);
        return "添加成功";
    }


    /**
     * 查询所有
     *
     * @return
     */
    public List<MogoUser> findAll() {
        logger.info("--------------------->[MongoDB find start]");
        return mongoTemplate.findAll(MogoUser.class);
    }


    /***
     * 根据id查询
     * @param id
     * @return
     */
    public MogoUser getUserById(String id) {
        logger.info("--------------------->[MongoDB find start]");
        Query query = new Query(Criteria.where("id").is(id));
        return mongoTemplate.findOne(query, MogoUser.class);
    }

    /**
     * 根据名称查询
     *
     * @param name
     * @return
     */
    public MogoUser getUserByName(String name) {
        logger.info("--------------------->[MongoDB find start]");
        Query query = new Query(Criteria.where("name").is(name));
        return mongoTemplate.findOne(query, MogoUser.class);
    }

    /**
     * 更新对象
     *
     * @param MogoUser
     * @return
     */
    public String updateUser(MogoUser MogoUser) {
        logger.info("--------------------->[MongoDB update start]");
        Query query = new Query(Criteria.where("_id").is(MogoUser.getUserId()));
        Update update = new Update().set("name", MogoUser.getUsername());
        //updateFirst 更新查询返回结果集的第一条
        mongoTemplate.updateFirst(query, update, MogoUser.class);
        //updateMulti 更新查询返回结果集的全部
//        mongoTemplate.updateMulti(query,update,MogoUser.class);
        //upsert 更新对象不存在则去添加
//        mongoTemplate.upsert(query,update,MogoUser.class);
        return "success";
    }

    /***
     * 删除对象
     * @param MogoUser
     * @return
     */
    public String deleteUser(MogoUser MogoUser) {
        logger.info("--------------------->[MongoDB delete start]");
        mongoTemplate.remove(MogoUser);
        return "success";
    }

    /**
     * 根据id删除
     *
     * @param id
     * @return
     */
    public String deleteUserById(String id) {
        logger.info("--------------------->[MongoDB delete start]");
        //findOne
        MogoUser MogoUser = getUserById(id);
        //delete
        deleteUser(MogoUser);
        return "success";
    }
}
