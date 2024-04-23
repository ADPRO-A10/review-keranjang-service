package id.ac.ui.cs.advprog.reviewkeranjangservice.service;

import java.util.Map;

public abstract class BaseHandler implements Handler{

    private String status;
    private Handler nextHandler;


    @Override
    public void setNext(Handler handler) {
    }

    @Override
    public abstract void handleRequest(Map<String, Object> Request);

    public String getStatus() {
        return "";
    }
}
