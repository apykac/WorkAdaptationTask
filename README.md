# WorkAdaptationTask
после утсановки бандлов первым запускать testbundle, затем apache-camel-cxfrs,
при принудительной остановки бандлов, стребуется рестарт ServiceMix
Пока не понял как решить проблему не перезапуская ServiceMix

тестирование методов по адресам:

http://localhost:9000/employeeservice/employees/{String}
Выводит на страницу то что вы ввели в конце строки

http://localhost:9000/employeeservice/methods/{int}
Выводит факториал введенного в конце строки числа
