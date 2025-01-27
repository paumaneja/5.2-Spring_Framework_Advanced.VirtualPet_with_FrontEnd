# VirtualPet Backend

## Descripció

VirtualPet és una aplicació web que permet gestionar i jugar amb mascotes virtuals. Aquesta aplicació està dividida en un backend desenvolupat amb Spring Boot i un frontend que interactua amb ell. Aquest repositori conté el codi del backend, que inclou l'autenticació amb JWT, gestió de mascotes, i funcionalitats específiques com el canvi d'armes per a mascotes de tipus "StarWars" i "LordRings".

## Característiques Principals

- Autenticació i autorització amb JWT.
- Gestió d'usuaris.
- Creació, actualització, i eliminació de mascotes virtuals.
- Canvi d'armes per a mascotes amb regles específiques segons el tipus de mascota.
- Maneig d'excepcions global.

## Requisits

- Java 17 o superior
- Maven 3.8.0 o superior
- MySQL 8.0 o superior

## Configuració i Instal·lació

1. **Clona el repositori:**

   ```bash
   git clone https://github.com/el_teu_usuari/VirtualPet.git
   cd VirtualPet
   ```

2. **Configura la base de dades:**

   - Crea una base de dades a MySQL anomenada `virtualpetdb`.
   - Actualitza el fitxer `src/main/resources/application.properties` amb les teves credencials de MySQL:
     ```properties
     spring.datasource.url=jdbc:mysql://localhost:3306/virtualpet
     spring.datasource.username=el_teu_usuari
     spring.datasource.password=la_teva_contrasenya
     spring.jpa.hibernate.ddl-auto=update
     ```

3. **Construeix el projecte:**

   ```bash
   mvn clean install
   ```

4. **Executa l'aplicació:**

   ```bash
   mvn spring-boot:run
   ```

5. **Accedeix al backend:**
   L'aplicació estarà disponible a `http://localhost:8080`.

## Endpoints Principals

### Autenticació

- **POST /auth/register:** Registra un nou usuari.
- **POST /auth/login:** Inicia sessió i obté un token JWT.

### Gestió de Mascotes

- **GET /pets:** Obté totes les mascotes de l'usuari actual.
- **POST /pets:** Crea una nova mascota.
- **PUT /pets/{id}:** Actualitza la informació d'una mascota.
- **DELETE /pets/{id}:** Elimina una mascota.
- **PATCH /pets/{id}/weapon:** Canvia l'arma d'una mascota (segons el tipus).

## Estructura del Projecte

- `src/main/java`: Conté el codi font principal.
  - `controller`: Gestiona les peticions HTTP.
  - `service`: Implementa la lògica de negoci.
  - `repository`: Interactua amb la base de dades.
  - `model`: Conté les entitats.
  - `config`: Configuracions de l'aplicació (seguretat, CORS, etc.).
  - `auth`: Classes relacionades amb l'autenticació.
  - `exception`: Maneig d'errors personalitzat.
- `src/main/resources`: Inclou els fitxers de configuració.

## Proves

Executa les proves unitàries:

```bash
mvn test
```

## Contribucions

Si vols contribuir al projecte:

1. Fes un fork del repositori.
2. Crea una branca per a la teva funcionalitat o correcció de bug.
3. Fes un pull request amb una descripció detallada.

## Llicència

Aquest projecte està sota la llicència MIT. Consulta el fitxer `LICENSE` per a més informació.

