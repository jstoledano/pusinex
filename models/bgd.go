package models

import (
	"gorm.io/gorm"
)

type Distrito struct {
	gorm.Model
	ID       uint
	Distrito uint `gorm:"primaryKey"`
	Cabecera string
}

type Municipio struct {
	gorm.Model
	ID        uint
	Municipio uint `gorm:"primaryKey"`
	Nombre    string
}

type Seccion struct {
	gorm.Model
	ID        uint
	Sección   uint `gorm:"primaryKey"`
	Distrito  uint
	Municipio uint
	Tipo      uint
	Estado    uint
}
