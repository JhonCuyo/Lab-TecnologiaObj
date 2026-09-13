class Persona:

    def __init__(self, nombre: str = "", edad: int = 0):
        self._nombre = nombre
        self._edad = edad

    @property
    def nombre(self) -> str:
        return self._nombre

    @nombre.setter
    def nombre(self, valor: str):
        self._nombre = valor

    @property
    def edad(self) -> int:
        return self._edad

    @edad.setter
    def edad(self, valor: int):
        self._edad = valor

    def __str__(self) -> str:
        return f"Nombre: {self._nombre}, Edad: {self._edad}"