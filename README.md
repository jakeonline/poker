# poker
###Instructions

1. Checkout repository at https://github.com/jakeonline/poker
2. Run mvn -U clean install
3. Change directory to target where the poker-1.0-SNAPSHOT.jar has been built
4. In Windows, run Java application against test file (provide the file path)
```
java -jar poker-1.0-SNAPSHOT.jar < poker-hands.txt
```
5. Alternativly, in Linux, run
```
cat poker-hands.txt | java -jar poker-1.0-SNAPSHOT.jar  
```
6. There is an option to include a system property "debug" with value "Y" to see some logging
```
java -Ddebug=Y -jar poker-1.0-SNAPSHOT.jar < poker-hands.txt
cat poker-hands.txt | java -Ddebug=Y -jar poker-1.0-SNAPSHOT.jar 
```