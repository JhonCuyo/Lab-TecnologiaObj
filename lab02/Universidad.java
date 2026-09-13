import java.util.*;
public class Universidad {
    private String nombre;
    private List<Curso> cursos;
    
    public Universidad(){
        this.cursos= new ArrayList<>();
    }
    public Universidad(String nombre){
        this.cursos= new ArrayList<>();
        this.nombre=nombre;
    }
    public String getNombre(){
        return nombre;
    }
    public void setNombre(String nombre){
        this.nombre=nombre;
    }
    public List<Curso> getCursos(){
        return cursos;
    }
    public void setCursos(List<Curso> cursos){
        this.cursos=cursos;
    }
    public void agregarCurso(Curso curso){
        this.cursos.add(curso);
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Universidad: ").append(nombre).append("\nCursos ofrecidos:\n");
        for (Curso c : cursos) {
            sb.append(" - ").append(c.toString()).append("\n");
        }
        return sb.toString();
    }
}
