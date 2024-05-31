include("lab1")
include("lab2")
include("lab2:dao")
findProject(":lab2:dao")
include("lab2:services")
findProject(":lab2:services")
include("lab2:controllers")
findProject(":lab2:controllers")?.name = "controllers"
include("lab2:b")
findProject(":lab2:b")?.name = "b"
include("lab2:b")
findProject(":lab2:b")?.name = "b"
include("lab2:App")
findProject(":lab2:App")?.name = "App"
include("lab5")
include("lab5:catMicroservice")
findProject(":lab5:catMicroservice")?.name = "catMicroservice"
include("lab5:ownerMicroservice")
findProject(":lab5:ownerMicroservice")?.name = "ownerMicroservice"
include("lab5:interfaceMicroservice")
findProject(":lab5:interfaceMicroservice")?.name = "interfaceMicroservice"
include("lab5:catMicroservice:app")
findProject(":lab5:catMicroservice:app")?.name = "app"
include("lab5:catMicroservice:controller")
findProject(":lab5:catMicroservice:controller")?.name = "controller"
include("lab5:catMicroservice:service")
findProject(":lab5:catMicroservice:service")?.name = "service"
include("lab5:catMicroservice:controllers")
findProject(":lab5:catMicroservice:controllers")?.name = "controllers"
include("lab5:interfaceMicroservice:app")
findProject(":lab5:interfaceMicroservice:app")?.name = "app"
include("lab5:interfaceMicroservice:controllers")
findProject(":lab5:interfaceMicroservice:controllers")?.name = "controllers"
include("lab5:interfaceMicroservice:services")
findProject(":lab5:interfaceMicroservice:services")?.name = "services"
include("lab5:ownerMicroservice:app")
findProject(":lab5:ownerMicroservice:app")?.name = "app"
include("lab5:ownerMicroservice:controllers")
findProject(":lab5:ownerMicroservice:controllers")?.name = "controllers"
include("lab5:ownerMicroservice:services")
findProject(":lab5:ownerMicroservice:services")?.name = "services"
include("lab5:catMicroservice:services")
findProject(":lab5:catMicroservice:services")?.name = "services"
include("lab5:catMicroservice:domain")
findProject(":lab5:catMicroservice:domain")?.name = "domain"
include("lab5:catMicroservice:dao")
findProject(":lab5:catMicroservice:dao")?.name = "dao"
include("lab5:ownerMicroservice:domain")
findProject(":lab5:ownerMicroservice:domain")?.name = "domain"
include("lab5:ownerMicroservice:dao")
findProject(":lab5:ownerMicroservice:dao")?.name = "dao"
include("lab5:core")
findProject(":lab5:core")?.name = "core"
include("lab5:interfaceMicroservice:dao")
findProject(":lab5:interfaceMicroservice:dao")?.name = "dao"
