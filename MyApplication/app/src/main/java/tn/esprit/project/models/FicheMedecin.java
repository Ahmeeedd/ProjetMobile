package tn.esprit.project.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class FicheMedecin {

    @PrimaryKey(autoGenerate = true)
    private long ficheId;
    @ColumnInfo
    private String nomPrenom;
    @ColumnInfo
    private String adresse;
    @ColumnInfo
    private int tel;
    @ColumnInfo
    private String email;


    public FicheMedecin() {
    }

    public FicheMedecin(long ficheId, String nomPrenom, String adresse, int tel, String email) {
        this.ficheId = ficheId;
        this.nomPrenom = nomPrenom;
        this.adresse = adresse;
        this.tel = tel;
        this.email = email;
    }

    public FicheMedecin(String nomPrenom, String adresse, int tel, String email) {
        this.nomPrenom = nomPrenom;
        this.adresse = adresse;
        this.tel = tel;
        this.email = email;
    }

    public long getFicheId() {
        return ficheId;
    }

    public void setFicheId(long ficheId) {
        this.ficheId = ficheId;
    }

    public String getNomPrenom() {
        return nomPrenom;
    }

    public void setNomPrenom(String nomPrenom) {
        this.nomPrenom = nomPrenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
