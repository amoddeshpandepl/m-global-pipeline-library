def call(java.util.Map params) {
    if( params == null ) {
        params = [:]
    }
    mavenParams = params['maven']
    stage("CI") {
        inNode {
            checkout scm
            ciCmdExtraFlags = mavenParams['ciCmdExtraFlags']
            if( ciCmdExtraFlags == null ) {
                ciCmdExtraFlags = ""
            }
            emvn("-U ${ciCmdExtraFlags} clean package",mavenParams)
        }
    }
    orgName = params['org']
    for (envParams in params['envs']) {
        envName = envParams['name']
        approve = envParams['approve']
        if(  approve == null || approve ) {
            input "Deploy to ${envName}?"
        }
        stage(envName) {
            inNode {
                checkout scm
                targetName = envParams['target']
                echo "Deploying to env: ${envName} target: ${targetName}"
                emvn("deploy -DskipTests \"-Danypoint.org=${orgName}\" \"-Danypoint.env=${envName}\" \"-Danypoint.target=${targetName}\"",params['maven'])
            }
        }
    }
}