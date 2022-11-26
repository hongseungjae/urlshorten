# URL 단축 서비스

## 요구사항
* 단축 URL 생성 기능
* 생성된 단축 URL로 요청시 원래 URL로 리다이렉트
* 데이터 저장은 DB가 아닌 ArrayList에 저장
* Optional) 단축된 URL에 대한 요청 횟수 저장 및 요청 횟수 조회 기능

## 중요 포인트
* 단축 URL로 어떤 값을 사용할 것 인가?
* 나중에 프론트엔드 페이지가 추가된다면 이 API 서버가 그대로 활용될 수 있는가?

## 구현방법
* 단축 URL과 원본 URL 매칭하는 방법


  단축 URL이 들어왔을 때 원본 URL로 랜덤 액세스 할 수 있는 리스트의 인덱스를 이용하는 방법

  1. http://www.naver.com 들어왔을 시 리스트에 추가
  2. 해당 index를 Encoding하여 단축 URL 생성 -> http://localhost:8080/m/
  3. http://localhost:8080/m/ 단축 URL이 들어올 시 Decoding
  4. Decoding한 값을 list에 인덱스에 매칭 후 원본 URL을 반환

* URL Encoding 방법


  Base64대신에 URL 예약어와 패딩 문자 = 에 안전한 Base62를 사용


## 테스트
* 단축 URL 생성 기능
  1. postman 이용
![image](https://user-images.githubusercontent.com/41093183/200281163-f9edabe2-6f4a-4e71-8cd9-89d5e02260b4.png)
![image](https://user-images.githubusercontent.com/41093183/200281481-833788a8-a238-4744-bd5e-c8d32f1633d5.png)

  2. http://localhost:8080/hello/url 이용
  
  
  ![image](https://user-images.githubusercontent.com/41093183/200282299-af125293-7b4f-4476-825a-dbc3445c12d1.png)
  ![image](https://user-images.githubusercontent.com/41093183/200282395-ec052451-1470-4404-92f7-654e20965123.png)




<br />

* 생성된 단축 URL로 요청시 원래 URL로 리다이렉트
![image](https://user-images.githubusercontent.com/41093183/200281681-d3385db8-4cae-42d2-bae7-e7cc450cab7c.png)

<br />

* 단축된 URL에 대한 요청 횟수 조회 기능
![image](https://user-images.githubusercontent.com/41093183/200281832-5f6d4005-4d28-42c9-b217-fb0a293cb85c.png)


## 구현 시 이슈
* 저장소로 List를 사용 시 ArrayList\<String\> list로 사용
  - String 타입에서 urlDto로 타입으로 변경 후 많은 코드 수정이 필요
  - 제네릭을 이용하여 ArrayList\<T\> list 형태로 사용하여야 했음

* 추후 List에서 DB를 이용 시 많은 코드가 변경 될것으로 보여 IStorage\<UrlDTO\> listStorage = new ListStorage\<\>()와 같은 형식으로 인터페이스를 사용함  
