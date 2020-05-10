package com.anmolvedant.javaproj.domain;

import java.sql.Timestamp;
import java.util.Date;

public class orderdb1 extends orderdb {
    public void setTime(Date tim)
    {
        super.setTime(new Timestamp(tim.getTime()));
    }
}
