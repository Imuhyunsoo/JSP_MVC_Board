package edu.global.ex.Dto;

/*
 이름       널?       유형
-------- -------- -------------
BID      NOT NULL NUMBER(4)
BNAME             VARCHAR2(20)
BTITLE            VARCHAR2(100)
BCONTENT          VARCHAR2(300)
BDATE             DATE
BHIT              NUMBER(4)
BGROUP            NUMBER(4)
BSTEP             NUMBER(4)
BINDENT           NUMBER(4)
*/

import java.sql.Timestamp;

public class BDto {

    private int BId;
    private String BName;
    private String BTitle;
    private String BContent;
    private Timestamp BDate;
    private int BHit;
    private int BGroup;
    private int BStep;
    private int BIndent;

    public BDto() {}

    public BDto(int BId, String BName, String BTitle, String BContent, Timestamp BDate, int BHit, int BGroup, int BStep, int BIndent) {
        this.BId = BId;
        this.BName = BName;
        this.BTitle = BTitle;
        this.BContent = BContent;
        this.BDate = BDate;
        this.BHit = BHit;
        this.BGroup = BGroup;
        this.BStep = BStep;
        this.BIndent = BIndent;
    }


    public int getBId() {
        return BId;
    }

    public String getBName() {
        return BName;
    }

    public String getBTitle() {
        return BTitle;
    }

    public String getBContent() {
        return BContent;
    }

    public Timestamp getBDate() {
        return BDate;
    }

    public int getBHit() {
        return BHit;
    }

    public int getBGroup() {
        return BGroup;
    }

    public int getBStep() {
        return BStep;
    }

    public int getBIndent() {
        return BIndent;
    }

    public void setBId(int BId) {
        this.BId = BId;
    }

    public void setBName(String BName) {
        this.BName = BName;
    }

    public void setBTitle(String BTitle) {
        this.BTitle = BTitle;
    }

    public void setBContent(String BContent) {
        this.BContent = BContent;
    }

    public void setBDate(Timestamp BDate) {
        this.BDate = BDate;
    }

    public void setBHit(int BHit) {
        this.BHit = BHit;
    }

    public void setBGroup(int BGroup) {
        this.BGroup = BGroup;
    }

    public void setBStep(int BStep) {
        this.BStep = BStep;
    }

    public void setBIndent(int BIndent) {
        this.BIndent = BIndent;
    }
}
