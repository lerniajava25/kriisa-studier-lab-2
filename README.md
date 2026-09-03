# Raytracer i Java

Det här är ett enkelt program som ritar upp 3D-objekt till en vanlig 2D-bild (`image.png`). Det fungerar genom att skicka ut osynliga strålar från en kamera och se vilka objekt de krockar med.

---

## Beskrivning av klasserna

Här är en enkel översikt över vad de olika filerna i projektet gör:

### Matte (`org.example.math`)
*   **Vector3.java:** Sparar en position eller en riktning i 3D (X, Y, Z). Används också för att spara färger (Röd, Grön, Blå). Innehåller mattehjälp för att plusa, minusa och gångra vektorer.
*   **Ray.java:** Representerar en stråle. Håller koll på var strålen startar (kameran) och åt vilket håll den åker.

### Former (`org.example.shapes`)
*   **Shape.java:** Ett gemensamt gränssnitt (interface) för alla former. Här finns också `Hit` som sparar om en stråle har träffat något, hur långt bort det var och vilken färg objektet har.
*   **Sphere.java:** En rund sfär (boll). Innehåller formeln för att räkna ut om en stråle träffar bollen.
*   **Triangle.java:** En platt triangel med tre hörn. Innehåller formeln för att se om en stråle träffar inuti triangeln.

### Huvudprogrammet
*   **Main.java:** Startar programmet. Här skapas listan med alla former (scenen). Den går igenom bildens alla pixlar, skjuter ut strålar, kollar vad som träffas närmast och sparar slutresultatet till bilden `image.png`.

---

## Hur du lägger till en ny form

Tack vare att alla former använder `Shape` kan du enkelt lägga till en ny form (t.ex. en fyrkantig `Box` eller ett platt `Plane`) utan att ändra koden i `Main.java`.

1. Skapa en ny klass i mappen `shapes` (t.ex. `Plane.java`).
2. Skriv `implements Shape` efter klassnamnet.
3. Skapa metoden `hit(Ray ray)` i din nya klass:
   * Om strålen **missar** din form, returnera `Shape.Hit.miss()`.
   * Om strålen **träffar**, returnera `new Hit(true, t, color)` där `t` är avståndet till träffen och `color` är färgen.
4. Lägg till din nya form i listan i `Main.java`:
   ```java
   scene.add(new Plane(...));
   ```
