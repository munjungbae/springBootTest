/* Java 1.8 샘플 코드 */ import java.io.BufferedReader; import java.io.InputStreamReader; import java.net.HttpURLConnection; import java.net.URI; import java.net.URL; import java.net.URLEncoder; class
ApiTest { public static void main(String[] args) throws Exception { String dataName = "데이터명"; String serviceKey = "서비스키"; String pageNo = "1"; String numOfRows = "10"; /* API를 호출하기 위한 URL 생성 */
StringBuilder urlBuilder = new StringBuilder("https://www.safetydata.go.kr/V2/api/DSSP-IF-10944"); /* API 키 */ urlBuilder.append("?" + "serviceKey=" + serviceKey); /* 페이지 번호 */ urlBuilder.append("&" +
"pageNo=" + pageNo); /* 페이지당 데이터 수 */ urlBuilder.append("&" + "numOfRows=" + numOfRows); System.out.println(urlBuilder.toString()); URI uri = new URI(urlBuilder.toString()); URL url = uri.toURL(); /*
API 호출하기 위한 HTTP 커넥션과 리더 생성 */ HttpURLConnection connection = (HttpURLConnection) url.openConnection(); connection.setRequestMethod("GET"); BufferedReader reader; /* API 호출 */ connection.connect(); if
(connection.getResponseCode() >= 200) { reader = new BufferedReader(new InputStreamReader(connection.getInputStream())); } else { System.err.println("Connection failed."); return; } /* API 응답에서 데이터 추출
*/ StringBuilder sb = new StringBuilder(); String line; while ((line = reader.readLine()) != null) { sb.append(line); reader.close(); connection.disconnect(); } } }
