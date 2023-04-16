
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
curl -X GET -i /pnl/705?strategyId=416 --data '&416'
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
    "uid": 765,
    "strategyId": 723,
    "instrumentCode": "29541",
    "tradingDay": 963,
    "avgBuyPrice": 45.23,
    "avgSellPrice": 9.60,
    "buyQuantity": 46,
    "sellQuantity": 113,
    "todayLong": 513,
    "todayShort": 315,
    "yesterdayLong": 667,
    "yesterdayShort": 471,
    "netPosition": 610,
    "aggregatedFee": 25.68,
    "approved": 333,
    "turnover": 286
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
curl -X GET -i /pnl/settlement?strategyId=298&tradingDay=756 --data '&298&756'
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
    "uid": 60,
    "strategyId": 535,
    "instrumentCode": "29541",
    "tradingDay": 638,
    "position": 840,
    "pnlTotal": 63.02,
    "pnlNet": 53.91,
    "tradeCost": 71.82,
    "exposure": 61.05,
    "approved": 665
  }
]
```

