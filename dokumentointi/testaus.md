# Testausdokumentti

Ohjelmaa on testattu sekä automatisoiduin yksikkö- ja integraatiotestein JUnitilla sekä manuaalisesti tapahtunein järjestelmätason testein.

## Yksikkö- ja integraatiotestaus

### sovelluslogiikka

Vain tiedostoja jotka ovat pakkauksessa [tetris.domain](https://github.com/elehtine/tetris/tree/master/src/main/java/tetris/domain) on testattu. Testiluokat ovat [GameTest](https://github.com/elehtine/tetris/blob/master/src/test/java/tetris/domain/GameTest.java), [GridTest](https://github.com/elehtine/tetris/blob/master/src/test/java/tetris/domain/GridTest.java) ja [PasswordHandlerTest](https://github.com/elehtine/tetris/blob/master/src/test/java/tetris/domain/PasswordHandlerTest.java).

DAO luokkia ei ole testattu yksikkö- ja integraatio testein. Myöskään luokkia [User](https://github.com/elehtine/tetris/blob/master/src/main/java/tetris/domain/User.java), [HighScore](https://github.com/elehtine/tetris/blob/master/src/main/java/tetris/domain/HighScore.java) ja [UserHighScoreService](https://github.com/elehtine/tetris/blob/master/src/main/java/tetris/domain/UserHighScoreService.java) ei ole testattu. Käyttöliittymäkoodi on myös jätetty testauksen ulkopuolelle.

### Testauskattavuus

Käyttöliittymäkerrosta lukuunottamatta sovelluksen testauksen rivikattavuus on 78% ja haarautumakattavuus 61%.
Jos otetaan huomioon vain pakkaus domain, rivikattavuus on 87% ja haarautumakattavuus 67%.

<img src="https://github.com/elehtine/tetris/blob/master/dokumentointi/kuvat/t-1.png" width="800">

Testaamatta jäivät tilanteet, joissa käyttäjät tai tehtävät tallettavia tiedostoja ei ole, tai niihin ei ole luku- ja kirjoitusoikeutta.

## Järjestelmätestaus

Sovelluksen järjestelmätestaus on suoritettu manuaalisesti.

### Asennus ja konfigurointi

Sovellus on haettu ja se on konfiguroitu [käyttöohjeen](https://github.com/elehtine/tetris/blob/master/dokumentointi/kayttoohje.md) mukaan luomalla _config.properties_-tiedosto.

Sovellusta on pelattu ja katsottu, että huipputulokset tallentuvat oikeilla nimillä.

### Toiminnallisuudet

Kaikki [määrittelydokumentin](https://github.com/elehtine/tetris/blob/master/dokumentointi/vaatimusmaarittely.md) ja käyttöohjeen listaamat toiminnallisuudet on käyty läpi manuaalisesti.

## Sovellukseen jääneet laatuongelmat

Sovellus ei anna tällä hetkellä järkeviä virheilmoituksia, jos jokin tiedostoista puuttuu
