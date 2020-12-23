package com.evan.wj.bean;

public class YQ_groupID_cnt {
    int gro_id;
    long cnt;

    public YQ_groupID_cnt(int gro_id, long cnt) {
        this.gro_id = gro_id;
        this.cnt = cnt;
    }

    public int getGro_id() {
        return gro_id;
    }

    public void setGro_id(int gro_id) {
        this.gro_id = gro_id;
    }

    public long getCnt() {
        return cnt;
    }

    public void setCnt(long cnt) {
        this.cnt = cnt;
    }
}
