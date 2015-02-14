package com.example.carlosrenato.searchy;

/**
 * Created by carlosrenato on 02-11-15.
 */
public class Cliente {
    private int ITEM;
    private String NOMBRE;
    private String DIRECCION;
    private String NUMERO;

    public int getItem() {
        return ITEM;
    }

    public void setItem(int item) {
        this.ITEM = item;
    }

    public String getNombre() {
        return NOMBRE;
    }

    public void setNombre(String nombre) {
        this.NOMBRE = nombre;
    }

    public String getDireccion() {
        return DIRECCION;
    }

    public void setDireccion(String direccion) {
        this.DIRECCION = direccion;
    }

    public String getNumero() {
        return NUMERO;
    }

    public void setNumero(String numero) {
        this.NUMERO = numero;
    }

    @Override
    public String toString() {
        return ITEM + "- " + NOMBRE;
    }
}
