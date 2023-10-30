package models

import (
	"log"
	"os"
	"sync"

	"gorm.io/driver/sqlite"
	"gorm.io/gorm"
)

var db *gorm.DB
var once sync.Once
var err error

// La estructura Tabla nos ayuda a cargar un archivo csv
// en la base de datos
type Table struct {
	Name    string
	Headers bool
	Fields  []string
	IdName  string
	IdCol   int
}

// La función InitDB inicializa la base de datos,
// usando el patrón de diseño `singleton` y devuelve
// un objeto gorm.DB y un objeto error
func InitDB() (*gorm.DB, error) {
	once.Do(func() {
		// La función sync.Do asegura que la inicialización se
		// realice solo una vez, no importa cuántas veces se llame
		// la función InitDB.
		if _, err := os.Stat("pusinex.db"); os.IsNotExist(err) {
			// Si no existe, crea la base de datos.
			db, err = gorm.Open(sqlite.Open("pusinex.db"), &gorm.Config{})
			if err != nil {
				log.Println("Error al abrir la base de datos: ", err)
				return
			}
			log.Println("Base de datos creada y abierta")
		} else {
			// Si la base de datos ya existe, abre la conexión
			db, err = gorm.Open(sqlite.Open("pusinex.db"), &gorm.Config{})
			if err != nil {
				log.Println("Error al abriur la base de datos: ", err)
				return
			}
			log.Println("Base de datos creada y abierta")
		}
	})

	return db, err
}

// La función InitTables verifica que existan las tablas Distrito,
// Municipio, Sección y Pusinex. Si no existe alguna tabla, la crea.
func InitTables(db *gorm.DB) error {
	if err := db.AutoMigrate(&Distrito{}, &Municipio{}, &Seccion{}); err != nil {
		return err
	}
	return nil
}

// La función CargarDatos(*db, file) carga el archivo
// csv `file` en la base indicada.
func CargarDatos(db *gorm.DB, file string, table Table) error {
	log.Println(table.Name)
	return nil
}
