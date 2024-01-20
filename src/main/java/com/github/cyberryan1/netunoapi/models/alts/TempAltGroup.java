package com.github.cyberryan1.netunoapi.models.alts;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class TempAltGroup {

    private Set<String> ipList = new HashSet<>();
    private Set<UUID> uuidList = new HashSet<>();

    public TempAltGroup() {}

    public TempAltGroup( Set<String> ipList, Set<UUID> uuidList ) {
        this.ipList = ipList;
        this.uuidList = uuidList;
    }

    public Set<String> getIpList() {
        return ipList;
    }

    public Set<UUID> getUuidList() {
        return uuidList;
    }
}