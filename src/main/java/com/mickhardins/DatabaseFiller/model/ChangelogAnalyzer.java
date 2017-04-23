package com.mickhardins.DatabaseFiller.model;

import com.mickhardins.DatabaseFiller.Utils;
import com.mickhardins.Deserializer.model.MTGJSONChangelog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by mick on 22/04/17.
 */
public class ChangelogAnalyzer {

    private MTGJSONChangelog changelog;
    private List<String> changedSetCodes;
    private boolean allSetsChanged;
    private boolean nothingChanged;



    public ChangelogAnalyzer(MTGJSONChangelog changelog) {
        this.changelog = changelog;
        this.allSetsChanged = false;
        this.nothingChanged = true;
        changedSetCodes = new ArrayList<>();
        elaborateChanges();
    }

    public MTGJSONChangelog getChangelog() {
        return changelog;
    }

    public void setChangelog(MTGJSONChangelog changelog) {
        this.changelog = changelog;
    }

    public List<String> getChangedSetCodes() {
        return changedSetCodes;
    }

    public void setChangedSetCodes(List<String> changedSetCodes) {
        this.changedSetCodes = changedSetCodes;
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
        String[] updatedSetCodes = changelog.getUpdatedSetFiles();
        String[] newSetCodes = changelog.getNewSetFiles();
        List<String> changedSet;
        List<String> newSetFiles;

        if (updatedSetCodes == null) {
             changedSet = new ArrayList<>();
        }
        else {
            changedSet = Arrays.asList(changelog.getUpdatedSetFiles());
        }

        if (newSetCodes == null) {
            newSetFiles = new ArrayList<>();
        }
        else {
            newSetFiles = Arrays.asList(changelog.getNewSetFiles());
        }

        if (changedSet.contains("ALL-x")) {
            this.allSetsChanged = true;
            return;
        }

        for (String setCode : changedSet) {

            if (endsWithX(setCode)) {
                changedSetCodes.add(setCode);
            }
        }

        for (String setCode : newSetFiles) {

            if (endsWithX(setCode)) {
                changedSetCodes.add(setCode);
            }
        }
    }







}
