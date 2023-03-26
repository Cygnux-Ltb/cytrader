
# 
## Get Settlement Price

**URL:** `/instrument/settlement`

**Type:** `GET`


**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** Get Settlement Price



**Query-parameters:**

| Parameter | Type | Required | Description | Since |
|-----------|------|----------|-------------|-------|
|instrumentCode|string|true|String|-|
|tradingDay|int32|true|    int|-|


**Request-example:**
```
curl -X GET -i /instrument/settlement?instrumentCode=84389&tradingDay=628 --data '&84389&628'
```

**Response-fields:**

| Field | Type | Description | Since |
|-------|------|-------------|-------|
|uid|int64|No comments found.|-|
|instrumentCode|string|instrumentCode|-|
|tradingDay|int32|tradingDay [*]|-|
|lastPrice|double|lastPrice|-|
|openPrice|double|openPrice|-|
|settlementPrice|double|settlementPrice|-|

**Response-example:**
```
[
  {
    "uid": 277,
    "instrumentCode": "84389",
    "tradingDay": 67,
    "lastPrice": 92.26,
    "openPrice": 79.86,
    "settlementPrice": 58.71
  }
]
```

## Get LastPrices

**URL:** `/instrument/last_price`

**Type:** `GET`


**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** Get LastPrices



**Query-parameters:**

| Parameter | Type | Required | Description | Since |
|-----------|------|----------|-------------|-------|
|instrumentCodes|string|true|String|-|


**Request-example:**
```
curl -X GET -i /instrument/last_price?instrumentCodes=344lxy --data '&344lxy'
```

**Response-fields:**

| Field | Type | Description | Since |
|-------|------|-------------|-------|
|instrumentCode|string|No comments found.|-|
|price|double|No comments found.|-|

**Response-example:**
```
[
  {
    "instrumentCode": "84389",
    "price": 37.02
  }
]
```

## Put LastPrice

**URL:** `/instrument/last_price`

**Type:** `PUT`


**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** Put LastPrice





**Request-example:**
```
curl -X PUT -i /instrument/last_price
```

**Response-fields:**

| Field | Type | Description | Since |
|-------|------|-------------|-------|
|any object|object|any object.|-|

**Response-example:**
```
{}
```

## Get [TradableInstrument] for [symbol] and [tradingDay]

**URL:** `/instrument/tradable/{tradingDay}/{symbol}`

**Type:** `GET`


**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** Get [TradableInstrument] for [symbol] and [tradingDay]


**Path-parameters:**

| Parameter | Type | Required | Description | Since |
|-----------|------|----------|-------------|-------|
|tradingDay|int32|true|String|-|
|symbol|string|true|    String|-|



**Request-example:**
```
curl -X GET -i /instrument/tradable/716/lq7qrj
```

**Response-fields:**

| Field | Type | Description | Since |
|-------|------|-------------|-------|
|any object|object|any object.|-|

**Response-example:**
```
{}
```

