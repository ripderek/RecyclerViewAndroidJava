package com.example.myapprecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Toast;


import com.example.myapprecyclerview.Modelos.Usuario;
import com.example.myapprecyclerview.WebService.Asynchtask;

import com.example.myapprecyclerview.Adaptadores.AdaptadorUsuario;
import com.example.myapprecyclerview.WebService.WebService;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



public class MainActivity extends AppCompatActivity  implements Asynchtask {
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

    /*
      Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new WebService("https://reqres.in/api/users",
                datos, MainActivity.this, MainActivity.this);
        ws.execute("GET");
     */
        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new WebService("https://fakestoreapi.com/products",
                datos, MainActivity.this, MainActivity.this);
        ws.execute("GET");

    }

    @Override
    public void processFinish(String result) throws JSONException {
        ArrayList<Usuario> lstUsuarios = new ArrayList<Usuario>();
    try {
        //JSONObject JSONlista == new ArrayList<>();

        //JSONArray JSONlistaUsuarios= JSONlista.getJSONArray("data");
        //lstUsuarios = Usuario.JsonObjectsBuild(JSONlistaUsuarios);

        JSONArray Json = new JSONArray(result);
        lstUsuarios =Usuario.JsonObjectsBuild(Json);


        int resId = R.anim.layout_animation_down_to_up;
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getApplicationContext(),
                resId);
        recyclerView.setLayoutAnimation(animation);


        AdaptadorUsuario adapatorUsuario = new AdaptadorUsuario(this, lstUsuarios);
        recyclerView.setAdapter(adapatorUsuario);


    }catch (JSONException e)
    {
        //fakestoreapi.com/products
        Toast.makeText(this.getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG);
    }
    }
}