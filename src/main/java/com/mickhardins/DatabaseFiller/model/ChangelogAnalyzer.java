package com.mickhardins.DatabaseFiller.model;

import com.mickhardins.Deserializer.model.MTGJSONChangelog;

import java.util.Arrays;
import java.util.List;

/**
 * Created by mick on 22/04/17.
 */
public class ChangelogAnalyzer {

    private MTGJSONChangelog changelog;
    private List<String> changedSetsCode;
    private List<String> chagedSetUrls;
    private boolean allSetsChanged;
    private boolean nothingChanged;



    public ChangelogAnalyzer(MTGJSONChangelog changelog) {
        this.changelog = changelog;
        this.allSetsChanged = false;
        this.nothingChanged = true;
        elaborateChanges();
    }

    public MTGJSONChangelog getChangelog() {
        return changelog;
    }

    public void setChangelog(MTGJSONChangelog changelog) {
        this.changelog = changelog;
    }

    public List<String> getChangedSetsCode() {
        return changedSetsCode;
    }

    public void setChangedSetsCode(List<String> changedSetsCode) {
        this.changedSetsCode = changedSetsCode;
    }

    public List<String> getChagedSetUrls() {
        return chagedSetUrls;
    }

    public void setChagedSetUrls(List<String> chagedSetUrls) {
        this.chagedSetUrls = chagedSetUrls;
    }

    public boolean isAllSetsChanged() {
        return allSetsChanged;
    }

    public void setAllSetsChanged(boolean allSetsChanged) {
        this.allSetsChanged = allSetsChanged;
    }

    public boolean isNothingChanged() {
        return nothingChanged;
    }

    public void setNothingChanged(boolean nothingChanged) {
        this.nothingChanged = nothingChanged;
    }

    /**
     * Se il setCode finisce per "-x" dobbiamo aggiornare il relativo set, in caso contrario
     * i cambiamenti interessano solo la versione del set senza extras, che non utilizziamo
     *
     * @param setCode codice del set che stiamo considerando
     * @return true se il setCode finisce per -x e.g "MPS_AKH-x"
     */
    private boolean endsWithX(String setCode) {
        return setCode.endsWith("-x");
    }

    private void elaborateChanges() {

        if(changelog.getNewSetFiles() == null && changelog.getUpdatedSetFiles() == null) {
            return;
        }

        this.nothingChanged = false;
        List<String> changedSet = Arrays.asList(changelog.getUpdatedSetFiles());
        List<String> newSetFiles = Arrays.asList(changelog.getNewSetFiles());

        if (changedSet.contains("ALL-x")) {
            this.allSetsChanged = true;
            return;
        }

        for (String setCode : changedSet) {

            if (endsWithX(setCode)) {
                changedSetsCode.add(setCode);
            }
        }

        for (String setCode : newSetFiles) {

            if (endsWithX(setCode)) {
                changedSetsCode.add(setCode);
            }
        }
    }





}
