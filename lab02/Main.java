public class Main {
    public static void main(String[] args) {
        
        Profesor prof1 = new Profesor("Carlos Mendoza", 45, "Algoritmos y Estructuras");
        Profesor prof2 = new Profesor("Ana Gómez", 38, "Bases de Datos");

        Estudiante est1 = new Estudiante("Juan Pérez", 20, "Ingeniería de Sistemas");
        Estudiante est2 = new Estudiante("Maria López", 22, "Ciencia de la Computación");
        Estudiante est3 = new Estudiante("Luis Torres", 21, "Ingenieria Electrica");

        Curso curso1 = new Curso("Programación Orientada a Objetos", "Lunes y Miércoles", "08:00", "10:00");
        Curso curso2 = new Curso("Bases de Datos I", "Martes y Jueves", "10:00", "12:00");

        Universidad unsa = new Universidad("Universidad Nacional San Agustin de Arequipa");
        unsa.agregarCurso(curso1);
        unsa.agregarCurso(curso2);

        System.out.println(unsa);

        Reporte gestorReportes = new Reporte();
        gestorReportes.generarReporteEstudiante(est1);
    }
}