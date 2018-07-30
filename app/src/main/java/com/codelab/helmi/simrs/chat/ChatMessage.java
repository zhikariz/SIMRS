/**
 * Copyright Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.codelab.helmi.simrs.chat;



import com.codelab.helmi.simrs.login.LoginModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class ChatMessage {
    private String text;
    private String friendId;
    private String senderId;
    private String timestamp;
    private String token;

    public ChatMessage(String text, String timestamp, String friendId, String senderId, String token) {
        this.text = text;
        this.timestamp = timestamp;
        this.friendId=friendId;
        this.senderId=senderId;
        this.token = token;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getReadableTime()
    {
        try {
            return formatTime(Long.valueOf(timestamp));
        }
        catch (NumberFormatException ignored) {
            return null;
        }
    }

    public LoginModel getReceiver() {
        return new LoginModel(friendId);
    }


    public String getTimestamp() {
        return timestamp;
    }

    public long getComparableTimestamp()
    {
        return Long.parseLong(timestamp);
    }

    public String formatTime(long time){
        // income time
        Calendar date = Calendar.getInstance();
        date.setTimeInMillis(time);

        // current time
        Calendar curDate = Calendar.getInstance();
        curDate.setTimeInMillis(System.currentTimeMillis());

        SimpleDateFormat dateFormat;
        if(date.get(Calendar.YEAR)==curDate.get(Calendar.YEAR)){
            if(date.get(Calendar.DAY_OF_YEAR) == curDate.get(Calendar.DAY_OF_YEAR) ){
                dateFormat = new SimpleDateFormat("h:mm a", Locale.US);
            }
            else{
                dateFormat = new SimpleDateFormat("MMM d", Locale.US);
            }
        }
        else{
            dateFormat = new SimpleDateFormat("MMM yyyy", Locale.US);
        }
        return dateFormat.format(time);
    }
}
