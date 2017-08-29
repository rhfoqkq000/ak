package com.donga.examples.boomin.Singleton;

/**
 * Created by pmk on 17. 2. 21.
 */
public class ManageSingleton {
    private static ManageSingleton mInstance = null;

    private String token;
    private String managerID;
    private String pcnotis_id;
    private String manageID;
    private String managePW;

    public static ManageSingleton getInstance() {
        if(mInstance == null)
        {
            mInstance = new ManageSingleton();
        }
        return mInstance;
    }

    private ManageSingleton() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getManagerID() {
        return managerID;
    }

    public void setManagerID(String managerID) {
        this.managerID = managerID;
    }

    public String getPcnotis_id() {
        return pcnotis_id;
    }

    public void setPcnotis_id(String pcnotis_id) {
        this.pcnotis_id = pcnotis_id;
    }

    public String getManageID() {
        return manageID;
    }

    public void setManageID(String manageID) {
        this.manageID = manageID;
    }

    public String getManagePW() {
        return managePW;
    }

    public void setManagePW(String managePW) {
        this.managePW = managePW;
    }
}
