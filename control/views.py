from dal import autocomplete
from django.db.models import Q
from django.views import View
from django.views.generic import TemplateView, DetailView, CreateView, ListView
from django_filters.rest_framework import DjangoFilterBackend
from django.urls import reverse, reverse_lazy
import zipfile
from django.http import FileResponse
import os
from pathlib import Path
from django.contrib.auth.mixins import LoginRequiredMixin
from rest_framework import viewsets
from control.forms import PUSINEXForm
from control.models import Localidad, Municipio, Pusinex, Seccion, Revision
from control.serializers import (LocalidadSerializer, MunicipioSerializer,
                                 PusinexSerializer, SeccionSerializer)

import logging

logger = logging.getLogger(__name__)


class Index(ListView):
    template_name = 'index.html'
    model = Municipio
    context_object_name = 'municipios'

    def get_queryset(self):
        qs = Municipio.objects.all()
        if self.request.GET.get('q'):
            qs = qs.filter(Q(nombre__icontains=self.request.GET.get('q')))
        return qs


class MunicipioAutoComplete(autocomplete.Select2QuerySetView):
    def get_queryset(self):
        qs = Municipio.objects.all()
        if self.q > "":
            qs = qs.filter(Q(nombre__istartswith=self.q))
        return qs


class PusinexDetail(DetailView):
    model = Pusinex
    context_object_name = 'pusinex'


class LocalidadDetail(DetailView):
    model = Localidad
    context_object_name = 'localidad'


class MunicipioDetail(DetailView):
    model = Municipio
    context_object_name = 'municipio'


class CreatePUSINEX(LoginRequiredMixin, CreateView):
    template_name = 'control/pusinex_form.html'
    form_class = PUSINEXForm
    model = Revision
    login_url = reverse_lazy('login')
    redirect_field_name = 'next'

    def form_invalid(self, form):
        logger.error(form.errors)
        return super(CreatePUSINEX, self).form_invalid(form)

    def form_valid(self, form):
        # Crea o busca un PUSINEX en la base de datos
        seccion = Seccion.objects.get(seccion=form.cleaned_data['seccion'])
        try:
            pusinex = Pusinex.objects.get(seccion=seccion)
        except Pusinex.DoesNotExist:
            pusinex = Pusinex.objects.create(seccion=seccion, activo=True)
        # Crea una revisión de PUSINEX
        revision = form.save(commit=False)
        revision.user = self.request.user
        revision.pusinex = pusinex
        revision.save()
        return super(CreatePUSINEX, self).form_valid(form)

    def get_success_url(self):
        return reverse('municipio', kwargs={'pk': self.object.pusinex.seccion.municipio.id})


class Administration(TemplateView):
    template_name = 'administration.html'


class MunicipioViewSet(viewsets.ModelViewSet):
    queryset = Municipio.objects.all()
    serializer_class = MunicipioSerializer


class SeccionViewSet(viewsets.ModelViewSet):
    queryset = Seccion.objects.all()
    serializer_class = SeccionSerializer
    filter_backends = [DjangoFilterBackend]
    filterset_fields = ['municipio', 'seccion']


class LocalidadViewSet(viewsets.ModelViewSet):
    queryset = Localidad.objects.all()
    serializer_class = LocalidadSerializer
    filter_backends = [DjangoFilterBackend]
    filterset_fields = ['municipio', ]


class PusinexViewSet(viewsets.ModelViewSet):
    queryset = Pusinex.objects.all()
    serializer_class = PusinexSerializer
    filterset_fields = ['id', 'seccion__seccion', ]


class LogoutView(TemplateView):
    next_page = reverse_lazy('index')
    redirect_field_name = 'next'


seccionesVNM2023 = (
    12, 14, 16, 17, 26, 27, 30, 34, 36, 43, 48, 74, 107, 184, 188,
    201, 203, 218, 261, 409, 414, 474, 478, 482, 506, 542, 543,
    533, 534, 606, 9, 124, 134, 141, 147, 266, 348, 349, 355, 363,
    364, 384, 624, 397, 441, 442, 469, 626, 567, 392, 159, 151, 2,
    78, 85, 87, 89, 234, 242, 290, 297, 325, 336, 425, 511, 512,
    515, 575, 581, 589, 593, 597, 314, 551, 473, 142)

seccionesVNM2024 = (
    14, 19, 26, 635, 68, 100, 102, 107, 109, 191, 213, 217, 365, 403,
    406, 421, 471, 484, 521, 530, 536, 543, 78, 88, 90, 165, 168, 220,
    232, 240, 253, 256, 257, 299, 333, 336, 347, 437, 439, 440, 444,
    446, 457, 464, 467, 626, 629, 509, 550, 556, 643, 295, 296, 126,
    139, 141, 144, 145, 266, 271, 283, 348, 355, 356, 360, 622, 623,
    639, 374, 375, 381, 382, 384, 561, 572, 597, 599, 154
)
queryVNM2023 = Seccion.objects.filter(seccion__in=seccionesVNM2023).order_by('distrito', 'seccion')
pusinexVNM2023 = Pusinex.objects.filter(seccion__seccion__in=seccionesVNM2023)

queryVNM2024 = Seccion.objects.filter(seccion__in=seccionesVNM2024).order_by('distrito', 'seccion')
pusinexVNM2024 = Pusinex.objects.filter(seccion__seccion__in=seccionesVNM2024)


class VNM2024(ListView):
    model = Seccion

    def get_queryset(self):
        qs = queryVNM2024
        return qs


class VNM2023(ListView):
    model = Seccion

    def get_queryset(self):
        qs = queryVNM2023
        return qs


class VNMZipView(View):
    @staticmethod
    def get(request, dto):
        files = []
        zip_name = Path('media', 'pusinex', f'pusinex_VNM_0{dto}.zip')
        zip_archive = zipfile.ZipFile(zip_name, mode='w', compression=zipfile.ZIP_DEFLATED, compresslevel=9)
        if dto:
            for p in pusinexVNM2024.filter(seccion__distrito__distrito=dto):
                files.append(Path(os.getcwd(), 'media', p.revision_set.latest().archivo.path))
        with zip_archive as archive:
            for file in files:
                archive.write(file, arcname=Path(file).name)
        return FileResponse(open(zip_name, 'rb'))
