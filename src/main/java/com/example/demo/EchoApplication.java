package com.example.demo;


import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.linecorp.bot.model.action.MessageAction;
import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.message.quickreply.QuickReply;
import com.linecorp.bot.model.message.quickreply.QuickReplyItem;
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
		int n = 10;
		try {
		switch(msg) {
		case "天気":
			final List<QuickReplyItem> items = Arrays.<QuickReplyItem>asList(
					QuickReplyItem.builder()
	                .action(new MessageAction("今日", "今日"))
	                .build(),
	                QuickReplyItem.builder()
	                .action(new MessageAction("明日", "明日"))
	                .build(),
	                QuickReplyItem.builder()
	                .action(new MessageAction("明後日", "明後日"))
	                .build()
	                );
			final QuickReply quickReply = QuickReply.items(items);
			return TextMessage
	                .builder()
	                .text("いつの天気が知りたいですか？")
	                .quickReply(quickReply)
	                .build();
		case "今日":
			n = 0;
			OriginalTextMessage = WeatherInfo.Info(n);
			break;
		case "明日":
			n = 1;
			OriginalTextMessage = WeatherInfo.Info(n);
			break;
		case "明後日":
			n = 2;
			OriginalTextMessage = WeatherInfo.Info(n);
			break;
		case "温度":
		case "気温":
			OriginalTextMessage = WeatherInfo.TemperatureInfo();
			break;
		case "遅延":
		case "電車":
			OriginalTextMessage = "何線の情報が知りたいですか？";
			break;
		default:
			OriginalTextMessage = DelayInfo.main(msg);		
		}
		if(OriginalTextMessage == null) {
			String type = "不明";
			OriginalTextMessage = RandomReply.main(type);
		}
		return new TextMessage(OriginalTextMessage);
		}catch (IOException ioe) {
			return new TextMessage("エラーが発生しました");
		}
    }
	
    @EventMapping
    public void handleDefaultMessageEvent(Event event) {
        System.out.println("event: " + event);
    }
}

