package main

import (
	"log"

	"gorm.io/driver/sqlite"
	"gorm.io/gorm"
)

func main() {
	db, err := gorm.Open(sqlite.Open("pusinex.db"), &gorm.Config{})
	if err != nil {
		panic("failed to connect database")
	}

	log.Printf("Database connection established: %v", db)
}
