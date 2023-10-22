package main

import (
	"encoding/csv"
	"log"
	"os"
	"strconv"

	"pusinex/models"

	"gorm.io/gorm/clause"
)

func main() {
	db, err := models.InitDB()
	if err != nil {
		panic("Error al crear la base de datos")
	}

	errTables := models.InitTables(db)
	if errTables != nil {
		panic(errTables)
	}

	var count int64
	distrito := models.Distrito{Distrito: 1, Cabecera: "Apizaco"}
	db.Where(models.Distrito{Distrito: distrito.Distrito}).FirstOrCreate(&distrito)
	db.Clauses(clause.OnConflict{
		// Columna en la que se verifica el conflicto (clave primaria)
		Columns: []clause.Column{{Name: "distrito"}},
		// Valores a actualizar en caso de conflicto
		DoUpdates: clause.Assignments(map[string]interface{}{"cabecera": distrito.Cabecera}),
	}).Create(&distrito)

	distritos := []models.Distrito{
		{Distrito: 1, Cabecera: "Apizaco"},
		{Distrito: 2, Cabecera: "Tlaxcala de Xicohtencatl"},
		{Distrito: 3, Cabecera: "Zacatelco"},
	}

	// Inicio de la transacción
	tx := db.Begin()

	// Recorremos los elementos del slice para realizar el upsert
	for _, dto := range distritos {
		log.Println(dto.Distrito, dto.Cabecera)
		if err := tx.Clauses(clause.OnConflict{
			Columns:   []clause.Column{{Name: "distrito"}},
			DoUpdates: clause.Assignments(map[string]interface{}{"id": dto.Distrito, "cabecera": dto.Cabecera}),
		}).Create(&dto).Error; err != nil {
			tx.Rollback()
			log.Println("distritos -- ", err)
		}
	}

	// Fin de la transacción
	tx.Commit()

	db.Model(&models.Distrito{}).Count(&count)
	log.Println(count)

	// Abrir el archivo
	f, err := os.Open("data/DISTRITO.txt")
	if err != nil {
		log.Fatal(err)
	}

	csvReader := csv.NewReader(f)
	data, err := csvReader.ReadAll()
	if err != nil {
		log.Fatal(err)
	}

	var distritosCSV []models.Distrito
	for _, row := range data[1:] {
		log.Println(row[0], row[1])
		d, _ := strconv.ParseUint(row[0], 10, 32)
		id := uint(d)
		distritosCSV = append(distritosCSV, models.Distrito{ID: id, Distrito: id, Cabecera: row[1]})
	}

	log.Println(distritosCSV)

	// Inicio de la transacción
	tx = db.Begin()
	// Recorremos los elementos del slice para realizar el upsert
	for _, dto := range distritosCSV {
		log.Println(dto.Distrito, dto.Cabecera)
		if err := tx.Clauses(clause.OnConflict{
			Columns:   []clause.Column{{Name: "distrito"}},
			DoUpdates: clause.Assignments(map[string]interface{}{"id": dto.Distrito, "cabecera": dto.Cabecera}),
		}).Create(&dto).Error; err != nil {
			tx.Rollback()
			log.Println("distritos -- ", err)
		}
		log.Println("distrito -- ", dto.Distrito, dto.Cabecera)
	}
	// Fin de la transacción
	tx.Commit()

	defer f.Close()
	log.Println(f.Name())

	apizaco := models.Municipio{ID: 3, Municipio: 3, Nombre: "APIZACO"}
	secc38 := models.Seccion{ID: 38, Seccion: 38, DistritoID: 3, MunicipioID: 3, Tipo: 1}

	db.Create(&apizaco)
	db.Create(&secc38)

	log.Println(apizaco)
	log.Println(secc38)
}
