# Exam Loader API

This repository contains a sample API for loading and managing exam data using `ExamController`. The API provides endpoints for uploading, retrieving, and deleting exam information.

## Table of Contents
- [Endpoints](#endpoints)
    - [Upload Exams File](#1-upload-exams-file)
    - [Get All Exams](#2-get-all-exams)
    - [Get Exams by Code](#3-get-exams-by-code)
    - [Delete All Exams](#4-delete-all-exams)


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
```bash
curl -X GET "http://localhost:8080/exams" \
-H "Accept: application/json"

```

```bash
curl -X GET "http://localhost:8080/exams?code=61086009" \
     -H "Accept: application/json"


```

```bash
curl -X DELETE "http://localhost:8080/exams"

```
