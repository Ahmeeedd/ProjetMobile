package tn.esprit.project.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.Date;

@Entity
public class Enfant {
    @PrimaryKey(autoGenerate = true)
    private long enfantId;
    @ColumnInfo
    private double poids;
    @ColumnInfo
    private int taille;
    @ColumnInfo
    private String prenom;
    @ColumnInfo
    private int age;
    @ColumnInfo
    @TypeConverters(Converter.class)
    private Date date_naiss;
    @ColumnInfo
    private int parentId;


    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public Enfant(long enfantId, double poids, int taille, String prenom, int age, Date date_naiss) {
        this.enfantId = enfantId;
        this.poids = poids;
        this.taille = taille;
        this.prenom = prenom;
        this.age = age;
        this.date_naiss = date_naiss;
    }

    public Enfant(double poids, int taille, String prenom, int age, Date date_naiss) {
        this.poids = poids;
        this.taille = taille;
        this.prenom = prenom;
        this.age = age;
        this.date_naiss = date_naiss;
    }
    public Enfant(double poids, int taille, String prenom, int age, Date date_naiss,int parentId) {
        this.poids = poids;
        this.taille = taille;
        this.prenom = prenom;
        this.age = age;
        this.parentId=parentId;
        this.date_naiss = date_naiss;
    }
    public Enfant() {
    }

    @Override
    public String toString() {
        return "Enfant{" +
                "enfantId=" + enfantId +
                ", poids=" + poids +
                ", taille=" + taille +
                ", prenom='" + prenom + '\'' +
                ", age=" + age +
                ", date_naiss=" + date_naiss +
                '}';
    }

    public long getEnfantId() {
        return enfantId;
    }

    public void setEnfantId(long enfantId) {
        this.enfantId = enfantId;
    }

    public double getPoids() {
        return poids;
    }

    public void setPoids(double poids) {
        this.poids = poids;
    }

    public int getTaille() {
        return taille;
    }

    public void setTaille(int taille) {
        this.taille = taille;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getDate_naiss() {
        return date_naiss;
    }

    public void setDate_naiss(Date date_naiss) {
        this.date_naiss = date_naiss;
    }
}
