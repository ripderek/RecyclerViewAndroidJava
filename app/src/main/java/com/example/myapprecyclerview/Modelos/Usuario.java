package com.example.myapprecyclerview.Modelos;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Usuario {
    private String nombres;
    private String email;
    private String website;
    private String urlavatar;
    public String getNombre() { return nombres; }
    public String getEmail() { return email; }
    public String getWebsite() { return website; }
    public String getUrlavatar() { return urlavatar; }
    public void setNombre(String nombre) { this.nombres = nombre; }
    public void setEmail(String email) { this.email = email; }
    public void setWebsite(String website) { this.website = website; }
    public void setUrlavatar(String urlavatar) { this.urlavatar = urlavatar; }
    public Usuario(JSONObject a) throws JSONException {
        nombres = a.getString("title").toString();
        email = a.getString("price").toString() ;
        website = a.getString("description").toString() ;
        urlavatar = a.getString("image").toString() ;
    }
    public static ArrayList<Usuario> JsonObjectsBuild(JSONArray datos) throws JSONException {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        for (int i = 0; i < datos.length(); i++) {
            usuarios.add(new Usuario(datos.getJSONObject(i)));
        }
        return usuarios;
    }
}

