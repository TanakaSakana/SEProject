package com.example.desktop.msg.msg_done;

import java.sql.Timestamp;

public class Message {
    private String Sender;
    private String Recipient;
    private String Title;
    private String Content;
    private Timestamp msgTime;

    public Message(String sender, String receipt, String title, String content) {
        Sender = sender;
        Recipient = receipt;
        Title = title;
        Content = content;
    }

    public Message() {
    }

    public Timestamp getMsgTime() {
        return msgTime;
    }

    public void setMsgTime(String msgTime) {
        this.msgTime = Timestamp.valueOf(msgTime);
    }

    public String getSender() {
        return Sender;
    }

    public String getReceipt() {
        return Recipient;
    }

    public String getTitle() {
        return Title;
    }

    public String getContent() {
        return Content;
    }
}
