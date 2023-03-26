
# 
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
curl -X GET -i /pnl/605?strategyId=415 --data '&415'
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
    "uid": 311,
    "strategyId": 234,
    "instrumentCode": "84389",
    "tradingDay": 147,
    "avgBuyPrice": 69.75,
    "avgSellPrice": 36.55,
    "buyQuantity": 68,
    "sellQuantity": 889,
    "todayLong": 57,
    "todayShort": 863,
    "yesterdayLong": 527,
    "yesterdayShort": 59,
    "netPosition": 60,
    "aggregatedFee": 27.44,
    "approved": 878,
    "turnover": 473
  }
]
```

## Put PnlDaily

**URL:** `/pnl/`

**Type:** `PUT`


**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** Put PnlDaily





**Request-example:**
```
curl -X PUT -i /pnl/
```

**Response-example:**
```
{}
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
curl -X GET -i /pnl/settlement?strategyId=52&tradingDay=648 --data '&52&648'
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
    "uid": 322,
    "strategyId": 508,
    "instrumentCode": "84389",
    "tradingDay": 593,
    "position": 999,
    "pnlTotal": 3.05,
    "pnlNet": 62.12,
    "tradeCost": 80.93,
    "exposure": 15.76,
    "approved": 650
  }
]
```

