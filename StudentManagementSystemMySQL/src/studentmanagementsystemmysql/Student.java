/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package studentmanagementsystemmysql;

/**
 *
 * @author SENTHURAN
 */
public class Student {
    private String regNo;
    private String nic;
    private String name;
    private int prfMark;
    private int dbmsMark;

    public Student(String regNo, String nic, String name, int prfMark, int dbmsMark){
            setRegNo(regNo);
            setNic(nic);
            setName(name);
            setPrfMark(prfMark);
            setDbmsMark(dbmsMark);
    }

    public void setRegNo(String regNo){
            this.regNo = regNo;
    }

    public void setNic(String nic){
            this.nic = nic;
    }

    public void setName(String name){
            this.name = name;
    }

    public void setPrfMark(int prfMark){
            this.prfMark = prfMark;
    }

    public void setDbmsMark(int dbmsMark){
            this.dbmsMark = dbmsMark;
    }

    public String getRegNo(){
            return regNo;
    }

    public String getNic(){
            return nic;
    }

    public String getName(){
            return name;
    }

    public int getPrfMark(){
            return prfMark;
    }

    public int getDbmsMark(){
            return dbmsMark;
    }
}
