from django.contrib import admin
from .models import Entidad, Distrito, Municipio, Seccion, Pusinex2


class PusinexInline(admin.TabularInline):
    model = Pusinex2
    extra = 1


class SeccionAdmin(admin.ModelAdmin):
    ordering = ['distrito', 'municipio', 'seccion']
    list_filter = ['distrito', 'municipio']
    inlines = [PusinexInline]


admin.site.register(Entidad)
admin.site.register(Distrito)
admin.site.register(Municipio)
admin.site.register(Seccion, SeccionAdmin)
