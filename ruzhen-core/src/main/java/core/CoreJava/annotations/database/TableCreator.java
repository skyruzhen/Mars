package core.CoreJava.annotations.database;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class TableCreator {
    public static void main(String[] args) throws Exception {
        args = new String[]{"core.CoreJava.annotations.database.Member"};
        for(String className:args){
            Class<?> cl = Class.forName(className);
            DBTable dbTable = cl.getAnnotation(DBTable.class);
            if(dbTable == null){
                System.out.println("No DBTable annotations in class "+className);
                continue;
            }
            String tableName = dbTable.name();
            if(tableName.length() < 1){
                tableName = cl.getName().toUpperCase();
            }
            List<String> columnDefs = new ArrayList<>();
            for (Field filed:cl.getDeclaredFields()){
                String columnName = null;
                Annotation[] anns = filed.getDeclaredAnnotations();
                if(anns.length < 1) continue;
                if(anns[0] instanceof SQLInteger){
                    SQLInteger sInt = (SQLInteger) anns[0];
                    if(sInt.name().length() < 1){
                        columnName = filed.getName().toUpperCase();
                    }else{
                        columnName = sInt.name();
                    }
                    columnDefs.add(columnName + " INT "+getConstraints(sInt.constraints()));
                }
                if(anns[0] instanceof SQLString){
                    SQLString sString = (SQLString) anns[0];
                    if(sString.name().length() < 1)
                        columnName = filed.getName().toUpperCase();
                    else
                        columnName = sString.name();
                    columnDefs.add(columnName + " VARCAHR("+sString.value()+")" + getConstraints(sString.constraints()));
                }
                StringBuilder createCommand = new StringBuilder("CREATE TABLE "+tableName+"(");
                for(String columnDef:columnDefs)
                    createCommand.append("\n    "+columnDef+",");
                String tableCreate = createCommand.substring(0, createCommand.length() -1) + ");";
                System.out.println("Table Creation SQL for "+className+ " is :\n"+tableCreate);
            }
        }
    }

    private static String getConstraints(Constraints con){
        String constraints = "";
        if(!con.allowNull())
            constraints += " NOT NULL";
        if(con.primaryKey())
            constraints += " PRIMARY KEY";
        if(con.unique())
            constraints += " UNIQUE";
        return constraints;
    }

}
