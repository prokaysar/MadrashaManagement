package me.prokawsar.madrashamanagement;

public class Student {
    private String cromic;
    private String dakhela;
    private String name;
    private String fname;
    private String jamat;
    private String phone;
    private String address;
    private String bodingFee;
    private String monthlyFee;

    public Student() {
    }

    public Student(String cromic, String dakhela, String name, String fname, String jamat, String phone, String address, String bodingFee, String monthlyFee) {
        this.cromic = cromic;
        this.dakhela = dakhela;
        this.name = name;
        this.fname = fname;
        this.jamat = jamat;
        this.phone = phone;
        this.address = address;
        this.bodingFee = bodingFee;
        this.monthlyFee = monthlyFee;
    }

    public String getCromic() {
        return cromic;
    }

    public String getDakhela() {
        return dakhela;
    }

    public String getName() {
        return name;
    }

    public String getFname() {
        return fname;
    }

    public String getJamat() {
        return jamat;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getBodingFee() {
        return bodingFee;
    }

    public String getMonthlyFee() {
        return monthlyFee;
    }

    @Override
    public String toString() {
        return "রোল নংঃ "+cromic +"\n"+"নামঃ "+ name;
    }
}
