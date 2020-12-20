package org.fanlychie.service;

public interface MessageService {

    default String getVersion() {
        return "1.0.0";
    }

    void send(String message);

}