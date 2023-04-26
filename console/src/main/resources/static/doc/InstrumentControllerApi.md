
# 交易标的查询接口
## 获取结算价格

**URL:** `/instrument/settlement`

**Type:** `GET`


**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 获取结算价格



**Query-parameters:**

| Parameter | Type | Required | Description | Since |
|-----------|------|----------|-------------|-------|
|tradingDay|int32|true|    int|-|
|instrumentCode|string|true|String|-|


**Request-example:**
```
curl -X GET -i /instrument/settlement?tradingDay=443&instrumentCode=14444 --data '&443&14444'
```

**Response-fields:**

| Field | Type | Description | Since |
|-------|------|-------------|-------|
|instrumentCode|string|交易标的代码 [*]|-|
|tradingDay|int32|交易日 [*]|-|
|closePrice|double|收盘价|-|
|openPrice|double|开盘价|-|
|settlementPrice|double|结算价|-|

**Response-example:**
```
[
  {
    "instrumentCode": "14444",
    "tradingDay": 855,
    "closePrice": 56.40,
    "openPrice": 42.86,
    "settlementPrice": 99.82
  }
]
```

## 获取最新价格

**URL:** `/instrument/last`

**Type:** `GET`


**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 获取最新价格



**Query-parameters:**

| Parameter | Type | Required | Description | Since |
|-----------|------|----------|-------------|-------|
|instrumentCodes|string|true|String|-|


**Request-example:**
```
curl -X GET -i /instrument/last?instrumentCodes=xusluo --data '&xusluo'
```

**Response-fields:**

| Field | Type | Description | Since |
|-------|------|-------------|-------|
|instrumentCode|string|No comments found.|-|
|lastPrice|double|No comments found.|-|

**Response-example:**
```
[
  {
    "instrumentCode": "14444",
    "lastPrice": 4.56
  }
]
```

## 更新最新价格

**URL:** `/instrument/last`

**Type:** `PUT`


**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 更新最新价格





**Request-example:**
```
curl -X PUT -i /instrument/last
```

**Response-example:**
```
OK
```

## 获取可交易的标的

**URL:** `/instrument/tradable/{tradingDay}/{symbol}`

**Type:** `GET`


**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 获取可交易的标的


**Path-parameters:**

| Parameter | Type | Required | Description | Since |
|-----------|------|----------|-------------|-------|
|tradingDay|int32|true|String|-|
|symbol|string|true|    String|-|



**Request-example:**
```
curl -X GET -i /instrument/tradable/627/i0zs2e
```

**Response-example:**
```
OK
```

