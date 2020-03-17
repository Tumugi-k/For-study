package com.example.demo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;

@SpringBootApplication
@LineMessageHandler

public class EchoApplication {
    public static void main(String[] args) {
        SpringApplication.run(EchoApplication.class, args);
    }
    
	@EventMapping
    public Message handleTextMessageEvent(MessageEvent<TextMessageContent> event) {
		final String msg = event.getMessage().getText();
		switch(msg) {
		case "天気":
			return new TextMessage("雲の上はいつだって晴れなのだ");
		default:
			final String OriginalMessageText = DelayInfo.DlayInfo(msg);
			return new TextMessage(OriginalMessageText);
		}	
    }
    
    
    @EventMapping
    public void handleDefaultMessageEvent(Event event) {
        System.out.println("event: " + event);
    }
}

