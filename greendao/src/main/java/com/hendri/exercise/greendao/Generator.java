package com.hendri.exercise.greendao;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

/**
 * Created by hendri on 11/23/2016.
 */

public class Generator {
    public static void main(String[] args) {
        Schema schema = new Schema(1, "com.hendri.exercise.testproject.database");
        generateDtbPost(schema);
        try {
            new DaoGenerator().generateAll(schema, "app/src/main/java");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void generateDtbPost(Schema schema) {
        Entity dtb_user_type = schema.addEntity("DtbTodo");
        dtb_user_type.addLongProperty("id").primaryKey();
        dtb_user_type.addLongProperty("userId");
        dtb_user_type.addStringProperty("title");
        dtb_user_type.addBooleanProperty("completed");
    }
}