package com.github.cyberryan1.netunoapi.reports;

public class ANetunoReportData {

    private int id = -1;
    private String playerUuid = null;
    private String reporterUuid = null;
    private long timestamp = -1L;
    private String reason = null;

    public ANetunoReportData( int id, String playerUuid, String reporterUuid, long timestamp, String reason ) {
        this.id = id;
        this.playerUuid = playerUuid;
        this.reporterUuid = reporterUuid;
        this.timestamp = timestamp;
        this.reason = reason;
    }

    public ANetunoReportData() {}

    public int getId() {
        return id;
    }

    public void setId( int id ) {
        this.id = id;
    }

    public String getPlayerUuid() {
        return playerUuid;
    }

    public void setPlayerUuid( String playerUuid ) {
        this.playerUuid = playerUuid;
    }

    public String getReporterUuid() {
        return reporterUuid;
    }

    public void setReporterUuid( String reporterUuid ) {
        this.reporterUuid = reporterUuid;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp( long timestamp ) {
        this.timestamp = timestamp;
    }

    public String getReason() {
        return reason;
    }

    public void setReason( String reason ) {
        this.reason = reason;
    }
}