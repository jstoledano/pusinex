from django.urls import path, re_path
from rest_framework import routers
from control.views import (
    MunicipioViewSet, SeccionViewSet,  PusinexViewSet,
    Index, Administration, CreatePUSINEX,
    PusinexDetail, MunicipioDetail,
    MunicipioAutoComplete, VNM2024, VNMZipView
)

router = routers.SimpleRouter()

router.register(r'municipio', MunicipioViewSet)
router.register(r'seccion', SeccionViewSet)
router.register(r'pusinex', PusinexViewSet)

urlpatterns = [
    re_path(r'^municipio-autocomplete/$', MunicipioAutoComplete.as_view(), name='municipio-autocomplete'),
    path('vnm/', VNM2024.as_view(), name='vnm'),
    path('vnm/<int:dto>', VNMZipView.as_view(), name='vnmZip'),
    path('pusinex/<int:pk>', PusinexDetail.as_view(), name='pusinex'),
    path('municipio/<int:pk>', MunicipioDetail.as_view(), name='municipio'),
    path('creation/', CreatePUSINEX.as_view(), name='create'),
    path('bgd/', Administration.as_view(), name='bgd'),
    path('', Index.as_view(), name='index')
]
