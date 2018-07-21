package com.iyaner.yaner.shiro;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.session.mgt.WebSessionKey;

import javax.servlet.ServletRequest;
import java.io.Serializable;

public class SessionManager  extends DefaultWebSessionManager {

    @Override
    protected Session retrieveSession(SessionKey sessionKey) throws UnknownSessionException {
        Serializable sessionID = getSessionId(sessionKey);
        ServletRequest request=null;
        if(sessionKey instanceof WebSessionKey){
            request=((WebSessionKey)sessionKey).getServletRequest();
        }
        if(request!=null&&sessionID!=null){
            Session session= (Session) request.getAttribute(sessionID.toString());
            if(session!=null) return session;
        }
        Session session=super.retrieveSession(sessionKey);
        if(request!=null&&sessionID!=null){
            request.setAttribute(sessionID.toString(),session);
        }
        return session;
    }
}
