package com.dh.poo;
import front.PanelManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    private PanelManager manager;
    public static void main(String[] args) {

        Main main = new Main();
        main.iniciarManager();
        main.showFrame();

    }

    public void iniciarManager(){
        manager = new PanelManager();
        manager.armarManager();
        manager.mostrarPanelUsers();
    }

    public void showFrame(){
        manager.showFrame();
    }
}