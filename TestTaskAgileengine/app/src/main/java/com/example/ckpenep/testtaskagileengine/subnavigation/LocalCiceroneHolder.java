package com.example.ckpenep.testtaskagileengine.subnavigation;

import java.util.HashMap;

import ru.terrakok.cicerone.Cicerone;
import ru.terrakok.cicerone.Router;

/**
 * Created by ckpenep on 23.03.2018.
 */

public class LocalCiceroneHolder {
    private HashMap<String, Cicerone<Router>> containers;

    public LocalCiceroneHolder() {
        containers = new HashMap<>();
    }

    public Cicerone<Router> getCicerone(String containerTag) {
        if (!containers.containsKey(containerTag)) {
            containers.put(containerTag, Cicerone.create());
        }
        return containers.get(containerTag);
    }
}
