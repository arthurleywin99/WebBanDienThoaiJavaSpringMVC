/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.viewmodel;

/**
 *
 * @author buing
 */
public class PayDetailsViewModel {
    String OrderUserID, OrderFullName, OrderPhoneNumber, OrderAddress, OrderNote, TransferID;
    boolean IsPaid;

    public PayDetailsViewModel() {
    }

    public PayDetailsViewModel(String OrderUserID, String OrderFullName, String OrderPhoneNumber, String OrderAddress, String OrderNote, String TransferID, boolean IsPaid) {
        this.OrderUserID = OrderUserID;
        this.OrderFullName = OrderFullName;
        this.OrderPhoneNumber = OrderPhoneNumber;
        this.OrderAddress = OrderAddress;
        this.OrderNote = OrderNote;
        this.TransferID = TransferID;
        this.IsPaid = IsPaid;
    }

    public String getOrderUserID() {
        return OrderUserID;
    }

    public void setOrderUserID(String OrderUserID) {
        this.OrderUserID = OrderUserID;
    }

    public String getOrderFullName() {
        return OrderFullName;
    }

    public void setOrderFullName(String OrderFullName) {
        this.OrderFullName = OrderFullName;
    }

    public String getOrderPhoneNumber() {
        return OrderPhoneNumber;
    }

    public void setOrderPhoneNumber(String OrderPhoneNumber) {
        this.OrderPhoneNumber = OrderPhoneNumber;
    }

    public String getOrderAddress() {
        return OrderAddress;
    }

    public void setOrderAddress(String OrderAddress) {
        this.OrderAddress = OrderAddress;
    }

    public String getOrderNote() {
        return OrderNote;
    }

    public void setOrderNote(String OrderNote) {
        this.OrderNote = OrderNote;
    }

    public String getTransferID() {
        return TransferID;
    }

    public void setTransferID(String TransferID) {
        this.TransferID = TransferID;
    }

    public boolean isIsPaid() {
        return IsPaid;
    }

    public void setIsPaid(boolean IsPaid) {
        this.IsPaid = IsPaid;
    }
    
    
}
