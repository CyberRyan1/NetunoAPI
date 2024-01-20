package com.github.cyberryan1.netunoapi.models.alts;


import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class TempAltGroup {

    private int groupId = -1;
    private Set<String> ipList = new HashSet<>();
    private Set<UUID> uuidList = new HashSet<>();

    public TempAltGroup( int groupId ) {
        this.groupId = groupId;
    }

    public TempAltGroup() {}

    public Set<String> getIpList() {
        return ipList;
    }

    public Set<UUID> getUuidList() {
        return uuidList;
    }

    public int getGroupId() { return groupId; }

    public void setGroupId( int id ) { this.groupId = id; }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;

        TempAltGroup that = ( TempAltGroup ) o;

        if ( getGroupId() != that.getGroupId() ) return false;
        if ( getIpList() != null ? !getIpList().equals( that.getIpList() ) : that.getIpList() != null ) return false;
        return getUuidList() != null ? getUuidList().equals( that.getUuidList() ) : that.getUuidList() == null;
    }

    @Override
    public int hashCode() {
        int result = getGroupId();
        result = 31 * result + ( getIpList() != null ? getIpList().hashCode() : 0 );
        result = 31 * result + ( getUuidList() != null ? getUuidList().hashCode() : 0 );
        return result;
    }
}