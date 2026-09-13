from Persona import Persona

class Estudiante(Persona):

    def __init__(self, nombre: str = "", edad: int = 0, carrera: str = ""):
        super().__init__(nombre, edad)
        self._carrera = carrera

    @property
    def carrera(self) -> str:
        return self._carrera

    @carrera.setter
    def carrera(self, valor: str):
        self._carrera = valor

    def __str__(self) -> str:
        return f"Estudiante [{super().__str__()}, Carrera: {self._carrera}]"