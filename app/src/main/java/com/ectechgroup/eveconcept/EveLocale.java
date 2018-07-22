package com.ectechgroup.eveconcept;

import java.util.Date;
import java.util.Map;

public class EveLocale {
    protected String establishmentId;
    protected String networkId;
    protected String establishmentName;

    protected String localUserId;
    protected Date lastJoinDate;

    protected Map<String, String> localeMetadata; // structured data: food/drink menu, map
    protected Map<String, String> promotions;
    protected Map<String, String> users;

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

    public String getLocalUserId() {
        return localUserId;
    }

    public void setLocalUserId(String localUserId) {
        this.localUserId = localUserId;
    }

    public Date getLastJoinDate() {
        return lastJoinDate;
    }

    public void setLastJoinDate(Date lastJoinDate) {
        this.lastJoinDate = lastJoinDate;
    }

    public Map<String, String> getLocaleMetadata() {
        return localeMetadata;
    }

    public void setLocaleMetadata(Map<String, String> localeMetadata) {
        this.localeMetadata = localeMetadata;
    }

    public Map<String, String> getPromotions() {
        return promotions;
    }

    public void setPromotions(Map<String, String> promotions) {
        this.promotions = promotions;
    }

    public Map<String, String> getUsers() {
        return users;
    }

    public void setUsers(Map<String, String> users) {
        this.users = users;
    }
}
