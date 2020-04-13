/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;

/**
 *
 * @author Marcel
 */
public class Uppercase implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer iduppercase;
    private String content;

    public Uppercase() {
    }

    public Uppercase(Integer iduppercase) {
        this.iduppercase = iduppercase;
    }

    public Uppercase(Integer iduppercase, String content) {
        this.iduppercase = iduppercase;
        this.content = content;
    }

    public Integer getIduppercase() {
        return iduppercase;
    }

    public void setIduppercase(Integer iduppercase) {
        this.iduppercase = iduppercase;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iduppercase != null ? iduppercase.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Uppercase)) {
            return false;
        }
        Uppercase other = (Uppercase) object;
        if ((this.iduppercase == null && other.iduppercase != null) || (this.iduppercase != null && !this.iduppercase.equals(other.iduppercase))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return content;
    }
    
}
