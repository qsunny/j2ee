package com.aaron.springbootDemo.core.api.msg;

import com.aaron.springbootDemo.api.IMessageApi;
import org.springframework.stereotype.Service;

@Service("messageApiImpl")
public class MessageImplApi implements IMessageApi {

    @Override
    public String sendMsg(String msg) {
        return String.format("hello,%s",msg);
    }
}
