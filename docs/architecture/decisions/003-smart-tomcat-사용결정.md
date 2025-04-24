# Smart Tomcat 플러그인 사용 결정

Date: 2025-04-24

## 상태

적용 중

## 맥락

IntelliJ Community Edition(CE)은 기본적으로 Tomcat과의 통합 실행 기능을 지원하지 않는다.  
기존의 Spring Boot 프로젝트에서는 내장 톰캣으로 쉽게 실행 가능했지만, JSP 기반 Spring Web MVC 프로젝트는 `.war` 형태의 배포와 서블릿 컨테이너 연동이 필요하다.

<pre>
- IntelliJ CE는 Web Application Facet과 war exploded 아티팩트를 지원하지 않음
- 내장 톰캣 없이도 JSP, Servlet 기반 프로젝트를 실행해야 함
- 수동 톰캣 배포 없이 빠르게 테스트 가능한 방법이 필요함
</pre>

**대안**:

- IntelliJ Ultimate Edition 사용  
  → 유료 라이선스로 인해 선택하지 않음

- 외부 톰캣에 수동 `.war` 배포  
  → 개발 및 디버깅 반복 작업에 비효율적

## 결정

- Smart Tomcat 플러그인 설치
- 톰캣 경로 수동 지정 (`apache-tomcat-9.0.104`)
- `.war exploded` 방식이 불가능하므로 `src/main/webapp` 디렉터리를 직접 Deployment 경로로 지정
- Before Launch에 `Build` 작업을 추가하여 클래스 및 리소스 정상 반영

## 결과

- IntelliJ CE 환경에서도 톰캣 연동 개발 환경을 구축할 수 있게 됨
- JSP, web.xml, dispatcher-servlet.xml 기반 MVC 구조를 테스트 가능
- `.war exploded`를 통한 자동 인식은 불가하지만, 디렉터리 직접 지정으로 완전한 대체 가능
- 실제 배포 전 단계의 구조를 IDE 내에서 그대로 반영할 수 있음