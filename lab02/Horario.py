class Horario:
    
    def __init__(
        self, dias: str = "", hora_inicio: str = "", hora_fin: str = ""
    ):
        self._dias = dias
        self._hora_inicio = hora_inicio
        self._hora_fin = hora_fin

    @property
    def dias(self) -> str:
        return self._dias

    @dias.setter
    def dias(self, valor: str):
        self._dias = valor

    @property
    def hora_inicio(self) -> str:
        return self._hora_inicio

    @hora_inicio.setter
    def hora_inicio(self, valor: str):
        self._hora_inicio = valor

    @property
    def hora_fin(self) -> str:
        return self._hora_fin

    @hora_fin.setter
    def hora_fin(self, valor: str):
        self._hora_fin = valor

    def __str__(self) -> str:
        return f"{self._dias} de {self._hora_inicio} a {self._hora_fin}"