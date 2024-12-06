package org.sulaiman;

import java.util.ArrayList;

public class DatabaseList<FromDataBase> extends ArrayList<FromDataBase> {
    public FromDataBase getByUid(int uid) {
        for (FromDataBase object : this) {
            int uidOfElement = switch (object.getClass().getName()) {
                case "org.sulaiman.Subject" -> ((Subject) object).getUid();
                case "org.sulaiman.ClassRoom" -> ((ClassRoom) object).getUid();
                case "org.sulaiman.Grade" -> ((Grade) object).getUid();
                case "org.sulaiman.Student" -> ((Student) object).getUid();
                case "org.sulaiman.Teacher" -> ((Teacher) object).getUid();
                default -> 0;
            };

            if(uidOfElement == uid) {
                return object;
            }
        }
        return null;
    }
}
