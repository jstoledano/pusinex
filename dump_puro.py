import json
import os
from control.models import Pusinex

def exportar():
    datos = []
    for p in Pusinex.objects.all():
        datos.append({
            'seccion_id': p.seccion_id,  # El número de sección (llave primaria)
            'f_act': p.f_act.isoformat() if p.f_act else None,
            'hojas': p.hojas,
            'archivo_name': p.archivo.name, # La ruta relativa del PDF
            'username': p.user.username if p.user else None # Migramos por nombre de usuario, no por ID
        })
    
    with open('pusinex_puros.json', 'w', encoding='utf-8') as f:
        json.dump(datos, f, indent=4, ensure_ascii=False)
    print(f"✅ Se exportaron {len(datos)} registros a 'pusinex_puros.json'")

exportar()