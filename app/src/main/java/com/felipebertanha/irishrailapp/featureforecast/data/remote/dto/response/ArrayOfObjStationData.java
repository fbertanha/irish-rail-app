package com.felipebertanha.irishrailapp.featureforecast.data.remote.dto.response;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "ArrayOfObjStationData")
public class ArrayOfObjStationData {

    @ElementList(name = "objStationData", required = false, entry = "objStationData", inline = true)
    List<ObjStationData> objStationData;

    public List<ObjStationData> getObjStationData() {
        return this.objStationData;
    }

    public void setObjStationData(List<ObjStationData> value) {
        this.objStationData = value;
    }

    public static class ObjStationData {

        @Element(name = "Origin", required = false)
        String origin;

        @Element(name = "Status", required = false)
        String status;

        @Element(name = "Destination", required = false)
        String destination;

        @Element(name = "Origintime", required = false)
        String origintime;

        @Element(name = "Late", required = false)
        String late;

        @Element(name = "Destinationtime", required = false)
        String destinationtime;

        @Element(name = "Traintype", required = false)
        String traintype;

        @Element(name = "Duein", required = false)
        String duein;

        @Element(name = "Direction", required = false)
        String direction;

        @Element(name = "Locationtype", required = false)
        String locationtype;

        @Element(name = "Expdepart", required = false)
        String expdepart;

        @Element(name = "Traincode", required = false)
        String traincode;

        @Element(name = "Traindate", required = false)
        String traindate;

        @Element(name = "Stationfullname", required = false)
        String stationfullname;

        @Element(name = "Querytime", required = false)
        String querytime;

        @Element(name = "Schdepart", required = false)
        String schdepart;

        @Element(name = "Stationcode", required = false)
        String stationcode;

        @Element(name = "Servertime", required = false)
        String servertime;

        @Element(name = "Exparrival", required = false)
        String exparrival;

        @Element(name = "Scharrival", required = false)
        String scharrival;

        @Element(name = "Lastlocation", required = false)
        String lastlocation;

        public String getOrigin() {
            return this.origin;
        }

        public void setOrigin(String value) {
            this.origin = value;
        }

        public String getStatus() {
            return this.status;
        }

        public void setStatus(String value) {
            this.status = value;
        }

        public String getDestination() {
            return this.destination;
        }

        public void setDestination(String value) {
            this.destination = value;
        }

        public String getOrigintime() {
            return this.origintime;
        }

        public void setOrigintime(String value) {
            this.origintime = value;
        }

        public String getLate() {
            return this.late;
        }

        public void setLate(String value) {
            this.late = value;
        }

        public String getDestinationtime() {
            return this.destinationtime;
        }

        public void setDestinationtime(String value) {
            this.destinationtime = value;
        }

        public String getTraintype() {
            return this.traintype;
        }

        public void setTraintype(String value) {
            this.traintype = value;
        }

        public String getDuein() {
            return this.duein;
        }

        public void setDuein(String value) {
            this.duein = value;
        }

        public String getDirection() {
            return this.direction;
        }

        public void setDirection(String value) {
            this.direction = value;
        }

        public String getLocationtype() {
            return this.locationtype;
        }

        public void setLocationtype(String value) {
            this.locationtype = value;
        }

        public String getExpdepart() {
            return this.expdepart;
        }

        public void setExpdepart(String value) {
            this.expdepart = value;
        }

        public String getTraincode() {
            return this.traincode;
        }

        public void setTraincode(String value) {
            this.traincode = value;
        }

        public String getTraindate() {
            return this.traindate;
        }

        public void setTraindate(String value) {
            this.traindate = value;
        }

        public String getStationfullname() {
            return this.stationfullname;
        }

        public void setStationfullname(String value) {
            this.stationfullname = value;
        }

        public String getQuerytime() {
            return this.querytime;
        }

        public void setQuerytime(String value) {
            this.querytime = value;
        }

        public String getSchdepart() {
            return this.schdepart;
        }

        public void setSchdepart(String value) {
            this.schdepart = value;
        }

        public String getStationcode() {
            return this.stationcode;
        }

        public void setStationcode(String value) {
            this.stationcode = value;
        }

        public String getServertime() {
            return this.servertime;
        }

        public void setServertime(String value) {
            this.servertime = value;
        }

        public String getExparrival() {
            return this.exparrival;
        }

        public void setExparrival(String value) {
            this.exparrival = value;
        }

        public String getScharrival() {
            return this.scharrival;
        }

        public void setScharrival(String value) {
            this.scharrival = value;
        }

        public String getLastlocation() {
            return this.lastlocation;
        }

        public void setLastlocation(String value) {
            this.lastlocation = value;
        }

    }

}