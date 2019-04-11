package com.example.root.foodonate;

import android.net.http.SslCertificate;
import android.view.View;

public class DonorItem {
    String dlocation;
    String dname;
    String dnos;

    public String getDlocation() {
        return dlocation;
    }

    public void setDlocation(String dlocation) {
        this.dlocation = dlocation;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getDnos() {
        return dnos;
    }

    public void setDnos(String dnos) {
        this.dnos = dnos;
    }

   /* @Override
    public String toString() {
        return dlocation+" "+dnos+" "+dname;
    }*/
}
