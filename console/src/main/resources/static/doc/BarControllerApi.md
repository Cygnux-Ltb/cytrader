
# 历史行情接口
## 获取1分钟Bar

**URL:** `/bar`

**Type:** `GET`


**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 获取行情Bar



**Query-parameters:**

| Parameter | Type | Required | Description | Since |
|-----------|------|----------|-------------|-------|
|tradingDay|int32|true|    交易日|-|
|instrumentCode|string|true|标的代码|-|


**Request-example:**
```
curl -X GET -i /bar?tradingDay=857&instrumentCode=14444 --data '&857&14444'
```

**Response-fields:**

| Field | Type | Description | Since |
|-------|------|-------------|-------|
|instrumentCode|string|交易标的代码 [*]|-|
|tradingDay|int32|交易日 [*]|-|
|actualDate|int32|实际日期 [*]|-|
|timePoint|int32|时间点 [*]|-|
|open|double|开盘价 [*]|-|
|high|double|最高价 [*]|-|
|low|double|最低价 [*]|-|
|close|double|收盘价 [*]|-|
|volume|double|成交量 [*]|-|
|turnover|double|成交额 [*]|-|

**Response-example:**
```
[
  {
    "instrumentCode": "14444",
    "tradingDay": 311,
    "actualDate": 272,
    "timePoint": 326,
    "open": 18.73,
    "high": 14.16,
    "low": 12.28,
    "close": 3.99,
    "volume": 80.93,
    "turnover": 32.92
  }
]
```

## Put Bar

**URL:** `/bar`

**Type:** `POST`


**Content-Type:** `APPLICATION_JSON_UTF8`

**Description:** 添加行情Bar





**Request-example:**
```
curl -X POST -H 'Content-Type: APPLICATION_JSON_UTF8' -i /bar
```

**Response-example:**
```
OK
```

