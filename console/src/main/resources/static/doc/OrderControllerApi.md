
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
curl -X GET -i /order/678?strategyId=299&investorId=53&instrumentCode=14444 --data '&299&53&14444'
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
    "uid": 98,
    "tradingDay": 698,
    "strategyId": 139,
    "instrumentCode": "14444",
    "investorId": "53",
    "brokerId": "53",
    "accountId": 702,
    "subAccountId": 331,
    "userId": 267,
    "ordSysId": 57,
    "ordType": "zj2hbl",
    "ordRef": "kgmvjx",
    "direction": "f",
    "side": "t",
    "offerPrice": 37.18,
    "offerQty": 131,
    "insertTime": "2023-04-23 11:45:51",
    "updateTime": "2023-04-23 11:45:51",
    "cancelTime": "2023-04-23 11:45:51",
    "frontId": 381,
    "sessionId": 685,
    "fee": 90.71,
    "adaptorType": "ih46zf",
    "remark": "dfg82g"
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
curl -X GET -i /order/status?tradingDay=116&strategyId=725 --data '&116&725'
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
    "uid": 969,
    "tradingDay": 868,
    "strategyId": 39,
    "instrumentCode": "14444",
    "investorId": "53",
    "brokerId": "53",
    "accountId": 292,
    "subAccountId": 821,
    "userId": 269,
    "ordSysId": 526,
    "ordRef": "1cccao",
    "ordMsgType": 804,
    "ordOffset": "v",
    "direction": "i",
    "limitPrice": 3.71,
    "status": 596,
    "statusMsg": "0e6v35",
    "brokerSysID": 893,
    "volume": 578,
    "volumeFilled": 205,
    "volumeRemained": 593,
    "price": 41.80,
    "tradeId": "53",
    "ordRejReason": 756,
    "insertTime": 267,
    "updateTime": 531,
    "cancelTime": 21,
    "remark": "4bjzak"
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

