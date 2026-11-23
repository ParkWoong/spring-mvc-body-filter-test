# Servlet body filters / Servlet 본문 필터

[ENG](#eng) | [KR](#kr)

## ENG

### Project structure

```text
pom.xml
src/main/java/io/github/parkwoong/example/Application.java
src/main/java/io/github/parkwoong/example/EchoController.java
src/main/java/io/github/parkwoong/example/filter/RequestHistoryLogger.java
src/main/java/io/github/parkwoong/example/filter/ResponseHistoryLogger.java
src/main/resources/application.properties
src/test/java/io/github/parkwoong/example/BodyFilterTest.java
```

### Code explanation

`RequestHistoryLogger` wraps the request and replays its bytes so the controller can still read the body. `ResponseHistoryLogger` wraps the response and copies its body back to the client. `EchoController` gives a small endpoint for the test. The example does not store or print body contents.

### Run and test

Use JDK 8 and Maven 3.6.3+. Run from the repository root:

```sh
java -version
mvn clean verify
mvn spring-boot:run
```

Send `POST /echo` with a text body. The test confirms the same body reaches the controller and client.

## KR

### 프로젝트 구조

```text
pom.xml
src/main/java/io/github/parkwoong/example/Application.java
src/main/java/io/github/parkwoong/example/EchoController.java
src/main/java/io/github/parkwoong/example/filter/RequestHistoryLogger.java
src/main/java/io/github/parkwoong/example/filter/ResponseHistoryLogger.java
src/main/resources/application.properties
src/test/java/io/github/parkwoong/example/BodyFilterTest.java
```

### 코드 설명

`RequestHistoryLogger`가 요청을 감싸고 바이트를 다시 전달해 컨트롤러도 본문을 읽을 수 있게 합니다. `ResponseHistoryLogger`는 응답을 감싸고 본문을 클라이언트에 복사합니다. `EchoController`는 테스트용 엔드포인트입니다. 본문을 저장하거나 출력하지 않습니다.

### 실행과 테스트

JDK 8과 Maven 3.6.3 이상을 사용합니다. 저장소 루트에서 실행하세요:

```sh
java -version
mvn clean verify
mvn spring-boot:run
```

텍스트 본문을 `POST /echo`로 보내세요. 테스트는 같은 본문이 컨트롤러와 클라이언트에 전달되는지 확인합니다.
