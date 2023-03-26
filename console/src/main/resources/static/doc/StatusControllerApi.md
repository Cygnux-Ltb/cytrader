
# 
## 

**URL:** `/status/`

**Type:** `GET`


**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 





**Request-example:**
```
curl -X GET -i /status/
```

**Response-fields:**

| Field | Type | Description | Since |
|-------|------|-------------|-------|
|productId|int32|No comments found.|-|
|strategyId|int32|No comments found.|-|
|instrumentCode|string|No comments found.|-|
|tradable|boolean|No comments found.|-|

**Response-example:**
```
[
  {
    "productId": 957,
    "strategyId": 15,
    "instrumentCode": "84389",
    "tradable": true
  }
]
```

## 

**URL:** `/status/command`

**Type:** `PUT`


**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 





**Request-example:**
```
curl -X PUT -i /status/command
```

**Response-example:**
```
{}
```

## 

**URL:** `/status/update`

**Type:** `PUT`


**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 



**Query-parameters:**

| Parameter | Type | Required | Description | Since |
|-----------|------|----------|-------------|-------|
|productId|int32|true|int|-|


**Request-example:**
```
curl -X PUT -i /status/update --data 'productId=87'
```

**Response-example:**
```
{}
```

