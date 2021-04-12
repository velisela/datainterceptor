package com.oracle.datainterceptor.model;

import java.io.Serializable;

/**
 * @author velisela
 *  This is a model class which holds the customer information 
 */

public class CustomerDetails implements Serializable {
 
    private static final long serialVersionUID = 1L;

    private String customerId;
    private String contractId;
    private String geozone;
    private String teamcode;
    private String projectcode;
    private String buildDuration;

    public CustomerDetails() {

    }

    public CustomerDetails(String customerId, String contractId, String geozone,String teamcode, String projectcode, String buildDuration) {
        this.customerId=customerId;
        this.contractId=contractId;
        this.geozone=geozone;
        this.teamcode=teamcode;
        this.projectcode=projectcode;
        this.buildDuration=buildDuration;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getGeozone() {
        return geozone;
    }

    public void setGeozone(String geozone) {
        this.geozone = geozone;
    }

    public String getTeamcode() {
        return teamcode;
    }

    public void setTeamcode(String teamcode) {
        this.teamcode = teamcode;
    }

    public String getProjectcode() {
        return projectcode;
    }

    public void setProjectcode(String projectcode) {
        this.projectcode = projectcode;
    }

    public String getBuildDuration() {
        return buildDuration;
    }

    public void setBuildDuration(String buildDuration) {
        this.buildDuration = buildDuration;
    }

    @Override
    public String toString() {
        return "CustomerDetails [customerId=" + customerId + ", contractId=" + contractId + ", geozone=" + geozone
                + ", teamcode=" + teamcode + ", projectcode=" + projectcode + ", buildDuration=" + buildDuration + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((customerId == null) ? 0 : customerId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CustomerDetails other = (CustomerDetails) obj;
        if (customerId == null) {
            if (other.customerId != null)
                return false;
        } else if (!customerId.equals(other.customerId))
            return false;
        return true;
    }

    public String getByField(String field) {
        switch(field) {
        case "customerId":
            return getCustomerId();
        case "contractId":
            return getContractId();
        case "geozone":
            return getGeozone();
        case "teamcode":
            return getTeamcode();
        case "projectcode":
            return getProjectcode();
        case "buildDuration":
            return getBuildDuration();
        default :
            return null;
        }
    }

}
