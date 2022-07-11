package com.developer.wang.utils.bean;

/**
 * Date:2018/7/16
 * Time:20:19
 * Author:Loren
 * Desc: 运营商相关：各国各运营商对应的国家码、网络码
 *
 */
public class OperatorBean {

    private String MCC;//国家码：国际移动用户识别码（IMSI）--IMSI共有15位，其结构如下：MCC+MNC+MSIN ，（MNC+MSIN=NMSI）

    private String MNC;//网络码

    private String ISO;//国家缩写

    private String Country;//国家

    private String CountryCode;//国家编码

    private String Network;//网络

    public String getMCC() {
        return MCC;
    }

    public void setMCC(String MCC) {
        this.MCC = MCC;
    }

    public String getMNC() {
        return MNC;
    }

    public void setMNC(String MNC) {
        this.MNC = MNC;
    }

    public String getISO() {
        return ISO;
    }

    public void setISO(String ISO) {
        this.ISO = ISO;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getCountryCode() {
        return CountryCode;
    }

    public void setCountryCode(String countryCode) {
        CountryCode = countryCode;
    }

    public String getNetwork() {
        return Network;
    }

    public void setNetwork(String network) {
        Network = network;
    }

    @Override
    public String toString() {
        return "JWOperatorBean{" +
                "MCC='" + MCC + '\'' +
                ", MNC='" + MNC + '\'' +
                ", ISO='" + ISO + '\'' +
                ", Country='" + Country + '\'' +
                ", CountryCode='" + CountryCode + '\'' +
                ", Network='" + Network + '\'' +
                '}';
    }
}
