folder('HPSIM') {
        description('hpsim project foloder created')
}
freeStyleJob('HPSIM/compile') {
    logRotator(-1, 10)
    scm {
        github('NagireddyGuduru/myweb', 'master')
    }
    steps {
        maven('clean compile')
    }
    publishers {
        downstream('HPSIM/test', 'SUCCESS')
    }
}
mavenJob('HPSIM/test') {
    logRotator(-1, 10)
     scm {
        github('NagireddyGuduru/myweb', 'master')
    }
    goals('clean test')
   
   publishers {
        downstream('HPSIM/sonar', 'SUCCESS')
    }
}
mavenJob('HPSIM/sonar') {
    logRotator(-1, 10)
     scm {
        github('NagireddyGuduru/myweb', 'master')
    }
    goals('clean sonar:sonar')
  publishers {
        downstream('HPSIM/nexus', 'SUCCESS')
    }
}
mavenJob('HPSIM/nexus') {
    logRotator(-1, 10)
     scm {
        github('NagireddyGuduru/myweb', 'master')
    }
    goals('clean deploy')
}
buildPipelineView('HPSIM/build-pipeline') {
    filterBuildQueue()
    filterExecutors()
    title('hpsim CI Pipeline')
    displayedBuilds(5)
    selectedJob('HPSIM/compile')
    alwaysAllowManualTrigger()
    showPipelineParameters()
    refreshFrequency(60)
}