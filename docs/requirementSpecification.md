# 云服务管理系统 详细设计
```plantumlcode

```
## 模块设计
> 云服务管理系统的核心功能，是对云服务的申请和释放。  
在处理申请和释放的过程中，都要根据当前云服务的状态，和云平台、封禁系统进行接口调用。
>  
```plantuml
@startuml
scale 800 height
skinparam backgroundColor LightYellow

package "云服务管理系统"    { 
    HTTP - [API Component]
   [API Component] - [Service Component]
   [Service Component] - [DAO]
}
database "MySql" {
    frame "CloudService" {
        [CSSM]
        [CSCCS]
        [CSCSMH]
    }
    [DAO] --> [CSSM]
    [DAO] --> [CSCCS]
    [DAO] --> [CSCSMH]
}

note left of 注释
[CSCCS]
Cloud Service-Container Current State 
[CSSM]
Cloud Service State Machine
[CSCSMH]
Cloud Service State Machine
end note
node "封禁系统" {
[Service Component] ..> [封禁系统API Component] :use http
}
node "云平台" {
[Service Component] ..> [云平台API Component] :use http
}
@enduml

```

# 实体关系图
```plantuml
scale 800 width
skinparam backgroundColor LightYellow
@startuml

class CSSM {
    CSSMID //currentStateID
    cloudserviceID
    containerID
    StateID
    StateCode
    StateName
    createuser
    createtime
    updateuser
    updatetime
    version
}
note right: 云服务容器生命周期状态表，\n记录每一个任务下的每一个\n服务的生命周期变化过程

cloudservice "1..*" - "1..1" CSSM 
(cloudservice, CSSM) .. CSCSMH  
class CSCSMH { 
    cloudserviceID
    containerID
    StateID
    StateCode
    StateName
    createuser
    createtime
    updateuser
    updatetime
    version
}
note left: 云服务容器状态历史表,\n定时将冷数据进行归档，\n保证热表中的查询效率

class app {
    appid  
    cpu  
    mem  
    disk  
    screen 
    account 
    password 
}
class container {
    containerid
    cpu
    mem
    disk 
    screen 
    location
    statecode
}
class cloudservice {
    serviceid
    containerid
    appids
    cloudServiceState
    spider:type,version
}
container "1..*" - "1..1" app
(app,container) .. cloudservice
@enduml
```

