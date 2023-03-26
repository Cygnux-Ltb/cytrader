
# 
## 返回全部Strategy

**URL:** `/strategy/`

**Type:** `GET`


**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 返回全部Strategy





**Request-example:**
```
curl -X GET -i /strategy/
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
    "uid": 739,
    "strategyId": 911,
    "strategyName": "john.kling",
    "strategyOwner": "ujuuw3",
    "strategyInfo": "zbkrl7"
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
curl -X GET -i /strategy/572
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
  "uid": 214,
  "strategyId": 624,
  "strategyName": "john.kling",
  "strategyOwner": "rfwlpv",
  "strategyInfo": "c0kgj0"
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
curl -X GET -i /strategy/993/param
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
    "uid": 187,
    "strategyId": 485,
    "strategyName": "john.kling",
    "ownerType": "7hxv2p",
    "owner": "slpi6f",
    "paramName": "john.kling",
    "paramType": "vke5c4",
    "paramValue": "hzay4l"
  }
]
```

## Put StrategyParam URI is StrategyId

**URL:** `/strategy/{strategyId}/param`

**Type:** `PUT`


**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** Put StrategyParam URI is StrategyId


**Path-parameters:**

| Parameter | Type | Required | Description | Since |
|-----------|------|----------|-------------|-------|
|strategyId|int32|true|int|-|



**Request-example:**
```
curl -X PUT -i /strategy/717/param
```

**Response-example:**
```
true
```

