# poker
###Instructions

1. Checkout repository at https://github.com/jakeonline/poker
2. Run
```
mvn -U clean install
```
3. Change directory to target where the poker-1.0-SNAPSHOT.jar has been built
4. In Windows, run Java application against test file (provide the file path)
```
java -jar poker-1.0-SNAPSHOT.jar < poker-hands.txt
```
5. Alternativly, in Linux, run
```
cat poker-hands.txt | java -jar poker-1.0-SNAPSHOT.jar  
```
6. Output is
```
Player 1: 263
Player 2: 237
```
7. There is an option to include a system property "debug" with value "Y" to see some logging
```
java -Ddebug=Y -jar poker-1.0-SNAPSHOT.jar < poker-hands.txt
cat poker-hands.txt | java -Ddebug=Y -jar poker-1.0-SNAPSHOT.jar 
```
Output has logging 
```
...
...
R498 - AH 4C 3S 8C 2H 5D 7S 6C 4H JH - Player 1 wins - HIGH_CARD vs HIGH_CARD
R499 - 3H 9C 4D QD 7S 5D 7H 4H 5S QH - Player 2 wins - HIGH_CARD vs PAIR
R500 - 2H 9S 9C JD KH TD 4H JC 9H 8H - Player 1 wins - PAIR vs HIGH_CARD
Player 1: 263
Player 2: 237
```