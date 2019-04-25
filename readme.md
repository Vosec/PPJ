Practicals and semestral project from Advanced java course

* mySQL:
  * mySQL DB verze 5
  * název DB použit v projektu: ppj_semestral
  * tabulky: states(statename), cities (id, cityname, statename, cityid) id=autoinkrement, statename FK, cityid = openweathermap cityid
  * application-prod.properties
  * create scripty v /mysql

* mongoDB:
  * název: ppj, collection: measurement 
  * nastavení: application-prod.properties

* veškerá konfigurace je v application.properties (expirace, interval, readonly, parametry ke stahování z openweather)
* v testech použit embeded mysql a mongodb - application-test.properties
* aplikaci lze spustit s jarkem (maven->package)

