
# 
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
curl -X GET -i /order/630?strategyId=364&investorId=84&instrumentCode=84389 --data '&364&84&84389'
```

**Response-example:**
```
{}
```

## 

**URL:** `/order/status`

**Type:** `GET`


**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 



**Query-parameters:**

| Parameter | Type | Required | Description | Since |
|-----------|------|----------|-------------|-------|
|tradingDay|int32|true|int|-|
|strategyId|int32|true|int|-|


**Request-example:**
```
curl -X GET -i /order/status?tradingDay=896&strategyId=264 --data '&896&264'
```

**Response-fields:**

| Field | Type | Description | Since |
|-------|------|-------------|-------|
|any object|object|any object.|-|

**Response-example:**
```
{}
```

## 

**URL:** `/order/`

**Type:** `PUT`


**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 





**Request-example:**
```
curl -X PUT -i /order/
```

**Response-fields:**

| Field | Type | Description | Since |
|-------|------|-------------|-------|
|any object|object|any object.|-|

**Response-example:**
```
{}
```

