package com.serendipity.jedis;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

public class JedisTest {
	@Test
	public void testJedis() throws Exception {
		Jedis jedis = new Jedis("192.168.25.128", 6379);
		jedis.set("test", "serendipity");
		System.out.println(jedis.get("test"));
		jedis.close();
	}

	@Test
	public void testJedisPool() throws Exception {
		// 创建一个连接池对象
		JedisPool jedisPool = new JedisPool("192.168.25.128", 6379);
		Jedis jedis = jedisPool.getResource();
		System.out.println(jedis.get("test"));
		jedis.close();
		jedisPool.close();
	}

	@Test
	public void testJedisCluster() throws Exception {
		// 创建一个JedisCluster对象，有一个参数nodes是一个set类型
		Set<HostAndPort> nodes = new HashSet<>();
		nodes.add(new HostAndPort("192.168.25.128", 7001));
		nodes.add(new HostAndPort("192.168.25.128", 7002));
		nodes.add(new HostAndPort("192.168.25.128", 7003));
		nodes.add(new HostAndPort("192.168.25.128", 7004));
		nodes.add(new HostAndPort("192.168.25.128", 7005));
		nodes.add(new HostAndPort("192.168.25.128", 7006));
		JedisCluster jedisCluster = new JedisCluster(nodes);
		jedisCluster.set("test1", "123");
		System.out.println(jedisCluster.get("test1"));
		// new JedisCluster(nodes);
		// 直接使用JedisCluster对象操作redis
		// 关闭jedisCluster对象
		jedisCluster.close();
	}
}
