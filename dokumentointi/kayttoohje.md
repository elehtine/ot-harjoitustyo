# Käyttöohje

Lataa tiedosto [tetris.jar](https://github.com/elehtine/tetris/releases/tag/viikko5)

# Konfigurointi

Ohjelma olettaa, että sen käynnistyshakemistossa on konfiguraatiotiedost config.properties, joka määrittelee käyttäjät ja huipputulokset tallentavien tiedostojen nimet. Tiedoston muoto on seuraava:
```
userFile=users.txt
highScoreFile=high-scores.txt
```

# Ohjelman käynnistäminen
Ohjelma käynnistetään komennolla
```
java -jar tetris.jar
```

# Käyttöliittymä

Sovellus aukeaa päävalikkoon. Siellä on nappeja, joiden tekstit kuvaavat, mitä ne tekevät.
Kirjautumisnäkymässä ja rekisteröintinäkymässä vasemmanpuoleinen kenttä on käyttäjänimelle ja oikeanpuoleinen on salasanalle.

## Pelinäkymä

Peli näkymässä on seuraavat komennot:
- nuoli ylös kääntää palikkaa
- nuoli oikealle/vasemmalle liikuttaa palikkaa sivuille
- nuoli alas liikuttaa palikkaa alaspäin
- välilyönti tiputtaa palikan pohjaan
- Escape (esc) näppäin palauttaa aloitusnäkymään
