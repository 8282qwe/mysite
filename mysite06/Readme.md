GlobalExceptionHandler와 ErrorController는 Spring Boot 애플리케이션에서 예외를 처리하는 두 가지 다른 접근 방식입니다. 주요 차이점은 다음과 같습니다:

## GlobalExceptionHandler (@ControllerAdvice)

1. 범위: 주로 애플리케이션 내부에서 발생하는 예외를 처리합니다.
2. 처리 시점: 컨트롤러 메서드 실행 중 발생하는 예외를 잡아냅니다.
3. 구현 방법: @ControllerAdvice 또는 @RestControllerAdvice 어노테이션을 사용하여 구현합니다.
4. 유연성: 다양한 예외 유형에 대해 세밀한 제어가 가능합니다.
5. 사용 사례: 비즈니스 로직 관련 예외, 유효성 검사 예외 등을 처리하는 데 적합합니다.

## ErrorController

1. 범위: 서블릿 컨테이너 수준에서 처리되지 않은 모든 예외를 다룹니다.
2. 처리 시점: 다른 모든 예외 처리 메커니즘이 실패한 후 최후의 수단으로 작동합니다.
3. 구현 방법: ErrorController 인터페이스를 구현하거나 BasicErrorController를 확장합니다.
4. 유연성: GlobalExceptionHandler에 비해 상대적으로 제한적입니다.
5. 사용 사례: 404 Not Found, 500 Internal Server Error 등 일반적인 HTTP 오류를 처리하는 데 주로 사용됩니다.

일반적으로 GlobalExceptionHandler를 사용하여 대부분의 예외를 처리하고, ErrorController를 통해 처리되지 않은 예외에 대한 기본 응답을 제공하는 것이 좋습니다. 이렇게 하면 예외 처리의 세밀한 제어와 일관된 오류 응답을 모두 달성할 수 있습니다.

화이트 라벨 에러 페이지, ServletContainer, 그리고 ControllerAdvice는 Spring Boot 애플리케이션의 예외 처리 메커니즘에서 각각 중요한 역할을 합니다. 이들의 관계와 동작 방식을 자세히 설명하겠습니다.

## ServletContainer의 역할

1. 웹 애플리케이션 실행: ServletContainer(예: Tomcat)는 웹 애플리케이션을 호스팅하고 실행합니다.

2. 요청 수신: 클라이언트로부터의 모든 HTTP 요청을 최초로 받습니다.

3. 서블릿 라이프사이클 관리: 서블릿의 초기화, 요청 처리, 소멸 등 전체 라이프사이클을 관리합니다.

4. 오류 처리: 애플리케이션에서 처리되지 않은 예외를 최종적으로 처리합니다.

## ControllerAdvice의 역할

1. 전역 예외 처리: Spring MVC 애플리케이션 전체에 대한 중앙집중식 예외 처리를 제공합니다.

2. 예외 타입별 처리: @ExceptionHandler 어노테이션을 사용하여 특정 예외 타입에 대한 처리 로직을 정의합니다.

3. 모델 데이터 추가: @ModelAttribute를 사용하여 모든 컨트롤러에 공통적으로 사용될 모델 데이터를 추가할 수 있습니다.

4. 바인딩/검증 설정: @InitBinder를 사용하여 컨트롤러에 공통적으로 적용될 데이터 바인딩과 검증 로직을 정의할 수 있습니다.

## 화이트 라벨 에러 페이지

1. 기본 오류 응답: Spring Boot에서 제공하는 기본 오류 페이지입니다.

2. 최후의 수단: ControllerAdvice나 다른 예외 처리 메커니즘이 예외를 처리하지 못했을 때 표시됩니다.

3. 커스터마이징 가능: 개발자가 직접 정의한 오류 페이지로 대체할 수 있습니다.

## 예외 처리 흐름

1. ServletContainer가 요청을 받아 DispatcherServlet으로 전달

2. DispatcherServlet이 요청을 처리하는 도중 예외 발생

3. ControllerAdvice의 @ExceptionHandler 메서드가 예외 처리를 시도
    - 처리 성공 시: 정의된 응답 반환
    - 처리 실패 시: 다음 단계로 진행

4. ErrorController (구현되어 있는 경우) 가 예외 처리를 시도
    - 처리 성공 시: 정의된 오류 페이지 반환
    - 처리 실패 시: 다음 단계로 진행

5. ServletContainer의 오류 처리 메커니즘 작동
    - web.xml에 정의된 error-page 설정에 따라 처리
    - 설정이 없으면 기본 오류 페이지 표시

6. 화이트 라벨 에러 페이지 표시 (Spring Boot의 기본 제공 오류 페이지)

## 예시 시나리오

1. 클라이언트가 요청을 보냄
2. ServletContainer(Tomcat)이 요청을 받아 DispatcherServlet으로 전달
3. 컨트롤러에서 NullPointerException 발생
4. ControllerAdvice의 @ExceptionHandler(NullPointerException.class) 메서드가 처리 시도
5. ControllerAdvice에서 처리하지 못하면, ErrorController로 전달
6. ErrorController도 처리하지 못하면, ServletContainer의 오류 처리 메커니즘으로 전달
7. ServletContainer도 처리하지 못하면, 최종적으로 화이트 라벨 에러 페이지 표시

이러한 구조를 통해 Spring Boot는 다양한 수준에서 예외를 처리할 수 있는 유연한 메커니즘을 제공합니다. ServletContainer는 전체 애플리케이션의 실행 환경을 제공하고, ControllerAdvice는 Spring MVC 레벨에서의 중앙집중식 예외 처리를 담당하며, 화이트 라벨 에러 페이지는 최후의 안전망 역할을 합니다. 개발자는 이러한 계층적 구조를 이해하고 활용하여 애플리케이션의 요구사항에 맞는 세밀한 예외 처리 전략을 구현할 수 있습니다.
