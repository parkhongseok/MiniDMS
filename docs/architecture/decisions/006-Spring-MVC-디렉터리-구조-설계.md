# Spring MVC 프로젝트 디렉터리 구조 설계

Date: 2025-04-27

## 상태
적용 중

## 맥락

이전 프로젝트(Spring Boot 기반)에서는 기능(도메인) 단위로 디렉터리를 나누고, 각 도메인 디렉터리 하위에 Controller, Service, Repository를 각각 구분하여 관리했다.

하지만 현재 진행 중인 레거시(Spring Web MVC) 프로젝트에서는 컨트롤러는 controller 패키지에 일괄 수집, 서비스는 service 패키지에 일괄 수집, 레포지토리는 repository 패키지에 일괄 수집하는 구조를 채택했다.

이 구조를 채택한 주된 이유는 다음과 같다:

- 레거시(Spring MVC)에서는 컴포넌트 스캔 범위를 명확히 지정해주어야 하고, dispatcher-servlet.xml에서 Controller 스캔만 별도로 설정해야 한다.

- 디렉터리 깊이가 깊을 경우, DispatcherServlet이 Controller를 찾지 못하거나 ComponentScan 범위 설정이 복잡해질 수 있다.

- 따라서 Controller는 controller 패키지에 일괄 수집하여, base-package="com.minidms.controller" 로 심플하게 관리하고자 했다.

- Service, Repository는 전역 ApplicationContext (applicationContext.xml)에서 관리하므로 별도로 패키지를 분리해 각 역할을 명확히 한다.

## 결정

디렉터리 구조는 다음과 같이 구성한다:
```
src/main/java/com/minidms/
├── controller/    # 모든 Controller 클래스 집합
├── service/       # 모든 Service 클래스 집합
├── repository/    # 모든 Repository 클래스 집합
├── domain/        # Entity 클래스
├── util/          # 파일 경로/파일 저장 관련 유틸리티
└── common/        # Enum, 상수, 예외 처리
```
- Controller는 dispatcher-servlet.xml 에서 따로 컴포넌트 스캔한다.

- Service, Repository는 applicationContext.xml 에서 전역으로 스캔한다.

## 결과

- 컴포넌트 스캔 경로 설정이 단순해져서 DispatcherServlet과 RootContext 설정이 명확해진다.

- 패키지 구성을 명확히 인지할 수 있고, 역할에 따라 코드를 쉽게 찾을 수 있다.

- 도메인별 분리보다는 레이어별 명확한 분리를 통해, 초기 프로젝트 구조를 단순화하고 관리 비용을 줄일 수 있다.