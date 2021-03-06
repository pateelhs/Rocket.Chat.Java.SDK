package io.rocketchat.common.data.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by sachin on 22/7/17.
 */

public class Room {


    enum Type {
        PUBLIC,
        PRIVATE,
        ONE_TO_ONE
    }

    protected String roomId;
    protected String roomName;
    protected UserObject userInfo;
    Type roomType;

    public Room(JSONObject object){
        try {
            roomId = object.getString("_id");
            String type = object.getString("t");
            if (type.equals("d")){
                roomType=Type.ONE_TO_ONE;
            }else if (type.equals("c")){
                roomType=Type.PUBLIC;
            }else{
                roomType= Type.PRIVATE;
            }
            roomName = object.optString("name");
            if (object.optJSONObject("u")!=null){
                userInfo=new UserObject(object.optJSONObject("u"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public String getRoomId() {
        return roomId;
    }

    public Type getRoomType() {
        return roomType;
    }

    public String getRoomName() {
        return roomName;
    }

    public UserObject getUserInfo() {
        return userInfo;
    }
}
