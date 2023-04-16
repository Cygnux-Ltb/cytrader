
# 产品服务接口
## 获取全部产品

**URL:** `/product/all`

**Type:** `GET`


**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 获取全部产品





**Request-example:**
```
curl -X GET -i /product/all
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
    "uid": 742,
    "productId": 628,
    "productName": "damien.swift",
    "subAccountId": "92",
    "userId": "92",
    "interfaceType": "xcnpce"
  }
]
```

## 获取指定产品信息

**URL:** `/product`

**Type:** `GET`


**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 获取指定产品信息



**Query-parameters:**

| Parameter | Type | Required | Description | Since |
|-----------|------|----------|-------------|-------|
|productId|int32|true|int|-|


**Request-example:**
```
curl -X GET -i /product?productId=317 --data '&317'
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
  "uid": 263,
  "productId": 155,
  "productName": "damien.swift",
  "subAccountId": "92",
  "userId": "92",
  "interfaceType": "7ix1tz"
}
```

## 产品初始化

**URL:** `/product/init`

**Type:** `PUT`


**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 产品初始化





**Request-example:**
```
curl -X PUT -i /product/init
```

**Response-example:**
```
OK
```

