package models

import (
	"time"

	"gorm.io/gorm"
)

type Distrito struct {
	gorm.Model
	ID        uint
	Distrito  uint `gorm:"primaryKey"`
	Cabecera  string
	Secciones []Seccion `gorm:"foreignKey:DistritoID"`
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
	Seccion     uint `gorm:"primaryKey"`
	DistritoID  uint
	MunicipioID uint
	Tipo        uint
}

type Pusinex struct {
	gorm.Model
	ID           uint
	SeccionID    uint
	RevisionTime time.Time
	Estado       int
	Descripcion  string
}
