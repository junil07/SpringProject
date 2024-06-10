package com.example.demo.seller.controller;

import com.example.demo.seller.domain.Orderitem;
import com.example.demo.seller.service.OrderitemService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/api")
public class DownloadController {

    @Autowired
    private OrderitemService orderItemService;

    //주문통합검색
    @PostMapping("/ordersearch/download")
    public ResponseEntity<byte[]> downloadordersearch(@RequestBody List<Map<String, String>> orderItems) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("ordersearch Status");

        // 헤더 행 생성
        Row headerRow = sheet.createRow(0);
        String[] headers = {"상품주문번호", "상품코드", "상품명", "개수", "가격", "주문일시", "주문상태", "배송상태", "처리상태"};
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
        }

        // 데이터 행 생성
        int rowNum = 1;
        for (Map<String, String> item : orderItems) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(item.get("orderlistId"));
            row.createCell(1).setCellValue(item.get("productCode"));
            row.createCell(2).setCellValue(item.get("productName"));
            row.createCell(3).setCellValue(item.get("orderitemPcount"));
            row.createCell(4).setCellValue(item.get("productPrice"));
            row.createCell(5).setCellValue(item.get("orderlistDate"));
            row.createCell(6).setCellValue(item.get("orderitemPstatus"));
            row.createCell(7).setCellValue(item.get("orderitemDstatus"));
            row.createCell(8).setCellValue(item.get("orderitemCase"));
        }

        // 엑셀 파일을 바이트 배열로 변환
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        workbook.write(bos);
        workbook.close();

        byte[] excelBytes = bos.toByteArray();

        // 오늘 날짜를 가져와서 형식에 맞게 변환
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = today.format(formatter);

        // 파일명에 오늘 날짜를 포함시킴
        String fileName = formattedDate + " 주문통합.xlsx";
        String encodedFileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8.toString()).replaceAll("\\+", "%20");

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        responseHeaders.setContentDispositionFormData("attachment", encodedFileName);

        return ResponseEntity
                .ok()
                .headers(responseHeaders)
                .body(excelBytes);
    }

    //미결제확인
    @PostMapping("/outstanding/download")
    public ResponseEntity<byte[]> downloadoutstanding(@RequestBody List<Map<String, String>> orderItems) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("outstanding Status");

        // 헤더 행 생성
        Row headerRow = sheet.createRow(0);
        String[] headers = {"상품주문번호", "통합주문번호", "주문일시", "구매자명", "구매자ID", "상품코드", "상품명", "결제상태"};
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
        }

        // 데이터 행 생성
        int rowNum = 1;
        for (Map<String, String> item : orderItems) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(item.get("orderitemId"));
            row.createCell(1).setCellValue(item.get("orderlistId"));
            row.createCell(2).setCellValue(item.get("orderlistDate"));
            row.createCell(3).setCellValue(item.get("buyerName"));
            row.createCell(4).setCellValue(item.get("buyerId"));
            row.createCell(5).setCellValue(item.get("productCode"));
            row.createCell(6).setCellValue(item.get("productName"));
            row.createCell(7).setCellValue(item.get("orderitemPstatus"));
        }

        // 엑셀 파일을 바이트 배열로 변환
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        workbook.write(bos);
        workbook.close();

        byte[] excelBytes = bos.toByteArray();

        // 오늘 날짜를 가져와서 형식에 맞게 변환
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = today.format(formatter);

        // 파일명에 오늘 날짜를 포함시킴
        String fileName = formattedDate + " 미결제.xlsx";
        String encodedFileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8.toString()).replaceAll("\\+", "%20");

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        responseHeaders.setContentDispositionFormData("attachment", encodedFileName);

        return ResponseEntity
                .ok()
                .headers(responseHeaders)
                .body(excelBytes);
    }

    //발주관리
    @PostMapping("/order/download")
    public ResponseEntity<byte[]> downloadorder(@RequestBody List<Map<String, String>> orderItems) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("order Status");

        // 헤더 행 생성
        Row headerRow = sheet.createRow(0);
        String[] headers = {"상품주문번호", "통합주문번호", "구매자이름", "구매자ID", "제품CODE", "제품명", "제품가격", "주문일", "결제상태", "배송상태", "주문처리일", "주소"};
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
        }

        // 데이터 행 생성
        int rowNum = 1;
        for (Map<String, String> item : orderItems) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(item.get("orderitemId"));
            row.createCell(1).setCellValue(item.get("orderlistId"));
            row.createCell(2).setCellValue(item.get("buyerName"));
            row.createCell(3).setCellValue(item.get("buyerId"));
            row.createCell(4).setCellValue(item.get("productCode"));
            row.createCell(5).setCellValue(item.get("productName"));
            row.createCell(6).setCellValue(item.get("productPrice"));
            row.createCell(7).setCellValue(item.get("orderlistDate"));
            row.createCell(8).setCellValue(item.get("orderitemPstatus"));
            row.createCell(9).setCellValue(item.get("orderitemDstatus"));
            row.createCell(10).setCellValue(item.get("orderitemDate"));
            row.createCell(11).setCellValue(item.get("orderlistAddress"));
        }

        // 엑셀 파일을 바이트 배열로 변환
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        workbook.write(bos);
        workbook.close();

        byte[] excelBytes = bos.toByteArray();

        // 오늘 날짜를 가져와서 형식에 맞게 변환
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = today.format(formatter);

        // 파일명에 오늘 날짜를 포함시킴
        String fileName = formattedDate + " 배송확인.xlsx";
        String encodedFileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8.toString()).replaceAll("\\+", "%20");

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        responseHeaders.setContentDispositionFormData("attachment", encodedFileName);

        return ResponseEntity
                .ok()
                .headers(responseHeaders)
                .body(excelBytes);
    }

    //배송현황관리
    @PostMapping("/deliverystatus/download")
    public ResponseEntity<byte[]> downloaddeliverystatus(@RequestBody List<Map<String, String>> orderItems) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Delivery Status");

        // 헤더 행 생성
        Row headerRow = sheet.createRow(0);
        String[] headers = {"상품주문번호", "통합주문번호", "상품코드", "상품이름", "상품구매 개수", "배송처리일", "배송상태", "배송지역", "처리상태"};
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
        }

        // 데이터 행 생성
        int rowNum = 1;
        for (Map<String, String> item : orderItems) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(item.get("orderitemId"));
            row.createCell(1).setCellValue(item.get("orderlistId"));
            row.createCell(2).setCellValue(item.get("productCode"));
            row.createCell(3).setCellValue(item.get("productName"));
            row.createCell(4).setCellValue(item.get("orderitemPcount"));
            row.createCell(5).setCellValue(item.get("orderitemDate"));
            row.createCell(6).setCellValue(item.get("orderitemDstatus"));
            row.createCell(7).setCellValue(item.get("orderlistAddress"));
            row.createCell(8).setCellValue(item.get("orderitemCase"));
        }

        // 엑셀 파일을 바이트 배열로 변환
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        workbook.write(bos);
        workbook.close();

        byte[] excelBytes = bos.toByteArray();

        // 오늘 날짜를 가져와서 형식에 맞게 변환
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = today.format(formatter);

        // 파일명에 오늘 날짜를 포함시킴
        String fileName = formattedDate + " 배송현황.xlsx";
        String encodedFileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8.toString()).replaceAll("\\+", "%20");

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        responseHeaders.setContentDispositionFormData("attachment", encodedFileName);

        return ResponseEntity
                .ok()
                .headers(responseHeaders)
                .body(excelBytes);
    }

    //구매확정내역
    @PostMapping("/buyconfirmation/download")
    public ResponseEntity<byte[]> downloadbuyconfirmation(@RequestBody List<Map<String, String>> orderItems) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("buyconfirmation Status");

        // 헤더 행 생성
        Row headerRow = sheet.createRow(0);
        String[] headers = {"상품주문번호", "통합주문번호", "구매확정일", "제품CODE", "제품명", "제품가격", "구매자ID", "구매자명"};
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
        }

        // 데이터 행 생성
        int rowNum = 1;
        for (Map<String, String> item : orderItems) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(item.get("orderitemId"));
            row.createCell(1).setCellValue(item.get("orderlistId"));
            row.createCell(2).setCellValue(item.get("orderitemDate"));
            row.createCell(3).setCellValue(item.get("productCode"));
            row.createCell(4).setCellValue(item.get("productName"));
            row.createCell(5).setCellValue(item.get("productPrice"));
            row.createCell(6).setCellValue(item.get("buyerId"));
            row.createCell(7).setCellValue(item.get("buyerName"));
        }

        // 엑셀 파일을 바이트 배열로 변환
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        workbook.write(bos);
        workbook.close();

        byte[] excelBytes = bos.toByteArray();

        // 오늘 날짜를 가져와서 형식에 맞게 변환
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = today.format(formatter);

        // 파일명에 오늘 날짜를 포함시킴
        String fileName = formattedDate + " 구매확정내역.xlsx";
        String encodedFileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8.toString()).replaceAll("\\+", "%20");

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        responseHeaders.setContentDispositionFormData("attachment", encodedFileName);

        return ResponseEntity
                .ok()
                .headers(responseHeaders)
                .body(excelBytes);
    }

    //취소내역
    @PostMapping("/cancel/download")
    public ResponseEntity<byte[]> downloadcancel(@RequestBody List<Map<String, String>> orderItems) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("cancel Status");

        // 헤더 행 생성
        Row headerRow = sheet.createRow(0);
        String[] headers = {"상품주문번호", "제품CODE", "제품명", "제품가격", "취소요청일", "결제상태", "배송상태", "취소확인"};
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
        }

        // 데이터 행 생성
        int rowNum = 1;
        for (Map<String, String> item : orderItems) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(item.get("orderitemId"));
            row.createCell(1).setCellValue(item.get("productCode"));
            row.createCell(2).setCellValue(item.get("productName"));
            row.createCell(3).setCellValue(item.get("productPrice"));
            row.createCell(4).setCellValue(item.get("orderitemDate"));
            row.createCell(5).setCellValue(item.get("orderitemPstatus"));
            row.createCell(6).setCellValue(item.get("orderitemDstatus"));
            row.createCell(7).setCellValue(item.get("orderitemCase"));
        }

        // 엑셀 파일을 바이트 배열로 변환
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        workbook.write(bos);
        workbook.close();

        byte[] excelBytes = bos.toByteArray();

        // 오늘 날짜를 가져와서 형식에 맞게 변환
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = today.format(formatter);

        // 파일명에 오늘 날짜를 포함시킴
        String fileName = formattedDate + " 취소내역.xlsx";
        String encodedFileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8.toString()).replaceAll("\\+", "%20");

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        responseHeaders.setContentDispositionFormData("attachment", encodedFileName);

        return ResponseEntity
                .ok()
                .headers(responseHeaders)
                .body(excelBytes);
    }

    //반품내역
    @PostMapping("/pereturn/download")
    public ResponseEntity<byte[]> downloadpereturn(@RequestBody List<Map<String, String>> orderItems) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("preturn Status");

        // 헤더 행 생성
        Row headerRow = sheet.createRow(0);
        String[] headers = {"상품주문번호", "제품CODE", "제품명", "제품가격", "주문일", "주문상태", "반품요청일", "반품처리상태"};
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
        }

        // 데이터 행 생성
        int rowNum = 1;
        for (Map<String, String> item : orderItems) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(item.get("orderitemId"));
            row.createCell(1).setCellValue(item.get("productCode"));
            row.createCell(2).setCellValue(item.get("productName"));
            row.createCell(3).setCellValue(item.get("productPrice"));
            row.createCell(4).setCellValue(item.get("orderitemDate"));
            row.createCell(5).setCellValue(item.get("orderitemDstatus"));
            row.createCell(6).setCellValue(item.get("orderitemDate"));
            row.createCell(7).setCellValue(item.get("orderitemCase"));
        }

        // 엑셀 파일을 바이트 배열로 변환
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        workbook.write(bos);
        workbook.close();

        byte[] excelBytes = bos.toByteArray();

        // 오늘 날짜를 가져와서 형식에 맞게 변환
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = today.format(formatter);

        // 파일명에 오늘 날짜를 포함시킴
        String fileName = formattedDate + " 반품내역.xlsx";
        String encodedFileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8.toString()).replaceAll("\\+", "%20");

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        responseHeaders.setContentDispositionFormData("attachment", encodedFileName);

        return ResponseEntity
                .ok()
                .headers(responseHeaders)
                .body(excelBytes);
    }

    //교환내역
    @PostMapping("/exchange/download")
    public ResponseEntity<byte[]> downloadexchange(@RequestBody List<Map<String, String>> orderItems) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("exchange Status");

        // 헤더 행 생성
        Row headerRow = sheet.createRow(0);
        String[] headers = {"상품주문번호", "통합주문번호", "제품CODE", "제품명", "제품가격", "주문상태", "주문일", "교환요청일", "교환처리상태"};
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
        }

        // 데이터 행 생성
        int rowNum = 1;
        for (Map<String, String> item : orderItems) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(item.get("orderitemId"));
            row.createCell(1).setCellValue(item.get("orderlistId"));
            row.createCell(2).setCellValue(item.get("productCode"));
            row.createCell(3).setCellValue(item.get("productName"));
            row.createCell(4).setCellValue(item.get("productPrice"));
            row.createCell(5).setCellValue(item.get("orderlistDate"));
            row.createCell(6).setCellValue(item.get("orderitemDstatus"));
            row.createCell(7).setCellValue(item.get("orderitemDate"));
            row.createCell(8).setCellValue(item.get("orderitemCase"));
        }

        // 엑셀 파일을 바이트 배열로 변환
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        workbook.write(bos);
        workbook.close();

        byte[] excelBytes = bos.toByteArray();

        // 오늘 날짜를 가져와서 형식에 맞게 변환
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = today.format(formatter);

        // 파일명에 오늘 날짜를 포함시킴
        String fileName = formattedDate + " 교환내역.xlsx";
        String encodedFileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8.toString()).replaceAll("\\+", "%20");

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        responseHeaders.setContentDispositionFormData("attachment", encodedFileName);

        return ResponseEntity
                .ok()
                .headers(responseHeaders)
                .body(excelBytes);
    }
}
