public class Horario {
    private String dias;
    private String horaInicio;
    private String horaFin;

    public Horario() {}

    public Horario(String dias, String horaInicio, String horaFin) {
        this.dias = dias;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }
    public String getDias() {
        return dias; 
    }
    public void setDias(String dias) { 
        this.dias = dias; 
    }
    public String getHoraInicio() { return 
        horaInicio; 
    }
    public void setHoraInicio(String horaInicio) { 
        this.horaInicio = horaInicio; 
    }
    public String getHoraFin() { 
        return horaFin; 
    }
    public void setHoraFin(String horaFin) { 
        this.horaFin = horaFin; 
    }
    @Override
    public String toString() {
        return dias + " de " + horaInicio + " a " + horaFin;
    }
}
