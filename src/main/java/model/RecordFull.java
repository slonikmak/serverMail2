package model;

/**
 * Created by Oceanos on 14.10.2016.
 */
public class RecordFull {
    private long id;

    private long time;
    private String date;
    private long sessionId;

    private double motion1;
    private double motion2;
    private double motion3;
    private double motion4;

    private double longtitude1;
    private double longtitude2;
    private double latitude1;
    private double latitude2;

    private double hull1;
    private double hull2;
    private double hull3;
    private double hull4;

    private double depth1;
    private double depth2;
    private double depth3;
    private double depth4;

    private double temp1;
    private double temp2;
    private double temp3;

    private double bat1;
    private double bat2;
    private double bat3;

    private double servoP1;
    private double servoP2;
    private double servoP3;
    private double servoP4;

    private double servoV1;
    private double servoV2;
    private double servoV3;
    private double servoV4;

    public RecordFull() {
    }

    public RecordFull(long id, long time, String date, long sessionId, double motion1, double motion2, double motion3, double motion4, double longtitude1, double longtitude2, double latitude1, double latitude2, double hull1, double hull2, double hull3, double hull4, double depth1, double depth2, double depth3, double depth4, double temp1, double temp2, double temp3, double bat1, double bat2, double bat3, double servoP1, double servoP2, double servoP3, double servoP4, double servoV1, double servoV2, double servoV3, double servoV4) {
        this.id = id;
        this.time = time;
        this.date = date;
        this.sessionId = sessionId;
        this.motion1 = motion1;
        this.motion2 = motion2;
        this.motion3 = motion3;
        this.motion4 = motion4;
        this.longtitude1 = longtitude1;
        this.longtitude2 = longtitude2;
        this.latitude1 = latitude1;
        this.latitude2 = latitude2;
        this.hull1 = hull1;
        this.hull2 = hull2;
        this.hull3 = hull3;
        this.hull4 = hull4;
        this.depth1 = depth1;
        this.depth2 = depth2;
        this.depth3 = depth3;
        this.depth4 = depth4;
        this.temp1 = temp1;
        this.temp2 = temp2;
        this.temp3 = temp3;
        this.bat1 = bat1;
        this.bat2 = bat2;
        this.bat3 = bat3;
        this.servoP1 = servoP1;
        this.servoP2 = servoP2;
        this.servoP3 = servoP3;
        this.servoP4 = servoP4;
        this.servoV1 = servoV1;
        this.servoV2 = servoV2;
        this.servoV3 = servoV3;
        this.servoV4 = servoV4;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getSessionId() {
        return sessionId;
    }

    public void setSessionId(long sessionId) {
        this.sessionId = sessionId;
    }

    public double getMotion1() {
        return motion1;
    }

    public void setMotion1(double motion1) {
        this.motion1 = motion1;
    }

    public double getMotion2() {
        return motion2;
    }

    public void setMotion2(double motion2) {
        this.motion2 = motion2;
    }

    public double getMotion3() {
        return motion3;
    }

    public void setMotion3(double motion3) {
        this.motion3 = motion3;
    }

    public double getMotion4() {
        return motion4;
    }

    public void setMotion4(double motion4) {
        this.motion4 = motion4;
    }

    public double getLongtitude1() {
        return longtitude1;
    }

    public void setLongtitude1(double longtitude1) {
        this.longtitude1 = longtitude1;
    }

    public double getLongtitude2() {
        return longtitude2;
    }

    public void setLongtitude2(double longtitude2) {
        this.longtitude2 = longtitude2;
    }

    public double getLatitude1() {
        return latitude1;
    }

    public void setLatitude1(double latitude1) {
        this.latitude1 = latitude1;
    }

    public double getLatitude2() {
        return latitude2;
    }

    public void setLatitude2(double latitude2) {
        this.latitude2 = latitude2;
    }

    public double getHull1() {
        return hull1;
    }

    public void setHull1(double hull1) {
        this.hull1 = hull1;
    }

    public double getHull2() {
        return hull2;
    }

    public void setHull2(double hull2) {
        this.hull2 = hull2;
    }

    public double getHull3() {
        return hull3;
    }

    public void setHull3(double hull3) {
        this.hull3 = hull3;
    }

    public double getHull4() {
        return hull4;
    }

    public void setHull4(double hull4) {
        this.hull4 = hull4;
    }

    public double getDepth1() {
        return depth1;
    }

    public void setDepth1(double depth1) {
        this.depth1 = depth1;
    }

    public double getDepth2() {
        return depth2;
    }

    public void setDepth2(double depth2) {
        this.depth2 = depth2;
    }

    public double getDepth3() {
        return depth3;
    }

    public void setDepth3(double depth3) {
        this.depth3 = depth3;
    }

    public double getDepth4() {
        return depth4;
    }

    public void setDepth4(double depth4) {
        this.depth4 = depth4;
    }

    public double getTemp1() {
        return temp1;
    }

    public void setTemp1(double temp1) {
        this.temp1 = temp1;
    }

    public double getTemp2() {
        return temp2;
    }

    public void setTemp2(double temp2) {
        this.temp2 = temp2;
    }

    public double getTemp3() {
        return temp3;
    }

    public void setTemp3(double temp3) {
        this.temp3 = temp3;
    }

    public double getBat1() {
        return bat1;
    }

    public void setBat1(double bat1) {
        this.bat1 = bat1;
    }

    public double getBat2() {
        return bat2;
    }

    public void setBat2(double bat2) {
        this.bat2 = bat2;
    }

    public double getBat3() {
        return bat3;
    }

    public void setBat3(double bat3) {
        this.bat3 = bat3;
    }

    public double getServoP1() {
        return servoP1;
    }

    public void setServoP1(double servoP1) {
        this.servoP1 = servoP1;
    }

    public double getServoP2() {
        return servoP2;
    }

    public void setServoP2(double servoP2) {
        this.servoP2 = servoP2;
    }

    public double getServoP3() {
        return servoP3;
    }

    public void setServoP3(double servoP3) {
        this.servoP3 = servoP3;
    }

    public double getServoP4() {
        return servoP4;
    }

    public void setServoP4(double servoP4) {
        this.servoP4 = servoP4;
    }

    public double getServoV1() {
        return servoV1;
    }

    public void setServoV1(double servoV1) {
        this.servoV1 = servoV1;
    }

    public double getServoV2() {
        return servoV2;
    }

    public void setServoV2(double servoV2) {
        this.servoV2 = servoV2;
    }

    public double getServoV3() {
        return servoV3;
    }

    public void setServoV3(double servoV3) {
        this.servoV3 = servoV3;
    }

    public double getServoV4() {
        return servoV4;
    }

    public void setServoV4(double servoV4) {
        this.servoV4 = servoV4;
    }
}
