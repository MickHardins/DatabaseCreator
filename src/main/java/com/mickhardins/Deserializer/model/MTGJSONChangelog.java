package com.mickhardins.Deserializer.model;

/**
 * Changelog object for mtgjson.com
 *
 */
public class MTGJSONChangelog {

    private String version;
    private String when;
    private long uniqueID;
    private String whenSiteMap;
    private String whenAtom;
    private String[] newSetFiles;
    private String[] updatedSetFiles;
    private String[] changes;

    public MTGJSONChangelog() {

    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getWhen() {
        return when;
    }

    public void setWhen(String when) {
        this.when = when;
    }

    public long getUniqueID() {
        return uniqueID;
    }

    public void setUniqueID(long uniqueID) {
        this.uniqueID = uniqueID;
    }

    public String getWhenSiteMap() {
        return whenSiteMap;
    }

    public void setWhenSiteMap(String whenSiteMap) {
        this.whenSiteMap = whenSiteMap;
    }

    public String getWhenAtom() {
        return whenAtom;
    }

    public void setWhenAtom(String whenAtom) {
        this.whenAtom = whenAtom;
    }

    public String[] getNewSetFiles() {
        return newSetFiles;
    }

    public void setNewSetFiles(String[] newSetFiles) {
        this.newSetFiles = newSetFiles;
    }

    public String[] getUpdatedSetFiles() {
        return updatedSetFiles;
    }

    public void setUpdatedSetFiles(String[] updatedSetFiles) {
        this.updatedSetFiles = updatedSetFiles;
    }

    public String[] getChanges() {
        return changes;
    }

    public void setChanges(String[] changes) {
        this.changes = changes;
    }
}
