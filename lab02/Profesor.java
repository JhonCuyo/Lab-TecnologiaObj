public class Profesor extends Persona {
    private String especialidad;

    public Profesor() { 
        super(); 
    }
    public Profesor(String nombre, int edad, String especialidad) {
        super(nombre, edad);
        this.especialidad = especialidad;
    }
    public String getEspecialidad() { 
        return especialidad; 
    }
    public void setEspecialidad(String especialidad) { 
        this.especialidad = especialidad; 
    }
    @Override
    public String toString() {
        return "Profesor [" + super.toString() + ", Especialidad: " + especialidad + "]";
    }
}