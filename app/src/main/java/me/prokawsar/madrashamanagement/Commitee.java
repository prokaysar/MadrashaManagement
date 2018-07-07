package me.prokawsar.madrashamanagement;

public class Commitee {
    private String name;
    private String podobi;
    private String phone;
    private String address;
    private String etc;

    public Commitee() {
    }

    public Commitee(String name, String podobi, String phone, String address, String etc) {
        this.name = name;
        this.podobi = podobi;
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
