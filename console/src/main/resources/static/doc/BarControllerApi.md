
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
curl -X GET -i /bar?tradingDay=777&instrumentCode=29541 --data '&777&29541'
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
    "instrumentCode": "29541",
    "tradingDay": 169,
    "actualDate": 983,
    "timePoint": 307,
    "open": 92.03,
    "high": 53.00,
    "low": 49.30,
    "close": 46.53,
    "volume": 12.18,
    "turnover": 47.31
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

