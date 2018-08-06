package com.example.jeanpaul.hylointerface;

public class Tarea {

    private int idTarea;
    private String patogeno = null;
    private boolean tareaPendiente = false;

    public int getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(int idTarea) {
        this.idTarea = idTarea;
    }

    public String getPatogeno() {
        return patogeno;
    }

    public void setPatogeno(String patogeno) {
        this.patogeno = patogeno;
    }

    public boolean isTareaPendiente() {
        return tareaPendiente;
    }

    public void setTareaPendiente(boolean tareaPendiente) {
        this.tareaPendiente = tareaPendiente;
    }
}
