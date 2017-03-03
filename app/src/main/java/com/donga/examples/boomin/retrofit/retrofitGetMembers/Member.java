package com.donga.examples.boomin.retrofit.retrofitGetMembers;

/**
 * Created by pmk on 17. 2. 22.
 */

public class Member {
    String id, stuId, name, coll, major, circle_id, push_permit, created_at, updated_at;

    public String getCircle_id() {
        return circle_id;
    }

    public String getColl() {
        return coll;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getId() {
        return id;
    }

    public String getMajor() {
        return major;
    }

    public String getName() {
        return name;
    }

    public String getPush_permit() {
        return push_permit;
    }

    public String getStuId() {
        return stuId;
    }

    public String getUpdated_at() {
        return updated_at;
    }
}
