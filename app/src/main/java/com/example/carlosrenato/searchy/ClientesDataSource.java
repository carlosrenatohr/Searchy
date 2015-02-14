package com.example.carlosrenato.searchy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by carlosrenato on 02-11-15.
 */
public class ClientesDataSource {

    private SQLiteDatabase db;
    private DBHelper dbhelper;
    public String[] allColumns = {"_id", "NOMBRE", "DIRECCION", "NUMERO"};

    public ClientesDataSource(Context context){
        dbhelper = new DBHelper(context);
        dbhelper.createDataBase();
//        dbhelper.open();
    }

    public void open() /*throws SQLException*/ {
        db = dbhelper.getWritableDatabase();
    }

    public void close(){
        dbhelper.close();
    }

    public List<Cliente> getAll(String match){
        List<Cliente> all = new ArrayList<Cliente>();
        open();
        String where = (!match.isEmpty()) ? "where NOMBRE like '%" + match + "%'" : "";
        Cursor cur = db.rawQuery("select * from clientes "+ where, null);
        cur.moveToFirst();
        while(!cur.isAfterLast()){
            Cliente cliente = toClient(cur);
            all.add(cliente);
            cur.moveToNext();
        }
        cur.close();
        close();
        return all;
    }

    public Cursor filtering(String match){
//        open();
//        Cursor cur = db.rawQuery("select ITEM as _id, NOMBRE, DIRECCION, NUMERO from clientes where NOMBRE like '"+match+"*';", null);
        Cursor cur = db.rawQuery("select * from Clientes where NOMBRE like '%"+match+"%';", null);
        if (cur != null){
            cur.moveToFirst();
        }
        return cur;
    }

    /** Convert cursor to client class**/
    public Cliente toClient(Cursor cur){
        Cliente cliente = new Cliente();
        cliente.setItem(cur.getInt(cur.getColumnIndexOrThrow("ITEM")));
        cliente.setNombre(cur.getString(cur.getColumnIndexOrThrow("NOMBRE")));
        cliente.setDireccion(cur.getString(cur.getColumnIndexOrThrow("DIRECCION")));
        cliente.setNumero(cur.getString(cur.getColumnIndexOrThrow("NUMERO")));
//        cliente.setNumero(cur.getString(3));
        return cliente;
    }

}
