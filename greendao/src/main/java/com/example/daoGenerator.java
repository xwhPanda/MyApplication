package com.example;

import org.greenrobot.greendao.generator.DaoGenerator;
import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Schema;

public class daoGenerator {

    public static void main(String[] args) throws Exception{
        Schema schema = new Schema(1,"com.jiqu.helper");
        new DaoGenerator().generateAll(schema,"C:\\Users\\Administrator\\AndroidStudioProjects\\MyApplication\\Helper\\src\\main\\java-gen");
        addTable(schema);
    }

    private static void addTable(Schema schema){
        Entity entity = schema.addEntity("table");
    }
}
