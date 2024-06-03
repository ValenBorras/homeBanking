package com.dh.poo;

import java.util.ArrayList;
import java.util.List;

public class homeBanking {
    private List<Usuario> usuarios;

    public homeBanking(){
        this.usuarios = new ArrayList<>();
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}
