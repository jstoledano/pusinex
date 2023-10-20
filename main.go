package main

import (
	"log"

	"gorm.io/gorm/clause"

	"pusinex/models"
)

func main() {
	db, err := models.InitDB()
	if err != nil {
		panic("Error al crear la base de datos")
	}

	log.Printf("Database connection established: %v", db)

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
		log.Println("distrito -- ", dto.Distrito, dto.Cabecera)
		if err := tx.Clauses(clause.OnConflict{
			Columns:   []clause.Column{{Name: "distrito"}},
			DoUpdates: clause.Assignments(map[string]interface{}{"cabecera": dto.Cabecera}),
		}).Create(&distrito).Error; err != nil {
			tx.Rollback()
			log.Println("distritos -- ", err)
		} else {
			// Una vez que la transacción se ha confirmado, podemos cargar los datos desde la base de datos
			var result models.Distrito
			db.Where("distrito = ?", dto.Distrito).First(&result)
			// Imprime los datos guardados
			log.Println("ID:", result.ID, "Distrito:", result.Distrito, "Cabecera:", result.Cabecera)
		}
	}

	// Fin de la transacción
	tx.Commit()

	db.Model(&models.Distrito{}).Count(&count)
	log.Println(count)
}
