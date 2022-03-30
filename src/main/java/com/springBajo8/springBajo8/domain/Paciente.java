package com.springBajo8.springBajo8.domain;

import java.util.List;

public class Paciente {

    //ATRIBUTES
    private String idPaciente;
    private String nombrePaciente;
    private String apellidosPaciente;
    private List<String> padecimientos;
    private List<String> tratamientos;


    //GETTER && SETTER
    public String getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(String idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getNombrePaciente() {
        return nombrePaciente;
    }

    public void setNombrePaciente(String nombrePaciente) {
        this.nombrePaciente = nombrePaciente;
    }

    public String getApellidosPaciente() {
        return apellidosPaciente;
    }

    public void setApellidosPaciente(String apellidosPaciente) {
        this.apellidosPaciente = apellidosPaciente;
    }

    public List<String> getPadecimientos() {
        return padecimientos;
    }

    public void setPadecimientos(List<String> padecimientos) {
        this.padecimientos = padecimientos;
    }

    public List<String> getTratamientos() {
        return tratamientos;
    }

    public void setTratamientos(List<String> tratamientos) {
        this.tratamientos = tratamientos;
    }
}
