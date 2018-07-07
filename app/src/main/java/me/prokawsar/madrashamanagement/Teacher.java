package me.prokawsar.madrashamanagement;

public class Teacher {
    private String name;
    private String podobi;
    private String qualification;
    private String phone;
    private String address;
    private String etc;

    public Teacher() {
    }

    public Teacher(String name, String podobi, String qualification, String phone, String address, String etc) {
        this.name = name;
        this.podobi = podobi;
        this.qualification = qualification;
        this.phone = phone;
        this.address = address;
        this.etc = etc;
    }

    public String getName() {
        return name;
    }

    public String getPodobi() {
        return podobi;
    }

    public String getQualification() {
        return qualification;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getEtc() {
        return etc;
    }
}
