package com.aoslec.haezzo.Bean;

public class HaezulgaeListBean {
    private String dnumber;
    private String dtitle;
    private String dimage;
    private String dcontent;
    private String ddate;
    private String dtime;
    private String dplace;
    private String unumber;
    private String hnumber;

    //---------------------Constructor
    public HaezulgaeListBean(String dnumber, String dtitle, String dimage, String dcontent, String ddate, String dtime, String dplace, String unumber, String hnumber) {
        this.dnumber = dnumber;
        this.dtitle = dtitle;
        this.dimage = dimage;
        this.dcontent = dcontent;
        this.ddate = ddate;
        this.dtime = dtime;
        this.dplace = dplace;
        this.unumber = unumber;
        this.hnumber = hnumber;
    }

    //---------------------Getter & Setter


    public String getDnumber() {
        return dnumber;
    }

    public void setDnumber(String dnumber) {
        this.dnumber = dnumber;
    }

    public String getDtitle() {
        return dtitle;
    }

    public void setDtitle(String dtitle) {
        this.dtitle = dtitle;
    }

    public String getDimage() {
        return dimage;
    }

    public void setDimage(String dimage) {
        this.dimage = dimage;
    }

    public String getDcontent() {
        return dcontent;
    }

    public void setDcontent(String dcontent) {
        this.dcontent = dcontent;
    }

    public String getDdate() {
        return ddate;
    }

    public void setDdate(String ddate) {
        this.ddate = ddate;
    }

    public String getDtime() {
        return dtime;
    }

    public void setDtime(String dtime) {
        this.dtime = dtime;
    }

    public String getDplace() {
        return dplace;
    }

    public void setDplace(String dplace) {
        this.dplace = dplace;
    }

    public String getUnumber() {
        return unumber;
    }

    public void setUnumber(String unumber) {
        this.unumber = unumber;
    }

    public String getHnumber() {
        return hnumber;
    }

    public void setHnumber(String hnumber) {
        this.hnumber = hnumber;
    }
}//