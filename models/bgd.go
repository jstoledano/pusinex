package models

import (
	"gorm.io/gorm"
)

type Distrito struct {
	gorm.Model
	Distrito uint
	Cabecera string
}

type Municipio struct {
	gorm.Model
	Municipio uint
	Nombre    string
}

type Seccion struct {
	gorm.Model
	Sección   uint
	Distrito  uint
	Municipio uint
	Tipo      uint
	Estado    uint
}
