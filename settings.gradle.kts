rootProject.name = "javaSpringProject"
include("banks")
include("banks:bank-core")
findProject(":banks:bank-core")?.name = "bank-core"
include("banks:bank-console")
findProject(":banks:bank-console")?.name = "bank-console"
include("banks")
include("banks:bank-core")
findProject(":banks:bank-core")?.name = "bank-core"
include("banks:bank-console")
findProject(":banks:bank-console")?.name = "bank-console"
include("lab2")
include("lab2:dao")
findProject(":lab2:dao")?.name = "dao"
include("lab2:service")
findProject(":lab2:service")?.name = "service"
include("lab2:model")
findProject(":lab2:model")?.name = "model"
include("lab2:controller")
findProject(":lab2:controller")?.name = "controller"
include("lab3")
include("lab3:service")
findProject(":lab3:service")?.name = "service"
include("lab3:model")
findProject(":lab3:model")?.name = "model"
include("lab3:controller")
findProject(":lab3:controller")?.name = "controller"
include("laba4")
include("laba4:controller")
findProject(":laba4:controller")?.name = "controller"
include("laba4:service")
findProject(":laba4:service")?.name = "service"
include("laba4:model")
findProject(":laba4:model")?.name = "model"
include("lab4")
include("lab4:controller")
findProject(":lab4:controller")?.name = "controller"
include("lab4:model")
findProject(":lab4:model")?.name = "model"
include("lab4:service")
findProject(":lab4:service")?.name = "service"
include("lab5")
include("lab5:kitties")
findProject(":lab5:kitties")?.name = "kitties"
include("lab5:owners")
findProject(":lab5:owners")?.name = "owners"
include("lab5:models")
findProject(":lab5:models")?.name = "models"
include("lab5:owners:owner-application")
findProject(":lab5:owners:owner-application")?.name = "owner-application"
include("lab5:owners:owner-client")
findProject(":lab5:owners:owner-client")?.name = "owner-client"
include("lab5:gateway")
findProject(":lab5:gateway")?.name = "gateway"
include("lab5:gateway:gateway-application")
findProject(":lab5:gateway:gateway-application")?.name = "gateway-application"
include("lab5:kitties:kitty-model")
findProject(":lab5:kitties:kitty-model")?.name = "kitty-model"
include("lab5:kitties:kitty-application")
findProject(":lab5:kitties:kitty-application")?.name = "kitty-application"
include("lab5:kitties:kitty-client")
findProject(":lab5:kitties:kitty-client")?.name = "kitty-client"
include("lab5:owners:owner-module")
findProject(":lab5:owners:owner-module")?.name = "owner-module"
include("lab5:gateway:gateway-module")
findProject(":lab5:gateway:gateway-module")?.name = "gateway-module"
include("lab5:config")
findProject(":lab5:config")?.name = "config"
