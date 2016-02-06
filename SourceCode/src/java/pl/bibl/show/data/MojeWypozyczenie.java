/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.bibl.show.data;

import java.io.Serializable;
import pl.bibl.jpa.*;

public class MojeWypozyczenie  implements Serializable{
    private String nazwa;
    private String autor;
    private String kategoria;
    private String wydawca;
    private String rok;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public MojeWypozyczenie(String id, String nazwa, String autor, String kategoria, String wydawca, String rok) {
         this.id = id;
        this.nazwa = nazwa;
        this.autor = autor;
        this.kategoria = kategoria;
        this.wydawca = wydawca;
        this.rok = rok;
        
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getKategoria() {
        return kategoria;
    }

    public void setKategoria(String kategoria) {
        this.kategoria = kategoria;
    }

    public String getWydawca() {
        return wydawca;
    }

    public void setWydawca(String wydawca) {
        this.wydawca = wydawca;
    }

    public String getRok() {
        return rok;
    }

    public void setRok(String rok) {
        this.rok = rok;
    }
}
