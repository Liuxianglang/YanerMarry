package com.iyaner.yaner.shiro;

import com.iyaner.yaner.entity.utils.StaticEasyVar;
import com.iyaner.yaner.service.RedisService;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;

import java.util.Collection;
import java.util.Set;

@Component
public class RedisCache<K,V> implements Cache<K,V> {

    @Autowired
    private RedisService redisService;

    private String getCacheKey(String key){
        return StaticEasyVar.SHIRO_CACHE_PREFIX+key;
    }
    @Override
    public V get(K k) throws CacheException {
        if(k instanceof  String){
            String key=getCacheKey(k.toString());
            byte[] val= (byte[]) redisService.get(key);
            return (V) SerializationUtils.deserialize(val);
        }
        return null;
    }

    @Override
    public V put(K k, V v) throws CacheException {
        System.out.println("设置权限数据.....");
        if(k instanceof  String){
            String key=getCacheKey(k.toString());
            redisService.set(key,SerializationUtils.serialize(v));
        }
        return v;
    }

    @Override
    public V remove(K k) throws CacheException {
        if(k instanceof  String){
            String key=getCacheKey(k.toString());
            byte[] val= (byte[]) redisService.get(key);
            redisService.remove(key);
            return (V) SerializationUtils.deserialize(val);
        }
        return null;
    }

    @Override
    public void clear() throws CacheException {

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Set<K> keys() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }
}
