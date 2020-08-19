package com.emp.EmployeeAddressManager.service;

public interface SequenceService {


    public long getNextSequence(String key) ;

    public int getNextIntSequence(String key) ;

    public void resetSequence(String key);
    public String userSequence(String key);
}

