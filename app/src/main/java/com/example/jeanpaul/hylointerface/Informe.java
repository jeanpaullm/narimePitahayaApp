package com.example.jeanpaul.hylointerface;

import java.util.ArrayList;

public class Informe {

    private String idPlanta;
    private String fecha;
    private String hora;
    private int primordios = 0;
    private int flores = 0;
    private int frutosVerdes = 0;
    private int frutosPintones = 0;
    private int frutosMaduros = 0;
    private boolean bacterias = false;
    private boolean hongos = false;
    private boolean insectos = false;
    private ArrayList<Tarea> tareas = new ArrayList<>();
    private boolean tareaPendiente = false;
    private long idInforme;

    private boolean test = true;

    public String getIdPlanta() {
        return this.idPlanta;
    }

    public void setIdPlanta(String idPlanta) {
        this.idPlanta = idPlanta;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public void incrementPrimordios() {
        this.primordios++;
    }

    public void decrementPrimordios() {
        if(this.primordios > 0) {
            this.primordios--;
        }
    }

    public void checkTareaPendiente() {
        if(isInsectos() || isHongos() || isBacterias()) {
            setTareaPendiente(true);
        }
        else {
            setTareaPendiente(false);
        }
    }

    public int getPrimordios() {
        return primordios;
    }

    public void setPrimordios(int primordios) {
        this.primordios = primordios;
    }

    public void incrementFlores() {
        this.flores++;
    }

    public void decrementFlores() {
        if(this.flores > 0) {
            this.flores--;
        }
    }

    public int getFlores() {
        return flores;
    }

    public void setFlores(int flores) {
        this.flores = flores;
    }

    public void incrementFrutosPintones() {
        this.frutosPintones++;
    }

    public void decrementFrutosPintones() {
        if(this.frutosPintones > 0) {
            this.frutosPintones--;
        }
    }

    public int getFrutosVerdes() {
        return frutosVerdes;
    }

    public void setFrutosVerdes(int frutosVerdes) {
        this.frutosVerdes = frutosVerdes;
    }

    public void incrementFrutosVerdes() {
        this.frutosVerdes++;
    }

    public void decrementFrutosVerdes() {
        if(this.frutosVerdes > 0) {
            this.frutosVerdes--;
        }
    }

    public int getFrutosPintones() {
        return frutosPintones;
    }

    public void setFrutosPintones(int frutosPintones) {
        this.frutosPintones = frutosPintones;
    }

    public void incrementFrutosMaduros() {
        this.frutosMaduros++;
    }

    public void decrementFrutosMaduros() {
        if(this.frutosMaduros > 0) {
            this.frutosMaduros--;
        }
    }

    public int getFrutosMaduros() {
        return frutosMaduros;
    }

    public void setFrutosMaduros(int frutosMaduros) {
        this.frutosMaduros = frutosMaduros;
    }

    public boolean isBacterias() {
        return bacterias;
    }

    public void setBacterias(boolean bacterias) {
        this.bacterias = bacterias;
        checkTareaPendiente();
    }

    public boolean isHongos() {
        return hongos;
    }

    public void setHongos(boolean hongos) {
        this.hongos = hongos;
        checkTareaPendiente();
    }

    public boolean isInsectos() {
        return insectos;
    }

    public void setInsectos(boolean insectos) {
        this.insectos = insectos;
        checkTareaPendiente();
    }

    public ArrayList<Tarea> getTareas() {
        return tareas;
    }

    public void setTareas(ArrayList<Tarea> tareas) {
        this.tareas = tareas;
    }

    public boolean isTareaPendiente() {
        return tareaPendiente;
    }

    public void setTareaPendiente(boolean tareaPendiente) {
        this.tareaPendiente = tareaPendiente;
    }

    public long getIdInforme() {
        return idInforme;
    }

    public void setIdInforme(long idInforme) {
        this.idInforme = idInforme;
    }

}
