from Estudiante import Estudiante

class Reporte:

    def generar_reporte_estudiante(self, estudiante: Estudiante):
        print(f"Nombre: {estudiante.nombre}")
        print(f"Edad: {estudiante.edad} años")
        print(f"Carrera: {estudiante.carrera}")

    def __str__(self) -> str:
        return "Módulo de generación de reportes académicos."