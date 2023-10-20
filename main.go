package main

import (
	"fmt"
	"log"

	"pusinex/models"
)

func main() {
	db, _ := models.InitDB()

	log.Printf("Database connection established: %v", db)
	db.Create(&models.Distrito{Distrito: 1, Cabecera: "Apizaco"})

	errTables := models.InitTables(db)
	if errTables != nil {
		panic(errTables)
	}

	var distrito models.Distrito
	db.Last(&distrito)
	var count int64
	db.Model(&models.Distrito{}).Count(&count)
	fmt.Printf("Distrito %02d - %s. Cantidad: %v\n", distrito.Distrito, distrito.Cabecera, count)

}
