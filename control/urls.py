from django.urls import path, re_path, include
from rest_framework import routers
from control.views import (
    Index, Administration, CreatePUSINEX,
    PusinexDetail, MunicipioDetail,
    MunicipioAutoComplete, VNM2024, VNMZipView, PUSINEXZip,
    PUSINEXLastUpdate, PUSINEXByYear
)
from control.viewsets import (
    MunicipioViewSet, SeccionViewSet, PusinexViewSet, EntidadViewSet, DistritoViewSet,
)

router = routers.SimpleRouter()

router.register(r'entidad', EntidadViewSet)
router.register(r'distrito', DistritoViewSet)
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
    path('paquete/', PUSINEXZip.as_view(), name='paquete'),
    # URL /year/{int} toma un año como parámetro y devuelve el último paquete de datos de ese año
    path('latest/<int:year>', PUSINEXByYear.as_view(), name='year'),
    path('latest/', PUSINEXLastUpdate.as_view(), name='latest'),
    path('api/', include((router.urls, 'api'))),
    path('api-auth/', include('rest_framework.urls')),
    path('', Index.as_view(), name='index')
]
