
# Calendar Tasks App  
Aplikacja webowa do zarządzania zadaniami w kalendarzu (React + Spring Boot + PostgreSQL + JWT)

---

## Uruchomienie projektu

### **1. Backend (Spring Boot)**

#### **Wymagania:**
- Java 21+
- Maven
- PostgreSQL

#### **Kroki uruchomienia:**

1. Sklonuj repozytorium  
   ```bash
   git clone https://github.com/xTheAlbatros/spring-react-app
   cd backend
   ```

2. Utwórz bazę danych PostgreSQL  
   ```sql
   CREATE DATABASE calendar_tasks;
   ```

3. Zmień zmienne w `application.properties` / `application.yml`  
   **WYMAGANE ZMIANY:**
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/calendar_tasks
   spring.datasource.username=postgres
   spring.datasource.password=twoje_haslo
   ```

4. Uruchom backend  
   ```bash
   mvn spring-boot:run
   ```

5. API powinno działać pod adresem:  
    http://localhost:8080

---

### **2. Frontend (React + Vite)**

#### **Wymagania:**
- Node.js 20+
- NPM lub Yarn

#### **Kroki uruchomienia:**

1. Przejdź do folderu frontendu:
   ```bash
   cd frontend
   ```

2. Zainstaluj zależności:
   ```bash
   npm install
   ```

3. Zmień konfigurację środowiska `.env`:
   ```env
   VITE_BACKEND_URL=http://localhost:8080
   ```

4. Uruchom aplikację:
   ```bash
   npm run dev
   ```

5. Frontend dostępny pod adresem:  
   http://localhost:3000

---

## Struktura projektu

# Backend – Spring Boot

```
backend/
│
├── src/
│   ├── main/
│   │   ├── java/pl/task/service/
│   │   │   ├── logic/
│   │   │   │   ├── auth/
│   │   │   │   ├── task/
│   │   │   │   ├── user/
│   │   │   │   ├── shared/
│   │   │   │   └── config/
│   │   │   │
│   │   │   ├── model/
│   │   │   ├── persistence/
│   │   │   └── web/controllers/
│   │   │
│   │   ├── resources/
│   │   │   ├── application.properties
│   │   │   └── schema.sql / data.sql
│   │   └── ...
│   └── test/
│
├── pom.xml
└── README.md
```

---

# Frontend – React + Vite

```
frontend/
│
├── src/
│   ├── api/
│   ├── components/
│   ├── contexts/
│   ├── pages/
│   ├── router/
│   ├── styles/
│   ├── App.jsx
│   └── main.jsx
│
├── public/
├── .env
└── package.json
```

---

##  Konfiguracja środowiska backendu

```
spring.datasource.url=jdbc:postgresql://localhost:5432/calendar_tasks
spring.datasource.username=postgres
spring.datasource.password=twoje_haslo

# JWT
application.security.jwt.secret-key=TWÓJ_KLUCZ
application.security.jwt.expiration=3600000
application.security.jwt.refresh-token.expiration=604800000
```

---

## Podsumowanie

Projekt składa się z:
- kompletnego backendu (Spring Boot + JWT + PostgreSQL),
- frontendu (React + Vite + Bootstrap),
- modułów auth, profile, tasks,
- widoków kalendarza, listy zadań na dziś, edycji profilu.

