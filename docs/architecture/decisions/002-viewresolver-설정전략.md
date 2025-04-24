# ViewResolver 설정 전략

Date: 2025-04-24

## 상태

적용 중

## 맥락

Spring MVC에서는 Controller가 반환하는 View 이름을 실제 JSP 경로로 변환해주는 ViewResolver 설정이 필요하다.  
본 프로젝트에서는 JSP 기반 View 렌더링을 사용할 예정이므로, 다음과 같은 조건을 만족하는 ViewResolver 설정이 필요했다.

<pre>
- View 파일은 src/main/webapp/WEB-INF/views/ 아래 위치
- 외부에서 직접 JSP 파일 접근 불가해야 함
- JSP 확장자를 기반으로 View를 식별해야 함
- DispatcherServlet에서 ViewResolver를 통한 이름 해석이 이루어져야 함
</pre>

**대안**:

- Thymeleaf, FreeMarker 등의 템플릿 엔진 도입  
  → JSP 학습 및 레거시 시스템 호환 목적과 맞지 않음
- REST API 방식 + 프론트 분리 구조  
  → 본 프로젝트의 직접 렌더링 방식과 충돌

## 결정

- ViewResolver로 `InternalResourceViewResolver` 채택
- View Prefix: `/WEB-INF/views/`
- View Suffix: `.jsp`

```xml
<bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
    <property name="prefix" value="/WEB-INF/views/" />
    <property name="suffix" value=".jsp" />
</bean>
```

## 결과
- 컨트롤러에서 return "home" 과 같은 방식으로 JSP 렌더링이 가능해짐

- View 파일 외부 접근을 차단하고, DispatcherServlet을 통해서만 접근 가능

- JSP 파일 경로를 하드코딩하지 않고 논리 이름으로만 처리 가능