
# 
## Get Bars

**URL:** `/bar/{tradingDay}`

**Type:** `GET`


**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** Get Bars


**Path-parameters:**

| Parameter | Type | Required | Description | Since |
|-----------|------|----------|-------------|-------|
|tradingDay|int32|true|    int|-|

**Query-parameters:**

| Parameter | Type | Required | Description | Since |
|-----------|------|----------|-------------|-------|
|instrumentCode|string|true|String|-|


**Request-example:**
```
curl -X GET -i /bar/791?instrumentCode=84389 --data '&84389'
```

**Response-fields:**

| Field | Type | Description | Since |
|-------|------|-------------|-------|
|uid|int64|No comments found.|-|
|instrumentCode|string|No comments found.|-|
|tradingDay|int32|No comments found.|-|
|actualDate|int32|No comments found.|-|
|timePoint|int32|No comments found.|-|
|open|double|No comments found.|-|
|high|double|No comments found.|-|
|low|double|No comments found.|-|
|close|double|No comments found.|-|
|volume|double|No comments found.|-|
|turnover|int64|No comments found.|-|

**Response-example:**
```
[
  {
    "uid": 128,
    "instrumentCode": "84389",
    "tradingDay": 346,
    "actualDate": 419,
    "timePoint": 117,
    "open": 99.53,
    "high": 72.18,
    "low": 2.19,
    "close": 77.92,
    "volume": 63.76,
    "turnover": 719
  }
]
```

## Put Bar

**URL:** `/bar/`

**Type:** `PUT`


**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** Put Bar





**Request-example:**
```
curl -X PUT -i /bar/
```

**Response-example:**
```
664
```

