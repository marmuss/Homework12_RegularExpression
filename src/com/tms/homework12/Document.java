package com.tms.homework12;

import java.util.ArrayList;

public class Document {
    ArrayList<String> docNumbers;
    String email;
    String phoneNumber;

    public Document(ArrayList<String> docNumbers, String email, String phoneNumber) {
        this.docNumbers = docNumbers;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Document{" +
                "docNumbers=" + docNumbers +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
