package io.rocketchat.core.model;

import io.rocketchat.common.data.model.Room;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

/**
 * Created by sachin on 19/7/17.
 */


public class SubscriptionObject extends Room{

    Date roomCreated;
    Date lastSeen;
    Boolean open;
    Boolean alert;
    Integer unread;
    Date updatedAt;
    String subscriptionId;

    String desktopNotifications;
    String mobilePushNotifications;
    String emailNotifications;

    public SubscriptionObject(JSONObject object)  {
        super(object);
        try {
            roomId = object.getString("rid");
            if (object.optJSONObject("ts")!=null) {
                roomCreated = new Date(object.getJSONObject("ts").getLong("$date"));
            }
            if (object.optJSONObject("ls")!=null) {
                lastSeen = new Date(object.getJSONObject("ls").getLong("$date"));
            }
            open = object.getBoolean("open");
            alert = object.getBoolean("alert");
            unread = object.getInt("unread");
            updatedAt = new Date(object.getJSONObject("_updatedAt").getLong("$date"));
            subscriptionId = object.getString("_id");

            desktopNotifications=object.optString("desktopNotifications");
            mobilePushNotifications=object.optString("mobilePushNotifications");
            emailNotifications=object.optString("emailNotifications");

        }catch (JSONException e) {
            e.printStackTrace();
            System.out.println("name is "+getRoomName());
        }
    }


    public Date getRoomCreated() {
        return roomCreated;
    }

    public Date getLastSeen() {
        return lastSeen;
    }

    public Boolean getOpen() {
        return open;
    }

    public Boolean getAlert() {
        return alert;
    }

    public Integer getUnread() {
        return unread;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

//    public String getDesktopNotifications() {
//        return desktopNotifications;
//    }
//
//    public String getMobilePushNotifications() {
//        return mobilePushNotifications;
//    }
//
//    public String getEmailNotifications() {
//        return emailNotifications;
//    }
}