from typing import List
from Curso import Curso

class Universidad:

    def __init__(self, nombre: str = ""):
        self._nombre = nombre
        self._cursos: List[Curso] = []  # Agregación

    @property
    def nombre(self) -> str:
        return self._nombre

    @nombre.setter
    def nombre(self, valor: str):
        self._nombre = valor

    @property
    def cursos(self) -> List[Curso]:
        return self._cursos

    @cursos.setter
    def cursos(self, valor: List[Curso]):
        self._cursos = valor

    def agregar_curso(self, curso: Curso):
        self._cursos.append(curso)

    def __str__(self) -> str:
        salida = f"Universidad: {self._nombre}\nCursos ofrecidos:\n"
        for c in self._cursos:
            salida += f" - {c}\n"
        return salida