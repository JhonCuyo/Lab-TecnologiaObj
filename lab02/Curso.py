from Horario import Horario

class Curso:

    def __init__(
        self,
        nombre: str = "",
        dias: str = "",
        hora_inicio: str = "",
        hora_fin: str = "",
    ):
        self._nombre = nombre
        self._horario = Horario(dias, hora_inicio, hora_fin)

    @property
    def nombre(self) -> str:
        return self._nombre

    @nombre.setter
    def nombre(self, valor: str):
        self._nombre = valor

    @property
    def horario(self) -> Horario:
        return self._horario

    @horario.setter
    def horario(self, valor: Horario):
        self._horario = valor

    def __str__(self) -> str:
        return f"Curso: {self._nombre} | Horario: {self._horario}"