package com.example.ebookbackend.websocket;

import com.example.ebookbackend.constant.common.CliAddOrderMul;
import com.example.ebookbackend.constant.common.CliAddOrderOne;
import lombok.var;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

@ServerEndpoint(value = "/transfer/{user_id}")
@Component
public class WebSocketServer {
    private static final ConcurrentHashMap<String, Session> SESSIONS = new ConcurrentHashMap<>();
    private static final Logger logger = Logger.getLogger("SalesLogger");

    public WebSocketServer() {

    }

    public void sendMessageToUserMul(String user_id, CliAddOrderMul message) {
        try {
            Session toSession = SESSIONS.get(user_id);
            var s = toSession.getBasicRemote();
            logger.log(Level.INFO, JSONObject.fromObject(message).toString());
            s.sendText(JSONObject.fromObject(message).toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void sendMessageToUserOne(String user_id, CliAddOrderOne message) {
        try {
            Session toSession = SESSIONS.get(user_id);
            var s = toSession.getBasicRemote();
            s.sendText(JSONObject.fromObject(message).toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    @OnOpen
    public void openConnection(Session session, @PathParam("user_id") String user_id) {
        logger.log(Level.INFO, "Connection opened.");
        if (SESSIONS.get(user_id) != null) {
            System.out.println("已经上线过了");
            return;
        }
        SESSIONS.put(user_id.trim(), session);
    }

    @OnClose
    public void onClose(@PathParam("user_id") String userId) {
        SESSIONS.remove(userId);
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        System.out.println("发生错误");
        throwable.printStackTrace();
    }
}
