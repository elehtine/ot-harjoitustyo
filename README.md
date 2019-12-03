# Tetris

Sovellus on tetris peli. Pelissä on erimuotoisia neljästä neliöstä koostuvia palikoita. Palikat putoavat peliin yksikerrallaan. Pelaajan ohjata niiden putoamista. Jos palikat muodostavat täyden vaakarivin, tämä katoaa. Näistä riveistä saa pisteitä.

## Huomio Javan versiosta

Käytetty JavaFX voi aiheuttaa ongelmia. Kuitenkin kaikki on tähän mennessä toiminut, joten toivottavasti ei tule ongelmia. 

## Dokumentaatio

[Vaatimusmäärittely](https://github.com/elehtine/ot-harjoitustyo/blob/master/dokumentointi/vaatimusmaarittely.md)

[Työaikakirjanpito](https://github.com/elehtine/ot-harjoitustyo/blob/master/dokumentointi/tyoaikakirjanpito.md) 

[Arkkitehtuurikuvaus](https://github.com/elehtine/ot-harjoitustyo/blob/master/dokumentointi/arkkitehtuuri.md) 

## Komentorivitoiminnot

### Testaus

Testit suoritetaan komennolla

```
mvn test
```

Testikattavuusraportti luodaan komennolla

```
mvn jacoco:report
```

Kattavuusraporttia voi tarkastella avaamalla selaimella tiedosto _target/site/jacoco/index.html_

### Suoritettavan jarin generointi

Komento

``` 
mvn package
```
generoi hakemistoon target suoritettavan jar-tiedoston _Tetris-1.0-SNAPSHOT.jar_

### Checkstyle

Tiedostoon [checkstyle.xml](https://github.com/elehtine/tetris/blob/master/checkstyle.xml) määrittelemät tarkistukset suoritetaan komennolla

```
mvn jxr:jxr checkstyle:checkstyle
```

Mahdolliset virheilmoitukset selviävät avaamalla selaimella tiedosto target/site/checkstyle.html
