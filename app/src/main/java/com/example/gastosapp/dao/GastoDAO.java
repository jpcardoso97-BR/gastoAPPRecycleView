package com.example.gastosapp.dao;

import com.example.gastosapp.model.Gasto;

import java.util.ArrayList;
import java.util.List;

public class GastoDAO {
    private static int ultimoId = 0;
    private static List<Gasto> gastoList = new ArrayList<>();

    public void inseri(Gasto gasto){
        gasto.setId(ultimoId);
        gastoList.add(gasto);
        ultimoId++;
    }

    public void remove(int id){
        gastoList.remove(id);
    }

    public void edita(int id, Gasto gasto){
        gastoList.set(id, gasto);
    }
    public void edita(Gasto gasto){
        int id = gasto.getId();
        for (Gasto item: gastoList) {
            if (item.getId()== id){
                gastoList.set(id, gasto);
            }
        }
    }


    public List<Gasto> recuperTodosGastos(){
        return gastoList;
    }
}
