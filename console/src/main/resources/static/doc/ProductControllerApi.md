
# 
## Get all product

**URL:** `/product`

**Type:** `GET`


**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** Get all product





**Request-example:**
```
curl -X GET -i /product
```

**Response-fields:**

| Field | Type | Description | Since |
|-------|------|-------------|-------|
|uid|int64|No comments found.|-|
|productId|int32|No comments found.|-|
|productName|string|No comments found.|-|
|subAccountId|string|No comments found.|-|
|userId|string|No comments found.|-|
|interfaceType|string|No comments found.|-|

**Response-example:**
```
[
  {
    "uid": 134,
    "productId": 924,
    "productName": "john.kling",
    "subAccountId": "84",
    "userId": "84",
    "interfaceType": "aph4ra"
  }
]
```

## 

**URL:** `/product/initialized`

**Type:** `PUT`


**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 





**Request-example:**
```
curl -X PUT -i /product/initialized
```

**Response-example:**
```
{}
```

## 

**URL:** `/product/{productId}`

**Type:** `GET`


**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 


**Path-parameters:**

| Parameter | Type | Required | Description | Since |
|-----------|------|----------|-------------|-------|
|productId|int32|true|int|-|



**Request-example:**
```
curl -X GET -i /product/724
```

**Response-fields:**

| Field | Type | Description | Since |
|-------|------|-------------|-------|
|uid|int64|No comments found.|-|
|productId|int32|No comments found.|-|
|productName|string|No comments found.|-|
|subAccountId|string|No comments found.|-|
|userId|string|No comments found.|-|
|interfaceType|string|No comments found.|-|

**Response-example:**
```
{
  "uid": 765,
  "productId": 158,
  "productName": "john.kling",
  "subAccountId": "84",
  "userId": "84",
  "interfaceType": "o51do7"
}
```

