from Persona import Persona

class Profesor(Persona):
    
    def __init__(
        self, nombre: str = "", edad: int = 0, especialidad: str = ""
    ):
        super().__init__(nombre, edad)
        self._especialidad = especialidad

    @property
    def especialidad(self) -> str:
        return self._especialidad

    @especialidad.setter
    def especialidad(self, valor: str):
        self._especialidad = valor

    def __str__(self) -> str:
        return f"Profesor [{super().__str__()}, Especialidad: {self._especialidad}]"