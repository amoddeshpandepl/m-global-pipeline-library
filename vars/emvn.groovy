def call(cmd, params =null) {
    if( params == null ) {
        params = [:]
    }
    withMaven(params) {
        fullCmd = "mvn ${cmd}".toString()
        if( isUnix() ) {
            sh fullCmd
        } else {
            bat fullCmd
        }
    }
}
