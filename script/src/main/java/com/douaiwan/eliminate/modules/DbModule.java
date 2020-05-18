package com.douaiwan.eliminate.modules;

import com.douaiwan.eliminate.config.ServerConfig;
import com.douaiwan.eliminate.dispathers.interfaces.IListener;
import com.douaiwan.eliminate.task.EventTask;
import com.google.inject.Inject;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Transaction;

import java.util.Map;

public class DbModule implements IListener<EventTask> {
    private final Jedis jedis;
    @Inject
    public DbModule() {
        jedis = initJedis();
    }
    private Jedis initJedis(){
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(1);
        config.setMaxIdle(1);
        JedisPool jedisPool = new JedisPool( config ,
                ServerConfig.getInstance().properties.getProperty("db_address"),
                Integer.parseInt(ServerConfig.getInstance().properties.getProperty("db_port")),
                5000,
                ServerConfig.getInstance().properties.getProperty("db_password"));
        Jedis jedis = jedisPool.getResource(); // 获取连接
        return jedis;
    }

    @Override
    public void call(EventTask eventTask) {

    }
}
