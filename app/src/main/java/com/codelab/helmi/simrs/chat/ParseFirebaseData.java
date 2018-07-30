package com.codelab.helmi.simrs.chat;

import android.content.Context;

import com.codelab.helmi.simrs.api.SharedPrefManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class ParseFirebaseData {
    private SharedPrefManager set;

    public ParseFirebaseData(Context context) {
        set = new SharedPrefManager(context);
    }

    public List<ChatMessage> getMessageListForUser(String msgData) {
        List<ChatMessage> chats = new ArrayList<>();
        if(msgData.replace("{","").replace("}","").split(",")[1].trim().equals("value = null"))
            return chats;
        ChatMessage tempMsg;
        String text = null, msgTime = null, senderId = null, receiverId = null, unread = null;
        for (String msgInConv : msgData.split("[}][,]")) {
            String[] temp = msgInConv.replace("}", "").split("[{]");
            String[] msgParts = temp[temp.length - 1].split(",");
            for (String part : msgParts) {
                if (part.split("=")[0].trim().equals("text"))
                    text = decodeText(part.split("=")[1].trim());
                if (part.split("=")[0].trim().equals("timestamp"))
                    msgTime = part.split("=")[1].trim();
                if (part.split("=")[0].trim().equals("senderid"))
                    senderId = part.split("=")[1].trim();
                if (part.split("=")[0].trim().equals("receiverid"))
                    receiverId = part.split("=")[1].trim();
                if (part.split("=")[0].trim().equals("token"))
                    unread = part.split("=")[1].trim();

            }
            tempMsg = new ChatMessage(text, msgTime, receiverId, senderId, unread);
            chats.add(tempMsg);
        }
        Collections.sort(chats, new Comparator<ChatMessage>() {
            public int compare(ChatMessage c1, ChatMessage c2) {
                return (c1.getComparableTimestamp() > c2.getComparableTimestamp() ? 1 : (c1.getComparableTimestamp() < c2.getComparableTimestamp() ? -1 : 0));
            }
        });
        return chats;
    }


    public String encodeText(String msg) {
        return msg.replace(",", "#comma#").replace("{", "#braceopen#").replace("}", "#braceclose#").replace("=", "#equals#");
    }

    private String decodeText(String msg) {
        return msg.replace("#comma#", ",").replace("#braceopen#", "{").replace("#braceclose#", "}").replace("#equals#", "=");
    }


}
