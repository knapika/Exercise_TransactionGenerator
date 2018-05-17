Komendy do wykonania:

gradle build

gradle shadowJar

Tutaj jakieś przykładowe dane (nie podawałem katalogu wyjściowego) :

java -jar build/libs/Transaction_generator.jar -customerIds 1:20 -dateRange "2018-03-08T00:00:00.000-0100":"2018-03-08T23:59:59.9
99-0100" -itemsFile items.csv -itemsCount 5:15 -itemsQuantity 1:30 -eventsCount 1000 -format xml

java -jar build/libs/Transaction_generator.jar  -itemsFile items.csv -itemsCount 5:15 -itemsQuantity 1:30 -eventsCount 5 -format yaml


gradle publishToMavenLocal


Jeżeli chcemy skorzystać z kontenera dockera, to należy wykonać polecenia:

docker build --tag generator .

docker run -v <sciezka do katalogu z plikami .properties i .csv>:/storage -it generator
  
Przykładowy katalog z wymienionymi danymi znajduje się w repo.
