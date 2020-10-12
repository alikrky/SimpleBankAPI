package com.karakaya.bank.SimpleBankAPI.Auxiliary;

import org.hibernate.collection.internal.PersistentBag;
import java.util.ArrayList;

public class HibernateFilter {

    public static Object filter(Object instance) {
        if (instance == null) {
            return instance;
        }
        if (instance instanceof PersistentBag) {
            ArrayList<Object> arrayList = new ArrayList<Object>();
            PersistentBag persBag = (PersistentBag) instance;
            if (persBag.wasInitialized()) {
                arrayList.addAll(persBag);
            }
            return arrayList;
        }
        return instance;
    }
}
