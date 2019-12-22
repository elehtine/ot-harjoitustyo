# Arkkitehtuurikuvaus

## Rakenne

Ohjelman rakenne noudattaa kolmitasoista kerrosarkkitehtuutria. Ylimpänä on _tetris.ui_, mikä sisältää käyttöliittymän. Keskellä on _tetris.domain_, mikä sisältää kaikki toiminnallisuudet, mitä tarvitaan pelissä tai tietokanta objektien käsittelyssä. Alimpana on _tetris-dao_, mikä sisältää tietokannan lukemisen ja muokkaamisen. 

## Käyttöliittymä

Käyttöliittymässä on 5 eri näkymää
- alkunäkymä
- kirjautumisnäkymä
- käyttäjätunnuksen luonti -näkymä
- pelinäkymä
- huippputulosnäkymä

Näistä jokainen on tuotettu JavaFX:n Scene-olion avulla. Käyttöliittymä sijaitsee _tetris.ui_ kansiossa.

## Sovelluslogiikka

Suurin osa sovelluslogiikkaa on itse tetris peli. Se on mahdollisimman erillään käyttöliittymästä. Käyttöliittymä pystyy vain käynnistämään ja sammuttamaan pelin halutessaan.

Lisäksi huipputulokset tulevat tallentumaan. Tämä on tarkoitus järjestää sovelluslogiikassa kokonaan. Käyttöliittymä pystyy hallitsemaan vain käyttäjien luontia ja kirjautumista.

Seuraava kuva on ohjelman luokka/pakkauskaavio:

<img src="https://github.com/elehtine/ot-harjoitustyo/blob/master/dokumentointi/kuvat/a-3.png" width="320">

## Sekvenssikaavio

Sekvenssikaavio, joka kuvaa tippuvan palikan pyörittämistä

<img src="https://github.com/elehtine/tetris/blob/master/dokumentointi/kuvat/a-2.png" width="320">
