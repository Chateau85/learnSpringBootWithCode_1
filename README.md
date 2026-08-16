# learnSpringBootWithCode_1

인프런 강의 "코드로 배우는 스프링 부트, 웹 MVC, DB 접근 기술" 실습 프로젝트입니다.

## 실행 환경

- Java 17
- Spring Boot 3.5
- Gradle Wrapper 8.14
- Spring MVC, Thymeleaf, Spring Data JPA, H2

## 실행

```bash
./gradlew bootRun
```

기본값은 별도 설치가 필요 없는 인메모리 H2 데이터베이스입니다. 외부 데이터베이스를 사용하려면 `SPRING_DATASOURCE_URL` 환경 변수로 JDBC URL을 지정하고, 운영 환경에서는 `SPRING_JPA_HIBERNATE_DDL_AUTO=validate`처럼 스키마 정책을 명시합니다.

## 검증

```bash
./gradlew clean test spotbugsMain spotbugsTest bootJar
```

SpotBugs와 FindSecBugs 보고서는 `build/reports/spotbugs/`에 생성됩니다.
