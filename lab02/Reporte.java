public class Reporte {
    
    public void generarReporteEstudiante(Estudiante estudiante){
        System.out.println("Reporte del Estudiante: ");
        System.out.println("Nombre: "+estudiante.getNombre());
        System.out.println("Edad: "+estudiante.getEdad()+" años");
        System.out.println("Carrera: "+estudiante.getCarrera());
    }
}
