from Profesor import Profesor
from Estudiante import Estudiante
from Curso import Curso
from Universidad import Universidad
from Reporte import Reporte

def main():
    prof1 = Profesor(
        "Carlos Mendoza", 45, "Algoritmos y Estructuras")
    prof2 = Profesor("Ana Gómez", 38, "Bases de Datos")

    est1 = Estudiante("Juan Pérez", 20, "Ingeniería de Sistemas")
    est2 = Estudiante("Maria López", 22, "Ciencia de la Computación")
    est3 = Estudiante("Luis Torres", 21, "Ingenieria Electrica")

    curso1 = Curso(
        "Programación Orientada a Objetos",
        "Lunes y Miércoles",
        "08:00",
        "10:00",
    )
    curso2 = Curso(
        "Bases de Datos I", "Martes y Jueves", "10:00", "12:00"
    )

    unsa = Universidad("Universidad Nacional San Agustin de Arequipa")
    unsa.agregar_curso(curso1)
    unsa.agregar_curso(curso2)

    print(unsa)

    gestor_reportes = Reporte()
    gestor_reportes.generar_reporte_estudiante(est1)


if __name__ == "__main__":
    main()