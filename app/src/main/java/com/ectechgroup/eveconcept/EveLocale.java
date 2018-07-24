package com.ectechgroup.eveconcept;

import java.util.Date;
import java.util.Map;

public class EveLocale {
    protected String establishmentId;
    protected String networkId;
    protected String establishmentName;
    protected String serviceEndpoint;

    /*
    protected String localUserId;
    protected Date lastJoinDate;

    protected Map<String, String> localeMetadata; // structured data: food/drink menu, map
    protected Map<String, String> promotions;
    protected Map<String, String> users;
*/
    public String getEstablishmentId() {
        return establishmentId;
    }

    public void setEstablishmentId(String establishmentId) {
        this.establishmentId = establishmentId;
    }

    public String getNetworkId() {
        return networkId;
    }

    public void setNetworkId(String networkId) {
        this.networkId = networkId;
    }

    public String getEstablishmentName() {
        return establishmentName;
    }

    public void setEstablishmentName(String establishmentName) {
        this.establishmentName = establishmentName;
    }

    public String getServiceEndpoint() {
        return serviceEndpoint;
    }

    public void setServiceEndpoint(String serviceEndpoint) {
        this.serviceEndpoint = serviceEndpoint;
    }
}
