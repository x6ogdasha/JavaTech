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
