/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package studentmanagementsystemmysql;
import java.sql.*;
import javax.swing.*;
import java.util.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.*;
/**
 *
 * @author SENTHURAN
 */
public class MainOperations {
    
    public static String[] batchComboBoxItem() throws ClassNotFoundException, SQLException{
        String[] batchNameArray = {"---Select a batch---"};
        String SQL = "SELECT batch_name FROM batch WHERE status=1;";
        Statement stm = DBConnection.getInstance().getConnection().createStatement();
        ResultSet rst = stm.executeQuery(SQL);
        while(rst.next()){
            String[] tempBatchNameArray = new String[batchNameArray.length+1];
            for(int i=0 ; i<batchNameArray.length ; i++){
                tempBatchNameArray[i]=batchNameArray[i];
            }
            tempBatchNameArray[batchNameArray.length]=rst.getString("batch_name");
            batchNameArray=tempBatchNameArray;
        }
        return batchNameArray;
    }
    
    public static int countStudent(String batchName) throws ClassNotFoundException, SQLException{
        String SQL = "SELECT COUNT(*) AS student_count FROM student WHERE SUBSTRING(student_id, 5, 3)="+batchName+";";
        Statement stm = DBConnection.getInstance().getConnection().createStatement();
        ResultSet rst = stm.executeQuery(SQL);
        rst.next();
        return rst.getInt("student_count")+1;
    }
    
    
    public static int addStudent(Student student) throws ClassNotFoundException, SQLException{
        try{
            String SQL = "INSERT INTO student VALUES ('"+student.getRegNo()+"','"+student.getNic()+"','"+student.getName()+"',"+student.getPrfMark()+","+student.getDbmsMark()+");";
            Statement stm = DBConnection.getInstance().getConnection().createStatement();
            stm.executeUpdate(SQL);
            return 0;
        }catch(SQLIntegrityConstraintViolationException ex){
            if(ex.getMessage().contains("PRIMARY")){
                return 1;
            }else{
                return 2;
            }
        }
    }
    
    public static boolean isIdExists(String regNo) throws ClassNotFoundException, SQLException{
        String SQL = "SELECT * FROM student WHERE student_id='"+regNo+"';";
        Statement stm=DBConnection.getInstance().getConnection().createStatement();
        ResultSet rst = stm.executeQuery(SQL);
        return rst.next();
    }
    
    public static String findNic(String regNo) throws ClassNotFoundException, SQLException{
        String SQL = "SELECT nic_no FROM student WHERE student_id='"+regNo+"';";
        Statement stm = DBConnection.getInstance().getConnection().createStatement();
        ResultSet rst = stm.executeQuery(SQL);
        rst.next();
        String nic=rst.getString("nic_no");
        return nic;
    }
    
    public static String findName(String regNo) throws ClassNotFoundException, SQLException{
        String SQL = "SELECT name FROM student WHERE student_id='"+regNo+"';";
        Statement stm = DBConnection.getInstance().getConnection().createStatement();
        ResultSet rst = stm.executeQuery(SQL);
        rst.next();
        String name = rst.getString("name");
        return name;
    }
    
    public static int findPrfMark(String regNo) throws ClassNotFoundException, SQLException{
        String SQL = "SELECT prf_mark FROM student WHERE student_id='"+regNo+"';";
        Statement stm = DBConnection.getInstance().getConnection().createStatement();
        ResultSet rst = stm.executeQuery(SQL);
        rst.next();
        int prfMark = rst.getInt("prf_mark");
        return prfMark;
    }
    
    public static int findDbmsMark(String regNo) throws ClassNotFoundException, SQLException{
        String SQL = "SELECT dbms_mark FROM student WHERE student_id='"+regNo+"';";
        Statement stm = DBConnection.getInstance().getConnection().createStatement();
        ResultSet rst = stm.executeQuery(SQL);
        rst.next();
        int dbmsMark = rst.getInt("dbms_mark");
        return dbmsMark;
    }
    
    public static boolean updateStudent(String regNo, String name, String nic) throws ClassNotFoundException, SQLException{
        String SQL = "UPDATE student SET name='"+name+"', nic_no='"+nic+"' WHERE student_id='"+regNo+"';";
        Statement stm = DBConnection.getInstance().getConnection().createStatement();
        int res = stm.executeUpdate(SQL);
        return res>0;
    }
    
    public static boolean deleteStudent(String regNo) throws ClassNotFoundException, SQLException{
        String SQL = "DELETE FROM student WHERE student_id='"+regNo+"';";
        Statement stm = DBConnection.getInstance().getConnection().createStatement();
        int res = stm.executeUpdate(SQL);
        return res>0;
    }
    
    public static boolean isBatchExists(String batchName) throws ClassNotFoundException, SQLException{
        String SQL = "SELECT * FROM batch WHERE batch_name='"+batchName+"';";
        Statement stm = DBConnection.getInstance().getConnection().createStatement();
        ResultSet rst = stm.executeQuery(SQL);
        return rst.next();
    }
    
    public static boolean addBatch(String batchName, int maxStudentCount) throws ClassNotFoundException, SQLException{
        String SQL = "INSERT INTO batch VALUES('"+batchName+"',1,"+maxStudentCount+");";
        Statement stm = DBConnection.getInstance().getConnection().createStatement();
        int res = stm.executeUpdate(SQL);
        return res>0;
    }
    
    public static String findStudentCount(String batchName) throws ClassNotFoundException, SQLException{
        String SQL = "SELECT COUNT(*) AS student_count FROM student WHERE SUBSTRING(student_id, 5, 3)='"+batchName+"';";
        Statement stm = DBConnection.getInstance().getConnection().createStatement();
        ResultSet rst = stm.executeQuery(SQL);
        rst.next();
        return rst.getString("student_count");
    }
    
    public static int findBatchStatus(String batchName) throws ClassNotFoundException, SQLException{
        String SQL = "SELECT status FROM batch WHERE batch_name='"+batchName+"';";
        Statement stm = DBConnection.getInstance().getConnection().createStatement();
        ResultSet rst = stm.executeQuery(SQL);
        rst.next();
        return rst.getInt("status");
    }
    
    public static String findBatchMaxCount(String batchName) throws ClassNotFoundException, SQLException{
        String SQL = "SELECT max_count FROM batch WHERE batch_name = '"+batchName+"';";
        Statement stm = DBConnection.getInstance().getConnection().createStatement();
        ResultSet rst = stm.executeQuery(SQL);
        rst.next();
        return rst.getString("max_count");
    }
    
    public static boolean updateBatch(String batchName, int status , int batchMaxCount) throws ClassNotFoundException, SQLException{
        String SQL = "UPDATE batch SET status="+status+", max_count="+batchMaxCount+" WHERE batch_name='"+batchName+"';";
        Statement stm = DBConnection.getInstance().getConnection().createStatement();
        int rst = stm.executeUpdate(SQL);
        return rst>0;
    }
    
    public static DefaultTableModel viewBatch() throws ClassNotFoundException, SQLException{
        String[] columnNames = {"No.", "Batch No.", "Student Count", "Max Student Count", "Status"};
	DefaultTableModel dtm = new DefaultTableModel(columnNames , 0);
        int loopCount = 1;
       
        String SQL = "SELECT batch_name, max_count, status FROM batch;";
        Statement stm = DBConnection.getInstance().getConnection().createStatement();
        ResultSet rst = stm.executeQuery(SQL);
        while(rst.next()){
            String batchName=rst.getString("batch_name");
            int batchMaxCount=rst.getInt("max_count");
            int batchStatus=rst.getInt("status");
            Object[] rowData={loopCount++ , batchName , findStudentCount(batchName) , batchMaxCount ,batchStatus==1 ? "Enrollment Open" : "Enrollment Close" };
            dtm.addRow(rowData);
        }
        return dtm;
    }
    
    public static boolean prfMarkUpdate(String regNo , int prfMark) throws ClassNotFoundException, SQLException{
        String SQL = "UPDATE student SET prf_mark = "+prfMark+" WHERE student_id = '"+regNo+"'  ;";
        Statement stm = DBConnection.getInstance().getConnection().createStatement();
        int rst = stm.executeUpdate(SQL);
        return rst>0;
    }
    
    public static boolean dbmsMarkUpdate(String regNo , int dbmsMark) throws ClassNotFoundException, SQLException{
        String SQL = "UPDATE student SET dbms_mark = "+dbmsMark+" WHERE student_id = '"+regNo+"'  ;";
        Statement stm = DBConnection.getInstance().getConnection().createStatement();
        int rst = stm.executeUpdate(SQL);
        return rst>0;
    }
    
    public static DefaultTableModel allStudentReport() throws ClassNotFoundException, SQLException{
        String[] columnNames = {"No.", "Registration No.", "NIC No.", "Student Name", "PRF Mark", "DBMS Mark", "GPA"};
        DefaultTableModel dtm = new DefaultTableModel(columnNames , 0);
        
        String SQL = "SELECT * FROM student ORDER BY name ASC;";
        Statement stm = DBConnection.getInstance().getConnection().createStatement();
        ResultSet rst = stm.executeQuery(SQL);
        int loopCount=1;
        while(rst.next()){
            String id = rst.getString("student_id");
            String nic = rst.getString("nic_no");
            String name = rst.getString("name");
            int prfMark = rst.getInt("prf_mark");
            int dbmsMark = rst.getInt("dbms_mark");
            
            Object[] rowData = {loopCount++, id, nic, name, prfMark==-1 ? "absent" : prfMark==-2 ? "not conducted" : prfMark, dbmsMark==-1 ? "absent" : dbmsMark==-2 ? "not conducted" : dbmsMark, String.format("%.3f",calculateGPA(prfMark, dbmsMark))};
            dtm.addRow(rowData);
        }
        return dtm;
    }
    
    public static DefaultTableModel industryEligiblestudentReport() throws ClassNotFoundException, SQLException{
        String[] columnNames = {"No.", "Registration No.", "NIC No.", "Student Name", "PRF Mark", "DBMS Mark", "GPA"};
        DefaultTableModel dtm = new DefaultTableModel(columnNames , 0); 
    
        String SQL = "SELECT * FROM student WHERE prf_mark>50 AND dbms_mark>50;";
        Statement stm = DBConnection.getInstance().getConnection().createStatement();
        ResultSet rst = stm.executeQuery(SQL);
        int loopCount=1;
        while(rst.next()){
            int prfMark = rst.getInt("prf_mark");
            int dbmsMark = rst.getInt("dbms_mark");
            double gpa = calculateGPA(prfMark, dbmsMark);
            if(gpa>3.25){
                String id = rst.getString("student_id");
                String nic = rst.getString("nic_no");
                String name = rst.getString("name");

                Object[] rowData = {loopCount++, id, nic, name, prfMark==-1 ? "absent" : prfMark==-2 ? "not conducted" : prfMark, dbmsMark==-1 ? "absent" : dbmsMark==-2 ? "not conducted" : dbmsMark, String.format("%.3f",gpa)};
                dtm.addRow(rowData);
            }
        }
        return dtm;
    }
    
    public static DefaultTableModel batchWiseReport(String batchName) throws ClassNotFoundException, SQLException{
        String[] columnNames = {"No.", "Registration No.","NIC No.", "Student Name", "PRF Mark", "DBMS Mark", "GPA"};
        DefaultTableModel dtm = new DefaultTableModel(columnNames , 0);
        
        String SQL = "SELECT * FROM student WHERE SUBSTRING(student_id, 5, 3)='"+batchName+"';";
        Statement stm = DBConnection.getInstance().getConnection().createStatement();
        ResultSet rst = stm.executeQuery(SQL);
        int loopCount=1;
        while(rst.next()){
            String id = rst.getString("student_id");
            String nic = rst.getString("nic_no");
            String name = rst.getString("name");
            int prfMark = rst.getInt("prf_mark");
            int dbmsMark = rst.getInt("dbms_mark");
            
            Object[] rowData = {loopCount++, id, nic, name, prfMark==-1 ? "absent" : prfMark==-2 ? "not conducted" : prfMark, dbmsMark==-1 ? "absent" : dbmsMark==-2 ? "not conducted" : dbmsMark, String.format("%.3f",calculateGPA(prfMark, dbmsMark))};
            dtm.addRow(rowData);
        }
        return dtm;
    }
    
    public static String[] batchesArray() throws ClassNotFoundException, SQLException{
        String[] batchNameArray = new String[1];
        
        String SQL = "SELECT batch_name FROM batch;";
        Statement stm = DBConnection.getInstance().getConnection().createStatement();
        ResultSet rst = stm.executeQuery(SQL);
        while(rst.next()){
            if(batchNameArray[0]==null){
                batchNameArray[0]=rst.getString("batch_name");
            }else{
                String[] tempBatchNameArray = new String[batchNameArray.length+1];
                for(int i=0 ; i<batchNameArray.length ; i++){
                    tempBatchNameArray[i]=batchNameArray[i];
                }
                tempBatchNameArray[batchNameArray.length] = rst.getString("batch_name");
                batchNameArray=tempBatchNameArray;
            }
        }
        return batchNameArray;
    }
    
    private static double findGpaValuesFromMarks(int marks){
            if(marks>=90)      return 4.25;
            else if(marks>=80) return 4.00;
            else if(marks>=75) return 3.70;
            else if(marks>=70) return 3.30;
            else if(marks>=65) return 3.00;
            else if(marks>=60) return 2.70;
            else if(marks>=55) return 2.30;
            else if(marks>=50) return 2.00;
            else if(marks>=45) return 1.70;
            else if(marks>=40) return 1.30;
            else if(marks>=30) return 1.00;
            else if(marks>=20) return 0.70;
            else               return 0.25;
    }

    //finding GPA value
    public static double calculateGPA(int prfMark, int dbmsMark){
            if(prfMark<0 && dbmsMark<0){
                    return 0.0;
            }
            if(prfMark>=0 && dbmsMark>=0){
                     return ((double)(findGpaValuesFromMarks(prfMark) + findGpaValuesFromMarks(dbmsMark))/2);
            }
            if(prfMark<0 && dbmsMark>=0){
                     return ((double)(findGpaValuesFromMarks(dbmsMark))/2);
            }
            if(prfMark>=0 && dbmsMark<0){
                     return ((double)(findGpaValuesFromMarks(prfMark))/2);
            }
            return 0.0;
    } 
    
}
