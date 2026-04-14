def verificar_alertas(tempos):
    alertas = []
    lentos = 0
    
    for i, tempo in enumerate(tempos):
        if tempo > 2000:
            lentos += 1
        else:
            lentos = 0
            
        if lentos == 3:
            alertas.append(i)
            lentos = 0
            
    return alertas