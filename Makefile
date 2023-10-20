.PHONY: fmt vet run build

.DEFAULT_GOAL := run

fmt:
	go fmt ./...

vet: fmt
	go vet ./...

test: vet
	go test -v ./...

run: vet
	go run main.go

build: vet
	go build 

clean: vet
	rm -rf ./pusinex.db
