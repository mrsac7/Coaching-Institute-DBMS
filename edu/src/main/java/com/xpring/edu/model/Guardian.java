package com.xpring.edu.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="guardian")
public class Guardian {
    
    @Id
    private int studentID;
    private String gfirstName;
    private String gmiddleName;
    private String glastName;
    private String gcontactNo;

   
    public Guardian() {
        
    }
    

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public String getGfirstName() {
        return gfirstName;
    }

    public void setGfirstName(String gfirstName) {
        this.gfirstName = gfirstName;
    }

    public String getGmiddleName() {
        return gmiddleName;
    }

    public void setGmiddleName(String gmiddleName) {
        this.gmiddleName = gmiddleName;
    }

    public String getGlastName() {
        return glastName;
    }

    public void setGlastName(String glastName) {
        this.glastName = glastName;
    }

    public String getGcontactNo() {
        return gcontactNo;
    }

    public void setGcontactNo(String gcontactNo) {
        this.gcontactNo = gcontactNo;
    }

    public Guardian(int studentID, String gfirstName, String gmiddleName, String glastName,
            String gcontactNo) {
        this.studentID = studentID;
        this.gfirstName = gfirstName;
        this.gmiddleName = gmiddleName;
        this.glastName = glastName;
        this.gcontactNo = gcontactNo;
    }

}
