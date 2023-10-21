package models

import (
	"gorm.io/gorm"
)

type Distrito struct {
	gorm.Model
	ID        uint
	Distrito  uint `gorm:"primaryKey"`
	Cabecera  string
	Secciones []Seccion
}

type Municipio struct {
	gorm.Model
	ID        uint
	Municipio uint `gorm:"primaryKey"`
	Nombre    string
	Secciones []Seccion
}

type Seccion struct {
	gorm.Model
	ID          uint
	Sección     uint `gorm:"primaryKey"`
	DistritoID  uint
	MunicipioID uint
	Tipo        uint
	Estado      uint
}
