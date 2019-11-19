# Tetris

Sovellus on tetris peli. Pelissä on erimuotoisia neljästä neliöstä koostuvia palikoita. Palikat putoavat peliin yksikerrallaan. Pelaajan ohjata niiden putoamista. Jos palikat muodostavat täyden vaakarivin, tämä katoaa. Näistä riveistä saa pisteitä.

## Dokumentaatio

[Vaatimusmäärittely](https://github.com/elehtine/ot-harjoitustyo/blob/master/dokumentointi/vaatimusmaarittely.md)

[Työaikakirjanpito](https://github.com/elehtine/ot-harjoitustyo/blob/master/dokumentointi/tyoaikakirjanpito.md) 

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
