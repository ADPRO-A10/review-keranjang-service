package id.ac.ui.cs.advprog.reviewkeranjangservice.service;

import java.util.Map;

public interface Handler {
    void setNext(Handler handler);
    void handleRequest(Map<String, Object> request);
}
