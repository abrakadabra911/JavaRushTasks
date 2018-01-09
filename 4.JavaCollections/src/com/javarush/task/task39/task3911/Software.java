package com.javarush.task.task39.task3911;

import java.util.*;

public class Software {
    int currentVersion;

    private Map<Integer, String> versionHistoryMap = new LinkedHashMap<>();

    public void addNewVersion(int version, String description) {
        if (version > currentVersion) {
            versionHistoryMap.put(version, description);
            currentVersion = version;
        }
    }

    public int getCurrentVersion() {
        return currentVersion;
    }

    public Map<Integer, String> getVersionHistoryMap() {
        return Collections.unmodifiableMap(versionHistoryMap);
    }

    public boolean rollback(int rollbackVersion) {
        boolean isRollbackVersionPresent = false;
        Iterator it = versionHistoryMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            int iterableVersion = (int)pair.getKey();
            if(iterableVersion == rollbackVersion) isRollbackVersionPresent = true;
        }
        if (!isRollbackVersionPresent) return false;
        currentVersion = rollbackVersion;
        Iterator it2 = versionHistoryMap.entrySet().iterator();
        List<Integer> listOfVersionsForDelete = new ArrayList<>();
        while (it2.hasNext()) {
            Map.Entry pair = (Map.Entry)it2.next();
            int iterableVersion = (int)pair.getKey();
            if(iterableVersion > rollbackVersion) listOfVersionsForDelete.add(iterableVersion);
        }
        for(Integer x: listOfVersionsForDelete) {
            versionHistoryMap.remove(x);
        }
        return true;
    }
}
