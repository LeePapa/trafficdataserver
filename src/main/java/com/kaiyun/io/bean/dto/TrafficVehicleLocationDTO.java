package com.kaiyun.io.bean.dto;

import com.kaiyun.io.packet.TrafficVehicleLocationPacket;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program: trafficdataserver
 * @description:
 * @author: Chonghao Zhong
 * @create: 2020-03-30 10:50
 **/
@Getter
@Setter
@ToString
public class TrafficVehicleLocationDTO {
    /**
     * 车牌号 21字节
     */
    private String vehicleNo;
    /**
     * 经度，单位为1*10^-6度。 4字节
     */
    private Double latitude;
    /**
     * 纬度，单位为1*10^-6度。 4字节
     */
    private Double longitude;
    /**
     * 2字节；海拔高度，单位为米（m）。
     */
    private short altitude;
    /**
     * 2字节； 速度，指卫星定位车载终端设备上传的行车速度信息，为必填项。单位为千米每小时（km/h）。
     */
    private short speed;
    /**
     * 2字节；方向，0-359，单位为度（。），正北为0，顺时针
     */
    private short direction;
    /**
     * 时间
     */
    private Date time;
    /**
     * 车辆颜色 1字节
     */
    private byte vehicleColor;
    /**
     * 2字节；行驶记录速度，指车辆行驶记录设备上传的行车速度信息，为必填项。单位为千米每小时（km/h）。
     */
    private short vehicleSpeed;
    /**
     * 4字节；车辆当前总里程数，值车辆上传的行车里程数。单位单位为千米（km）。
     */
    private int mileage;
    /**
     * 4字节；车辆状态，二进制表示
     */
    private int vehicleStatus;
    /**
     * 4字节；报警状态，二进制表示
     */
    private int alarm;

    public TrafficVehicleLocationDTO(TrafficVehicleLocationPacket packet) throws ParseException {
        this.vehicleNo = packet.getVehicleNo();
        this.latitude = (double) (packet.getLat() / 1000000);
        this.longitude = (double) (packet.getLon() / 1000000);
        this.altitude = packet.getAltitude();
        this.speed = packet.getVec1();
        this.direction = packet.getDirection();
        this.time = new Timestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(packet.getDate() + " " + packet.getTime()).getTime());
        this.vehicleColor = packet.getVehicleColor();
        this.vehicleSpeed = packet.getVec2();
        this.mileage = packet.getVec3();
        this.vehicleStatus = packet.getState();
        this.alarm = packet.getAlarm();
    }
}
