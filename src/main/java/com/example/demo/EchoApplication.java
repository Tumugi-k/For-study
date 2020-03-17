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
		String OriginalTextMessage = null;
		switch(msg) {
		case "天気":
			//天気予報取得メソッドのreturnをセットする
			OriginalTextMessage = "いいお天気ですね";
			break;
		case "温度":
		case "気温":
			//現在の気温取得メソッドのreturnをセットする
			OriginalTextMessage = "今日は寒いですね";
			break;
		case "遅延":
		case "電車":
			OriginalTextMessage = "何線の情報が知りたい？";
			break;
		default:
			OriginalTextMessage = DelayInfo.DlayInfo(msg);			
		}	
		return new TextMessage(OriginalTextMessage);
    }
    
    
    @EventMapping
    public void handleDefaultMessageEvent(Event event) {
        System.out.println("event: " + event);
    }
}

