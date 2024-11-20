# Exam Loader API

This repository contains a sample API for loading and managing exam data. The API provides endpoints for uploading, retrieving, and deleting exam information.

## Table of Contents
- [Getting Started](#getting-started)
    - [Prerequisites](#prerequisites)
    - [Running the Application](#running-the-application)
- 
- [Endpoints](#endpoints)
    - [Upload Exams File](#1-upload-exams-file)
    - [Get All Exams](#2-get-all-exams)
    - [Get Exams by Code](#3-get-exams-by-code)
    - [Delete All Exams](#4-delete-all-exams)

## Getting Started
### Prerequisites
- [Java 17](https://jdk.java.net/java-se-ri/17)
- [Lombok](https://projectlombok.org/download)
- [Maven](https://maven.apache.org/download.cgi)

### Running the Application
1. Clone the repository:
```bash
git clone git@github.com:claytonrm/examloader.git
```
2. Open the project/repo on your IDE and run [ExamloaderApplication.java](src/main/java/com/demo/examloader/ExamloaderApplication.java) main class or in your bash (project root) `mvn spring-boot:run` (Ctrl + C to stop running it)

### Running Tests
```shell
mvn test
```

## Endpoints

### 1. Upload Exams File
**Endpoint:** `POST /exams/upload`

Uploads an exam file (e.g., a CSV file) to the server.

**Example `curl` Command:**
```bash
curl -X POST "http://localhost:8080/exams/upload" \
     -H "Content-Type: multipart/form-data" \
     -F "file=@/Users/yourUsername/Downloads/exercise.csv"
```
### 2. Get All Exams
**Endpoint:** `GET /exams`

```bash
curl -X GET "http://localhost:8080/exams" \
-H "Accept: application/json"

```
### 3. Get Exams by Code
```bash
curl -X GET "http://localhost:8080/exams?code=61086009" \
     -H "Accept: application/json"

```
### 4. Delete All Exams
```bash
curl -X DELETE "http://localhost:8080/exams"

```
