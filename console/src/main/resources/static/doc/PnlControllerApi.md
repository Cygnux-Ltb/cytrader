
# PNL服务接口
## 查询PNL

**URL:** `/pnl/{tradingDay}`

**Type:** `GET`


**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 查询PNL


**Path-parameters:**

| Parameter | Type | Required | Description | Since |
|-----------|------|----------|-------------|-------|
|tradingDay|int32|true|int|-|

**Query-parameters:**

| Parameter | Type | Required | Description | Since |
|-----------|------|----------|-------------|-------|
|strategyId|int32|true|int|-|


**Request-example:**
```
curl -X GET -i /pnl/414?strategyId=238 --data '&238'
```

**Response-fields:**

| Field | Type | Description | Since |
|-------|------|-------------|-------|
|uid|int64|No comments found.|-|
|strategyId|int32|strategyId|-|
|instrumentCode|string|instrumentCode|-|
|tradingDay|int32|tradingDay|-|
|avgBuyPrice|double|avgBuyPrice|-|
|avgSellPrice|double|avgSellPrice|-|
|buyQuantity|int32|buyQuantity|-|
|sellQuantity|int32|sellQuantity|-|
|todayLong|int32|todayLong|-|
|todayShort|int32|todayShort|-|
|yesterdayLong|int32|yesterdayLong|-|
|yesterdayShort|int32|yesterdayShort|-|
|netPosition|int32|netPosition|-|
|aggregatedFee|double|aggregatedFee|-|
|approved|int32|approved|-|
|turnover|int32|turnover|-|

**Response-example:**
```
[
  {
    "uid": 108,
    "strategyId": 987,
    "instrumentCode": "14444",
    "tradingDay": 629,
    "avgBuyPrice": 84.22,
    "avgSellPrice": 45.83,
    "buyQuantity": 103,
    "sellQuantity": 459,
    "todayLong": 519,
    "todayShort": 159,
    "yesterdayLong": 734,
    "yesterdayShort": 929,
    "netPosition": 381,
    "aggregatedFee": 28.83,
    "approved": 280,
    "turnover": 87
  }
]
```

## Put PnlDaily

**URL:** `/pnl`

**Type:** `PUT`


**Content-Type:** `APPLICATION_JSON_UTF8`

**Description:** Put PnlDaily





**Request-example:**
```
curl -X PUT -H 'Content-Type: APPLICATION_JSON_UTF8' -i /pnl
```

**Response-example:**
```
OK
```

## 查询结算PNL

**URL:** `/pnl/settlement`

**Type:** `GET`


**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 查询结算PNL



**Query-parameters:**

| Parameter | Type | Required | Description | Since |
|-----------|------|----------|-------------|-------|
|strategyId|int32|true|int|-|
|tradingDay|int32|true|int|-|


**Request-example:**
```
curl -X GET -i /pnl/settlement?strategyId=684&tradingDay=410 --data '&684&410'
```

**Response-fields:**

| Field | Type | Description | Since |
|-------|------|-------------|-------|
|uid|int64|No comments found.|-|
|strategyId|int32|strategyId|-|
|instrumentCode|string|instrumentCode|-|
|tradingDay|int32|tradingDay|-|
|position|int32|position|-|
|pnlTotal|double|pnlTotal|-|
|pnlNet|double|pnlNet|-|
|tradeCost|double|tradeCost|-|
|exposure|double|exposure|-|
|approved|int32|approved|-|

**Response-example:**
```
[
  {
    "uid": 721,
    "strategyId": 149,
    "instrumentCode": "14444",
    "tradingDay": 201,
    "position": 969,
    "pnlTotal": 16.67,
    "pnlNet": 25.64,
    "tradeCost": 43.55,
    "exposure": 17.09,
    "approved": 481
  }
]
```

