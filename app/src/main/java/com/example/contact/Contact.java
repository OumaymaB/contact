package com.example.contact;

public class Contact {
    private long id;
    private String first_Name;
    private String last_Name;
    private String job;
    private String phone;
    private String email;

    public Contact(long id, String first_Name, String last_Name, String job, String phone, String email) {
        this.id = id;
        this.first_Name = first_Name;
        this.last_Name = last_Name;
        this.job = job;
        this.phone = phone;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirst_Name() {
        return first_Name;
    }

    public void setFirst_Name(String first_Name) {
        this.first_Name = first_Name;
    }

    public String getLast_Name() {
        return last_Name;
    }

    public void setLast_Name(String last_Name) {
        this.last_Name = last_Name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
