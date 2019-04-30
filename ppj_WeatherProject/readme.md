Toto je složka semestrálního projektu. Základ je použit z ppj_JPA-mongoDB, kde bylo plně funkční připojení k mongu (+základní metody) a zároveň funkční testy s embeded mongoDB...

* mySQL:
  * mySQL DB verze 5
  * název DB použit v projektu: ppj_semestral
  * tabulky: states(name), cities (id, name, statename, cityid) id=autoinkrement, statename FK, cityid = openweathermap cityid
  * application-prod.properties
  * create scripty v /mysql

* mongoDB:
  * název: ppj, collection: measurement 
  * nastavení: application-prod.properties

* veškerá konfigurace je v application.properties (expirace, interval, readonly, parametry ke stahování z openweather)
* v testech použit embeded mysql a mongodb - application-test.properties
* aplikaci lze spustit s jarkem (maven->package)
