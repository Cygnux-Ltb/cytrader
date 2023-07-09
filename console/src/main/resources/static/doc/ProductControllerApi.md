
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
    "uid": 946,
    "productId": 829,
    "productName": "wilhemina.hirthe",
    "subAccountId": "53",
    "userId": "53",
    "interfaceType": "suclpl"
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
curl -X GET -i /product?productId=49 --data '&49'
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
  "uid": 946,
  "productId": 344,
  "productName": "wilhemina.hirthe",
  "subAccountId": "53",
  "userId": "53",
  "interfaceType": "ageuol"
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

