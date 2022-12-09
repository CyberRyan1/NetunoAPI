package com.github.cyberryan1.netunoapi.models.alts;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class NAltGroup {

    private int groupId = -1;
    private List<NAltEntry> entries = new ArrayList<>();

    public NAltGroup( int groupId ) {
        this.groupId = groupId;
    }

    public NAltGroup() {}

    public void addEntry( NAltEntry entry ) {
        entries.add( entry );
    }

    public boolean containsEntry( NAltEntry search ) {
        for ( NAltEntry entry : entries ) {
            if ( entry.getUuid().equals( search.getUuid() ) && entry.getIp().equals( search.getIp() ) ) {
                return true;
            }
        }
        return false;
    }

    public void removeEntry( NAltEntry entry ) {
        entries.remove( entry );
    }

    public List<NAltEntry> getEntries() {
        return entries;
    }

    public List<UUID> getUuids() {
        return entries.stream()
                .map( entry -> UUID.fromString( entry.getUuid() ) )
                .distinct()
                .collect( Collectors.toList() );
    }

    public List<String> getIps() {
        return entries.stream()
                .map( NAltEntry::getIp )
                .distinct()
                .collect( Collectors.toList() );
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId( int groupId ) {
        this.groupId = groupId;
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;

        NAltGroup that = ( NAltGroup ) o;

        if ( getGroupId() != that.getGroupId() ) return false;
        return getEntries() != null ? getEntries().equals( that.getEntries() ) : that.getEntries() == null;
    }

    @Override
    public int hashCode() {
        int result = getGroupId();
        result = 31 * result + ( getEntries() != null ? getEntries().hashCode() : 0 );
        return result;
    }
}