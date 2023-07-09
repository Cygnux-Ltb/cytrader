
# 系统指令服务
## 更新参数

**URL:** `/command/param`

**Type:** `PUT`


**Content-Type:** `APPLICATION_JSON_UTF8`

**Description:** 更新参数



**Query-parameters:**

| Parameter | Type | Required | Description | Since |
|-----------|------|----------|-------------|-------|
|productId|int32|true|int|-|


**Request-example:**
```
curl -X PUT -H 'Content-Type: APPLICATION_JSON_UTF8' -i /command/param --data 'productId=444'
```

**Response-example:**
```
OK
```

## 安全更新参数

**URL:** `/command/safe`

**Type:** `PUT`


**Content-Type:** `APPLICATION_JSON_UTF8`

**Description:** 安全更新参数





**Request-example:**
```
curl -X PUT -H 'Content-Type: APPLICATION_JSON_UTF8' -i /command/safe
```

**Response-example:**
```
OK
```

