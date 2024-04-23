package id.ac.ui.cs.advprog.reviewkeranjangservice.service;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
public abstract class BaseHandler implements Handler{

    @Setter
    private String status;
    private Handler nextHandler;


    @Override
    public void setNext(Handler handler) {
        this.nextHandler = handler;
    }

    @Override
    public abstract void handleRequest(Map<String, Object> request);

}
