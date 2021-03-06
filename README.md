# Building Info ![Building status](https://travis-ci.org/blazejpp/building-info.svg?branch=master)

## Opis projektu
Aplikacja dla administratorów budynków, którzy pragną optymalizować koszty zarządzania budynkami. Nasza aplikacja Building Info umożliwi pozyskanie informacji o parametrach budynku na poziomie pomieszczeń, kondygnacji oraz całych budynków. Aplikacja będzie dostępna poprzez GUI a także jako zdalne API dzięki czemu można ją zintegrować z istniejącymi narzędziami.

## Struktura danych:
- Lokacja to budynek, poziom, lub pomieszczenie
- Budynek może składać się z poziomów, a te z pomieszczeń
- Każda lokalizacja jest charakteryzowana przez:
  - id – unikalny identyfikator
  - name – opcjonalna nazwa lokalizacji
- Pomieszczenie dodatkowo jest charakteryzowane przez:
  - area = powierzchnia w m^2
  - cube = kubatura pomieszczenia w m^3
  - heating = poziom zużycia energii ogrzewania (float)
  - light – łączna moc oświetlenia


### Wymagania
https://docs.google.com/spreadsheets/d/e/2PACX-1vSxEKEBzcopOqfu9OHFwQkD2oDQlztfqAW0Tf_IXjElZQyKDUrzl4-oxI78NQEHZaLh1Vorl2RSyEf3/pubhtml

### Instrukcje uruchomienia
1. Instalacja bazy danych MYSQL
2. Utworzenie bazy danych w MYSQL
```sh
mysql> CREATE DATABASE building-info;
```
3. W /src/main/resorces/application.properties przypisać do pól spring.datasource.username i spring.datasource.password nazwę użytkownika i hasło do utworzonej w pkt. 2 bazy danych.
4. W katalogu głównym uruchomić terminal i wpisać
```sh
$ mvn spring-boot:run
```
5. Aplikację można zobaczyć w przeglądarce pod adresem http://localhost:8080/swagger-ui.html
6. Plik .jar znajduje się w /target/
