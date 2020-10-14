package com.example.gastosapp.dao;

import com.example.gastosapp.model.Gasto;

import java.util.ArrayList;
import java.util.List;

public class GastoDAO {
    private static List<Gasto> gastoList = new ArrayList<>();

    public void inseri(Gasto gasto){
        gastoList.add(gasto);
    }

    public void remove(int id){
        gastoList.remove(id);
    }

    public void edita(int id, Gasto gasto){
        gastoList.set(id, gasto);
    }

    public List<Gasto> recuperTodosGastos(){
        return gastoList;
    }
}
