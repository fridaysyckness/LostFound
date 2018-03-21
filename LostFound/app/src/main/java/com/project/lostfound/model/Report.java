package com.project.lostfound.model;

public class Report {

    private String reportid;
    private String type;
    private String datereported;
    private String itemname;
    private String details;
    private String brand;
    private String category;
    private String location;
    private String locationdetails;
    private String contactinformation;
    private String remarks;

    public Report() {}

    public String getReportid() {
        return reportid;
    }

    public void setReportid(String reportid) {
        this.reportid = reportid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDatereported() {
        return datereported;
    }

    public void setDatereported(String datereported) {
        this.datereported = datereported;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocationdetails() {
        return locationdetails;
    }

    public void setLocationdetails(String locationdetails) {
        this.locationdetails = locationdetails;
    }

    public String getContactinformation() {
        return contactinformation;
    }

    public void setContactinformation(String contactinformation) {
        this.contactinformation = contactinformation;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}

