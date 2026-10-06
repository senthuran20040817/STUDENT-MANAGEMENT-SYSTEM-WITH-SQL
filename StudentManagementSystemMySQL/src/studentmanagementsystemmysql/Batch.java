/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package studentmanagementsystemmysql;

/**
 *
 * @author SENTHURAN
 */
public class Batch {
    // Batch status
    public static final int ENROLLMENTOPEN = 1;
    public static final int ENROLLMENTCLOSED = 0;
	
    private int batchName;
    private int batchStatus;
    private int batchMaxCount;

    public Batch(int batchName, int batchStatus, int batchMaxCount){
        setBatchName(batchName);
        setBatchStatus(batchStatus);
        setBatchMaxCount(batchMaxCount);
    }

    public void setBatchName(int batchName){
        this.batchName = batchName;
    }

    public void setBatchStatus(int batchStatus){
        this.batchStatus = batchStatus;
    }

    public void setBatchMaxCount(int batchMaxCount){
        this.batchMaxCount = batchMaxCount;
    }

    public int getBatchName(){
        return batchName;
    }

    public int getBatchStatus(){
        return batchStatus;
    }

    public int getBatchMaxCount(){
        return batchMaxCount;
    }
}
