from django.urls import path, re_path
from rest_framework import routers
from control.views import (
    MunicipioViewSet, LocalidadViewSet, SeccionViewSet,  PusinexViewSet,
    Index, Administration, CreatePUSINEX,
    PusinexDetail, LocalidadDetail, MunicipioDetail,
    MunicipioAutoComplete, VNM2023, VNMZipView
)

router = routers.SimpleRouter()

router.register(r'municipio', MunicipioViewSet)
router.register(r'seccion', SeccionViewSet)
router.register(r'localidad', LocalidadViewSet)
router.register(r'pusinex', PusinexViewSet)

urlpatterns = [
    re_path(r'^municipio-autocomplete/$', MunicipioAutoComplete.as_view(), name='municipio-autocomplete'),
    path('vnm/', VNM2023.as_view(), name='vnm'),
    path('vnm/<int:dto>', VNMZipView.as_view(), name='vnmZip'),
    path('pusinex/<int:pk>', PusinexDetail.as_view(), name='pusinex'),
    path('localidad/<int:pk>', LocalidadDetail.as_view(), name='localidad'),
    path('municipio/<int:pk>', MunicipioDetail.as_view(), name='municipio'),
    path('creation/', CreatePUSINEX.as_view(), name='create'),
    path('bgd/', Administration.as_view(), name='bgd'),
    path('', Index.as_view(), name='index')
]
