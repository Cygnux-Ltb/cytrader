
# 订单服务接口
## 查询Order

**URL:** `/order/{tradingDay}`

**Type:** `GET`


**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 查询Order


**Path-parameters:**

| Parameter | Type | Required | Description | Since |
|-----------|------|----------|-------------|-------|
|tradingDay|int32|true|    String|-|

**Query-parameters:**

| Parameter | Type | Required | Description | Since |
|-----------|------|----------|-------------|-------|
|strategyId|int32|true|    int|-|
|investorId|string|true|    String|-|
|instrumentCode|string|true|int|-|


**Request-example:**
```
curl -X GET -i /order/582?strategyId=162&investorId=92&instrumentCode=29541 --data '&162&92&29541'
```

**Response-fields:**

| Field | Type | Description | Since |
|-------|------|-------------|-------|
|uid|int64|No comments found.|-|
|tradingDay|int32|tradingDay [*]|-|
|strategyId|int32|strategyId [*]|-|
|instrumentCode|string|instrumentCode [*]|-|
|investorId|string|investorId [*]|-|
|brokerId|string|brokerId [*]|-|
|accountId|int32|accountId [*]|-|
|subAccountId|int32|subAccountId [*]|-|
|userId|int32|userId [*]|-|
|ordSysId|int64|ordSysId [*]|-|
|ordType|string|ordType|-|
|ordRef|string|orderRef|-|
|direction|string|direction|-|
|side|string|side|-|
|offerPrice|double|offerPrice|-|
|offerQty|int32|offerQty|-|
|insertTime|string|insertTime|-|
|updateTime|string|updateTime|-|
|cancelTime|string|cancelTime|-|
|frontId|int32|frontId|-|
|sessionId|int32|sessionId|-|
|fee|double|fee double 19_4|-|
|adaptorType|string|adaptorType|-|
|remark|string|remark|-|

**Response-example:**
```
[
  {
    "uid": 274,
    "tradingDay": 196,
    "strategyId": 575,
    "instrumentCode": "29541",
    "investorId": "92",
    "brokerId": "92",
    "accountId": 684,
    "subAccountId": 662,
    "userId": 233,
    "ordSysId": 323,
    "ordType": "a0nuix",
    "ordRef": "3asv5v",
    "direction": "3",
    "side": "a",
    "offerPrice": 39.48,
    "offerQty": 993,
    "insertTime": "2023-04-16 19:48:41",
    "updateTime": "2023-04-16 19:48:41",
    "cancelTime": "2023-04-16 19:48:41",
    "frontId": 350,
    "sessionId": 382,
    "fee": 54.45,
    "adaptorType": "k7hcwy",
    "remark": "hfd2m2"
  }
]
```

## 获取订单最新状态

**URL:** `/order/status`

**Type:** `GET`


**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 获取订单最新状态



**Query-parameters:**

| Parameter | Type | Required | Description | Since |
|-----------|------|----------|-------------|-------|
|tradingDay|int32|true|int|-|
|strategyId|int32|true|int|-|


**Request-example:**
```
curl -X GET -i /order/status?tradingDay=941&strategyId=660 --data '&941&660'
```

**Response-fields:**

| Field | Type | Description | Since |
|-------|------|-------------|-------|
|uid|int64|No comments found.|-|
|tradingDay|int32|tradingDay [*]|-|
|strategyId|int32|strategyId [*]|-|
|instrumentCode|string|instrumentCode [*]|-|
|investorId|string|investorId [*]|-|
|brokerId|string|brokerId [*]|-|
|accountId|int32|accountId [*]|-|
|subAccountId|int32|subAccountId [*]|-|
|userId|int32|userId [*]|-|
|ordSysId|int64|ord_sys_id [*]|-|
|ordRef|string|order_ref|-|
|ordMsgType|int32|order_msg_type|-|
|ordOffset|string|ord_offset|-|
|direction|string|direction|-|
|limitPrice|double|limit_price double 19_4|-|
|status|int32|order_status char|-|
|statusMsg|string|status_msg|-|
|brokerSysID|int64|brokerSysID|-|
|volume|int32|volume int|-|
|volumeFilled|int32|volume_filled int|-|
|volumeRemained|int32|volume_remained int|-|
|price|double|price double 19_4|-|
|tradeId|string|trade_id varchar 21|-|
|ordRejReason|int32|ord_rej_reason|-|
|insertTime|int32|insert_time|-|
|updateTime|int32|update_time|-|
|cancelTime|int32|cancel_time|-|
|remark|string|remark|-|

**Response-example:**
```
[
  {
    "uid": 495,
    "tradingDay": 64,
    "strategyId": 534,
    "instrumentCode": "29541",
    "investorId": "92",
    "brokerId": "92",
    "accountId": 149,
    "subAccountId": 338,
    "userId": 636,
    "ordSysId": 645,
    "ordRef": "eno9l2",
    "ordMsgType": 36,
    "ordOffset": "g",
    "direction": "v",
    "limitPrice": 8.09,
    "status": 385,
    "statusMsg": "zq0nzd",
    "brokerSysID": 228,
    "volume": 128,
    "volumeFilled": 508,
    "volumeRemained": 747,
    "price": 46.20,
    "tradeId": "92",
    "ordRejReason": 610,
    "insertTime": 874,
    "updateTime": 575,
    "cancelTime": 603,
    "remark": "ej3gax"
  }
]
```

## 新增订单

**URL:** `/order`

**Type:** `PUT`


**Content-Type:** `APPLICATION_JSON_UTF8`

**Description:** 新增订单





**Request-example:**
```
curl -X PUT -H 'Content-Type: APPLICATION_JSON_UTF8' -i /order
```

**Response-example:**
```
OK
```

