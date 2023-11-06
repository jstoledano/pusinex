# coding: utf-8
"""Formularios para Gestión de PUSINEX."""

#         app: control
#      módulo: forms
# descripción: Formularios para PUSINEX
#       autor: Javier Sanchez Toledano
#       fecha: 25 de enero de 2023


from django import forms
from crispy_forms.helper import FormHelper
from crispy_forms.layout import Layout, Submit, Div, HTML, Field, Button
from crispy_forms.bootstrap import FormActions
from control.models import Pusinex2, Seccion
import logging

logger = logging.getLogger(__name__)


class PUSINEXForm(forms.ModelForm):
    seccion = forms.ModelChoiceField(queryset=Seccion.objects.all().order_by('seccion'))
    f_act = forms.DateField(label='Fecha de Actualización', widget=forms.DateInput(attrs={'type': 'date'}))
    hojas = forms.IntegerField(min_value=1)
    archivo = forms.FileField()
    observaciones = forms.CharField(widget=forms.Textarea, required=False)

    class Meta:
        exclude = ('user', )
        model = Pusinex2

    def __init__(self, *args, **kwargs):
        super(PUSINEXForm, self).__init__(*args, **kwargs)
        self.helper = FormHelper()
        self.helper.layout = Layout (
            Div(
                Field('seccion', wrapper_class='col-3 mb-4'),
                Field('f_act', wrapper_class='col-4'),
                Field('hojas', wrapper_class='col-3'),
                css_class='row'
            ),
            Div(
                Field('archivo', wrapper_class='col-10 mb-2'),
                css_class='row'
            ),
            Div(
                Field('observaciones', wrapper_class='col-md-10', rows='3'),
                css_class='row'
            ),
            Div(
                HTML('<hr>'),
                FormActions(
                    Submit('save', 'Guardar cambios'),
                    Button('cancel', 'Cancelar')
                ),
                css_class='modal-footer'
            )
        )
