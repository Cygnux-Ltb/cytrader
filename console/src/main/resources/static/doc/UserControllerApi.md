
# 用户服务
## 用户登陆

**URL:** `/user/signin`

**Type:** `POST`


**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 用户登陆



**Query-parameters:**

| Parameter | Type | Required | Description | Since |
|-----------|------|----------|-------------|-------|
|sign|string|false|    用户名/邮箱/手机号|-|
|password|string|false|密码|-|


**Request-example:**
```
curl -X POST -i /user/signin --data 'sign=hnnw5i&password=byvkfi'
```

**Response-example:**
```
true
```

## 用户注册, 当前不支持新用户注册

**URL:** `/user/signup`

**Type:** `POST`


**Content-Type:** `application/x-www-form-urlencoded;charset=UTF-8`

**Description:** 用户注册, 当前不支持新用户注册



**Query-parameters:**

| Parameter | Type | Required | Description | Since |
|-----------|------|----------|-------------|-------|
|sign|string|false|    标识|-|
|type|int32|false|    标识类型|-|
|password|string|false|密码|-|


**Request-example:**
```
curl -X POST -i /user/signup --data 'sign=pellup&type=928&password=18scg1'
```

**Response-fields:**

| Field | Type | Description | Since |
|-------|------|-------------|-------|
|code|int32|No comments found.|-|
|message|string|No comments found.|-|
|array|boolean|No comments found.|-|
|data|object|No comments found.|-|

**Response-example:**
```
{
  "code": 497,
  "message": "success",
  "array": true,
  "data": {}
}
```

