package com.iyaner.yaner.shiro;

import com.iyaner.yaner.entity.utils.StaticEasyVar;
import com.iyaner.yaner.service.RedisService;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.SimpleSession;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.SerializationUtils;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class RedisSessionDao extends AbstractSessionDAO {

    @Autowired
    private RedisService redisService;

    private String getSessionKey(String key){
        return StaticEasyVar.SHIRO_SESSION_PREFIX +key;
    }

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionID=generateSessionId(session);
        if(session!=null&&session instanceof SimpleSession&&session.getId()==null){
            ((SimpleSession) session).setId(sessionID);
        }
        if(session!=null&&session.getId()!=null){
            String key=getSessionKey(sessionID.toString());
            byte[] value=SerializationUtils.serialize(session);
            redisService.set(key,value,StaticEasyVar.SHIRO_SESSION_DEFAULTTIME);
        }
        return sessionID;
    }

    @Override
    protected Session doReadSession(Serializable sessionID) {
        if(sessionID!=null){
            String key=getSessionKey(sessionID.toString());
            byte[] val= (byte[]) redisService.get(key);
            if(val!=null){
                Session session= (Session) SerializationUtils.deserialize(val);
                if(session!=null){
                    return session;
                }
            }
        }
        return null;
    }

    @Override
    public void update(Session session) throws UnknownSessionException {
        if(session!=null&&session.getId()!=null){
            String key=getSessionKey(session.getId().toString());
            byte[] val=SerializationUtils.serialize(session);
            redisService.set(key,val,StaticEasyVar.SHIRO_SESSION_DEFAULTTIME);
        }
    }

    @Override
    public void delete(Session session) {
        if(session!=null&&session.getId()!=null){
            String key=getSessionKey(session.getId().toString());
            redisService.remove(key);
        }
    }

    @Override
    public Collection<Session> getActiveSessions() {
        String keys=StaticEasyVar.SHIRO_SESSION_PREFIX+"*";
        Set<String> set= (Set<String>) redisService.get(keys);
        Set<Session> sessionSet=new HashSet<>();
        if(set!=null&&set.size()>0){
            for (String key:set) {
                byte[] val= (byte[]) redisService.get(key);
                Session session= (Session) SerializationUtils.deserialize(val);
                sessionSet.add(session);
            }
        }
        return sessionSet;
    }
}
