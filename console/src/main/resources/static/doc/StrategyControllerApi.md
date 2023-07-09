
# 策略服务
## 返回全部Strategy

**URL:** `/strategy`

**Type:** `GET`


**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 返回全部Strategy





**Request-example:**
```
curl -X GET -i /strategy
```

**Response-fields:**

| Field | Type | Description | Since |
|-------|------|-------------|-------|
|uid|int64|No comments found.|-|
|strategyId|int32|No comments found.|-|
|strategyName|string|No comments found.|-|
|strategyOwner|string|No comments found.|-|
|strategyInfo|string|No comments found.|-|

**Response-example:**
```
[
  {
    "uid": 351,
    "strategyId": 94,
    "strategyName": "wilhemina.hirthe",
    "strategyOwner": "om00h6",
    "strategyInfo": "8xm9mf"
  }
]
```

## 使用StrategyId作为get params访问Strategy

**URL:** `/strategy/{strategyId}`

**Type:** `GET`


**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 使用StrategyId作为get params访问Strategy


**Path-parameters:**

| Parameter | Type | Required | Description | Since |
|-----------|------|----------|-------------|-------|
|strategyId|int32|true|int|-|



**Request-example:**
```
curl -X GET -i /strategy/195
```

**Response-fields:**

| Field | Type | Description | Since |
|-------|------|-------------|-------|
|uid|int64|No comments found.|-|
|strategyId|int32|No comments found.|-|
|strategyName|string|No comments found.|-|
|strategyOwner|string|No comments found.|-|
|strategyInfo|string|No comments found.|-|

**Response-example:**
```
{
  "uid": 116,
  "strategyId": 670,
  "strategyName": "wilhemina.hirthe",
  "strategyOwner": "ze5a2g",
  "strategyInfo": "suaftb"
}
```

## 使用StrategyId作为URI访问Param

**URL:** `/strategy/{strategyId}/param`

**Type:** `GET`


**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 使用StrategyId作为URI访问Param


**Path-parameters:**

| Parameter | Type | Required | Description | Since |
|-----------|------|----------|-------------|-------|
|strategyId|int32|true|int|-|



**Request-example:**
```
curl -X GET -i /strategy/636/param
```

**Response-fields:**

| Field | Type | Description | Since |
|-------|------|-------------|-------|
|uid|int64|No comments found.|-|
|strategyId|int32|No comments found.|-|
|strategyName|string|No comments found.|-|
|ownerType|string|No comments found.|-|
|owner|string|No comments found.|-|
|paramName|string|No comments found.|-|
|paramType|string|No comments found.|-|
|paramValue|string|No comments found.|-|

**Response-example:**
```
[
  {
    "uid": 155,
    "strategyId": 225,
    "strategyName": "wilhemina.hirthe",
    "ownerType": "2hy0x5",
    "owner": "pe7dl1",
    "paramName": "wilhemina.hirthe",
    "paramType": "88f5fz",
    "paramValue": "rmpbse"
  }
]
```

## Put StrategyParam URI is StrategyId

**URL:** `/strategy/{strategyId}/param`

**Type:** `PUT`


**Content-Type:** `APPLICATION_JSON_UTF8`

**Description:** Put StrategyParam URI is StrategyId


**Path-parameters:**

| Parameter | Type | Required | Description | Since |
|-----------|------|----------|-------------|-------|
|strategyId|int32|true|int|-|



**Request-example:**
```
curl -X PUT -H 'Content-Type: APPLICATION_JSON_UTF8' -i /strategy/564/param
```

**Response-example:**
```
true
```

